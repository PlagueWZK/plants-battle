package ind.plague.pvz.scene.scenes;

import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.audio.Audio;
import ind.plague.pvz.input.InputHandler;
import ind.plague.pvz.scene.Manager;
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
    private final Audio BGM;

    {
        BGM = ResourceGetter.AUDIO_MENU_BGM;
    }

    public MenuScene(Manager manager, InputHandler inputHandler) {
        super(manager, inputHandler);
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
        super.onEnter();
        BGM.play(true);
    }

    @Override
    public void onExit() {
        super.onExit();
        BGM.stop();
    }

    @Override
    public void onInput() {
        ifHaveKeyPressed(() -> {
            manager.switchScene(SceneType.SELECT_SCENE);
        });
    }
}
