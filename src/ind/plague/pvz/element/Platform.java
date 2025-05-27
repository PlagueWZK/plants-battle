package ind.plague.pvz.element;

import ind.plague.pvz.Main;
import ind.plague.pvz.util.Painter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author PlagueWZK
 * description: Platform
 * date: 2025/5/27 19:16
 */

public class Platform {
    public static class collisionShape {
        public int y;
        public int left, right;
        public collisionShape(int y, int left, int right) {
            this.y = y;
            this.left = left;
            this.right = right;
        }
        public void draw(Graphics2D g) {
            g.setColor(Color.RED);
            g.drawLine(left, y, right, y);
        }
    }

    private final collisionShape shape;
    private final BufferedImage img;
    private final Vector2 positon;


    public Platform(BufferedImage img, Vector2 positon) {
        this.shape = new collisionShape(Math.round(positon.getY() + 20), Math.round(positon.getX()), Math.round(positon.getX() + img.getWidth()));
        this.img = img;
        this.positon = positon;
    }
    public Platform(BufferedImage img, Vector2 positon, int dy) {
        this.shape = new collisionShape(Math.round(positon.getY() + dy), Math.round(positon.getX()), Math.round(positon.getX() + img.getWidth()));
        this.img = img;
        this.positon = positon;
    }

    public void draw(Graphics2D g) {
        Painter.draw(g, img, positon);
        if (Main.DEBUG) {
            shape.draw(g);
        }
    }
}
