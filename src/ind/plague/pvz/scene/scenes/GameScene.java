package ind.plague.pvz.scene.scenes;


import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.core.Camera;
import ind.plague.pvz.element.Platform;
import ind.plague.pvz.role.roles.Peashooter;
import ind.plague.pvz.role.roles.Role;
import ind.plague.pvz.role.roles.Sunflower;
import ind.plague.pvz.util.GameUtil;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * @author PlagueWZK
 * description: GameScene
 * date: 2025/5/13 23:34
 */

public class GameScene extends BasicScene {

    private final Sticker hills = ResourceGetter.IMAGE_HILLS;
    private final Vector2 hillsPosition = GameUtil.getCenterDrawPosition(hills.getImg());
    private final Sticker sky = ResourceGetter.IMAGE_SKY;
    private final Vector2 skyPosition = GameUtil.getCenterDrawPosition(sky.getImg());
    private final ArrayList<Platform> platforms = new ArrayList<>();


    {
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_LARGE, new Vector2(122, 455), 60));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(175, 360)));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(855, 360)));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(515, 225)));
        platforms.trimToSize();
    }


    @Override
    public void mousePressed(int buttonCode) {

    }

    @Override
    public void mouseReleased(int buttonCode) {

    }


    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        for (Role player : players) {
            if (player != null) {
                player.update(deltaTime);
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        sky.draw(g, hillsPosition);
        hills.draw(g, skyPosition);

        for (Platform platform : platforms) {
            platform.draw(g);
        }
        for (Role player : players) {
            if (player != null) {
                player.draw(g);
            }
        }
    }

    @Override
    public void onEnter() {

    }

    @Override
    public void onExit() {

    }

    @Override
    public void keyPressed(int keyCode) {
        for (Role player : players) {
            player.keyPressed(keyCode);
        }
    }

    @Override
    public void keyReleased(int keyCode) {
        for (Role player : players) {
            player.keyReleased(keyCode);
        }
    }
}
