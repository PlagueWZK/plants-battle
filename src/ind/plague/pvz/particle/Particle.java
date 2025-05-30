package ind.plague.pvz.particle;

import ind.plague.pvz.animation.Atlas;
import ind.plague.pvz.util.Painter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;

/**
 * @author PlagueWZK
 * description: Particle
 * date: 2025/5/30 22:59
 */

public class Particle {
    private int timer;
    private int lifespan;
    private int frameIndex;
    private final Vector2 position = new Vector2();
    private boolean valid = true;
    private Atlas atlas;

    public void update(long deltaTime) {
        deltaTime /= 1_000_000;
        timer += (int) deltaTime;
        if (timer >= lifespan) {
            timer = 0;
            frameIndex++;
            if (frameIndex >= atlas.size()) {
                frameIndex = atlas.size() - 1;
                valid = false;
            }
        }
    }

    public void draw(Graphics2D g) {
        Painter.draw(g, atlas.getImage(frameIndex), position);
    }

    public Particle(Vector2 position, Atlas atlas, int lifespan) {
        this.lifespan = lifespan;
        this.atlas = atlas;
        this.position.set(position);
    }

    public Particle(float x, float y, Atlas atlas, int lifespan) {
        this.lifespan = lifespan;
        this.atlas = atlas;
        this.position.set(x, y);
    }

    public void setAtlas(Atlas atlas) {
        this.atlas = atlas;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    public void setPosition(Vector2 position) {
        setPosition(position.getX(), position.getY());
    }
    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }
    public boolean checkValid() {
        return valid;
    }
}
