package org.example.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.ModHelper;

import java.util.Iterator;

public class MyRelic extends CustomRelic {
    // 遗物ID（此处的ModHelper在“04 - 本地化”中提到）
    public static final String ID = ModHelper.makePath("MyRelic");
    // 图片路径
    private static final String IMG_PATH = "ExampleModResources/img/relics/MyRelic.png";
    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.STARTER;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    private static final Logger logger = LogManager.getLogger(MyRelic.class);
    private int currentPowerUp = 0;

    public MyRelic() {
        super(ID, ImageMaster.loadImage(IMG_PATH), RELIC_TIER, LANDING_SOUND);
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new MyRelic();
    }

    @Override
    public void atBattleStart() {
        logger.info("对决开始");
        AbstractPlayer p = AbstractDungeon.player;
        super.atBattleStart();
        this.addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, 3), 3));
    }

//    @Override
//    public int onPlayerGainedBlock(float blockAmount) {
//        AbstractPlayer p = AbstractDungeon.player;
//        int nextPowerUp = (p.currentBlock + (int) blockAmount) / 10;
//        int gap = nextPowerUp - this.currentPowerUp;
//        this.currentPowerUp = nextPowerUp;
//        if (gap != 0) {
//            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, gap), gap));
//        }
//        return super.onPlayerGainedBlock(blockAmount);
//    }
//
//
//    @Override
//    public void onPlayerEndTurn() {
//        AbstractPlayer p = AbstractDungeon.player;
//        super.onPlayerEndTurn();
//        doPowerUpGap(p);
//    }
//
//    @Override
//    public int onAttacked(DamageInfo info, int damageAmount) {
//        AbstractPlayer p = AbstractDungeon.player;
//        doPowerUpGap(p);
//        return super.onAttacked(info, damageAmount);
//    }
//
//    @Override
//    public void atTurnStart() {
//        AbstractPlayer p = AbstractDungeon.player;
//        super.atTurnStart();
//        doPowerUpGap(p);
//    }
//
//    @Override
//    public void atTurnStartPostDraw() {
//        AbstractPlayer p = AbstractDungeon.player;
//        super.atTurnStartPostDraw();
//        doPowerUpGap(p);
//    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        super.update();
        doPowerUpGap(p);
    }

    public void doPowerUpGap(AbstractPlayer p) {
        int nextPowerUp = p.currentBlock / 10;
        int gap = nextPowerUp - this.currentPowerUp;
        this.currentPowerUp = nextPowerUp;
        if (gap != 0) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, gap), gap));
        }
    }
}
