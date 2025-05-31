package ind.plague.pvz.element.bullet;

import ind.plague.pvz.Main;
import ind.plague.pvz.core.Entity;
import ind.plague.pvz.core.GameFrame;
import ind.plague.pvz.role.roles.PlayerID;
import ind.plague.pvz.util.Vector2;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Bullet
 * date: 2025/5/28 18:56
 */
@SuppressWarnings("unused")
public abstract class Bullet implements Entity {
    protected Vector2 position = new Vector2();
    protected Vector2 size = new Vector2();
    protected Vector2 velocity = new Vector2();
    protected int damage;

    protected boolean valid = true;
    protected boolean canRemove = false;

    protected Runnable callback;

    protected PlayerID targetID;

    public abstract void update(long deltaTime);

    public void draw(Graphics2D g) {
        if (Main.DEBUG) {
            g.setColor(Color.RED);
            g.drawRect((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY());
        }
    }

    public void onCollide() {
        if (callback != null) {
            callback.run();
        }
    }

    public boolean checkCollision(Vector2 position, Vector2 size) {
        return this.position.x + this.size.x / 2 >= position.x && this.position.x + this.size.x / 2 <= position.x + size.x && this.position.y + this.size.y / 2 >= position.y && this.position.y + this.size.y / 2 <= position.y + size.y;
    }

    public boolean checkOutBounds() {
        return this.position.x + this.size.x <= 0 || this.position.x >= GameFrame.getWidth() || this.position.y + this.size.y <= 0 || this.position.y >= GameFrame.getHeight();
    }

    public boolean checkCanRemove() {
        return canRemove;
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(float x, float y) {
        this.velocity.set(x, y);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public PlayerID getTargetID() {
        return targetID;
    }

    public void setTargetID(PlayerID targetID) {
        this.targetID = targetID;
    }
}
