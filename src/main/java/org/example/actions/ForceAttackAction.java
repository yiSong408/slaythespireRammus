package org.example.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.security.cert.TrustAnchor;

public class ForceAttackAction extends AbstractGameAction {


    public ForceAttackAction(AbstractMonster m) {
        this.target = m;
    }

    @Override
    public void update() {
        AbstractMonster m = (AbstractMonster) this.target;
        if (m.intent != AbstractMonster.Intent.ATTACK) {
            m.setMove((byte) 1, AbstractMonster.Intent.ATTACK, m.damage.get(0).base);
            m.createIntent();
            AbstractDungeon.actionManager.addToBottom(new SetMoveAction(m, (byte) 1, AbstractMonster.Intent.ATTACK));
            m.applyPowers();
        }
        this.isDone = true;
    }
}
