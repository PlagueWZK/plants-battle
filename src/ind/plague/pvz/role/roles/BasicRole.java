package ind.plague.pvz.role.roles;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.event.GameEventListener;
import ind.plague.pvz.event.events.GameKeyEvent;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.event.KeyEvent;
import java.io.InputStream;

/**
 * @author PlagueWZK
 * description: BasicRole
 * date: 2025/5/14 19:39
 */

public abstract class BasicRole implements Role {

    final PlayerID ID;
    final Vector2 position = new Vector2(0, 0);
    final Vector2 velocity = new Vector2(0, 0);
    boolean goLeftDown = false;
    boolean  goRightDown = false;
    boolean isFacingRight = true;

    Animation animationIdleLeft;
    Animation animationIdleRight;
    Animation animationRunLeft;
    Animation animationRunRight;

    public BasicRole(PlayerID id) {
        this.ID = id;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    @Override
    public void setPosition(int x, int y) {
        this.position.set(x, y);
    }

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Vector2 velocity) {
        this.velocity.set(velocity);
    }

    @Override
    public void setVelocity(float x, float y) {
        this.velocity.set(x, y);
    }

    @Override
    public void update(long deltaTime) {
        isFacingRight = goRightDown && !goLeftDown;

    }

    @Override
    public void keyReleased(int keyCode) {
        switch (ID) {
            case PLAYER_1 -> {
                switch (keyCode) {
                    case KeyEvent.VK_A -> {
                        goLeftDown = false;
                    }
                    case KeyEvent.VK_D -> {
                        goRightDown = false;
                    }
                }
            }
            case PLAYER_2 -> {
                switch (keyCode) {
                    case KeyEvent.VK_LEFT -> {
                        goLeftDown = false;
                    }
                    case KeyEvent.VK_RIGHT -> {
                        goRightDown = false;
                    }
                }
            }
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        switch (ID) {
            case PLAYER_1 -> {
                switch (keyCode) {
                    case KeyEvent.VK_A -> {
                        goLeftDown = true;
                    }
                    case KeyEvent.VK_D -> {
                        goRightDown = true;
                    }
                }
            }
            case PLAYER_2 -> {
                switch (keyCode) {
                    case KeyEvent.VK_LEFT -> {
                        goLeftDown = true;
                    }
                    case KeyEvent.VK_RIGHT -> {
                        goRightDown = true;
                    }
                }
            }
        }
    }
}
