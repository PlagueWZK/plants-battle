package ind.plague.pvz.event.events;

import ind.plague.pvz.scene.scenes.SelectScene;

/**
 * @author PlagueWZK
 * description: RoleRequest
 * date: 2025/5/27 22:16
 */

public class RoleRequest extends BasicEvent{
    SelectScene.RoleType roleType;
    int playerType;
    public RoleRequest(int t, SelectScene.RoleType roleType) {
        this.roleType = roleType;
        this.playerType = t;
    }
    public SelectScene.RoleType getRoleType() {
        return roleType;
    }
    public int getPlayerType() {
        return playerType;
    }
}
