package ind.plague.pvz.input;

/**
 * @author PlagueWZK
 * description: InputManager
 * date: 2025/5/14 14:58
 */

public class InputManager implements InputHandler {
    private final boolean[] keyState;
    private final boolean[] mouseState;
    private int mouseX, mouseY;


    {
        keyState = new boolean[256];
        mouseState = new boolean[3];
    }

    @Override
    public void setMouseState(int buttonCode, boolean state) {
        mouseState[buttonCode] = state;
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
        return mouseState[buttonCode];
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
        keyState[keyCode] = state;
    }

    @Override
    public boolean getKeyState(int keyCode) {
        return keyState[keyCode];
    }
}
