package ind.plague.pvz.animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * @author PlagueWZK
 * description: Animation
 * date: 2025/5/14 20:11
 */

public class Animation {
    private static final HashMap<Float, AlphaComposite> ALPHA_COMPOSITE_CACHE = new HashMap<>();

    private int timer;
    private int interval;
    private int frameIndex;
    private boolean loop;

    private Atlas atlas;
    private final Runnable callback;

    public Animation(Atlas atlas, int ms, boolean loop, Runnable callback) {
        this.atlas = atlas;
        this.interval = ms;
        this.loop = loop;
        this.callback = callback;
    }

    public void update(long deltaTime) {
        timer += (int) (deltaTime/1000_000);
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
        g.drawImage(getFrame(), x, y, null);
    }

    public void draw(Graphics2D g, int x, int y, float alpha) {
        g.setComposite(getAlphaComposite(alpha));
        draw(g, x, y);
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

    private AlphaComposite getAlphaComposite(float alpha) {
        if (alpha < 0 || alpha > 1) throw new IllegalArgumentException("alpha的值需要满足范围[0,1]");
        return ALPHA_COMPOSITE_CACHE.computeIfAbsent(alpha, (_) -> AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
}
