package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.core.Camera;
import ind.plague.pvz.input.InputHandler;
import ind.plague.pvz.scene.Manager;
import ind.plague.pvz.scene.Scene;
import ind.plague.pvz.scene.SceneType;
import ind.plague.pvz.util.Timer;

/**
 * @author PlagueWZK
 * description: BasicScene
 * date: 2025/5/14 14:52
 */

public abstract class BasicScene implements Scene {

    protected static boolean isListening = true;
    protected static Timer BanInputTimer;

    static {
        BanInputTimer = new Timer(0, false, () -> isListening = true);
    }

    protected final Manager manager;
    protected final InputHandler inputHandler;
    protected int mouseX, mouseY;


    public BasicScene(Manager manager, InputHandler inputHandler) {
        this.manager = manager;
        this.inputHandler = inputHandler;
    }

    @Override
    public void update(long deltaTime) {
        if (isListening) {
            onInput();
        } else {
            BanInputTimer.update(deltaTime);
        }
        inputHandler.update();
        mouseX = inputHandler.getMouseX();
        mouseY = inputHandler.getMouseY();
        Camera.camera.update(deltaTime);
    }

    @Override
    public void onEnter() {
        System.out.println("进入" + getSceneType() + "场景");
    }

    @Override
    public void onExit() {
        System.out.println("退出" + getSceneType() + "场景");
        banInput(1000);
    }

    public void ifKey(int keyCode, Runnable runnable) {
        if (inputHandler.getKeyState(keyCode)) runnable.run();
    }

    public void ifHaveKey(Runnable runnable) {
        if (inputHandler.ifHaveKey()) {
            runnable.run();
        }
    }

    public void ifKeyPressed(int keyCode, Runnable runnable) {
        if (inputHandler.ifKeyPressed(keyCode)) runnable.run();
    }

    public void ifKeyReleased(int keyCode, Runnable runnable) {
        if (inputHandler.ifKeyReleased(keyCode)) runnable.run();
    }

    public void ifMouse(int buttonCode, Runnable runnable) {
        if (inputHandler.getMouseState(buttonCode)) runnable.run();
    }

    public void ifMousePressed(int buttonCode, Runnable runnable) {
        if (inputHandler.ifMousePressed(buttonCode)) runnable.run();
    }

    public void ifMouseReleased(int buttonCode, Runnable runnable) {
        if (inputHandler.ifMouseReleased(buttonCode)) runnable.run();
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
}
