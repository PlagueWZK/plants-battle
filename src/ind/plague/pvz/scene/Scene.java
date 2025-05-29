package ind.plague.pvz.scene;


import ind.plague.pvz.role.roles.PlayerID;
import ind.plague.pvz.role.roles.Role;
import ind.plague.pvz.scene.scenes.SelectScene;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Scene
 * date: 2025/5/12 16:59
 */

public interface Scene {

    void update(long deltaTime);

    void draw(Graphics2D g);

    void onEnter();

    void onExit();

    void keyPressed(int code);

    void keyReleased(int code);

    void mousePressed(int code);

    void mouseReleased(int code);

    void setMouseX(int mouseX);

    void setMouseY(int mouseY);

    SceneType getSceneType();
}
