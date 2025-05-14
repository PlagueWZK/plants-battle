package ind.plague.pvz.util;

/**
 * @author PlagueWZK
 * description: Vector2
 * date: 2025/5/12 16:21
 */

public class Vector2 {
    private float x;
    private float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        this.x = v.getX();
        this.y = v.getY();
    }

    public float getLength() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 将坐标矢量归一化
     *
     * @return 归一化后的坐标矢量。返回一个新Vector2对象
     */
    public Vector2 normalize() {
        float length = getLength();
        if (length == 0) return new Vector2(0, 0);
        return new Vector2(x / length, y / length);
    }

    public Vector2 add(Vector2 vec) {
        return new Vector2(x + vec.x, y + vec.y);
    }

    public Vector2 sub(Vector2 vec) {
        return new Vector2(x - vec.x, y - vec.y);
    }

    public Vector2 mul(Vector2 vec) {
        return new Vector2(x * vec.x, y * vec.y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2 v) {
        this.x = v.getX();
        this.y = v.getY();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
