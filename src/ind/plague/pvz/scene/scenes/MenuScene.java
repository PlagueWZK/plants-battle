package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.animation.Atlas;
import ind.plague.pvz.input.InputHandler;
import ind.plague.pvz.scene.Manager;
import ind.plague.pvz.scene.SceneType;
import ind.plague.pvz.util.Timer;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author PlagueWZK
 * description: MenuScene
 * date: 2025/5/12 17:13
 */

public class MenuScene extends BasicScene {

    Atlas atlas1 = new Atlas();
    Atlas atlas2 = new Atlas();
    Animation animation;
    int x,y;
    Timer timer;

    public MenuScene(Manager manager, InputHandler inputHandler) {
        super(manager, inputHandler);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        animation.update(deltaTime);
        timer.update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
        animation.draw(g, x, y);
    }

    @Override
    public void onEnter() {
        System.out.println("进入" + getSceneType() + "场景");
        atlas1.loadFromFile("/image/role/sunflowers/sunflower_run_%d.png", 5);
        atlas2 = atlas1.flipAtlas();
        animation = new Animation(atlas1, 500, false, () -> animation.setAtlas(atlas2));
        timer = new Timer(5000, false, () -> {manager.switchScene(SceneType.GAME_SCENE);});
    }

    @Override
    public void onExit() {
        System.out.println("退出" + getSceneType() + "场景");
    }

    @Override
    public void onInput() {
        ifKey(KeyEvent.VK_D, () -> x++);
        ifKey(KeyEvent.VK_A, () -> x--);
        ifKey(KeyEvent.VK_W, () -> y--);
        ifKey(KeyEvent.VK_S, () -> y++);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.MENU_SCENE;
    }
}
