package ind.plague.pvz.event.events;

import java.awt.event.InputEvent;

/**
 * @author PlagueWZK
 * description: GameKeyEvent
 * date: 2025/5/19 13:15
 */

public class GameKeyEvent extends BasicEvent {
    private final Action action;
    private final InputEvent event;

    public GameKeyEvent(InputEvent event, Action action) {
        this.action = action;
        this.event = event;
    }

    public Action getAction() {
        return action;
    }

    public InputEvent getEvent() {
        return event;
    }

    public enum Action {
        KEY_PRESS,
        KEY_RELEASE,
        MOUSE_PRESS,
        MOUSE_RELEASE,
        MOUSE_CLICK,
        MOUSE_MOVE
    }
}
