package ind.plague.pvz.event.events;

import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.role.roles.PlayerID;
import ind.plague.pvz.role.roles.Role;

/**
 * @author PlagueWZK
 * description: GetEntityRequest
 * date: 2025/5/29 22:17
 */

public class GetPlayerRequest implements GameEvent {
    private final PlayerID requestID;
    private Role target;
    public GetPlayerRequest(PlayerID playerID) {
        requestID = playerID;
    }
    public void setTarget(Role target) {
        this.target = target;
    }
    public Role getTarget() {
        return target;
    }

    public PlayerID getRequestID() {
        return requestID;
    }
}
