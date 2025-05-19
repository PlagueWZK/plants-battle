package ind.plague.pvz.event.events;

import ind.plague.pvz.scene.SceneType;

/**
 * @author PlagueWZK
 * description: SceneChangeEvent
 * date: 2025/5/19 13:08
 */

public class SceneChangeEvent extends BasicEvent{
    private final SceneType type;
    public SceneChangeEvent(SceneType type) {
        this.type = type;
    }
    public SceneType getType() {
        return type;
    }
}
