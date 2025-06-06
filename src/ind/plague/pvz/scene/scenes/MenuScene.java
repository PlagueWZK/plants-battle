package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.audio.Audio;
import ind.plague.pvz.event.events.SceneChangeEvent;
import ind.plague.pvz.scene.SceneType;
import ind.plague.pvz.util.ResourceGetter;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: MenuScene
 * date: 2025/5/12 17:13
 */

public class MenuScene extends BasicScene {

    private final Sticker backGround = ResourceGetter.IMAGE_MENU_BACKGROUND;
    private final Audio BGM = ResourceGetter.AUDIO_MENU_BGM;

    public MenuScene() {
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void draw(Graphics2D g) {
        backGround.draw(g, 0, 0);
    }

    @Override
    public void onEnter() {
        BGM.reset();
        BGM.play(true);
    }

    @Override
    public void onExit() {
        BGM.stop();
    }

    @Override
    public void keyReleased(int keyCode) {

    }

    @Override
    public void mousePressed(int buttonCode) {

    }

    @Override
    public void mouseReleased(int buttonCode) {

    }

    @Override
    public void keyPressed(int keyCode) {
        publish(new SceneChangeEvent(SceneType.SELECT_SCENE));
    }
}
