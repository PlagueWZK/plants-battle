package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.audio.Audio;
import ind.plague.pvz.core.GameFrame;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.events.SceneChangeEvent;
import ind.plague.pvz.event.events.SetPlayerEvent;
import ind.plague.pvz.role.roles.PlayerID;
import ind.plague.pvz.scene.SceneType;
import ind.plague.pvz.util.GameUtil;
import ind.plague.pvz.util.Painter;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author PlagueWZK
 * description: SelectScene
 * date: 2025/5/15 13:21
 */

public class SelectScene extends BasicScene {

    private static final ArrayList<RoleType> ROLE_MAP = new ArrayList<>();
    private static final Vector2 ZERO = Vector2.ZERO;
    private static final Vector2 HALF = Vector2.ZERO.addNoModify(new Vector2(GameFrame.getWidth() * 57f / 100, 0));


    static {
        ROLE_MAP.addAll(Arrays.asList(RoleType.values()));
    }

    private final Sticker selectorBackground = ResourceGetter.IMAGE_SELECTOR_BACKGROUND;
    private final Audio BGM = ResourceGetter.AUDIO_MENU_BGM;

    private final Sticker selectorTip = ResourceGetter.IMAGE_SELECTOR_TIP;
    private final Sticker gravestone1 = ResourceGetter.IMAGE_GRAVESTONE_RIGHT;
    private final Sticker gravestone2 = ResourceGetter.IMAGE_GRAVESTONE_LEFT;
    private final Sticker VS = ResourceGetter.IMAGE_VS;
    private final Sticker oneP = ResourceGetter.IMAGE_1P;
    private final Sticker onePDesc = ResourceGetter.IMAGE_1P_DESC;
    private Sticker onePSelectorR = ResourceGetter.IMAGE_1P_SELECTED_IDLE_RIGHT;
    private Sticker onePSelectorL = ResourceGetter.IMAGE_1P_SELECTED_IDLE_LEFT;


    private final Sticker twoP = ResourceGetter.IMAGE_2P;
    private final Sticker twoPDesc = ResourceGetter.IMAGE_2P_DESC;
    private Sticker twoPSelectorR = ResourceGetter.IMAGE_2P_SELECTED_IDLE_RIGHT;
    private Sticker twoPSelectorL = ResourceGetter.IMAGE_2P_SELECTED_IDLE_LEFT;


    private RoleType player1 = ROLE_MAP.getFirst();
    private RoleType player2 = ROLE_MAP.get(1);
    private Sticker background1 = player2.backGroundR;
    private Sticker background2 = player1.backGroundL;

    private final Vector2 firstPlayerPosition = new Vector2(GameFrame.getWidth() * 16f / 100, GameFrame.getHeight() * 32f / 100);
    private final Vector2 onePPosition = new Vector2(GameFrame.getWidth() * 16f / 100, GameFrame.getHeight() / 10f);
    private final Vector2 onePDescPosition = new Vector2(GameFrame.getWidth() * 9f / 100, GameFrame.getHeight() * 3 / 4f);
    private final Vector2 firstGravestonePosition = new Vector2(GameFrame.getWidth() / 12f, GameFrame.getHeight() / 5f);
    private final Vector2 onePSelectorRPosition = firstPlayerPosition.addNoModify(new Vector2(GameFrame.getWidth() / 6f, GameFrame.getHeight() / 10f));
    private final Vector2 onePSelectorLPosition = firstPlayerPosition.addNoModify(new Vector2(-GameFrame.getWidth() / 8f, GameFrame.getHeight() / 10f));
    private final Vector2 onePRoleNamePosition = firstPlayerPosition.addNoModify(new Vector2(0, GameFrame.getHeight() / 5f));


    private final Vector2 secondPlayerPosition = GameUtil.horizontalSymmetry(firstPlayerPosition, player1.animation.getFrame());
    private final Vector2 twoPPosition = GameUtil.horizontalSymmetry(onePPosition, twoP.getImg());
    private final Vector2 twoPDescPosition = GameUtil.horizontalSymmetry(onePDescPosition, twoPDesc.getImg());
    private final Vector2 secondGravestonePosition = GameUtil.horizontalSymmetry(firstGravestonePosition, gravestone1.getImg());
    private final Vector2 twoPSelectorRPosition = secondPlayerPosition.addNoModify(new Vector2(GameFrame.getWidth() / 6f, GameFrame.getHeight() / 10f));
    private final Vector2 twoPSelectorLPosition = secondPlayerPosition.addNoModify(new Vector2(-GameFrame.getWidth() / 8f, GameFrame.getHeight() / 10f));
    private final Vector2 twoPRoleNamePosition = secondPlayerPosition.addNoModify(new Vector2(0, GameFrame.getHeight() / 5f));


    private final Vector2 vsPosition = new Vector2(GameUtil.getCenterXDrawPosition(VS.getImg()), GameUtil.getCenterYDrawPosition(VS.getImg()));
    private final Vector2 selectTipPosition = new Vector2(GameUtil.getCenterXDrawPosition(selectorTip.getImg()), GameFrame.getHeight() * 88 / 100f);


    private final Audio switchSound = ResourceGetter.AUDIO_SWITCH;
    private final Audio confirmSound = ResourceGetter.AUDIO_CONFIRM;

    public SelectScene() {
    }


    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        player1.animation.update(deltaTime);
        if (player1 != player2) {
            player2.animation.update(deltaTime);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        selectorBackground.draw(g, 0, 0);
        background1.draw(g, ZERO, 0.8f);
        background2.draw(g, HALF, 0.8f);
        VS.draw(g, vsPosition);

        selectorTip.draw(g, selectTipPosition);
        oneP.draw(g, onePPosition);
        onePDesc.draw(g, onePDescPosition);
        gravestone1.draw(g, firstGravestonePosition);

        twoP.draw(g, twoPPosition);
        twoPDesc.draw(g, twoPDescPosition);
        gravestone2.draw(g, secondGravestonePosition);


        player1.animation.draw(g, firstPlayerPosition);
        player2.animation.draw(g, secondPlayerPosition);

        onePSelectorL.draw(g, onePSelectorLPosition);
        onePSelectorR.draw(g, onePSelectorRPosition);
        twoPSelectorL.draw(g, twoPSelectorLPosition);
        twoPSelectorR.draw(g, twoPSelectorRPosition);

        Painter.drawShadedText(g, player1.name, onePRoleNamePosition, Color.WHITE, 25);
        Painter.drawShadedText(g, player2.name, twoPRoleNamePosition, Color.WHITE, 25);
    }

    @Override
    public void onEnter() {
        BGM.play(true);
    }

    @Override
    public void onExit() {
        BGM.stop();
    }

    @Override
    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A -> {
                onePSelectorL = ResourceGetter.IMAGE_1P_SELECTED_IDLE_LEFT;
                player1 = ROLE_MAP.get((player1.ordinal() + 1) % ROLE_MAP.size());
                background2 = player1.backGroundL;
                switchSound.play(false);
            }
            case KeyEvent.VK_D -> {
                onePSelectorR = ResourceGetter.IMAGE_1P_SELECTED_IDLE_RIGHT;
                player1 = ROLE_MAP.get((player1.ordinal() + ROLE_MAP.size() - 1) % ROLE_MAP.size());
                background2 = player1.backGroundL;
                switchSound.play(false);
            }
            case KeyEvent.VK_LEFT -> {
                twoPSelectorL = ResourceGetter.IMAGE_2P_SELECTED_IDLE_LEFT;
                player2 = ROLE_MAP.get((player2.ordinal() + ROLE_MAP.size() - 1) % ROLE_MAP.size());
                background1 = player2.backGroundR;
                switchSound.play(false);
            }
            case KeyEvent.VK_RIGHT -> {
                twoPSelectorR = ResourceGetter.IMAGE_2P_SELECTED_IDLE_RIGHT;
                player2 = ROLE_MAP.get((player2.ordinal() + 1) % ROLE_MAP.size());
                background1 = player2.backGroundR;
                switchSound.play(false);
            }
        }
    }

    @Override
    public void mousePressed(int buttonCode) {

    }

    @Override
    public void mouseReleased(int buttonCode) {

    }

    @Override
    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A -> {
                onePSelectorL = ResourceGetter.IMAGE_1P_SELECTED_DOWN_LEFT;
            }
            case KeyEvent.VK_D -> {
                onePSelectorR = ResourceGetter.IMAGE_1P_SELECTED_DOWN_RIGHT;
            }
            case KeyEvent.VK_LEFT -> {
                twoPSelectorL = ResourceGetter.IMAGE_2P_SELECTED_DOWN_LEFT;
            }
            case KeyEvent.VK_RIGHT -> {
                twoPSelectorR = ResourceGetter.IMAGE_2P_SELECTED_DOWN_RIGHT;
            }
            case KeyEvent.VK_ENTER -> {
                EventBus.instance.publish(new SetPlayerEvent(PlayerID.PLAYER_1, player1));
                EventBus.instance.publish(new SetPlayerEvent(PlayerID.PLAYER_2, player2));
                EventBus.instance.publish(new SceneChangeEvent(SceneType.GAME_SCENE));
                confirmSound.play(false);
            }
        }
    }

    public enum RoleType {
        PEASHOOTER("婉逗射手", new Animation(ResourceGetter.ATLAS_PEASHOOTER_IDLE_RIGHT, 100, true), ResourceGetter.IMAGE_PEASHOOTER_BACKGROUND_RIGHT, ResourceGetter.IMAGE_PEASHOOTER_BACKGROUND_LEFT),
        SUNFLOWER("龙日葵", new Animation(ResourceGetter.ATLAS_SUNFLOWER_IDLE_RIGHT, 100, true), ResourceGetter.IMAGE_SUNFLOWER_BACKGROUND_RIGHT, ResourceGetter.IMAGE_SUNFLOWER_BACKGROUND_LEFT);

        public final String name;
        public final Animation animation;
        public final Sticker backGroundR;
        public final Sticker backGroundL;

        RoleType(String name, Animation animation, Sticker backGround1, Sticker backGround2) {
            this.name = name;
            this.animation = animation;
            this.backGroundR = backGround1;
            this.backGroundL = backGround2;
        }
    }
}
