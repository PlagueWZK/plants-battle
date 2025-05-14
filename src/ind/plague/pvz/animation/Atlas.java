package ind.plague.pvz.animation;

import ind.plague.pvz.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * @author PlagueWZK
 * description: Atlas
 * date: 2025/5/14 16:20
 */

public class Atlas {
    private final ArrayList<BufferedImage> images = new ArrayList<>();

    /**
     * 加载同一目录一系列的图片。图片名称要求以数字区分，从1开始
     *
     * @param pathTemplate 图片路径模板, 通过类资源加载
     * @param num          加载图片数量
     */
    public void loadFromFile(String pathTemplate, int num) {
        images.clear();
        images.ensureCapacity(num);
        for (int i = 0; i < num; i++) {
            String pathFile = String.format(pathTemplate, i + 1); // 生成文件路径
            images.add(ImageUtil.loadImage(pathFile));
        }
    }

    public Atlas flipAtlas() {
        Atlas atlas = new Atlas();
        for (int i = 0; i < size(); i++) {
            atlas.addImage(ImageUtil.flipImage(getImage(i)));
        }
        return atlas;
    }

    public int size() {
        return images.size();
    }

    public BufferedImage getImage(int index) {
        if (index < 0 || index >= images.size()) {
            return null;
        }
        return images.get(index);
    }

    public void addImage(BufferedImage image) {
        images.add(image);
    }
}
