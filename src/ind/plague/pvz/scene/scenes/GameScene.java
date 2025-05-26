package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.event.events.GameKeyEvent;
import ind.plague.pvz.scene.SceneType;


import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author PlagueWZK
 * description: GameScene
 * date: 2025/5/13 23:34
 */

public class GameScene extends BasicScene {
    @Override
    protected void mousePressed(int buttonCode) {

    }

    @Override
    protected void mouseReleased(int buttonCode) {

    }

    public GameScene() {
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
    public void onEnter() {

    }

    @Override
    public void onExit() {

    }

    @Override
    public void onInput(GameKeyEvent event) {

    }

    @Override
    protected void keyPressed(int keyCode) {

    }

    @Override
    protected void keyReleased(int keyCode) {

    }


}
