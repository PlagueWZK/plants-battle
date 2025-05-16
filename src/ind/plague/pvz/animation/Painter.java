package ind.plague.pvz.animation;

import ind.plague.pvz.core.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * @author PlagueWZK
 * description: Painter
 * date: 2025/5/15 17:59
 */

public class Painter {

    private static final HashMap<Float, AlphaComposite> ALPHA_COMPOSITE_CACHE = new HashMap<>();

    public static void draw(Graphics2D g, BufferedImage img, int x, int y) {
        g.drawImage(img, Math.round(x - Camera.camera.getPosition().getX() - Camera.camera.getShakeOffset().getX()), Math.round(y - Camera.camera.getPosition().getY() - Camera.camera.getShakeOffset().getY()), null);
    }

    public static void draw(Graphics2D g, BufferedImage img, int x, int y, float alpha) {
        g.setComposite(getAlphaComposite(alpha));
        draw(g, img, x, y);
        g.setComposite(getAlphaComposite(1.0f));
    }

    public static AlphaComposite getAlphaComposite(float alpha) {
        if (alpha < 0 || alpha > 1) throw new IllegalArgumentException("alpha的值需要满足范围[0,1]");
        return ALPHA_COMPOSITE_CACHE.computeIfAbsent(alpha, (_) -> AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
}
