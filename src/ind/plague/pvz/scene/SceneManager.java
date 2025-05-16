package ind.plague.pvz.scene;


import java.awt.*;
import java.util.HashMap;

/**
 * @author PlagueWZK
 * description: SceneManager
 * date: 2025/5/12 17:54
 */

public class SceneManager implements Manager {

    private final HashMap<SceneType, Scene> scenes;

    {
        scenes = new HashMap<>();

    }

    private Scene currentScene;

    public void update(long deltaTime) {
        currentScene.update(deltaTime);
    }

    public void draw(Graphics2D g) {
        currentScene.draw(g);
    }

    @Override
    public void switchScene(SceneType sceneType) {
        if (currentScene != null) {
            currentScene.onExit();
        }
        currentScene = scenes.get(sceneType);
        currentScene.onEnter();
    }

    public void registerScene(SceneType sceneType, Scene scene) {
        scenes.put(sceneType, scene);
    }

}
