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
    private final boolean[] keyState;

    {
        scenes = new HashMap<>();
        keyState = new boolean[256];

        for (SceneType sceneType : SceneType.values()) {
            try {
                Class<?> clazz = sceneType.getSceneClass();
                registerScene(sceneType, (Scene) clazz.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException("反射创建场景实例失败" + sceneType, e);
            }
        }
    }

    private Scene currentScene;

    public void update(long deltaTime) {
        currentScene.update(deltaTime, this);
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

    public void registerScene(SceneType sceneType, Scene scene) {
        scenes.put(sceneType, scene);
    }
}
