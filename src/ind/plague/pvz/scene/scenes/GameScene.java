package ind.plague.pvz.scene.scenes;


import java.awt.*;

/**
 * @author PlagueWZK
 * description: GameScene
 * date: 2025/5/13 23:34
 */

public class GameScene extends BasicScene {
    @Override
    public void mousePressed(int buttonCode) {

    }

    @Override
    public void mouseReleased(int buttonCode) {

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
    public void keyPressed(int keyCode) {

    }

    @Override
    public void keyReleased(int keyCode) {

    }


}
