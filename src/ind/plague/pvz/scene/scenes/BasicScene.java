package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.input.InputHandler;
import ind.plague.pvz.scene.Manager;
import ind.plague.pvz.scene.Scene;

/**
 * @author PlagueWZK
 * description: BasicScene
 * date: 2025/5/14 14:52
 */

public abstract class BasicScene implements Scene {
    protected final Manager manager;
    protected final InputHandler inputHandler;
    protected int mouseX, mouseY;

    protected BasicScene(Manager manager, InputHandler inputHandler) {
        this.manager = manager;
        this.inputHandler = inputHandler;
    }

    @Override
    public void update(long deltaTime) {
        onInput();
        mouseX = inputHandler.getMouseX();
        mouseY = inputHandler.getMouseY();
    }

    protected void ifKey(int keyCode, Runnable runnable) {
        if (inputHandler.getKeyState(keyCode)) runnable.run();
    }
    protected void ifMouse(int buttonCode, Runnable runnable) {
        if (inputHandler.getMouseState(buttonCode)) runnable.run();
    }
}
