package ind.plague.pvz.role.roles;

/**
 * @author PlagueWZK
 * description: PlayerID
 * date: 2025/5/28 00:03
 */

public enum PlayerID {
    PLAYER_1,
    PLAYER_2;
    public static PlayerID getPlayerID(int id) {
        return id == 1 ? PLAYER_1 : PLAYER_2;
    }
}
