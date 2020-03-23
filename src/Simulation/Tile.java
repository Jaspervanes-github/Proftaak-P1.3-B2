package Simulation;


import java.awt.geom.Point2D;

public class Tile {
    private int width;
    private int height;
    private Point2D position;
    private Direction direction;

    public Tile(Point2D position) {
        this.width = 16;
        this.height = 16;
        this.position = position;
        this.direction = Direction.UP;
    }
    public Tile(){
        this.width = 16;
        this.height = 16;
        this.position = null;
        this.direction = null;
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

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
