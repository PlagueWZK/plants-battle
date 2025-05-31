package ind.plague.pvz.element.bullet;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.core.Camera;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: SunBullet
 * date: 2025/5/28 18:58
 */

public class SunBullet extends Bullet {
    private static final float gravity = 1e-3f;
    Animation idleAnimation = new Animation(ResourceGetter.ATLAS_BULLET_SUN_IDLE, 50, true);
    Animation explodeAnimation = new Animation(ResourceGetter.ATLAS_BULLET_SUN_EXPLODE, 50, false, () -> canRemove = true);
    Vector2 explodeRenderOffset = new Vector2();


    public SunBullet() {
        size.set(96, 96);
        damage = 20;
        explodeRenderOffset.set((idleAnimation.getFrame().getWidth() - explodeAnimation.getFrame().getWidth()) / 2f,
                (idleAnimation.getFrame().getHeight() - explodeAnimation.getFrame().getHeight()) / 2f);
    }

    @Override
    public void onCollide() {
        super.onCollide();
        Camera.camera.shake(5, 250);
        ResourceGetter.AUDIO_BULLET_SUN_EXPLODE.play(false);
    }

    @Override
    public void update(long deltaTime) {
        if (valid) {
            int delta = (int) (deltaTime / 1000_000);
            velocity.y += gravity * delta;
            position.add(velocity.mulTemp((float) delta));
            idleAnimation.update(deltaTime);
        } else {
            explodeAnimation.update(deltaTime);
        }
        if (checkOutBounds()) {
            canRemove = true;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        if (valid) {
            idleAnimation.draw(g, position);
        } else {
            explodeAnimation.draw(g, position.addTemp(explodeRenderOffset));
        }
    }
}
