package ind.plague.pvz.event.events;

import ind.plague.pvz.core.Entity;
import ind.plague.pvz.event.GameEvent;

/**
 * @author PlagueWZK
 * description: AddEntityEvent
 * date: 2025/5/29 16:05
 */

public record AddEntityEvent(Entity entity) implements GameEvent {
}
