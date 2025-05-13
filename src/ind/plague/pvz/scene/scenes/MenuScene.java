package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.scene.Manager;
import ind.plague.pvz.scene.Scene;
import ind.plague.pvz.scene.SceneType;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: MenuScene
 * date: 2025/5/12 17:13
 */

public class MenuScene implements Scene {
    private Manager manager;
    @Override
    public void update(long deltaTime) {
        System.out.println("更新" + getSceneType() + "场景");
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawRoundRect(10, 10, 100, 100, 10, 10);
        System.out.println("绘制" + getSceneType() + "场景");
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
        return SceneType.MENU_SCENE;
    }

    @Override
    public void setManager(Manager sceneManager) {
        this.manager = sceneManager;
    }
}
