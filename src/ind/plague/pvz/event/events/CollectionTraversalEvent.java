package ind.plague.pvz.event.events;

import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.scene.scenes.GameScene;

import java.util.function.Consumer;

/**
 * @author PlagueWZK
 * description: CollisionCheckEvent
 * date: 2025/5/29 22:53
 */

public record CollectionTraversalEvent<T>(GameScene.ListType listType, Consumer<T> consumer) implements GameEvent {
}
