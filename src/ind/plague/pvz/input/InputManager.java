package ind.plague.pvz.input;

import java.util.Arrays;

/**
 * @author PlagueWZK
 * description: InputManager
 * date: 2025/5/14 14:58
 */

public class InputManager implements InputHandler {
    private final boolean[] currentKeyState;
    private final boolean[] previousKeyState;
    private final boolean[] currentMouseState;
    private final boolean[] previousMouseState;

    private int mouseX, mouseY;




    {
        currentKeyState = new boolean[256];
        previousKeyState = new boolean[256];
        currentMouseState = new boolean[6];
        previousMouseState  = new boolean[6];
    }

    @Override
    public void update() {
        System.arraycopy(currentKeyState, 0, previousKeyState, 0, currentKeyState.length);
        System.arraycopy(currentMouseState, 0, previousMouseState, 0, currentMouseState.length);
    }

    @Override
    public void setMouseState(int buttonCode, boolean state) {
        currentMouseState[buttonCode] = state;
    }

    @Override
    public void setMouseX(int x) {
        mouseX  = x;
    }

    @Override
    public void setMouseY(int y) {
        mouseY = y;
    }

    @Override
    public boolean getMouseState(int buttonCode) {
        return currentMouseState[buttonCode];
    }

    @Override
    public boolean ifMousePressed(int buttonCode) {
        return currentMouseState[buttonCode] && !previousMouseState[buttonCode];
    }

    @Override
    public boolean ifMouseReleased(int buttonCode) {
        return !currentMouseState[buttonCode] && previousMouseState[buttonCode];
    }

    @Override
    public int getMouseX() {
        return mouseX;
    }

    @Override
    public int getMouseY() {
        return mouseY;
    }


    @Override
    public void setKeyState(int keyCode, boolean state) {
        currentKeyState[keyCode] = state;
    }

    @Override
    public boolean getKeyState(int keyCode) {
        return currentKeyState[keyCode];
    }

    @Override
    public boolean ifKeyReleased(int keyCode) {
        return !currentKeyState[keyCode] && previousKeyState[keyCode];
    }

    @Override
    public boolean ifKeyPressed(int keyCode) {
        return currentKeyState[keyCode] && !previousKeyState[keyCode];
    }

    @Override
    public boolean ifHaveKey() {
        for (boolean b : currentKeyState) {
            if (b) return true;
        }
        return false;
    }

    @Override
    public void reset() {
        Arrays.fill(currentKeyState, false);
        Arrays.fill(previousKeyState, false);
        Arrays.fill(currentMouseState, false);
        Arrays.fill(previousMouseState, false);
    }
}
