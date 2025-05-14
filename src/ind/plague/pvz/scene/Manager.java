package ind.plague.pvz.scene;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Manager
 * date: 2025/5/13 18:37
 */

public interface Manager {

    void switchScene(SceneType sceneType);

    void update(long deltaTime);

    void draw(Graphics2D g);

    void registerScene(SceneType sceneType, Scene scene);

}
