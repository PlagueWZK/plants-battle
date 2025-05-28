package ind.plague.pvz.element.bullet;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.core.Camera;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: SunBulletEx
 * date: 2025/5/28 18:58
 */

public class SunBulletEx extends Bullet{
    Animation idleAnimation = new Animation(ResourceGetter.ATLAS_BULLET_SUN_EX_IDLE, 50, true);
    Animation explodeAnimation = new Animation(ResourceGetter.ATLAS_BULLET_SUN_EX_EXPLODE, 50, false, () -> canRemove = true);
    Vector2 explodeRenderOffset = new Vector2();


    public SunBulletEx() {
        size = new Vector2(288, 288);
        damage = 20;
        explodeRenderOffset.set((idleAnimation.getFrame().getWidth() - explodeAnimation.getFrame().getWidth()) / 2f,
                (idleAnimation.getFrame().getHeight() - explodeAnimation.getFrame().getHeight()) / 2f);
        velocity = new Vector2(0, 0.28f);
    }

    @Override
    public void onCollide() {
        super.onCollide();
        Camera.camera.shake(20, 350);
        ResourceGetter.AUDIO_BULLET_SUN_EX_EXPLODE.play(false);
    }

    @Override
    public void update(long deltaTime) {
        if (valid) {
            position.add(velocity.mulTemp((float) deltaTime / 1_000_000));
            idleAnimation.update(deltaTime);
        } else {
            explodeAnimation.update(deltaTime);
        }
        if (checkOutBounds()) {
            canRemove = true;
        }
    }

    @Override
    public boolean checkCollision(Vector2 position, Vector2 size) {
        boolean collideX = Math.max(this.position.x + this.size.x, position.x + size.x) - Math.min(this.position.x, position.x) <= this.size.x + size.x;
        boolean collideY = Math.max(this.position.y + this.size.y, position.y + size.y) - Math.min(this.position.y, position.y) <= this.size.y + size.y;
        return collideX && collideY;
    }

    @Override
    public void draw(Graphics2D g) {
        if (valid) {
            idleAnimation.draw(g, position);
        } else {
            explodeAnimation.draw(g, position.addTemp(explodeRenderOffset));
        }
    }
}
