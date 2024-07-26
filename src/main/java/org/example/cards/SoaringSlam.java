package org.example.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.CombustPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.relics.MyRelic;
import org.example.utils.ModHelper;

import java.util.Iterator;

import static org.example.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class SoaringSlam extends CustomCard {
    public static final String ID = ModHelper.makePath(SoaringSlam.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleModResources/img/cards/SoaringSlam.png";
    private static final int COST = 2;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;

    private static final Logger logger = LogManager.getLogger(MyRelic.class);

    public SoaringSlam() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 10;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new CombustPower(p, 0, this.damage), this.damage));
        Iterator<AbstractPower> iterator = p.powers.iterator();
        while (iterator.hasNext()) {
            AbstractPower po = iterator.next();
            if (po.ID.equals("ExampleModDashPower")) {
                Iterator<AbstractMonster> var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

                AbstractMonster mo;
                while(var3.hasNext()) {
                    mo = var3.next();
                    this.addToBot(new ApplyPowerAction(mo, p, new StrengthPower(mo, -po.amount), -po.amount, true, AbstractGameAction.AttackEffect.NONE));
                }
                this.addToBot(new RemoveSpecificPowerAction(p, p, po.ID));
                break;
            }
        }

    }
}
