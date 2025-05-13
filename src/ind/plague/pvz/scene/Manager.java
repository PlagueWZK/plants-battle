package ind.plague.pvz.scene;

/**
 * @author PlagueWZK
 * description: Manager
 * date: 2025/5/13 18:37
 */

public interface Manager {
    void registerScene(Scene scene);

    void switchScene(SceneType sceneType);

    boolean getKeyState(int keyCode);
}
