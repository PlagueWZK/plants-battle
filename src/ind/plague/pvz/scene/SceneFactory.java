package ind.plague.pvz.scene;

/**
 * @author PlagueWZK
 * description: SceneFactory
 * date: 2025/5/14 15:19
 */

public class SceneFactory {
    public static Scene createScene(SceneType sceneType) {
        try {
            Class<?> clazz = sceneType.getSceneClass();
            return (Scene) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("反射创建场景实例失败" + sceneType, e);
        }
    }
}
