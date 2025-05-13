package ind.plague.pvz.scene;

import ind.plague.pvz.scene.scenes.*;

/**
 * @author PlagueWZK
 * description: SceneType
 * date: 2025/5/12 17:03
 */

public enum SceneType {
    MENU_SCENE(MenuScene.class),
    GAME_SCENE(GameScene.class);

    private final Class<?> sceneClass;

    SceneType(Class<?> clazz) {
        this.sceneClass = clazz;
    }

    public Class<?> getSceneClass() {
        return sceneClass;
    }
}
