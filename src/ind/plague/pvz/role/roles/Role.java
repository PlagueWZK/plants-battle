package ind.plague.pvz.role.roles;

import ind.plague.pvz.core.Entity;
import ind.plague.pvz.util.Vector2;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Role
 * date: 2025/5/14 19:40
 */

public interface Role extends Entity {
    void draw(Graphics2D g);

    void update(long deltaTime);

    Vector2 getPosition();

    Vector2 getSize();

    void setPosition(Vector2 position);

    void setPosition(int x, int y);

    void attack();

    void attackEx();

    void keyPressed(int keyCode);

    void keyReleased(int keyCode);
}
