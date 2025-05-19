package ind.plague.pvz;

import ind.plague.pvz.core.GameFrame;
import ind.plague.pvz.input.InputHandler;
import ind.plague.pvz.input.InputManager;
import ind.plague.pvz.scene.Manager;
import ind.plague.pvz.scene.SceneFactory;
import ind.plague.pvz.scene.SceneManager;
import ind.plague.pvz.scene.SceneType;

/**
 * @author PlagueWZK
 * description: Main
 * date: 2025/5/12 16:19
 */
public class Main {
    public static void main(String[] args) {

        initialize().startGameLoop();
    }

    public static GameFrame initialize() {
        Manager sm = new SceneManager();
        InputHandler ih = new InputManager();
        for (SceneType sceneType : SceneType.values()) {
            sm.registerScene(sceneType, SceneFactory.createScene(sceneType, sm, ih));
        }
        sm.switchScene(SceneType.SELECT_SCENE);
        return new GameFrame(sm, ih);
    }
}
