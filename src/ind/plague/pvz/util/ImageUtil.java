package ind.plague.pvz.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author PlagueWZK
 * description: ImageUtil
 * date: 2025/5/14 17:00
 */

public class ImageUtil {
    /**
     * 从相对路径中加载图片
     * @param filePath 类资源加载路径
     * @return 路径对应的图片
     */
    public static BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(ImageUtil.class.getResource(filePath)));
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
}
