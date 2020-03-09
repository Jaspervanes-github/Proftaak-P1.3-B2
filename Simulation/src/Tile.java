import java.util.HashMap;

public class Tile {
    private int width;
    private int height;
    private HashMap<Float, Float> position;
    private int distance;

    public Tile(HashMap<Float, Float> position) {
        this.width = 16;
        this.height = 16;
        this.position = position;
        this.distance = 0;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public HashMap<Float, Float> getPosition() {
        return position;
    }

    public void setPosition(HashMap<Float, Float> position) {
        this.position = position;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
