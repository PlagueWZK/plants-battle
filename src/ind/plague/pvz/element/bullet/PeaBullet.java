package ind.plague.pvz.element.bullet;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.util.ResourceGetter;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: PeaBullet
 * date: 2025/5/28 18:57
 */

public class PeaBullet extends Bullet {

    Animation breakAnimation = new Animation(ResourceGetter.ATLAS_BULLET_PEA_BREAK, 100, false, () -> canRemove = true);
    Sticker img = new Sticker(ResourceGetter.IMAGE_BULLET_PEA);

    public PeaBullet() {
        super();
        size.set(64, 64);
        damage = 10;
    }

    @Override
    public void update(long deltaTime) {
        position.add(velocity.mulTemp((float) deltaTime / 1_000_000));
        if (!valid) {
            breakAnimation.update(deltaTime);
        }
        if (checkOutBounds()) {
            canRemove = true;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        if (valid) {
            img.draw(g, position);
        }
    }

    @Override
    public void onCollide() {
        super.onCollide();
        switch ((int) (Math.random() * 3)) {
            case 0 -> {
                ResourceGetter.AUDIO_BULLET_PEA_BREAK_1.play(false);
            }
            case 1 -> {
                ResourceGetter.AUDIO_BULLET_PEA_BREAK_2.play(false);
            }
            case 2 -> {
                ResourceGetter.AUDIO_BULLET_PEA_BREAK_3.play(false);
            }
        }
    }
}
