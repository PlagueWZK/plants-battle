package ind.plague.pvz.role.roles;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.element.bullet.Bullet;
import ind.plague.pvz.element.bullet.SunBullet;
import ind.plague.pvz.element.bullet.SunBulletEx;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.events.AddEntityEvent;
import ind.plague.pvz.event.events.GetPlayerRequest;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author PlagueWZK
 * description: Sunflower
 * date: 2025/5/27 22:05
 */

public class Sunflower extends BasicRole {
    Animation animationText = new Animation(ResourceGetter.ATLAS_SUNFLOWER_TEXT, 100, false);
    boolean showText = false;
    final Vector2 sunSpeed = new Vector2(0.25f, -0.5f);
    final float exSunSpeed = 0.15f;

    public Sunflower(PlayerID id) {
        super(id);
        animationIdleLeft = new Animation(ResourceGetter.ATLAS_SUNFLOWER_IDLE_LEFT, 75, true);
        animationIdleRight = new Animation(ResourceGetter.ATLAS_SUNFLOWER_IDLE_RIGHT, 75, true);
        animationRunLeft = new Animation(ResourceGetter.ATLAS_SUNFLOWER_RUN_LEFT, 75, true);
        animationRunRight = new Animation(ResourceGetter.ATLAS_SUNFLOWER_RUN_RIGHT, 75, true);
        animationAttackExLeft = new Animation(ResourceGetter.ATLAS_SUNFLOWER_ATTACK_EX_LEFT, 100, false, ()  -> {
            isExAttack = false;
            showText = false;
         });
        animationAttackExRight = new Animation(ResourceGetter.ATLAS_SUNFLOWER_ATTACK_EX_RIGHT, 100, false, ()  -> {
            isExAttack = false;
            showText = false;
        });
        attackCdTime  = 250;
        size.set(96,96);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        if (showText) {
            BufferedImage text = animationText.getFrame();
            animationText.draw(g, Math.round(position.x - (size.x - text.getWidth()) / 2), Math.round(position.y - text.getHeight()));
        }
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        if (showText) {
            animationText.update(deltaTime);
        }
    }

    @Override
    public void attack() {
        Bullet bullet = new SunBullet();
        Vector2 bulletSize = bullet.getSize();
        bullet.setPosition(position.x + (size.x - bulletSize.x) / 2, position.y);
        bullet.setVelocity(isFacingRight ? sunSpeed.x : -sunSpeed.x, sunSpeed.y);
        bullet.setTargetID(ID == PlayerID.PLAYER_1 ? PlayerID.PLAYER_2 : PlayerID.PLAYER_1);
        bullet.setCallback(() -> mp += 35);
        EventBus.instance.publish(new AddEntityEvent(bullet));
    }

    @Override
    public void attackEx() {
        isExAttack = true;
        showText = true;
        animationText.reset();
        if (isFacingRight) {
            animationAttackExRight.reset();
        } else {
            animationAttackExLeft.reset();
        }

        Bullet bullet = new SunBulletEx();
        Vector2 bulletSize = bullet.getSize();

        GetPlayerRequest request = new GetPlayerRequest(ID == PlayerID.PLAYER_1 ? PlayerID.PLAYER_2 : PlayerID.PLAYER_1);
        Role target = request.getTarget();
        Vector2 targetPosition = target.getPosition();
        Vector2 targetSize = target.getSize();

        bullet.setPosition(targetPosition.x + (targetSize.x - bulletSize.x) / 2, -size.y);
        bullet.setVelocity(0,  exSunSpeed);

        bullet.setTargetID(ID == PlayerID.PLAYER_1 ? PlayerID.PLAYER_2 : PlayerID.PLAYER_1);
        bullet.setCallback(() -> mp += 50);

        EventBus.instance.publish(new AddEntityEvent(bullet));

        ResourceGetter.AUDIO_SUNFLOWER_TEXT.play(false);
    }
}
