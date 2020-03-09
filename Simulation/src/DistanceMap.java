import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class DistanceMap {

    private Queue<Point2D> toDo;
    private Point2D[] distance;
    private Point2D[] next;
    private TerrainMap tiledMap;

    public DistanceMap(TerrainMap tiledMap) {
        this.toDo = new Queue<Point2D>() {
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
            public Iterator<Point2D> iterator() {
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
            public boolean add(Point2D point2D) {
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
            public boolean addAll(Collection<? extends Point2D> c) {
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
            public boolean offer(Point2D point2D) {
                return false;
            }

            @Override
            public Point2D remove() {
                return null;
            }

            @Override
            public Point2D poll() {
                return null;
            }

            @Override
            public Point2D element() {
                return null;
            }

            @Override
            public Point2D peek() {
                return null;
            }
        };
        this.distance = new Point2D[]{};
        this.next = new Point2D[]{};
        this.tiledMap = tiledMap;
    }

    public void generateDistanceMap(Point2D startPoint) {
        Point2D current = startPoint;
        this.distance[0] = startPoint;
        this.toDo.offer(startPoint);
        current.setDistance(0);

        while (!this.toDo.isEmpty()) {
            current = this.toDo.element();
            next[0] = new Point2D.Double(current.getX() + 1, current.getY());
            next[1] = new Point2D.Double(current.getX() - 1, current.getY());
            next[2] = new Point2D.Double(current.getX() + 1, current.getY() + 1);
            next[3] = new Point2D.Double(current.getX() - 1, current.getY() - 1);

            if (!contains(this.distance, this.next[0]) && next[0].getX() < this.tiledMap.getWidth() && next[0].getY() < this.tiledMap.getHeight() && !collisionMap.contains(next[0])) {
                this.distance[this.distance.length] = next[0];
                this.toDo.offer(next[0]);
                this.next[0].setDistance(current.getDistance()+1);
            }
            if (!contains(this.distance, this.next[1]) && next[1].getX() < this.tiledMap.getWidth() && next[1].getY() < this.tiledMap.getHeight() && !collisionMap.contains(next[1])) {
                this.distance[this.distance.length] = next[1];
                this.toDo.offer(next[1]);
                this.next[1].setDistance(current.getDistance()+1);
            }
            if (!contains(this.distance, this.next[2]) && next[2].getX() < this.tiledMap.getWidth() && next[2].getY() < this.tiledMap.getHeight() && !collisionMap.contains(next[2])) {
                this.distance[this.distance.length] = next[2];
                this.toDo.offer(next[2]);
                this.next[2].setDistance(current.getDistance()+1);
            }
            if (!contains(this.distance, this.next[3]) && next[3].getX() < this.tiledMap.getWidth() && next[3].getY() < this.tiledMap.getHeight() && !collisionMap.contains(next[3])) {
                this.distance[this.distance.length] = next[3];
                this.toDo.offer(next[0]);
                this.next[3].setDistance(current.getDistance()+1);
            }
        }
    }

    public static boolean contains(final Point2D[] array, Point2D point) {
        boolean result = false;

        for (Point2D i : array) {
            if (i == point) {
                result = true;
                break;
            }
        }
        return result;
    }
}
