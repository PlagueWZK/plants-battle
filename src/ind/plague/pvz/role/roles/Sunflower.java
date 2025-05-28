package ind.plague.pvz.role.roles;

import ind.plague.pvz.animation.Animation;
import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.scene.Scene;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Sunflower
 * date: 2025/5/27 22:05
 */

public class Sunflower extends BasicRole {


    public Sunflower(PlayerID id) {
        super(id);
        animationIdleLeft = new Animation(ResourceGetter.ATLAS_SUNFLOWER_IDLE_LEFT, 100, true);
        animationIdleRight = new Animation(ResourceGetter.ATLAS_SUNFLOWER_IDLE_RIGHT, 100, true);
        animationRunLeft = new Animation(ResourceGetter.ATLAS_SUNFLOWER_RUN_LEFT, 100, true);
        animationRunRight = new Animation(ResourceGetter.ATLAS_SUNFLOWER_RUN_RIGHT, 100, true);
        size.set(96,96);
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void attack() {


    }

    @Override
    public void attackEx() {

    }
}
