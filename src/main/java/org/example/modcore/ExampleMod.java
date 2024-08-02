package org.example.modcore;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import org.example.cards.*;
import org.example.characters.MyCharacter;
import org.example.relics.MyRelic;

import java.nio.charset.StandardCharsets;

import static org.example.characters.MyCharacter.PlayerColorEnum.EXAMPLE_GREEN;
import static org.example.characters.MyCharacter.PlayerColorEnum.MY_CHARACTER;

@SpireInitializer // 加载mod的注解
public class ExampleMod implements EditKeywordsSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber {
    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = "ExampleModResources/img/charSelect/button.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "ExampleModResources/img/charSelect/portrait.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "ExampleModResources/img/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "ExampleModResources/img/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "ExampleModResources/img/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "ExampleModResources/img/512/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "ExampleModResources/img/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "ExampleModResources/img/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "ExampleModResources/img/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "ExampleModResources/img/512/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENERGY_ORB = "ExampleModResources/img/512/cost_orb.png";
    public static final Color MY_COLOR = new Color(79.0F / 255.0F, 185.0F / 255.0F, 9.0F / 255.0F, 1.0F);

    // 构造方法
    public ExampleMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(EXAMPLE_GREEN, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, MY_COLOR, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENERGY_ORB, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);
    }

    // 注解需要调用的方法，必须写
    public static void initialize() {
        new ExampleMod();
    }

    @Override
    public void receiveEditCards() {
        // 向basemod注册卡牌
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        BaseMod.addCard(new Scratch());
        BaseMod.addCard(new ThrowSand());
        BaseMod.addCard(new FlyingStab());
        BaseMod.addCard(new Taunt());
        BaseMod.addCard(new Roll());
        BaseMod.addCard(new DoubleSideArmor());
        BaseMod.addCard(new EnergyStrike());
        BaseMod.addCard(new PrepareToFight());
        BaseMod.addCard(new PrepareToDash());
        BaseMod.addCard(new GoldenArmor());

        BaseMod.addCard(new Powerball());
        BaseMod.addCard(new DefensiveBallCurl());
        BaseMod.addCard(new FrenzyingTaunt());
        BaseMod.addCard(new FrozenArmor());
        BaseMod.addCard(new MoltenRammus());
        BaseMod.addCard(new ArmorRelease());
        BaseMod.addCard(new HextechRammus());
        BaseMod.addCard(new DurianDefender());
        BaseMod.addCard(new FullMental());
        BaseMod.addCard(new RisingUp());
        BaseMod.addCard(new LastDefend());
        BaseMod.addCard(new Spinning());
        BaseMod.addCard(new BeSteady());
        BaseMod.addCard(new ArmorPowerUp());
        BaseMod.addCard(new DefendMod());
        BaseMod.addCard(new SpikeBoomerang());
        BaseMod.addCard(new KeepDefend());
        BaseMod.addCard(new Earthquake());

        BaseMod.addCard(new SoaringSlam());
        BaseMod.addCard(new KingRammus());
        BaseMod.addCard(new SweeperRammus());
        BaseMod.addCard(new ChromeRammus());
        BaseMod.addCard(new GuardianAttack());

    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        String cardStrings = Gdx.files.internal("ExampleModResources/localization/" + lang + "/card.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String charStrings = Gdx.files.internal("ExampleModResources/localization/" + lang + "/character.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CharacterStrings.class, charStrings);
        String relicStrings = Gdx.files.internal("ExampleModResources/localization/" + lang + "/relic.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String powerStrings = Gdx.files.internal("ExampleModResources/localization/" + lang + "/power.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
//        BaseMod.loadCustomStringsFile(CardStrings.class, "ExampleModResources/localization/" + lang + "/card.json"); // 加载相应语言的卡牌本地化内容。
        // 如果是中文，加载的就是"ExampleResources/localization/ZHS/cards.json"
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new MyCharacter(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, MY_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new MyRelic(), RelicType.SHARED);
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String lang = "eng";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "zh";
        } else {
            lang = "en"; // 如果没有相应语言的版本，默认加载英语
        }

        String json = Gdx.files.internal("ExampleModResources/localization/Keywords_" + lang + ".json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                // 这个id要全小写
                BaseMod.addKeyword("examplemod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}
