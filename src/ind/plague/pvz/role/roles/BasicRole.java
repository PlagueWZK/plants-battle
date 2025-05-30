package ind.plague.pvz.role.roles;

import ind.plague.pvz.Main;
import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.animation.Atlas;
import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.element.Platform;
import ind.plague.pvz.element.bullet.Bullet;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.events.CollectionTraversalEvent;
import ind.plague.pvz.particle.Particle;
import ind.plague.pvz.scene.scenes.GameScene;
import ind.plague.pvz.util.GameUtil;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Timer;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * @author PlagueWZK
 * description: BasicRole
 * date: 2025/5/14 19:39
 */

public abstract class BasicRole implements Role {

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

    boolean isInvulnerable = false;
    boolean isShowingSketchFrame = false;
    Timer invulnerableTimer = new Timer(750, false, () -> isInvulnerable = false);
    Timer invulnerableBlinkTimer = new Timer(75, true, () -> isShowingSketchFrame = !isShowingSketchFrame);
    Sticker sketchFrame = new Sticker();

    Vector<Particle> particles = new Vector<>();
    Atlas runEffectAtlas = ResourceGetter.ATLAS_RUN_EFFECT;
    Timer runEffectTimer = new Timer(75, true, () -> {
        BufferedImage frame = runEffectAtlas.getImage(0);
        Particle particle = new Particle((position.x + (size.x - frame.getWidth()) / 2), (position.y + size.y - frame.getHeight()), runEffectAtlas, 45);
        particles.add(particle);
    });
    Timer dieEffectTimer = new Timer(35, true, () -> {
        BufferedImage frame = runEffectAtlas.getImage(0);
        Particle particle = new Particle((position.x + (size.x - frame.getWidth()) / 2), (position.y + size.y - frame.getHeight()), runEffectAtlas, 150);
        particles.add(particle);
    });


    Animation animationIdleLeft;
    Animation animationIdleRight;
    Animation animationRunLeft;
    Animation animationRunRight;
    Animation animationAttackExRight;
    Animation animationAttackExLeft;
    Animation animationDieRight;
    Animation animationDieLeft;
    Animation currentAnimation;

    Sticker cursor1 = new Sticker(ResourceGetter.IMAGE_1P_CURSOR);
    Sticker cursor2 = new Sticker(ResourceGetter.IMAGE_2P_CURSOR);
    boolean showCursor = true;
    Timer cursorTimer = new Timer(2500, false, () -> showCursor = false);
    float alpha = 1.0f;
    Timer alphaTimer = new Timer(200, true, () -> alpha = Math.max(0, alpha - 0.05f));

    Animation jumpEffect = new Animation(ResourceGetter.ATLAS_JUMP_EFFECT, 25, false, () -> showJumpEffect = false);
    Animation landEffect = new Animation(ResourceGetter.ATLAS_LAND_EFFECT, 50, false, () -> showLandEffect = false);
    boolean showJumpEffect = false;
    boolean showLandEffect = false;
    Vector2 jumpEffectPosition = new Vector2();
    Vector2 landEffectPosition = new Vector2();

    Vector2 lastHurtDirection = new Vector2();

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
            if (!isExAttack) {
                isFacingRight = direction > 0;
            }
            currentAnimation = isFacingRight ? animationRunRight : animationRunLeft;

            float distance = direction * runVelocity * deltaTime / 1000000;
            run(distance);
        } else {
            currentAnimation = isFacingRight ? animationIdleRight : animationIdleLeft;
            runEffectTimer.paused();
        }
        if (isExAttack) {
            currentAnimation = isFacingRight ? animationAttackExRight : animationAttackExLeft;
        }
        if (isJumping) {
            jump();
            isJumping = false;
        }
        if (showCursor) {
            alphaTimer.update(deltaTime);
        }
        if (isShowingSketchFrame) {
            sketchFrame.setImg(GameUtil.getSketchImage(currentAnimation.getFrame()));
        }
        if (hp <= 0) {
            dieEffectTimer.update(deltaTime);
            currentAnimation = lastHurtDirection.x < 0 ? animationDieLeft : animationDieRight;
        }
        currentAnimation.update(deltaTime);
        jumpEffect.update(deltaTime);
        landEffect.update(deltaTime);

        attackCdTimer.update(deltaTime);
        invulnerableTimer.update(deltaTime);
        invulnerableBlinkTimer.update(deltaTime);
        runEffectTimer.update(deltaTime);
        cursorTimer.update(deltaTime);


        particles.removeIf(particle -> !particle.checkValid());
        particles.forEach(particle -> particle.update(deltaTime));



        moveAndCollide((int) (deltaTime / 1000000));
    }

    public void run(float distance) {
        if (isExAttack) return;
        position.add(distance, 0);
        runEffectTimer.resume();
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
        showJumpEffect = true;
        jumpEffect.reset();

        BufferedImage frame = jumpEffect.getFrame();
        jumpEffectPosition.set(position.x + (size.x - frame.getWidth()) / 2, position.y + size.y - frame.getHeight());
    }

    public void land() {
        showLandEffect = true;
        landEffect.reset();

        BufferedImage frame = landEffect.getFrame();
        landEffectPosition.set(position.x + (size.x - frame.getWidth()) / 2, position.y + size.y - frame.getHeight());

    }

    public void moveAndCollide(int delta) {
        float lastVelocityY = velocity.getY();
        velocity.add(0, gravity * delta);
        position.add(velocity.mulTemp(delta));
        if (hp <= 0) return;
        if (velocity.getY() > 0) {
            EventBus.instance.publish(new CollectionTraversalEvent<>(GameScene.ListType.PLATFORM, Platform.class, platform -> {
                boolean isCollideX = (Math.max(position.getX() + size.getX(), platform.shape.right) - Math.min(position.getX(), platform.shape.left) <= size.getX() + platform.shape.right - platform.shape.left);
                boolean isCollideY = platform.shape.y >= position.getY() && platform.shape.y <= position.getY() + size.getY();
                if (isCollideX && isCollideY) {
                    float deltaPosY = velocity.getY() * delta;
                    float lastTickFootPosY = position.getY() + size.getY() - deltaPosY;
                    if (lastTickFootPosY <= platform.shape.y) {
                        position.set(position.getX(), platform.shape.y - size.getY());
                        velocity.set(velocity.getX(), 0);
                        if (lastVelocityY != 0) {
                            land();
                        }
                        return true;
                    }
                }
                return false;
            }));
            if (!isInvulnerable) {
                EventBus.instance.publish(new CollectionTraversalEvent<>(
                        GameScene.ListType.BULLET,
                        Bullet.class,
                        bullet -> {
                            if (!bullet.isValid() || !bullet.getTargetID().equals(ID)) return false;
                            if (bullet.checkCollision(position, size)) {
                                makeInvulnerable();
                                bullet.onCollide();
                                bullet.setValid(false);
                                hp -= bullet.getDamage();
                                Vector2 bulletPosition = bullet.getPosition();
                                lastHurtDirection.set(bulletPosition.x - position.x, bulletPosition.y - position.y);
                                if (hp <= 0) {
                                    velocity.x = lastHurtDirection.x < 0 ? 0.35f : -0.35f;
                                    velocity.y = -1f;
                                }
                            }
                            return false;
                        }
                ));
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (showJumpEffect) {
            jumpEffect.draw(g, jumpEffectPosition);
        }
        if (showLandEffect) {
            landEffect.draw(g, landEffectPosition);
        }

        particles.forEach(particle -> particle.draw(g));

        if (hp > 0 && isInvulnerable && isShowingSketchFrame) {
            sketchFrame.draw(g, position);
        } else {
            currentAnimation.draw(g, position);
        }
        if (showCursor) {
            switch (ID) {
                case PLAYER_1 -> {
                    cursor1.draw(g, (int) (position.x + (size.x - cursor1.getImg().getWidth()) / 2), (int) (position.y - cursor1.getImg().getHeight()), alpha);
                }
                case PLAYER_2 -> {
                    cursor2.draw(g, (int) (position.x + (size.x - cursor2.getImg().getWidth()) / 2), (int) (position.y - cursor2.getImg().getHeight()), alpha);
                }
            }
        }
        if (Main.DEBUG) {
            g.setColor(Color.RED);
            g.drawRect((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY());
        }

    }

    public void makeInvulnerable() {
        isInvulnerable = true;
        invulnerableTimer.reset();
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
                case KeyEvent.VK_1 -> mp += 100;
            }
        }
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMp() {
        return mp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }
}
