package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.audio.Audio;
import ind.plague.pvz.core.GameFrame;
import ind.plague.pvz.event.events.GameKeyEvent;
import ind.plague.pvz.util.GameUtil;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author PlagueWZK
 * description: SelectScene
 * date: 2025/5/15 13:21
 */

public class SelectScene extends BasicScene {

    private static final ArrayList<RoleType> ROLE_MAP = new ArrayList<>();

    static {
        ROLE_MAP.addAll(Arrays.asList(RoleType.values()));
    }

    private final Sticker selectorBackground = ResourceGetter.IMAGE_SELECTOR_BACKGROUND;
    private final Audio BGM = ResourceGetter.AUDIO_MENU_BGM;

    private final Sticker selectorTip = ResourceGetter.IMAGE_SELECTOR_TIP;
    private final Sticker gravestone = ResourceGetter.IMAGE_GRAVESTONE;
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

    private final Vector2 firstPlayerPosition = new Vector2(GameFrame.getWidth() * 16f / 100, GameFrame.getHeight() * 32f / 100);
    private final Vector2 onePPosition = new Vector2(GameFrame.getWidth() * 16f / 100, GameFrame.getHeight() / 10f);
    private final Vector2 onePDescPosition = new Vector2(GameFrame.getWidth() * 9f / 100, GameFrame.getHeight() * 3 / 4f);
    private final Vector2 firstGravestonePosition = new Vector2(GameFrame.getWidth() / 12f, GameFrame.getHeight() / 5f);
    private final Vector2 onePSelectorRPosition = firstPlayerPosition.addNoModify(new Vector2(GameFrame.getWidth() / 10f, 0));
    private final Vector2 onePSelectorLPosition = firstPlayerPosition.addNoModify(new Vector2(- GameFrame.getWidth() / 16f, 0));



    private final Vector2 secondPlayerPosition = GameUtil.horizontalSymmetry(firstPlayerPosition, player1.getAnimation().getFrame());
    private final Vector2 twoPPosition = GameUtil.horizontalSymmetry(onePPosition, twoP.getImg());
    private final Vector2 twoPDescPosition = GameUtil.horizontalSymmetry(onePDescPosition, twoPDesc.getImg());
    private final Vector2 secondGravestonePosition = GameUtil.horizontalSymmetry(firstGravestonePosition, gravestone.getImg());
    private final Vector2 twoPSelectorRPosition = secondPlayerPosition.addNoModify(new Vector2(GameFrame.getWidth() / 10f, 0));
    private final Vector2 twoPSelectorLPosition = secondPlayerPosition.addNoModify(new Vector2(- GameFrame.getWidth() / 16f, 0));


    private final Vector2 vsPosition = new Vector2(GameUtil.getCenterXDrawPosition(VS.getImg()), GameUtil.getCenterYDrawPosition(VS.getImg()));
    private final Vector2 selectTipPosition = new Vector2(GameUtil.getCenterXDrawPosition(selectorTip.getImg()), GameFrame.getHeight() * 88 / 100f);


    public SelectScene() {
    }


    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        player1.getAnimation().update(deltaTime);
        player2.getAnimation().update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
        selectorBackground.draw(g, 0, 0);
        VS.draw(g, vsPosition);

        selectorTip.draw(g, selectTipPosition);
        oneP.draw(g, onePPosition);
        onePDesc.draw(g, onePDescPosition);
        gravestone.draw(g, firstGravestonePosition);

        twoP.draw(g, twoPPosition);
        twoPDesc.draw(g, twoPDescPosition);
        gravestone.draw(g, secondGravestonePosition);


        player1.getAnimation().draw(g, firstPlayerPosition);
        player2.getAnimation().draw(g, secondPlayerPosition);

        onePSelectorL.draw(g, onePSelectorLPosition);
        onePSelectorR.draw(g, onePSelectorRPosition);
        twoPSelectorL.draw(g, twoPSelectorLPosition);
        twoPSelectorR.draw(g, twoPSelectorRPosition);

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
    public void onInput(GameKeyEvent event) {
    }

    @Override
    protected void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_A -> {
                onePSelectorL = ResourceGetter.IMAGE_1P_SELECTED_IDLE_LEFT;
                player1 = ROLE_MAP.get((player1.ordinal() + 1) % ROLE_MAP.size());
            }
            case KeyEvent.VK_D -> {
                onePSelectorR = ResourceGetter.IMAGE_1P_SELECTED_IDLE_RIGHT;
                player1 = ROLE_MAP.get((player1.ordinal() + ROLE_MAP.size() - 1) % ROLE_MAP.size());
            }
            case KeyEvent.VK_LEFT -> {
                twoPSelectorL = ResourceGetter.IMAGE_2P_SELECTED_IDLE_LEFT;
                player2 = ROLE_MAP.get((player2.ordinal() + ROLE_MAP.size() - 1) % ROLE_MAP.size());
            }
            case KeyEvent.VK_RIGHT -> {
                twoPSelectorR = ResourceGetter.IMAGE_2P_SELECTED_IDLE_RIGHT;
                player2 = ROLE_MAP.get((player2.ordinal() + 1) % ROLE_MAP.size());
            }
        }
    }

    @Override
    protected void mousePressed(int buttonCode) {

    }

    @Override
    protected void mouseReleased(int buttonCode) {

    }

    @Override
    protected void keyPressed(int keyCode) {
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
        }
    }

    private enum RoleType {
        PEASHOOTER(new Animation(ResourceGetter.ATLAS_PEASHOOTER_IDLE_RIGHT, 100, true)),
        SUNFLOWER(new Animation(ResourceGetter.ATLAS_SUNFLOWER_IDLE_RIGHT, 100, true));

        private final Animation animation;

        RoleType(Animation animation) {
            this.animation = animation;
        }

        public Animation getAnimation() {
            return animation;
        }
    }
}
