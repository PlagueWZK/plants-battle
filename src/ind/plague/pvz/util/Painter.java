package ind.plague.pvz.util;

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

    public static void draw(Graphics2D g, BufferedImage img, float x, float y) {
        g.drawImage(img, Math.round(x - Camera.camera.getPosition().getX() - Camera.camera.getShakeOffset().getX()), Math.round(y - Camera.camera.getPosition().getY() - Camera.camera.getShakeOffset().getY()), null);
    }

    public static void draw(Graphics2D g, BufferedImage img, Vector2 position) {
        draw(g, img, position.getX(), position.getY());
    }

    public static void draw(Graphics2D g, BufferedImage img, float x, float y, float alpha) {
        g.setComposite(getAlphaComposite(alpha));
        draw(g, img, x, y);
        g.setComposite(getAlphaComposite(1.0f));
    }

    public static void draw(Graphics2D g, BufferedImage img, Vector2 position, float alpha) {
        draw(g, img, position.getX(), position.getY(), alpha);
    }

    public static AlphaComposite getAlphaComposite(float alpha) {
        if (alpha < 0 || alpha > 1) throw new IllegalArgumentException("alpha的值需要满足范围[0,1]");
        return ALPHA_COMPOSITE_CACHE.computeIfAbsent(alpha, (_) -> AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }

    public static void drawText(Graphics2D g, String text, float x, float y, Color color, float size) {
        g.setFont(GameUtil.getFont(size));
        g.setColor(color);
        g.drawString(text, Math.round(x - Camera.camera.getPosition().getX()), Math.round(y - Camera.camera.getPosition().getY()));
    }

    public static void drawShadedText(Graphics2D g, String text, float x, float y, Color color, float size, Color shadowColor) {
        g.setFont(GameUtil.getFont(size));
        g.setColor(shadowColor);
        g.drawString(text, Math.round(x - Camera.camera.getPosition().getX() + 2), Math.round(y - Camera.camera.getPosition().getY() + 2));
        g.setColor(color);
        g.drawString(text, Math.round(x - Camera.camera.getPosition().getX()), Math.round(y - Camera.camera.getPosition().getY()));
    }

    public static void drawShadedText(Graphics2D g, String text, float x, float y, Color color, float size) {
        drawShadedText(g, text, x, y, color, size, Color.BLACK);
    }

    public static void drawShadedText(Graphics2D g, String text, Vector2 position, Color color, float size) {
        drawShadedText(g, text, position.getX(), position.getY(), color, size, Color.BLACK);
    }
}
