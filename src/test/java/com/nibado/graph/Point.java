package com.nibado.graph;


public class Point {
    public int X, Y;

    public Point(final int x, final int y) {
        X = x;
        Y = y;
    }

    public Point() {
        this(0, 0);
    }

    @Override
    public String toString() {
        return "{" + X + "," + Y + "}";
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Point) {
            final Point p = (Point) o;
            return p.X == X && p.Y == Y;
        }
        else {
            return false;
        }
    }

    public double distanceTo(final Point other) {
        return Math.sqrt(Math.pow(other.X - X, 2) + Math.pow(other.Y - Y, 2));
    }

}
