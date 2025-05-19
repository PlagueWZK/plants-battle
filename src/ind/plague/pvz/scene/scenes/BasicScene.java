package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.core.Camera;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.event.GameEventListener;
import ind.plague.pvz.event.events.GameKeyEvent;
import ind.plague.pvz.scene.Scene;
import ind.plague.pvz.scene.SceneType;
import ind.plague.pvz.util.Timer;

/**
 * @author PlagueWZK
 * description: BasicScene
 * date: 2025/5/14 14:52
 */

public abstract class BasicScene implements Scene, GameEventListener {

    protected static boolean isListening = true;
    protected static Timer BanInputTimer;

    static {
        BanInputTimer = new Timer(0, false, () -> isListening = true);
    }

    protected int mouseX, mouseY;

    {
        EventBus.instance.subscribe(GameKeyEvent.class, this);
    }


    public BasicScene() {
    }

    @Override
    public void update(long deltaTime) {
        Camera.camera.update(deltaTime);
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void onEvent(GameEvent event) {
        if (event instanceof GameKeyEvent keyEvent) {
            switch (keyEvent.getAction()) {
                case KEY_PRESS -> {
                    keyPressed(keyEvent.getEvent());
                }
                case KEY_RELEASE -> {
                    keyReleased(keyEvent.getEvent());
                }
            }
        }
    }

    @Override
    public void onExit() {

    }

    @Override
    public SceneType getSceneType() {
        return SceneType.typeOf(this);
    }

    protected void banInput(int ms) {
        BanInputTimer.setInterval(ms);
        BanInputTimer.reset();
        isListening = false;
    }

    protected abstract void keyPressed(int keyCode);
    protected abstract void keyReleased(int keyCode);
}
