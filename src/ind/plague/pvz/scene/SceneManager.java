package ind.plague.pvz.scene;


import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.event.GameEventListener;
import ind.plague.pvz.event.events.SceneChangeEvent;

import java.awt.*;
import java.util.HashMap;

/**
 * @author PlagueWZK
 * description: SceneManager
 * date: 2025/5/12 17:54
 */

public class SceneManager implements GameEventListener {

    private final HashMap<SceneType, Scene> scenes;

    {
        scenes = new HashMap<>();
        EventBus.instance.subscribe(SceneChangeEvent.class, this);
    }

    private Scene currentScene;


    public void update(long deltaTime) {
        currentScene.update(deltaTime);

    }

    public void draw(Graphics2D g) {
        currentScene.draw(g);
    }

    public void switchScene(SceneType sceneType) {
        if (currentScene != null) {
            currentScene.onExit();
        }
        currentScene = scenes.get(sceneType);
        currentScene.onEnter();
    }

    public void registerScene(SceneType sceneType, Scene scene) {
        scenes.put(sceneType, scene);
    }


    @Override
    public void onEvent(GameEvent event) {
        if (event instanceof SceneChangeEvent changeEvent) {
            switchScene(changeEvent.getType());
        }
    }
}
