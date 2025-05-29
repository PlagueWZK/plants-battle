package ind.plague.pvz.event.events;

import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.role.roles.PlayerID;
import ind.plague.pvz.scene.scenes.SelectScene;

public record SetPlayerEvent(PlayerID id, SelectScene.RoleType role) implements GameEvent {
}
