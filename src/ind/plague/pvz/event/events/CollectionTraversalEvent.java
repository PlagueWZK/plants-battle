package ind.plague.pvz.event.events;

import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.scene.scenes.GameScene;

import java.util.function.Predicate;

/*
  @author PlagueWZK
 * description: CollisionCheckEvent
 * date: 2025/5/29 22:53
 */

/**
 * 集合遍历事件
 *
 * @param listType  指定集合类型，涉及强制类型转换
 * @param predicate 消费函数，形参为集合元素。返回表示是否终止遍历：true-终止遍历
 * @param <T>       集合元素类型
 */
public record CollectionTraversalEvent<T>(GameScene.ListType listType, Class<T> type,
                                          Predicate<T> predicate) implements GameEvent {
}
