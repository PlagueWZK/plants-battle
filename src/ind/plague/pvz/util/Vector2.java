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

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * 将坐标矢量归一化
     *
     * @return 归一化后的坐标矢量。返回一个新Vector2对象
     */
    public Vector2 normalize() {
        double length = getLength();
        return new Vector2((float) (x / length), (float) (y / length));
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
