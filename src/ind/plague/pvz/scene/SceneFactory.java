package ind.plague.pvz.scene;

import ind.plague.pvz.scene.scenes.MenuScene;

/**
 * @author PlagueWZK
 * description: SceneFactory
 * date: 2025/5/13 19:27
 */

public class SceneFactory {
    private final Manager sceneManager;

    public SceneFactory(Manager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public Scene createScene(String scenePackagePath, SceneType sceneType) {
        try {
            String className = scenePackagePath + "." + sceneType.getClassName();
            Class<?> clazz = Class.forName(className);
            Scene scene = (Scene) clazz.getDeclaredConstructor().newInstance();
            scene.setManager(sceneManager);
            return scene;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("场景类未找到: " + sceneType.getClassName(), e);
        } catch (Exception e) {
            throw new RuntimeException("场景创建失败: " + sceneType, e);
        }
    }
}
