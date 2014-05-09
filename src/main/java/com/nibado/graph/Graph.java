package com.nibado.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph<T> {
    private final List<Node<T>> _nodes;
    private Node<T> _root;

    public Graph() {
        _nodes = new ArrayList<Node<T>>();
    }

    public void add(final Node<T> node) {
        _nodes.add(node);
        node.setIndex(_nodes.size() - 1);
    }

    public void connect(final Node<T> from, final Node<T> to) {
        from.addEdge(to);
    }

    public Node<T> getRoot() {
        return _root;
    }

    public void setRoot(final Node<T> root) {
        _root = root;
    }

    public List<Node<T>> getNodes() {
        return _nodes;
    }

}

