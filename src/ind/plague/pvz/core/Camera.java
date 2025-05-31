package ind.plague.pvz.core;

import ind.plague.pvz.util.Timer;
import ind.plague.pvz.util.Vector2;

/**
 * @author PlagueWZK
 * description: Camera
 * date: 2025/5/14 23:41
 */
@SuppressWarnings("unused")
public class Camera {
    public final static Camera camera;

    static {
        camera = new Camera();
    }

    private final Timer shakeTimer;
    private final Vector2 shakeOffset;
    private final Vector2 position;
    private final Vector2 velocity;
    private final Vector2 moveDistance;
    private boolean isShaking = false;
    private float shakeStrength;

    {
        shakeOffset = new Vector2(0, 0);
        shakeTimer = new Timer(false, () -> {
            isShaking = false;
            shakeOffset.reset();
        });
        position = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
        moveDistance = new Vector2(0, 0);
    }

    private Camera() {
    }

    public void update(long deltaTime) {
        move(deltaTime);
        shakeTimer.update(deltaTime);
        if (isShaking) {
            shakeOffset.set((float) (Math.random() * 2 - 1) * shakeStrength, (float) (Math.random() * 2 - 1) * shakeStrength);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getShakeOffset() {
        return shakeOffset;
    }

    public void shake(float strength, int ms) {
        isShaking = true;
        shakeStrength = strength;
        shakeTimer.setInterval(ms);
        shakeTimer.reset();
    }

    public void reset() {
        position.set(0, 0);
    }

    public void move(long deltaTime) {
        if (velocity.equals(Vector2.ZERO)) return;
        deltaTime /= 1000_000;
        moveDistance.set(velocity.getX() * deltaTime, velocity.getY() * deltaTime);
        position.add(moveDistance);
    }
}
