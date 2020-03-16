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
    private ArrayList<Tile> finished;
    private ArrayList<Tile> next = new ArrayList<>();
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
        this.finished = new ArrayList<>();
        this.tiledMap = tiledMap;
        this.collision = tiledMap.getLayers().get(0).getCollision();
        System.out.println("Out of constructor");
    }

    public ArrayList<Tile> generateDirectionMap(Tile startPoint) {
        System.out.println("in method generate");
        Tile current = startPoint;
        this.finished.add(startPoint);
        this.toDo.offer(startPoint);
//        System.out.println(this.toDo);
        current.setDirection(Direction.STAY);
        System.out.println("Still no error");

        while (!this.toDo.isEmpty()) {
            current = this.toDo.element();
if(current == null){
    this.toDo.offer(current);
    continue;
}
            this.next.set(0,new Tile(new Point2D.Double(current.getPosition().getX() + current.getWidth(), current.getPosition().getY())));
            this.next.set(1,new Tile(new Point2D.Double(current.getPosition().getX() - current.getWidth(), current.getPosition().getY())));
            this.next.set(2,new Tile(new Point2D.Double(current.getPosition().getX(), current.getPosition().getY() + current.getHeight())));
            this.next.set(3,new Tile(new Point2D.Double(current.getPosition().getX(), current.getPosition().getY() - current.getHeight())));

            if (!contains(this.finished, this.next.get(0)) && this.next.get(0).getPosition().getX()*this.next.get(0).getWidth() < this.tiledMap.getWidth() &&
                    this.next.get(0).getPosition().getY()*this.next.get(0).getHeight() < this.tiledMap.getHeight() && !this.collision.contains(this.next.get(0).getPosition())) {
                this.finished.add(this.next.get(0));
                this.toDo.offer(this.next.get(0));
                this.next.get(0).setDirection(Direction.LEFT);
            }
            if (!contains(this.finished, this.next.get(1)) && this.next.get(1).getPosition().getX()*this.next.get(1).getWidth() < this.tiledMap.getWidth() &&
                    this.next.get(1).getPosition().getY()*this.next.get(1).getHeight() < this.tiledMap.getHeight() && !this.collision.contains(this.next.get(1).getPosition())) {
                this.finished.add(this.next.get(1));
                this.toDo.offer(this.next.get(1));
                this.next.get(1).setDirection(Direction.RIGHT);
            }
            if (!contains(this.finished, this.next.get(2)) && this.next.get(2).getPosition().getX()*this.next.get(2).getWidth() < this.tiledMap.getWidth() &&
                    this.next.get(2).getPosition().getY()*this.next.get(2).getHeight() < this.tiledMap.getHeight() && !this.collision.contains(this.next.get(2).getPosition())) {
                this.finished.add(this.next.get(2));
                this.toDo.offer(this.next.get(2));
                this.next.get(2).setDirection(Direction.DOWN);
            }
            if (!contains(this.finished, this.next.get(3)) && this.next.get(3).getPosition().getX()*this.next.get(3).getWidth() < this.tiledMap.getWidth() &&
                    this.next.get(3).getPosition().getY()*this.next.get(3).getHeight() < this.tiledMap.getHeight() && !this.collision.contains(this.next.get(3).getPosition())) {
                this.finished.add(this.next.get(3));
                this.toDo.offer(this.next.get(3));
                this.next.get(3).setDirection(Direction.UP);
            }
        }
        return this.finished;
    }

    public static boolean contains(final ArrayList<Tile> arrayList, Tile tile) {
        boolean result = false;

        for (Tile i : arrayList) {
            if (i.getPosition() == tile.getPosition()) {
                result = true;
                break;
            }
        }
        return result;
    }
}
