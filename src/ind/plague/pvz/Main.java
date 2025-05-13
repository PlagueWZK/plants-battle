package ind.plague.pvz;

import ind.plague.pvz.core.GameFrame;
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


        SceneManager sm = new SceneManager();
        SceneFactory sg = new SceneFactory(sm);
        sm.switchScene(SceneType.MENU_SCENE);

        GameFrame gf = new GameFrame(sm);

        long startTime = System.nanoTime();
        while (true) {
            long now = System.nanoTime();
            long deltaTime = now - startTime;
            startTime = now;
            gf.update(deltaTime);
            gf.draw(deltaTime);
        }
    }
}
