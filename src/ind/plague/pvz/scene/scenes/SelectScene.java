package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.animation.Atlas;
import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.core.Camera;
import ind.plague.pvz.input.InputHandler;
import ind.plague.pvz.scene.Manager;
import ind.plague.pvz.scene.SceneType;
import ind.plague.pvz.util.ResourceGetter;

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

    public SelectScene(Manager manager, InputHandler inputHandler) {
        super(manager, inputHandler);
    }


    Atlas atlas1 = new Atlas();
    Animation animation;
    int x, y;

    {
        {
            atlas1.loadFromFile("/image/role/sunflowers/sunflower_run_%d.png", 5);
            animation = new Animation(atlas1, 75, true);
        }
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        animation.update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
        selectorBackground.draw(g, 0, 0);
        animation.draw(g, x, y);
    }

    @Override
    public void onInput() {
        ifKey(KeyEvent.VK_D, () -> x++);
        ifKey(KeyEvent.VK_A, () -> x--);
        ifKey(KeyEvent.VK_W, () -> y--);
        ifKey(KeyEvent.VK_S, () -> y++);
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
