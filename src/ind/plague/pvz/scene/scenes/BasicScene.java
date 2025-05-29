package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.core.Camera;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.scene.Scene;
import ind.plague.pvz.scene.SceneType;


/**
 * @author PlagueWZK
 * description: BasicScene
 * date: 2025/5/14 14:52
 */

public abstract class BasicScene implements Scene {


    protected int mouseX, mouseY;


    public BasicScene() {
    }

    @Override
    public void update(long deltaTime) {
        Camera.camera.update(deltaTime);
    }


    public void publish(GameEvent event) {
        EventBus.instance.publish(event);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.typeOf(this);
    }

    public abstract void keyPressed(int keyCode);

    public abstract void keyReleased(int keyCode);

    public abstract void mousePressed(int buttonCode);

    public abstract void mouseReleased(int buttonCode);

    @Override
    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    @Override
    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }
}
