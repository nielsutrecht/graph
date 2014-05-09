package com.nibado.graph.traverse;

import java.util.List;

import com.nibado.graph.Node;

public class AStar<T> implements PathFind<T> {
    private final Heuristic<T> _heuristic;

    public AStar(final Heuristic<T> heuristic) {
        _heuristic = heuristic;
    }

    public List<Node<T>> findPath(final Node<T> from, final Node<T> to) {
        // TODO Auto-generated method stub
        return null;
    }
}
