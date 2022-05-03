package a05;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeST<Value> {

    private class Node {
        private Point2D p;      // the point
        private Value value;    // the symbol table maps the point to this value
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
    }

    public KdTreeST() {}

    public boolean isEmpty() {
        
    }

    public int size() {

    }

    public void put(Point2D p, Value val) {

    }

    public Value get(Point2D p) {

    }

    public boolean contains(Point2D p) {

    }

    public Iterable<Point2D> points() {

    }

    public Iterable<Point2D> range(RectHV rect) {

    }

    public Point2D nearest(Point2D p) {

    }

    public static void main(String[] args) {

    }
}
