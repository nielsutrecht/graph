package com.nibado.graph;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private static final int INITIAL_EDGES_SIZE = 1;
    private int _index;
    private T _value;
    private final List<Node<T>> _edges;

    public Node() {
        this(null);
    }

    public Node(final T value) {
        _value = value;
        _edges = new ArrayList<Node<T>>(INITIAL_EDGES_SIZE);
    }

    protected void setIndex(final int index) {
        _index = index;
    }

    public int getIndex() {
        return _index;
    }

    public T getValue() {
        return _value;
    }

    public void setValue(final T value) {
        this._value = value;
    }

    public List<Node<T>> getEdges() {
        return _edges;
    }

    public void addEdge(final Node<T> edgeTo) {
        _edges.add(edgeTo);
    }

    @Override
    public String toString() {
        return "(" + getIndex() + ":" + getValue().toString() + ")";
    }

}
