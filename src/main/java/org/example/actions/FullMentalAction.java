package org.example.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class FullMentalAction extends AbstractGameAction {
    public int[] multiDamage;
    private boolean freeToPlayOnce = false;
    private AbstractPlayer p;
    private int energyOnUse = -1;

    public FullMentalAction(AbstractPlayer p, int amount, boolean freeToPlayOnce, int energyOnUse) {
        this.amount = amount;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }

    @Override
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }

        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (effect > 0) {
            for (int i = 0; i < effect; ++i) {
                this.addToBot(new ApplyPowerAction(this.p, this.p, new MetallicizePower(this.p, this.amount), this.amount));
            }

            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }

        this.isDone = true;
    }
}
