package ind.plague.pvz.input;

/**
 * @author PlagueWZK
 * description: InputHandler
 * date: 2025/5/14 14:58
 */

public interface InputHandler {
    void setKeyState(int keyCode, boolean state);
    boolean getKeyState(int keyCode);
    boolean ifKeyPressed(int keyCode);
    boolean ifKeyReleased(int keyCode);
    boolean ifHaveKeyPressed();

    void setMouseState(int buttonCode, boolean state);
    boolean getMouseState(int buttonCode);
    boolean ifMousePressed(int buttonCode);
    boolean ifMouseReleased(int buttonCode);

    void setMouseX(int x);
    void setMouseY(int y);
    int getMouseX();
    int getMouseY();

    void reset();
    void update();

}
