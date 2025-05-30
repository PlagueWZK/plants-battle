package ind.plague.pvz.util;

import ind.plague.pvz.animation.Atlas;
import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.audio.Audio;

import java.awt.image.BufferedImage;

/**
 * @author PlagueWZK
 * description: ResourceGetter
 * date: 2025/5/15 17:51
 */

public class ResourceGetter {
    public static final Sticker IMAGE_MENU_BACKGROUND = newSticker("/image/scene/menu/menu_background.png");
    public static final Sticker IMAGE_SELECTOR_BACKGROUND = newSticker("/image/scene/selector/selector_background.png");

    public static final Atlas ATLAS_SUNFLOWER_RUN_RIGHT = new Atlas("/image/role/sunflowers/sunflower_run_%d.png", 5);
    public static final Atlas ATLAS_SUNFLOWER_RUN_LEFT = ATLAS_SUNFLOWER_RUN_RIGHT.flipAtlas();

    public static final Atlas ATLAS_SUNFLOWER_ATTACK_EX_RIGHT = new Atlas("/image/role/sunflowers/sunflower_attack_ex_%d.png", 9);
    public static final Atlas ATLAS_SUNFLOWER_ATTACK_EX_LEFT = ATLAS_SUNFLOWER_ATTACK_EX_RIGHT.flipAtlas();
    public static final Atlas ATLAS_SUNFLOWER_IDLE_RIGHT = new Atlas("/image/role/sunflowers/sunflower_idle_%d.png", 8);
    public static final Atlas ATLAS_SUNFLOWER_IDLE_LEFT = ATLAS_SUNFLOWER_IDLE_RIGHT.flipAtlas();
    public static final Atlas ATLAS_SUNFLOWER_DIE_RIGHT = new Atlas("/image/role/sunflowers/sunflower_die_%d.png", 2);
    public static final Atlas ATLAS_SUNFLOWER_DIE_LEFT = ATLAS_SUNFLOWER_DIE_RIGHT.flipAtlas();
    public static final Atlas ATLAS_SUNFLOWER_TEXT = new Atlas("/image/role/sunflowers/sun_text_%d.png", 6);


    public static final Atlas ATLAS_PEASHOOTER_RUN_RIGHT = new Atlas("/image/role/peashooter/peashooter_run_%d.png", 5);
    public static final Atlas ATLAS_PEASHOOTER_RUN_LEFT = ATLAS_PEASHOOTER_RUN_RIGHT.flipAtlas();

    public static final Atlas ATLAS_PEASHOOTER_ATTACK_EX_RIGHT = new Atlas("/image/role/peashooter/peashooter_attack_ex_%d.png", 3);
    public static final Atlas ATLAS_PEASHOOTER_ATTACK_EX_LEFT = ATLAS_PEASHOOTER_ATTACK_EX_RIGHT.flipAtlas();
    public static final Atlas ATLAS_PEASHOOTER_IDLE_RIGHT = new Atlas("/image/role/peashooter/peashooter_idle_%d.png", 9);
    public static final Atlas ATLAS_PEASHOOTER_IDLE_LEFT = ATLAS_PEASHOOTER_IDLE_RIGHT.flipAtlas();
    public static final Atlas ATLAS_PEASHOOTER_DIE_RIGHT = new Atlas("/image/role/peashooter/peashooter_die_%d.png", 4);
    public static final Atlas ATLAS_PEASHOOTER_DIE_LEFT = ATLAS_PEASHOOTER_DIE_RIGHT.flipAtlas();


    public static final BufferedImage IMAGE_BULLET_PEA = GameUtil.loadImage("/image/bullet/pea/pea.png");
    public static final Atlas ATLAS_BULLET_PEA_BREAK = new Atlas("/image/bullet/pea/pea_break_%d.png", 3);

    public static final Atlas ATLAS_BULLET_SUN_IDLE = new Atlas("/image/bullet/sun/sun_%d.png", 5);
    public static final Atlas ATLAS_BULLET_SUN_EXPLODE = new Atlas("/image/bullet/sun/sun_explode_%d.png", 5);
    public static final Atlas ATLAS_BULLET_SUN_EX_IDLE = new Atlas("/image/bullet/sun/sun_ex_%d.png", 5);
    public static final Atlas ATLAS_BULLET_SUN_EX_EXPLODE = new Atlas("/image/bullet/sun/sun_ex_explode_%d.png", 5);


    public static final Sticker IMAGE_VS = newSticker("/image/scene/selector/VS.png");
    public static final Sticker IMAGE_1P = newSticker("/image/scene/selector/1P.png");
    public static final Sticker IMAGE_1P_DESC = newSticker("/image/scene/selector/1P_desc.png");

    public static final Sticker IMAGE_1P_SELECTED_IDLE_RIGHT = newSticker("/image/scene/selector/1P_selector_btn_idle.png");
    public static final Sticker IMAGE_1P_SELECTED_IDLE_LEFT = new Sticker(GameUtil.flipImage(IMAGE_1P_SELECTED_IDLE_RIGHT.getImg()));
    public static final Sticker IMAGE_1P_SELECTED_DOWN_RIGHT = newSticker("/image/scene/selector/1P_selector_btn_down.png");
    public static final Sticker IMAGE_1P_SELECTED_DOWN_LEFT = new Sticker(GameUtil.flipImage(IMAGE_1P_SELECTED_DOWN_RIGHT.getImg()));

    public static final Sticker IMAGE_2P = newSticker("/image/scene/selector/2P.png");
    public static final Sticker IMAGE_2P_DESC = newSticker("/image/scene/selector/2P_desc.png");
    public static final Sticker IMAGE_2P_SELECTED_IDLE_RIGHT = newSticker("/image/scene/selector/2P_selector_btn_idle.png");
    public static final Sticker IMAGE_2P_SELECTED_IDLE_LEFT = new Sticker(GameUtil.flipImage(IMAGE_2P_SELECTED_IDLE_RIGHT.getImg()));

    public static final Sticker IMAGE_2P_SELECTED_DOWN_RIGHT = newSticker("/image/scene/selector/2P_selector_btn_down.png");
    public static final Sticker IMAGE_2P_SELECTED_DOWN_LEFT = new Sticker(GameUtil.flipImage(IMAGE_2P_SELECTED_DOWN_RIGHT.getImg()));


    public static final Sticker IMAGE_GRAVESTONE_RIGHT = newSticker("/image/scene/selector/gravestone.png");
    public static final Sticker IMAGE_GRAVESTONE_LEFT = new Sticker(GameUtil.flipImage(IMAGE_GRAVESTONE_RIGHT.getImg()));
    public static final Sticker IMAGE_SELECTOR_TIP = newSticker("/image/scene/selector/selector_tip.png");

    public static final Sticker IMAGE_PEASHOOTER_BACKGROUND_RIGHT = newSticker("/image/scene/selector/peashooter_selector_background.png");
    public static final Sticker IMAGE_PEASHOOTER_BACKGROUND_LEFT = new Sticker(GameUtil.flipImage(IMAGE_PEASHOOTER_BACKGROUND_RIGHT.getImg()));

    public static final Sticker IMAGE_SUNFLOWER_BACKGROUND_RIGHT = newSticker("/image/scene/selector/sunflower_selector_background.png");
    public static final Sticker IMAGE_SUNFLOWER_BACKGROUND_LEFT = new Sticker(GameUtil.flipImage(IMAGE_SUNFLOWER_BACKGROUND_RIGHT.getImg()));

    public static final Sticker IMAGE_HILLS = newSticker("/image/scene/game/hills.png");
    public static final Sticker IMAGE_SKY = newSticker("/image/scene/game/sky.png");
    public static final BufferedImage IMAGE_PLATFORM_LARGE = GameUtil.loadImage("/image/scene/game/platform_large.png");
    public static final BufferedImage IMAGE_PLATFORM_SMALL = GameUtil.loadImage("/image/scene/game/platform_small.png");


    public static final BufferedImage IMAGE_AVATAR_PEASHOOTER = GameUtil.loadImage("/image/avatar/avatar_peashooter.png");
    public static final BufferedImage IMAGE_AVATAR_SUNFLOWER = GameUtil.loadImage("/image/avatar/avatar_sunflower.png");

    public static final BufferedImage IMAGE_WINNER_BAR = GameUtil.loadImage("/image/scene/game/winner_bar.png");
    public static final BufferedImage IMAGE_1P_WINNER = GameUtil.loadImage("/image/scene/game/1P_winner.png");
    public static final BufferedImage IMAGE_2P_WINNER = GameUtil.loadImage("/image/scene/game/2P_winner.png");
    public static final BufferedImage IMAGE_1P_CURSOR = GameUtil.loadImage("/image/scene/game/1P_cursor.png");
     public static final BufferedImage IMAGE_2P_CURSOR = GameUtil.loadImage("/image/scene/game/2P_cursor.png");

    public static final Atlas ATLAS_RUN_EFFECT = new Atlas("/image/particle/run_effect_%d.png", 4);
    public static final Atlas ATLAS_JUMP_EFFECT = new Atlas("/image/particle/jump_effect_%d.png", 5);
    public static final Atlas ATLAS_LAND_EFFECT = new Atlas("/image/particle/land_effect_%d.png", 2);

    public static final Audio AUDIO_MENU_BGM = new Audio("/sound/BGM/bgm_menu.wav");
    public static final Audio AUDIO_GAME_BGM = new Audio("/sound/BGM/bgm_game.wav");
    public static final Audio AUDIO_CONFIRM = new Audio("/sound/effect/ui_confirm.wav");
    public static final Audio AUDIO_SWITCH = new Audio("/sound/effect/ui_switch.wav");
    public static final Audio AUDIO_WIN = new Audio("/sound/effect/ui_win.wav");

    public static final Audio AUDIO_BULLET_PEA_BREAK_1 = new Audio("/sound/effect/pea_break_1.wav");
    public static final Audio AUDIO_BULLET_PEA_BREAK_2 = new Audio("/sound/effect/pea_break_2.wav");
    public static final Audio AUDIO_BULLET_PEA_BREAK_3 = new Audio("/sound/effect/pea_break_3.wav");
    public static final Audio AUDIO_BULLET_SUN_EXPLODE = new Audio("/sound/effect/sun_explode.wav");
    public static final Audio AUDIO_BULLET_SUN_EX_EXPLODE = new Audio("/sound/effect/sun_explode_ex.wav");


    public static final Audio AUDIO_PEASHOOTER_ATTACK_1 = new Audio("/sound/effect/pea_shoot_1.wav");
    public static final Audio AUDIO_PEASHOOTER_ATTACK_2 = new Audio("/sound/effect/pea_shoot_2.wav");
    public static final Audio AUDIO_PEASHOOTER_ATTACK_EX = new Audio("/sound/effect/pea_shoot_ex.wav");

    public static final Audio AUDIO_SUNFLOWER_TEXT = new Audio("/sound/effect/sun_text.wav");

    private static Sticker newSticker(String path) {
        return new Sticker(GameUtil.loadImage(path));
    }
}
