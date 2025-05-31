package ind.plague.pvz.scene.scenes;


import ind.plague.pvz.Main;
import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.core.GameFrame;
import ind.plague.pvz.element.Platform;
import ind.plague.pvz.element.bullet.Bullet;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.event.GameEventListener;
import ind.plague.pvz.event.events.*;
import ind.plague.pvz.role.roles.Peashooter;
import ind.plague.pvz.role.roles.PlayerID;
import ind.plague.pvz.role.roles.Role;
import ind.plague.pvz.role.roles.Sunflower;
import ind.plague.pvz.scene.SceneType;
import ind.plague.pvz.ui.status_bar.StatusBar;
import ind.plague.pvz.util.GameUtil;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Timer;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author PlagueWZK
 * description: GameScene
 * date: 2025/5/13 23:34
 */

public class GameScene extends BasicScene implements GameEventListener {

    private static final float winnerBarSlideSpeed = 3e-6f;
    private static final float winnerTextSlideSpeed = 1.5e-6f;
    private final Sticker hills = ResourceGetter.IMAGE_HILLS;
    private final Vector2 hillsPosition = GameUtil.getCenterDrawPosition(hills.getImg());
    private final Sticker sky = ResourceGetter.IMAGE_SKY;
    private final Vector2 skyPosition = GameUtil.getCenterDrawPosition(sky.getImg());
    private final HashMap<PlayerID, Role> players = new HashMap<>(2);
    private final HashMap<ListType, Collection<?>> lists = new HashMap<>();
    private final ArrayList<Platform> platforms = new ArrayList<>(4);
    private final List<Bullet> bullets = new CopyOnWriteArrayList<>();
    private final StatusBar statusBar1 = new StatusBar();
    private final StatusBar statusBar2 = new StatusBar();
    private final Sticker winnerBar = new Sticker(ResourceGetter.IMAGE_WINNER_BAR);
    private final Sticker winnerText = new Sticker();
    private final Vector2 winnerBarPosition = new Vector2();
    private final Vector2 winnerTextPosition = new Vector2();
    private final Timer winnerSlideOut = new Timer(1000, false, () -> EventBus.instance.publish(new SceneChangeEvent(SceneType.MENU_SCENE)));
    private boolean isGameOver = false;
    private int winnerBarDstX;
    private int winnerTextDstX;
    private boolean isSlideOutStart = false;
    private final Timer winnerSlideIn = new Timer(2500, false, () -> isSlideOutStart = true);

    {
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_LARGE, new Vector2(122, 455), 60));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(175, 360), 20));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(855, 360), 20));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(515, 225), 20));
        putAllLists(platforms, bullets);
        EventBus.instance.subscribe(this, AddEntityEvent.class, SetPlayerEvent.class, GetPlayerRequest.class, CollectionTraversalEvent.class);
    }


    @Override
    public void mousePressed(int buttonCode) {

    }

    @Override
    public void mouseReleased(int buttonCode) {

    }

    @Override
    public void onEvent(GameEvent event) {
        switch (event) {
            case AddEntityEvent e -> handleAddEvent(e);
            case SetPlayerEvent e -> setPlayer(e);
            case GetPlayerRequest e -> e.setTarget(players.get(e.getRequestID()));
            case CollectionTraversalEvent<?> e -> handleTraversal(e);
            default -> throw new IllegalStateException("Unexpected value: " + event);
        }
    }

    private void handleAddEvent(AddEntityEvent e) {
        if (Objects.requireNonNull(e.entity()) instanceof Bullet b) {
            addBullet(b);
        } else {
            throw new IllegalStateException("Unexpected value: " + e.entity());
        }
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        players.forEach((_, role) -> role.update(deltaTime));
        bullets.removeIf(Bullet::checkCanRemove);
        for (Bullet bullet : bullets) {
            bullet.update(deltaTime);
        }

        Role player1 = players.get(PlayerID.PLAYER_1);
        Role player2 = players.get(PlayerID.PLAYER_2);
        Vector2 position1 = player1.getPosition();
        Vector2 position2 = player2.getPosition();
        if (position1.y > GameFrame.getHeight()) player1.setHp(0);
        if (position2.y > GameFrame.getHeight()) player2.setHp(0);
        if (player1.getHp() <= 0 || player2.getHp() <= 0) {
            if (!isGameOver) {
                ResourceGetter.AUDIO_GAME_BGM.stop();
                ResourceGetter.AUDIO_WIN.play(false);
                winnerText.setImg(player1.getHp() <= 0 ? ResourceGetter.IMAGE_2P_WINNER : ResourceGetter.IMAGE_1P_WINNER);
                isGameOver = true;
            }
        }

        statusBar1.setHp(players.get(PlayerID.PLAYER_1).getHp());
        statusBar1.setMp(players.get(PlayerID.PLAYER_1).getMp());
        statusBar2.setHp(players.get(PlayerID.PLAYER_2).getHp());
        statusBar2.setMp(players.get(PlayerID.PLAYER_2).getMp());

        if (isGameOver) {
            winnerBarPosition.x += winnerBarSlideSpeed * deltaTime;
            winnerTextPosition.x += winnerTextSlideSpeed * deltaTime;
            if (!isSlideOutStart) {
                winnerSlideIn.update(deltaTime);
                if (winnerBarPosition.x > winnerBarDstX) winnerBarPosition.x = winnerBarDstX;
                if (winnerTextPosition.x > winnerTextDstX) winnerTextPosition.x = winnerTextDstX;
            } else {
                winnerSlideOut.update(deltaTime);
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

        players.forEach((_, role) -> role.draw(g));

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
        if (isGameOver) {
            winnerBar.draw(g, winnerBarPosition);
            winnerText.draw(g, winnerTextPosition);
        } else {
            statusBar1.draw(g);
            statusBar2.draw(g);
        }
    }

    @Override
    public void onEnter() {
        isGameOver = false;
        isSlideOutStart = false;

        winnerBarPosition.set(-winnerBar.getImg().getWidth(), GameUtil.getCenterYDrawPosition(winnerBar.getImg()));
        winnerBarDstX = GameUtil.getCenterXDrawPosition(winnerBar.getImg());
        winnerTextPosition.set(winnerBarPosition.x, GameUtil.getCenterYDrawPosition(ResourceGetter.IMAGE_1P_WINNER));
        winnerTextDstX = GameUtil.getCenterXDrawPosition(ResourceGetter.IMAGE_1P_WINNER);

        winnerSlideIn.reset();
        winnerSlideOut.reset();

        players.get(PlayerID.PLAYER_1).setPosition(new Vector2(200, 50));
        players.get(PlayerID.PLAYER_2).setPosition(new Vector2(975, 50));
        ResourceGetter.AUDIO_GAME_BGM.play(true);
    }

    @Override
    public void onExit() {
        players.clear();
        bullets.clear();
        Main.DEBUG = false;

    }

    @Override
    public void keyPressed(int keyCode) {
        players.forEach((_, role) -> role.keyPressed(keyCode));
    }

    @Override
    public void keyReleased(int keyCode) {
        players.forEach((_, role) -> role.keyReleased(keyCode));
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    private void setPlayer(SetPlayerEvent e) {
        players.put(e.id(), switch (e.role()) {
            case PEASHOOTER -> new Peashooter(e.id());
            case SUNFLOWER -> new Sunflower(e.id());
        });
        if (e.id() == PlayerID.PLAYER_1) {
            statusBar1.setAvatar(getAvatar(e.role()));
            statusBar1.setPosition(235, 625);
        } else {
            statusBar2.setAvatar(getAvatar(e.role()));
            statusBar2.setPosition(675, 625);
        }
    }

    private BufferedImage getAvatar(SelectScene.RoleType type) {
        return switch (type) {
            case PEASHOOTER -> ResourceGetter.IMAGE_AVATAR_PEASHOOTER;
            case SUNFLOWER -> ResourceGetter.IMAGE_AVATAR_SUNFLOWER;
        };
    }

    private void putAllLists(Collection<?>... list) {
        for (ListType value : ListType.values()) {
            lists.put(value, list[value.ordinal()]);
        }
    }

    private <T> void handleTraversal(CollectionTraversalEvent<T> event) {
        Collection<?> collection = lists.get(event.listType());
        if (collection == null) return;
        Class<T> type = event.type();
        for (Object o : collection) {
            if (event.predicate().test(type.cast(o))) break;
        }
    }

    public enum ListType {
        PLATFORM, BULLET
    }
}
