package Simulation;

import java.awt.geom.Point2D;
import java.util.*;

public class DirectionMap {

    private Queue<Tile> toDo;
    private ArrayList<Tile> finished;
    private ArrayList<Tile> next = new ArrayList<>();
    private TerrainMap tiledMap;
    private ArrayList<Point2D> collision;

    public DirectionMap(TerrainMap tiledMap) {
        this.toDo = new LinkedList<>();
        this.finished = new ArrayList<>();
        this.tiledMap = tiledMap;
        this.collision = tiledMap.getLayers().get(0).getCollision();

        this.next.add(new Tile());
        this.next.add(new Tile());
        this.next.add(new Tile());
        this.next.add(new Tile());

    }

    public ArrayList<Tile> generateDirectionMap(Tile startPoint) {
        ArrayList<Tile> tiles;
        Tile current = startPoint;
        this.finished.add(startPoint);
        this.toDo.add(startPoint);
        current.setDirection(Direction.STAY);

        while (!this.toDo.isEmpty() && this.toDo.size() > 0) {
            current = this.toDo.element();

            if (current == null && current != startPoint) {
                this.toDo.add(current);
                continue;
            }

            this.next.set(0, new Tile(new Point2D.Double(current.getPosition().getX() + current.getWidth(), current.getPosition().getY())));
            this.next.set(1, new Tile(new Point2D.Double(current.getPosition().getX() - current.getWidth(), current.getPosition().getY())));
            this.next.set(2, new Tile(new Point2D.Double(current.getPosition().getX(), current.getPosition().getY() + current.getHeight())));
            this.next.set(3, new Tile(new Point2D.Double(current.getPosition().getX(), current.getPosition().getY() - current.getHeight())));

            if (!contains(this.finished, this.next.get(0)) && this.next.get(0).getPosition().getX() < this.tiledMap.getWidth() * this.tiledMap.getTileWidth() &&
                    this.next.get(0).getPosition().getY() < this.tiledMap.getHeight() * this.tiledMap.getTileHeight()
                    && this.next.get(0).getPosition().getX() >= 0 && this.next.get(0).getPosition().getY() >= 0 && !checkCollision(this.next.get(0))) {
                this.next.get(0).setDirection(Direction.LEFT);
                this.finished.add(this.next.get(0));
                this.toDo.add(this.next.get(0));
            }
            if (!contains(this.finished, this.next.get(1)) && this.next.get(1).getPosition().getX() < this.tiledMap.getWidth() * this.tiledMap.getTileWidth() &&
                    this.next.get(1).getPosition().getY() < this.tiledMap.getHeight() * this.tiledMap.getTileHeight() &&
                    this.next.get(1).getPosition().getX() >= 0 && this.next.get(1).getPosition().getY() >= 0 && !checkCollision(this.next.get(1))) {
                this.next.get(1).setDirection(Direction.RIGHT);
                this.finished.add(this.next.get(1));
                this.toDo.add(this.next.get(1));

            }
            if (!contains(this.finished, this.next.get(2)) && this.next.get(2).getPosition().getX() < this.tiledMap.getWidth() * this.tiledMap.getTileWidth() &&
                    this.next.get(2).getPosition().getY() < this.tiledMap.getHeight() * this.tiledMap.getTileHeight()
                    && this.next.get(2).getPosition().getX() >= 0 && this.next.get(2).getPosition().getY() >= 0 && !checkCollision(this.next.get(2))) {
                this.next.get(2).setDirection(Direction.UP);
                this.finished.add(this.next.get(2));
                this.toDo.add(this.next.get(2));

            }
            if (!contains(this.finished, this.next.get(3)) && this.next.get(3).getPosition().getX() < this.tiledMap.getWidth() * this.tiledMap.getTileWidth() &&
                    this.next.get(3).getPosition().getY() < this.tiledMap.getHeight() * this.tiledMap.getTileHeight()
                    && this.next.get(3).getPosition().getX() >= 0 && this.next.get(3).getPosition().getY() >= 0 && !checkCollision(this.next.get(3))) {
                this.next.get(3).setDirection(Direction.DOWN);
                this.finished.add(this.next.get(3));
                this.toDo.add(this.next.get(3));

            }
            this.toDo.poll();

        }
        tiles = new ArrayList<>(this.finished);
        this.finished.clear();
        this.next.clear();
        this.next.add(new Tile());
        this.next.add(new Tile());
        this.next.add(new Tile());
        this.next.add(new Tile());
        this.toDo.clear();
        return tiles;
    }

    public boolean checkCollision(Tile tile) {
        for (Point2D c : this.collision) {
            if (tile.getPosition().getX() >= c.getX()*16 && tile.getPosition().getX() <= c.getX()*16 + 16
                    && tile.getPosition().getY() <= c.getY()*16 && tile.getPosition().getY() >= c.getY()*16 - 16) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(final ArrayList<Tile> arrayList, Tile tile) {
        boolean result = false;

        for (Tile i : arrayList) {
            if (i.getPosition().getX() == tile.getPosition().getX() && i.getPosition().getY() == tile.getPosition().getY()) {
                result = true;
                break;
            }
        }
        return result;
    }
}