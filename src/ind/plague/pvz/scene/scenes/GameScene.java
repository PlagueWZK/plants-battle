package ind.plague.pvz.scene.scenes;


import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.element.Platform;
import ind.plague.pvz.element.bullet.Bullet;
import ind.plague.pvz.role.roles.BasicRole;
import ind.plague.pvz.role.roles.Role;
import ind.plague.pvz.util.GameUtil;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

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
    public final CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
    {
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_LARGE, new Vector2(122, 455), 60));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(175, 360), 20));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(855, 360), 20));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(515, 225), 20));
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
        for (Bullet bullet : bullets) {
            bullet.update(deltaTime);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        sky.draw(g, hillsPosition);
        hills.draw(g, skyPosition);

        for (Platform platform : platforms) {
            platform.draw(g);
        }
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        for (Role player : players) {
            if (player != null) {
                player.draw(g);
            }
        }
    }

    @Override
    public void onEnter() {
        BasicRole.setScene(this);
        players[0].setPosition(new Vector2(200, 50));
        players[1].setPosition(new Vector2(975, 50));

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

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }
}
