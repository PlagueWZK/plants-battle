package ind.plague.pvz.animation;

import ind.plague.pvz.util.Painter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author PlagueWZK
 * description: Sticker
 * date: 2025/5/15 18:12
 */

public class Sticker {
    private BufferedImage img;

    public Sticker() {

    }

    public Sticker(BufferedImage img) {
        this.img = img;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void draw(Graphics2D g, int x, int y) {
        Painter.draw(g, img, x, y);
    }

    public void draw(Graphics2D g, Vector2 positon) {
        Painter.draw(g, img, positon.getX(), positon.getY());
    }

    public void draw(Graphics2D g, int x, int y, float alpha) {
        Painter.draw(g, img, x, y, alpha);
    }

    public void draw(Graphics2D g, Vector2 positon, float alpha) {
        Painter.draw(g, img, positon.getX(), positon.getY(), alpha);
    }
}
