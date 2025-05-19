package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.audio.Audio;
import ind.plague.pvz.core.Camera;
import ind.plague.pvz.core.GameFrame;
import ind.plague.pvz.input.InputHandler;
import ind.plague.pvz.scene.Manager;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author PlagueWZK
 * description: SelectScene
 * date: 2025/5/15 13:21
 */

public class SelectScene extends BasicScene {
    private final Sticker selectorBackground = ResourceGetter.IMAGE_SELECTOR_BACKGROUND;
    private final Audio BGM = ResourceGetter.AUDIO_MENU_BGM;

    private final Sticker selectorTip = ResourceGetter.IMAGE_SELECTOR_TIP;
    private final Sticker gravestone = ResourceGetter.IMAGE_GRAVESTONE;
    private final Sticker VS = ResourceGetter.IMAGE_VS;
    private final Sticker oneP = ResourceGetter.IMAGE_1P;
    private final Sticker onePDesc = ResourceGetter.IMAGE_1P_DESC;
    private final Sticker twoP = ResourceGetter.IMAGE_2P;
    private final Sticker twoPDesc = ResourceGetter.IMAGE_2P_DESC;

    private final Animation animationOfFirstSelector;
    private final Animation animationOfSecondSelector;

    private final Vector2 firstSelectorPosition = new Vector2(GameFrame.getWidth()* 19 / 100f, GameFrame.getHeight() / 3f);
    private final Vector2 secondSelectorPosition = new Vector2(GameFrame.getWidth() * 3 / 4f, GameFrame.getHeight() / 3f);

    private final Vector2 onePPosition = new Vector2(GameFrame.getWidth() / 5f, GameFrame.getHeight() / 10f);
    private final Vector2 onePDescPosition = new Vector2(GameFrame.getWidth() / 9f, GameFrame.getHeight() * 3 / 4f);
    private final Vector2 firstGravestonePosition = new Vector2(GameFrame.getWidth() / 9f, GameFrame.getHeight() / 5f);

    private final Vector2 twoPPosition = new Vector2(GameFrame.getWidth() * 3 / 4f, GameFrame.getHeight() / 10f);
    private final Vector2 twoPDescPosition = new Vector2(GameFrame.getWidth() * 2 / 3f, GameFrame.getHeight() * 3 / 4f);
    private final Vector2 secondGravestonePosition = new Vector2(GameFrame.getWidth() * 2 / 3f, GameFrame.getHeight() / 5f);

    private final Vector2 vsPosition = new Vector2(GameFrame.getWidth() * 31 / 100f, GameFrame.getHeight() * 18 / 100f);
    private final Vector2 selectTipPosition = new Vector2(GameFrame.getWidth() * 37 / 100f, GameFrame.getHeight() * 88 / 100f);



    public SelectScene(Manager manager, InputHandler inputHandler) {
        super(manager, inputHandler);
    }

    {
        animationOfFirstSelector = new Animation(ResourceGetter.ATLAS_SUNFLOWER_IDLE_RIGHT, 100, true);
        animationOfSecondSelector = new Animation(ResourceGetter.ATLAS_PEASHOOTER_IDLE_RIGHT, 100, true);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        animationOfFirstSelector.update(deltaTime);
        animationOfSecondSelector.update(deltaTime);
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



        animationOfFirstSelector.draw(g, firstSelectorPosition);
        animationOfSecondSelector.draw(g, secondSelectorPosition);

    }

    @Override
    public void onEnter() {
        super.onEnter();
        BGM.play(true);
    }

    @Override
    public void onExit() {
        super.onExit();
        BGM.stop();
    }

    @Override
    public void onInput() {
        ifKeyPressed(KeyEvent.VK_H, () -> System.out.println("按下H"));
        ifKeyReleased(KeyEvent.VK_H, () -> System.out.println("松开H"));
        ifMousePressed(MouseEvent.BUTTON1, () -> System.out.println("鼠标左键按下"));
        ifMouseReleased(MouseEvent.BUTTON1, () -> System.out.println("鼠标左键松开"));
        ifMousePressed(MouseEvent.BUTTON2, () -> System.out.println("鼠标中键按下"));
        ifMouseReleased(MouseEvent.BUTTON2, () -> System.out.println("鼠标中键松开"));
        ifMousePressed(MouseEvent.BUTTON3, () -> System.out.println("鼠标右键按下"));
        ifMouseReleased(MouseEvent.BUTTON3, () -> System.out.println("鼠标右键松开"));
        ifKey(KeyEvent.VK_R, () -> Camera.camera.shake(8, 500));
    }
}
