package ind.plague.pvz.ui.status_bar;

import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.util.Painter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author PlagueWZK
 * description: status_bar
 * date: 2025/5/30 17:51
 */
public class StatusBar {
    private static final int width = 275;
    private final Vector2 position = new Vector2();
    Sticker avatar = new Sticker();
    private int hp;
    private int mp;

    public void draw(Graphics2D g) {
        g.drawImage(avatar.getImg(), (int) position.x, (int) position.y, null);

        g.setColor(Painter.getColor(5, 5, 5));
        g.fillRoundRect((int) (position.x + 100), (int) (position.y + 10), width + 3 * 2, 26, 8, 8);
        g.fillRoundRect((int) (position.x + 100), (int) (position.y + 45), width + 3 * 2, 26, 8, 8);
        g.setColor(Painter.getColor(255, 255, 255));
        g.fillRoundRect((int) (position.x + 100), (int) (position.y + 10), width + 3, 23, 8, 8);
        g.fillRoundRect((int) (position.x + 100), (int) (position.y + 45), width + 3, 23, 8, 8);

        float hpBarWidth = width * Math.max(0, hp) / 100.0f;
        float mpBarWidth = width * Math.min(100, mp) / 100.0f;
        g.setColor(Painter.getColor(197, 61, 67));
        g.fillRoundRect((int) (position.x + 100), (int) (position.y + 10), (int) (hpBarWidth + 3), 23, 8, 8);
        g.setColor(Painter.getColor(83, 131, 195));
        g.fillRoundRect((int) (position.x + 100), (int) (position.y + 45), (int) (mpBarWidth + 3), 23, 8, 8);
    }

    public void setAvatar(BufferedImage img) {
        avatar.setImg(img);
    }

    public void setPosition(Vector2 position) {
        this.position.set(position);
    }

    public void setPosition(int x, int y) {
        this.position.set(x, y);
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
