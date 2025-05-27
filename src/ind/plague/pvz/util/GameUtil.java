package ind.plague.pvz.util;

import ind.plague.pvz.core.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author PlagueWZK
 * description: GameUtil
 * date: 2025/5/14 17:00
 */

public class GameUtil {


    public static final HashMap<Float, Font> fontCache = new HashMap<>();

    public static Font DEFAULT_FONT;

    static {
        try (InputStream is = GameUtil.class.getResourceAsStream("/font/IPix.ttf")) {
            if (is == null) {
                throw new IllegalArgumentException("字体文件无效");
            }
            DEFAULT_FONT = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException("加载字体失败", e);
        }
    }

    /**
     * 从相对路径中加载图片
     *
     * @param filePath 类资源加载路径
     * @return 路径对应的图片
     */
    public static BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(GameUtil.class.getResource(filePath), filePath + "为null"));
        } catch (IOException e) {
            throw new RuntimeException("加载图片失败: " + filePath, e);
        }
    }

    public static BufferedImage scaleImage(BufferedImage image, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, width, height, null);
        return scaledImage;
    }

    /**
     * 加载通过{@code width}和{@code height}缩放的图片
     *
     * @param filePath 图片路径
     * @param width    缩放宽度
     * @param height   缩放高度
     * @return 返回缩放后的图片
     */
    public static BufferedImage loadScaledImage(String filePath, int width, int height) {
        return scaleImage(loadImage(filePath), width, height);
    }

    public static BufferedImage flipImage(BufferedImage image) {
        BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(),
                image.getWidth(), 0, 0, image.getHeight(),
                null);
        return flippedImage;
    }

    public static Font getFont(float size) {
        return fontCache.computeIfAbsent(size, s -> DEFAULT_FONT.deriveFont(Font.BOLD, s));
    }

    /**
     * 获取将图片绘制到面板中间时对应的绘制点坐标
     *
     * @param img 将要绘制的图片
     * @return 返回一个Vector2表示坐标
     */
    public static Vector2 getCenterDrawPosition(BufferedImage img) {
       return new Vector2(getCenterXDrawPosition(img), getCenterYDrawPosition(img));
    }
    public static float getCenterXDrawPosition(BufferedImage img) {
        return GameFrame.getWidth() / 2f - img.getWidth() / 2f;

    }

    public static float getCenterYDrawPosition(BufferedImage img) {
        return GameFrame.getHeight() / 2f - img.getHeight() / 2f;
    }

    /**
     * 水平对称
     *
     * @param v   源绘制点
     * @param img 要得到对称点的图片
     * @return 水平对称点
     */
    public static Vector2 horizontalSymmetry(Vector2 v, BufferedImage img) {
        return new Vector2(GameFrame.getWidth() - v.getX() - img.getWidth(), v.getY());
    }
}
