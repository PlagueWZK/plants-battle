package ind.plague.pvz.scene;

import ind.plague.pvz.scene.scenes.GameScene;
import ind.plague.pvz.scene.scenes.MenuScene;
import ind.plague.pvz.scene.scenes.SelectScene;

import java.util.HashMap;

/**
 * @author PlagueWZK
 * description: SceneType
 * date: 2025/5/12 17:03
 */

public enum SceneType {

    MENU_SCENE(MenuScene.class),
    GAME_SCENE(GameScene.class),
    SELECT_SCENE(SelectScene.class);

    private static final HashMap<Scene, SceneType> TYPE_MAP = new HashMap<>();

    private final Class<?> sceneClass;

    SceneType(Class<?> clazz) {
        this.sceneClass = clazz;
    }

    public Class<?> getSceneClass() {
        return sceneClass;
    }

    public static SceneType typeOf(Scene scene) {
        return TYPE_MAP.computeIfAbsent(scene, k -> {
            for (SceneType value : values()) {
                if (value.sceneClass == k.getClass()) {
                    return value;
                }
            }
            return null;
        });
    }
}