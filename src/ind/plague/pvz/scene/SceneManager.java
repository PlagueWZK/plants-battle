package ind.plague.pvz.scene;


import java.awt.*;
import java.util.HashMap;

/**
 * @author PlagueWZK
 * description: SceneManager
 * date: 2025/5/12 17:54
 */

public class SceneManager implements Manager{

    private static final String SCENE_PACKAGE_PATH = "ind.plague.pvz.scene.scenes";
    private final HashMap<SceneType, Scene> scenes;
    private final boolean[] keyState;

    {
        scenes = new HashMap<>();
        keyState = new boolean[256];
        SceneFactory sceneFactory = new SceneFactory(this);

        for (SceneType sceneType : SceneType.values()) {
            registerScene(sceneFactory.createScene(SCENE_PACKAGE_PATH, sceneType));
        }
    }

    private Scene currentScene;

    public void update(long deltaTime) {
        currentScene.update(deltaTime);
    }

    public void draw(Graphics2D g) {
        currentScene.draw(g);
    }

    public void setKeyState(int keyCode, boolean state) {
        keyState[keyCode] = state;
    }

    @Override
    public boolean getKeyState(int keyCode) {
        return keyState[keyCode];
    }

    @Override
    public void switchScene(SceneType sceneType) {
        if (currentScene != null) {
            currentScene.onExit();
        }
        currentScene = scenes.get(sceneType);
        currentScene.onEnter();
    }

    @Override
    public void registerScene(Scene scene) {
        scenes.put(scene.getSceneType(), scene);
    }
}
