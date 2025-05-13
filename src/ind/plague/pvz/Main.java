package ind.plague.pvz;

import ind.plague.pvz.core.GameFrame;
import ind.plague.pvz.scene.SceneManager;
import ind.plague.pvz.scene.SceneType;

/**
 * @author PlagueWZK
 * description: Main
 * date: 2025/5/12 16:19
 */
public class Main {
    // 目标帧时间（微秒），用于控制帧率为约60FPS
    public static void main(String[] args) {
        SceneManager sm = new SceneManager();
        sm.switchScene(SceneType.MENU_SCENE);

        GameFrame gf = new GameFrame(sm);

        gf.startGameLoop();
    }
}
