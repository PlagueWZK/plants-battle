package ind.plague.pvz.util;

/**
 * @author PlagueWZK
 * description: Vector2
 * date: 2025/5/12 16:21
 */

public class Vector2 {
    public static final Vector2 ZERO = new Vector2(0, 0);
    private static final Vector2 temp = new Vector2();

    public float x;
    public float y;


    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        this.x = v.getX();
        this.y = v.getY();
    }

    public Vector2() {
        this.set(ZERO);
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
        this.set(x + vec.x, y + vec.y);
        return this;
    }

    public Vector2 add(float x, float y) {
        this.set(this.x + x, this.y + y);
        return this;
    }

    public Vector2 addNoModify(Vector2 vec) {
        return new Vector2(x + vec.x, y + vec.y);
    }

    public Vector2 addTemp(Vector2 vec) {
        temp.set(x + vec.x, y + vec.y);
        return temp;
    }

    public Vector2 sub(Vector2 vec) {
        this.set(x - vec.x, y - vec.y);
        return this;
    }

    public Vector2 mul(float scale) {
        this.set(x * scale, y * scale);
        return this;
    }

    public Vector2 mulNoModify(float scale) {
        return new Vector2(x * scale, y * scale);
    }

    public Vector2 mulTemp(float scale) {
        temp.set(x * scale, y * scale);
        return temp;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void reset() {
        this.set(ZERO);
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2 v) {
            return v.x == x && v.y == y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vector(" +
                x +
                "," +
                y +
                ")";
    }
}
