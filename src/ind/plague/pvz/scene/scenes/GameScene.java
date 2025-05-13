package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.scene.Manager;
import ind.plague.pvz.scene.Scene;
import ind.plague.pvz.scene.SceneType;
import ind.plague.pvz.util.Timer;


import java.awt.*;

/**
 * @author PlagueWZK
 * description: GameScene
 * date: 2025/5/13 23:34
 */

public class GameScene implements Scene {

    Timer test = new Timer(1000, true, () -> {
        System.out.println("更新");
    });
    @Override
    public void update(long deltaTime, Manager manager) {
        test.update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
    }

    @Override
    public void onEnter() {
        System.out.println("进入" + getSceneType() + "场景");
    }

    @Override
    public void onExit() {
        System.out.println("退出" + getSceneType() + "场景");
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.GAME_SCENE;
    }

}
