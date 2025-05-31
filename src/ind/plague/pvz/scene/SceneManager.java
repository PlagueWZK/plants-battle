package ind.plague.pvz.scene;


import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.event.GameEventListener;
import ind.plague.pvz.event.events.GameKeyEvent;
import ind.plague.pvz.event.events.SceneChangeEvent;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 * @author PlagueWZK
 * description: SceneManager
 * date: 2025/5/12 17:54
 */

public class SceneManager implements GameEventListener {

    private final HashMap<SceneType, Scene> scenes;
    private Scene currentScene;

    {
        scenes = new HashMap<>();
        EventBus.instance.subscribe(this, SceneChangeEvent.class, GameKeyEvent.class);
    }

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
        switch (event) {
            case SceneChangeEvent e -> switchScene(e.getType());
            case GameKeyEvent e -> handleKeyEvent(e);
            default -> throw new IllegalStateException("Unexpected value: " + event);
        }
    }

    private void handleKeyEvent(GameKeyEvent keyEvent) {
        InputEvent i = keyEvent.getEvent();
        switch (keyEvent.getAction()) {
            case KEY_PRESS -> currentScene.keyPressed(((KeyEvent) i).getKeyCode());
            case KEY_RELEASE -> currentScene.keyReleased(((KeyEvent) i).getKeyCode());
            case MOUSE_MOVE -> {
                currentScene.setMouseX(((MouseEvent) i).getX());
                currentScene.setMouseY(((MouseEvent) i).getY());
            }
            case MOUSE_PRESS -> currentScene.mousePressed(((MouseEvent) i).getButton());
            case MOUSE_RELEASE -> currentScene.mouseReleased(((MouseEvent) i).getButton());
        }
    }
}
