package Simulation;

import Simulation.Direction;
import Simulation.TerrainMap;
import Simulation.Tile;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class DirectionMap {

    private Queue<Tile> toDo;
    private Tile[] finished;
    private Tile[] next;
    private TerrainMap tiledMap;
    private ArrayList<Point2D> collision;

    public DirectionMap(TerrainMap tiledMap) {
        this.toDo = new Queue<Tile>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Tile> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Tile tile) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Tile> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean offer(Tile tile) {
                return false;
            }

            @Override
            public Tile remove() {
                return null;
            }

            @Override
            public Tile poll() {
                return null;
            }

            @Override
            public Tile element() {
                return null;
            }

            @Override
            public Tile peek() {
                return null;
            }
        };
        this.finished = new Tile[]{};
        this.next = new Tile[]{};
        this.tiledMap = tiledMap;
        this.collision = tiledMap.getLayers().get(0).getCollision();
    }

    public Tile[] generateDirectionMap(Tile startPoint) {
        Tile current = startPoint;
        this.finished[0] = startPoint;
        this.toDo.offer(startPoint);
        current.setDirection(Direction.STAY);

        while (!this.toDo.isEmpty()) {
            current = this.toDo.element();
            next[0] = new Tile(new Point2D.Double(current.getPosition().getX() + current.getWidth(), current.getPosition().getY()));
            next[1] = new Tile(new Point2D.Double(current.getPosition().getX() - current.getWidth(), current.getPosition().getY()));
            next[2] = new Tile(new Point2D.Double(current.getPosition().getX(), current.getPosition().getY() + current.getHeight()));
            next[3] = new Tile(new Point2D.Double(current.getPosition().getX(), current.getPosition().getY() - current.getHeight()));

//            next[0] = new Point2D.Double(current.getX() + 1, current.getY());
//            next[1] = new Point2D.Double(current.getX() - 1, current.getY());
//            next[2] = new Point2D.Double(current.getX() + 1, current.getY() + 1);
//            next[3] = new Point2D.Double(current.getX() - 1, current.getY() - 1);

            if (!contains(this.finished, this.next[0]) && next[0].getPosition().getX()*next[0].getWidth() < this.tiledMap.getWidth() &&
                    next[0].getPosition().getY()*next[0].getHeight() < this.tiledMap.getHeight() && !this.collision.contains(next[0].getPosition())) {
                this.finished[this.finished.length] = next[0];
                this.toDo.offer(next[0]);
                this.next[0].setDirection(Direction.LEFT);
            }
            if (!contains(this.finished, this.next[1]) && next[1].getPosition().getX()*next[1].getWidth() < this.tiledMap.getWidth() &&
                    next[1].getPosition().getY()*next[1].getHeight() < this.tiledMap.getHeight() && !this.collision.contains(next[1].getPosition())) {
                this.finished[this.finished.length] = next[1];
                this.toDo.offer(next[1]);
                this.next[1].setDirection(Direction.RIGHT);
            }
            if (!contains(this.finished, this.next[2]) && next[2].getPosition().getX()*next[2].getWidth() < this.tiledMap.getWidth() &&
                    next[2].getPosition().getY()*next[2].getHeight() < this.tiledMap.getHeight() && !this.collision.contains(next[2].getPosition())) {
                this.finished[this.finished.length] = next[2];
                this.toDo.offer(next[2]);
                this.next[2].setDirection(Direction.DOWN);
            }
            if (!contains(this.finished, this.next[3]) && next[3].getPosition().getX()*next[3].getWidth() < this.tiledMap.getWidth() &&
                    next[3].getPosition().getY()*next[3].getHeight() < this.tiledMap.getHeight() && !this.collision.contains(next[3].getPosition())) {
                this.finished[this.finished.length] = next[3];
                this.toDo.offer(next[0]);
                this.next[3].setDirection(Direction.UP);
            }
        }
        return this.finished;
    }

    public static boolean contains(final Tile[] array, Tile tile) {
        boolean result = false;

        for (Tile i : array) {
            if (i.getPosition() == tile.getPosition()) {
                result = true;
                break;
            }
        }
        return result;
    }
}
