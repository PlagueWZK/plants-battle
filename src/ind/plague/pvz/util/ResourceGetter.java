package ind.plague.pvz.util;

import ind.plague.pvz.animation.Sticker;

import java.awt.image.BufferedImage;

/**
 * @author PlagueWZK
 * description: ResourceGetter
 * date: 2025/5/15 17:51
 */

public class ResourceGetter {
    public static final Sticker IMAGE_MENU_BACKGROUND = newSticker("/image/scene/menu_background.png");
    public static final Sticker IMAGE_SELECTOR_BACKGROUND = newSticker("/image/scene/selector_background.png");


    private static Sticker newSticker(String path) {
        return new Sticker(ImageUtil.loadImage(path));
    }
}
