package ind.plague.pvz.role.roles;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.core.Camera;
import ind.plague.pvz.element.bullet.Bullet;
import ind.plague.pvz.element.bullet.PeaBullet;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.events.AddEntityEvent;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Timer;
import ind.plague.pvz.util.Vector2;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Peashooter
 * date: 2025/5/27 22:02
 */

public class Peashooter extends BasicRole {
    private final float peaSpeed = 0.75f;
    private final float peaExSpeed = 1.5f;

    private final Timer exAttackTimer = new Timer(2500, false, () -> isExAttack = false);
    private final Timer spawnPeaTimer = new Timer(100, true, () -> spawnPeaBullet(peaExSpeed));

    public Peashooter(PlayerID id) {
        super(id);
        animationIdleLeft = new Animation(ResourceGetter.ATLAS_PEASHOOTER_IDLE_LEFT, 75, true);
        animationIdleRight = new Animation(ResourceGetter.ATLAS_PEASHOOTER_IDLE_RIGHT, 75, true);
        animationRunLeft = new Animation(ResourceGetter.ATLAS_PEASHOOTER_RUN_LEFT, 75, true);
        animationRunRight = new Animation(ResourceGetter.ATLAS_PEASHOOTER_RUN_RIGHT, 75, true);
        animationAttackExRight = new Animation(ResourceGetter.ATLAS_PEASHOOTER_ATTACK_EX_RIGHT, 75, true);
        animationAttackExLeft = new Animation(ResourceGetter.ATLAS_PEASHOOTER_ATTACK_EX_LEFT, 75, true);
        size.set(96, 96);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        if (isExAttack) {
            Camera.camera.shake(2, 100);
            exAttackTimer.update(deltaTime);
            spawnPeaTimer.update(deltaTime);
        }
    }

    @Override
    public void attack() {
        if (isExAttack) return;
        spawnPeaBullet(peaSpeed);
        switch ((int)(Math.random() * 2)) {
            case 0 -> {
                ResourceGetter.AUDIO_PEASHOOTER_ATTACK_1.play(false);
            }
            case 1 -> {
                ResourceGetter.AUDIO_PEASHOOTER_ATTACK_2.play(false);
            }
        }
    }

    @Override
    public void attackEx() {
        isExAttack = true;
        exAttackTimer.reset();
        ResourceGetter.AUDIO_PEASHOOTER_ATTACK_EX.play(false);
    }

    private void spawnPeaBullet(float speed) {
        Bullet bullet = new PeaBullet();
        Vector2 bulletSize = bullet.getSize();
        Vector2 bulletPositon = bullet.getPosition();
        Vector2 bulletVelocity = bullet.getVelocity();
        bulletPositon.x = isFacingRight ? position.x + size.x - bulletSize.x / 2 : position.x - bulletSize.x / 2;
        bulletPositon.y = position.y;
        bulletVelocity.x = isFacingRight ? speed : -speed;
        bulletVelocity.y = 0;

        bullet.setTargetID(ID == PlayerID.PLAYER_1 ? PlayerID.PLAYER_2 : PlayerID.PLAYER_1);
        bullet.setCallback(() -> mp += 25);
        EventBus.instance.publish(new AddEntityEvent(bullet));
    }
}
