package ind.plague.pvz.scene;

import ind.plague.pvz.input.InputHandler;

/**
 * @author PlagueWZK
 * description: SceneFactory
 * date: 2025/5/14 15:19
 */

public class SceneFactory {
    public static Scene createScene(SceneType sceneType, Manager manager, InputHandler inputHandler) {
        try {
            Class<?> clazz = sceneType.getSceneClass();
            return (Scene) clazz.getDeclaredConstructor(Manager.class, InputHandler.class).newInstance(manager, inputHandler);
        } catch (Exception e) {
            throw new RuntimeException("反射创建场景实例失败" + sceneType, e);
        }
    }
}
