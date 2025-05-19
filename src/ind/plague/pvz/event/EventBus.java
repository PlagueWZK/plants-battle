package ind.plague.pvz.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author PlagueWZK
 * description: EventBus
 * date: 2025/5/19 12:57
 */

public class EventBus {
    public static EventBus instance = new EventBus();

    private final Map<Class<? extends GameEvent>, List<GameEventListener>> listeners = new ConcurrentHashMap<>();

    private EventBus() {
    }

    public void subscribe(Class<? extends GameEvent> eventType, GameEventListener listener) {
        listeners.computeIfAbsent(eventType, _ -> new ArrayList<>()).add(listener);
    }

    public void publish(GameEvent event) {
        List<GameEventListener> listeners = this.listeners.get(event.getClass());
        if (listeners != null) {
            listeners.forEach(listener -> listener.onEvent(event));
        }
    }
}