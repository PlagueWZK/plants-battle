package ind.plague.pvz.role.roles;

import ind.plague.pvz.util.Vector2;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Role
 * date: 2025/5/14 19:40
 */

public interface Role {
    void draw(Graphics2D g);

    void update(long deltaTime);

    Vector2 getPosition();

    void setPosition(Vector2 position);

    void setPosition(int x, int y);

    Vector2 getVelocity();

    void setVelocity(Vector2 velocity);

    void setVelocity(float x, float y);

    void keyPressed(int keyCode);
    void keyReleased(int keyCode);
}
