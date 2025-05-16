package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.input.InputHandler;
import ind.plague.pvz.scene.Manager;
import ind.plague.pvz.scene.SceneType;


import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author PlagueWZK
 * description: GameScene
 * date: 2025/5/13 23:34
 */

public class GameScene extends BasicScene {

    public GameScene(Manager manager, InputHandler inputHandler) {
        super(manager, inputHandler);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.PINK);
        g.fillOval(mouseX - 5, mouseY - 5, 10, 10);
    }

    @Override
    public void onInput() {
        ifKey(KeyEvent.VK_9, () -> {
            System.out.println(getSceneType());
        });
        ifKey(KeyEvent.VK_ESCAPE, () -> {
            manager.switchScene(SceneType.MENU_SCENE);
        });
    }
}
