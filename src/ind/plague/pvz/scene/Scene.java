package ind.plague.pvz.scene;

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
    void onInput();
    SceneType getSceneType();
}
