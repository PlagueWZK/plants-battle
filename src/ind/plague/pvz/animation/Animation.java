package ind.plague.pvz.animation;

import ind.plague.pvz.util.Painter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author PlagueWZK
 * description: Animation
 * date: 2025/5/14 20:11
 */

public class Animation {

    private int timer;
    private int interval;
    private int frameIndex;
    private boolean loop;

    private Atlas atlas;
    private Runnable callback;

    public Animation(Atlas atlas, int ms, boolean loop, Runnable callback) {
        this.atlas = atlas;
        this.interval = ms;
        this.loop = loop;
        this.callback = callback;
    }

    public Animation(Atlas atlas, int ms, boolean loop) {
        this.atlas = atlas;
        this.interval = ms;
        this.loop = loop;
    }

    public void update(long deltaTime) {
        timer += (int) (deltaTime / 1000_000);
        if (timer >= interval) {
            timer = 0;
            frameIndex++;
            if (frameIndex >= atlas.size()) {
                frameIndex = loop ? 0 : atlas.size() - 1;
                if (!loop && callback != null) callback.run();
            }
        }
    }

    public void draw(Graphics2D g, int x, int y) {
        Painter.draw(g, getFrame(), x, y);
    }
    public void draw(Graphics2D g, Vector2 positon) {
        Painter.draw(g, getFrame(), positon.getX(), positon.getY());
    }

    public void draw(Graphics2D g, int x, int y, float alpha) {
        Painter.draw(g, getFrame(), x, y, alpha);
    }
    public void draw(Graphics2D g, Vector2 positon, float alpha) {
        Painter.draw(g, getFrame(), positon.getX(), positon.getY(), alpha);
    }

    public void reset() {
        timer = 0;
        frameIndex = 0;
    }

    public void setAtlas(Atlas atlas) {
        reset();
        this.atlas = atlas;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public void setInterval(int ms) {
        this.interval = ms;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public BufferedImage getFrame() {
        return atlas.getImage(frameIndex);
    }

    public boolean isFinished() {
        if (loop) return false;
        return frameIndex == atlas.size() - 1;
    }

}
