package ind.plague.pvz.scene.scenes;


import ind.plague.pvz.animation.Sticker;
import ind.plague.pvz.element.Platform;
import ind.plague.pvz.element.bullet.Bullet;
import ind.plague.pvz.event.EventBus;
import ind.plague.pvz.event.GameEvent;
import ind.plague.pvz.event.GameEventListener;
import ind.plague.pvz.event.events.AddEntityEvent;
import ind.plague.pvz.event.events.CollectionTraversalEvent;
import ind.plague.pvz.event.events.GetPlayerRequest;
import ind.plague.pvz.event.events.SetPlayerEvent;
import ind.plague.pvz.role.roles.*;
import ind.plague.pvz.util.GameUtil;
import ind.plague.pvz.util.ResourceGetter;
import ind.plague.pvz.util.Vector2;

import java.awt.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

/**
 * @author PlagueWZK
 * description: GameScene
 * date: 2025/5/13 23:34
 */

public class GameScene extends BasicScene implements GameEventListener {

    private final Sticker hills = ResourceGetter.IMAGE_HILLS;
    private final Vector2 hillsPosition = GameUtil.getCenterDrawPosition(hills.getImg());
    private final Sticker sky = ResourceGetter.IMAGE_SKY;
    private final Vector2 skyPosition = GameUtil.getCenterDrawPosition(sky.getImg());

    private final EnumMap<ListType, Collection<?>> lists = new EnumMap<>(ListType.class);
    private final ArrayList<Platform> platforms = new ArrayList<>();
    private final CopyOnWriteArrayList<Bullet> bullets = new CopyOnWriteArrayList<>();
    private final HashMap<PlayerID, Role> players = new HashMap<>(2);

    {
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_LARGE, new Vector2(122, 455), 60));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(175, 360), 20));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(855, 360), 20));
        platforms.add(new Platform(ResourceGetter.IMAGE_PLATFORM_SMALL, new Vector2(515, 225), 20));
        platforms.trimToSize();
        addAllLists(platforms, bullets);
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
        switch (e.entity()) {
            case Bullet b -> addBullet(b);
            default -> throw new IllegalStateException("Unexpected value: " + e.entity());
        }
    }

    @Override
    public void update(long deltaTime) {
        super.update(deltaTime);
        players.forEach((id, role) -> role.update(deltaTime));
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
        players.forEach((_, role) -> role.draw(g));
    }

    @Override
    public void onEnter() {
        BasicRole.setScene(this);
        players.get(PlayerID.PLAYER_1).setPosition(new Vector2(200, 50));
        players.get(PlayerID.PLAYER_2).setPosition(new Vector2(975, 50));

    }

    @Override
    public void onExit() {

    }

    @Override
    public void keyPressed(int keyCode) {
        players.forEach((id, role) -> role.keyPressed(keyCode));
    }

    @Override
    public void keyReleased(int keyCode) {
        players.forEach((id, role) -> role.keyReleased(keyCode));
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    private void setPlayer(SetPlayerEvent e) {
        players.put(e.id(), switch (e.role()) {
            case PEASHOOTER -> new Peashooter(e.id());
            case SUNFLOWER -> new Sunflower(e.id());
        });
    }

    public enum ListType {
        PLATFORM,
        BULLET,
    }

    private void addAllLists(Collection<?>... list) {
        for (ListType value : ListType.values()) {
            lists.put(value, list[value.ordinal()]);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void handleTraversal(CollectionTraversalEvent<T> event) {
        Collection<T> collection = (Collection<T>) lists.get(event.listType());
        if (collection == null) return;
        Consumer<T> consumer = event.consumer();
        for (T o : collection) {
            consumer.accept(o);
        }
    }
}
