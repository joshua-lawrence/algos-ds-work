package a05;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.TreeMap;

public class PointST<Value> {

    TreeMap<Point2D, Value> tree;

    public PointST() {
        tree = new TreeMap<>();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public void put(Point2D p, Value val) {
        tree.put(p, val);
    }

    public Value get(Point2D p) {
        return tree.get(p);
    }

    public boolean contains(Point2D p) {
        return tree.containsKey(p);
    }

    public Iterable<Point2D> points() {
        return tree.keySet();
    }

    public Iterable<Point2D> range(RectHV rect) {
        if(rect == null) throw new NullPointerException();
        var topRightBound = new Point2D(rect.xmax(), rect.ymax());
        var botLeftBound = new Point2D(rect.xmin(), rect.ymin());

        return tree.subMap(botLeftBound, true, topRightBound, true).keySet();
    }

    public Point2D nearest(Point2D p) {
        if(p == null) throw new NullPointerException();
        var floor = tree.floorKey(p);
        var ceiling = tree.ceilingKey(p);

        if(floor != null && ceiling != null) {
            if(p.distanceSquaredTo(floor) >= p.distanceSquaredTo(ceiling)) return ceiling;
            else return floor;
        }
        else if(floor == null) {
            return ceiling;
        }
        else return floor;
    }

    public static void main(String[] args) {
        PointST<String> points = new PointST<>();
        points.put(new Point2D(0, 1), "hello");
        points.put(new Point2D(-2, -2), "hello1");
        points.put(new Point2D(2, 1), "hello2");
        points.put(new Point2D(10, 1), "hello2");
        points.put(new Point2D(2, 10), "hello2");
        points.put(new Point2D(2, 2), "hello2");
        points.put(new Point2D(2, 2), "hello2");
        points.put(new Point2D(7, 5), "hello2");

    }
}
