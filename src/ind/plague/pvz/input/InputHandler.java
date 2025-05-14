package ind.plague.pvz.input;

/**
 * @author PlagueWZK
 * description: InputHandler
 * date: 2025/5/14 14:58
 */

public interface InputHandler {
    void setKeyState(int keyCode, boolean state);
    void setMouseState(int buttonCode, boolean state);
    boolean getKeyState(int keyCode);
    boolean getMouseState(int buttonCode);
    int getMouseX();
    int getMouseY();
    void setMouseX(int x);
    void setMouseY(int y);
}
