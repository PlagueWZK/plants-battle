package ind.plague.pvz;

import ind.plague.pvz.util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

/**
 * @author PlagueWZK
 * description: Test
 * date: 2025/5/14 16:50
 */

public class Test {
    public static void main(String[] args) throws Exception {
        BufferedImage i = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = i.createGraphics();

        AlphaComposite a0 = (AlphaComposite) g2d.getComposite();
        AlphaComposite a1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
        AlphaComposite a2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        AlphaComposite a3 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        System.out.println(a0);
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);
        System.out.println(a0.equals(a1));
        System.out.println(a0.equals(a2));
        System.out.println(a1.equals(a2));
    }
}
