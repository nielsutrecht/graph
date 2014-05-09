package com.nibado.collections;

import java.util.ArrayList;
import java.util.List;

public class QuadTree<T extends Point> {

    public static final int MIN_SIZE = 16;
    private List<QuadTree<T>> _children;
    private QuadTree<T> _parent;
    private List<T> _values;
    private Bounds _bounds;

    private QuadTree() {
        _values = new ArrayList<T>();
    }

    public QuadTree(final int size) {
        this();
        _children = null;
        _bounds = new Bounds(0, 0, nearestPowerOfTwo(size));
        _parent = null;
    }

    private QuadTree(final QuadTree<T> parent, final Bounds bounds) {
        this();
        _bounds = bounds;
        _parent = parent;
    }

    public void add(final T value) {
        if(_bounds.getSize() == MIN_SIZE) {
            _values.add(value);
        }
        else {
            if(_children == null) {
                subDivide();
            }
            _children.get(_bounds.getIndex(value)).add(value);
        }
    }

    private QuadTree<T> getRoot() {
        if (_parent == null) {
            return this;
        }
        else {
            return _parent.getRoot();
        }
    }

    private void subDivide() {
        _children = new ArrayList<QuadTree<T>>(4);
        final int x = _bounds._x;
        final int y = _bounds._y;
        final int hSize = _bounds._size / 2;
        _children.add(new QuadTree<T>(this, new Bounds(x, y, hSize)));
        _children.add(new QuadTree<T>(this, new Bounds(x + hSize, y, hSize)));
        _children.add(new QuadTree<T>(this, new Bounds(x, y + hSize, hSize)));
        _children.add(new QuadTree<T>(this, new Bounds(x + hSize, y + hSize, hSize)));

    }



    private static final int nearestPowerOfTwo(final int num) {
        int size = MIN_SIZE;
        while (size < num) {
            size = size * size;
        }

        return size;
    }

    private class Bounds {
        private final int _x;
        private final int _y;
        private final int _size;

        public Bounds(final int x, final int y, final int size) {
            _x = x;
            _y = y;
            _size = size;
        }

        public boolean inside(final Point value) {
            return (value.getX() >= _x && value.getX() <= (_x + _size) && value.getY() >= _y && value.getY() <= (_y + _size));
        }

        public int getIndex(final Point value) {
            int index = 0;
            if (value.getX() > (_x + _size / 2)) {
                index++;
            }
            if (value.getY() > (_y + _size / 2)) {
                index += 2;
            }
            return index;
        }

        public int getSize() {
            return _size;
        }
    }
}
