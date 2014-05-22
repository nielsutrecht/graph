package com.nibado.graph.traverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.nibado.collections.BitMap;
import com.nibado.graph.Node;

public class AStar<T> implements PathFind<T>, NodeVisitor<T> {
    private final Heuristic<T> _heuristic;
    private final List<NodeListener<T>> _listeners = new ArrayList<NodeListener<T>>();

    public AStar(final Heuristic<T> heuristic) {
        _heuristic = heuristic;
    }

    public List<Node<T>> findPath(final Node<T> from, final Node<T> to) {
        final LinkedList<Node<T>> path = new LinkedList<Node<T>>();
        final BitMap visited = new BitMap();
        final boolean found = findPath(from, to, visited, path, 0.0);
        if (found) {
            return path;
        }
        else {
            return null;
        }
    }

    private boolean findPath(final Node<T> from, final Node<T> to, final BitMap visited, final LinkedList<Node<T>> path, final double pathCost) {
        visit(from);
        visited.set(from.getIndex(), true);
        if (to == from) {
            path.addFirst(to);
            return true;
        }
        int openCount = 0;
        do {
            double minCost = Double.MAX_VALUE;
            double g = 0.0;
            Node<T> minNode = null;
            for (final Node<T> e : from.getEdges()) {
                if (!visited.get(e.getIndex())) {
                    openCount++;
                    g = pathCost + _heuristic.calc(from.getValue(), e.getValue());
                    final double h = _heuristic.calc(e.getValue(), to.getValue());
                    final double f = g + h;

                    if (f < minCost) {
                        minCost = f;
                        minNode = e;

                    }
                }
            }
            final boolean found = findPath(minNode, to, visited, path, g);
            if (found) {
                path.addFirst(from);
                return true;
            }
        } while (openCount > 0);

        return false;
    }

    public void addListener(final NodeListener<T> listener) {
        _listeners.add(listener);
    }

    private void visit(final Node<T> node) {
        for (final NodeListener<T> l : _listeners) {
            l.nodeVisited(node);
        }
    }
}
