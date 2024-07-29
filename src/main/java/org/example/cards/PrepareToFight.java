package org.example.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.example.utils.ModHelper;

import java.util.Iterator;

import static org.example.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;

public class PrepareToFight extends CustomCard {
    public static final String ID = ModHelper.makePath(PrepareToFight.class.getSimpleName());
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "ExampleModResources/img/cards/Test.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = EXAMPLE_GREEN;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    public PrepareToFight() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.block=this.baseBlock=5;
        this.exhaust=true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator<AbstractCard> iterator = p.hand.group.iterator();
        while (iterator.hasNext()){
            AbstractCard ca = iterator.next();
            if(ca.cardID.equals("ExampleModeDefend")){
                ca.upgrade();
                ca.superFlash();
                ca.applyPowers();
            }
        }
    }
}
