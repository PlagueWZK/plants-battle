package ind.plague.pvz.role.roles;

import ind.plague.pvz.Main;
import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.element.Platform;
import ind.plague.pvz.element.bullet.Bullet;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.events.CollectionTraversalEvent;
import ind.plague.pvz.scene.Scene;
import ind.plague.pvz.scene.scenes.GameScene;
import ind.plague.pvz.util.Timer;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author PlagueWZK
 * description: BasicRole
 * date: 2025/5/14 19:39
 */

public abstract class BasicRole implements Role {

    protected static GameScene SCENE;
    protected boolean isExAttack = false;

    int mp = 100;
    int hp = 100;

    final float gravity = 2.8e-3f;
    final float runVelocity = 0.45f;
    final float jumpVelocity = -1.0f;

    final PlayerID ID;
    final Vector2 position = new Vector2(0, 0);
    final Vector2 velocity = new Vector2(0, 0);
    final Vector2 size = new Vector2(0, 0);
    int goLeftDown = 0;
    int goRightDown = 0;
    boolean isJumping = false;
    boolean isFacingRight;

    int attackCdTime = 500;
    boolean canAttack = true;
    Timer attackCdTimer = new Timer(attackCdTime, false, () -> canAttack = true);

    Animation animationIdleLeft;
    Animation animationIdleRight;
    Animation animationRunLeft;
    Animation animationRunRight;
    Animation animationAttackExRight;
    Animation animationAttackExLeft;
    Animation currentAnimation;

    public BasicRole(PlayerID id) {
        this.ID = id;
        isFacingRight = ID == PlayerID.PLAYER_1;
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
    public void update(long deltaTime) {
        int direction = goRightDown - goLeftDown;
        if (direction != 0) {
            isFacingRight = direction > 0;
            currentAnimation = isFacingRight ? animationRunRight : animationRunLeft;

            float distance = direction * runVelocity * deltaTime / 1000000;
            run(distance);
        } else {
            currentAnimation = isFacingRight ? animationIdleRight : animationIdleLeft;
        }
        if (isExAttack) {
            currentAnimation = isFacingRight ? animationAttackExRight : animationAttackExLeft;
        }
        if (isJumping) {
            jump();
            isJumping = false;
        }
        currentAnimation.update(deltaTime);
        moveAndCollide((int) (deltaTime / 1000000));
        attackCdTimer.update(deltaTime);
    }

    public void run(float distance) {
        if (isExAttack) return;
        position.add(distance, 0);
    }

    @Override
    public Vector2 getSize() {
        return size;
    }

    public void jump() {
        if (velocity.getY() != 0 || isExAttack) {
            return;
        }
        velocity.add(0, jumpVelocity);
    }

    public void moveAndCollide(int delta) {
        velocity.add(0, gravity * delta);
        position.add(velocity.mulTemp(delta));
        if (velocity.getY() > 0) {


        }
        EventBus.instance.publish(new CollectionTraversalEvent<Platform>(GameScene.ListType.PLATFORM, platform -> {
            boolean isCollideX = (Math.max(position.getX() + size.getX(), platform.shape.right) - Math.min(position.getX(), platform.shape.left) <= size.getX() + platform.shape.right - platform.shape.left);
            boolean isCollideY = platform.shape.y >= position.getY() && platform.shape.y <= position.getY() + size.getY();
            if (isCollideX && isCollideY) {
                float deltaPosY = velocity.getY() * delta;
                float lastTickFootPosY = position.getY() + size.getY() - deltaPosY;
                if (lastTickFootPosY <= platform.shape.y) {
                    position.set(position.getX(), platform.shape.y - size.getY());
                    velocity.set(velocity.getX(), 0);
                }
                return true;
            }
            return false;
        }));
        EventBus.instance.publish(new CollectionTraversalEvent<Bullet>(
                GameScene.ListType.BULLET,
                bullet -> {
                    if (!bullet.isValid() || !bullet.getTargetID().equals(ID)) return false;
                    if (bullet.checkCollision(position, size)) {
                        bullet.onCollide();
                        bullet.setValid(false);
                        hp -= bullet.getDamage();
                    }
                    return false;
                }
        ));
    }

    @Override
    public void draw(Graphics2D g) {
        currentAnimation.draw(g, position);
    }

    @Override
    public void keyReleased(int keyCode) {
        switch (ID) {
            case PLAYER_1 -> {
                switch (keyCode) {
                    case KeyEvent.VK_A -> {
                        goLeftDown = 0;
                    }
                    case KeyEvent.VK_D -> {
                        goRightDown = 0;
                    }
                }
            }
            case PLAYER_2 -> {
                switch (keyCode) {
                    case KeyEvent.VK_LEFT -> {
                        goLeftDown = 0;
                    }
                    case KeyEvent.VK_RIGHT -> {
                        goRightDown = 0;
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
                        goLeftDown = 1;
                    }
                    case KeyEvent.VK_D -> {
                        goRightDown = 1;
                    }
                    case KeyEvent.VK_W -> {
                        isJumping = true;
                    }
                    case KeyEvent.VK_F -> {
                        if (canAttack) {
                            attack();
                            canAttack = false;
                            attackCdTimer.reset();
                        }
                    }
                    case KeyEvent.VK_G -> {
                        if (mp >= 100) {
                            attackEx();
                            if (Main.DEBUG) return;
                            mp = 0;
                        }
                    }
                }
            }
            case PLAYER_2 -> {
                switch (keyCode) {
                    case KeyEvent.VK_LEFT -> {
                        goLeftDown = 1;
                    }
                    case KeyEvent.VK_RIGHT -> {
                        goRightDown = 1;
                    }
                    case KeyEvent.VK_UP -> {
                        isJumping = true;
                    }
                    case KeyEvent.VK_PERIOD -> {
                        if (canAttack) {
                            attack();
                            canAttack = false;
                            attackCdTimer.reset();
                        }
                    }
                    case KeyEvent.VK_SLASH -> {
                        if (mp >= 100) {
                            attackEx();
                            if (Main.DEBUG) return;
                            mp = 0;
                        }
                    }
                }
            }
        }

        if (Main.DEBUG) {
            switch (keyCode) {
                case KeyEvent.VK_1 -> {
                    mp += 100;
                }
            }
        }
    }

    public static void setScene(Scene scene) {
        SCENE = (GameScene) scene;
    }
}
