package com.nibado.graph.traverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.nibado.collections.BitMap;
import com.nibado.graph.Graph;
import com.nibado.graph.Node;

public class DepthFirst<T> implements NodeFind<T>, PathFind<T>, NodeVisitor<T> {

    private final List<NodeListener<T>> _listeners = new ArrayList<NodeListener<T>>();

    public Node<T> find(final Graph<T> graph, final T value) {
        final List<Node<T>> nodes = new ArrayList<Node<T>>(1);
        find(getStart(graph), value, true, new BitMap(graph.getNodes().size()), nodes);
        if (nodes.size() > 0) {
            return nodes.get(0);
        }
        else {
            return null;
        }
    }

    public List<Node<T>> findAll(final Graph<T> graph, final T value) {
        final List<Node<T>> nodes = new ArrayList<Node<T>>(10);

        find(getStart(graph), value, false, new BitMap(graph.getNodes().size()), nodes);

        return nodes;
    }

    private void find(final Node<T> node, final T value, final boolean returnFirst, final BitMap visited, final List<Node<T>> nodes) {
        visit(node);
        visited.set(node.getIndex(), true);
        if (nodes.size() > 0 && returnFirst) {
            return;
        }
        if (node.getValue().equals(value)) {
            nodes.add(node);
            if (returnFirst)
                return;
        }
        for (final Node<T> e : node.getEdges()) {
            if (!visited.get(e.getIndex())) {
                find(e, value, returnFirst, visited, nodes);
            }
        }
    }

    private Node<T> getStart(final Graph<T> graph) {
        return graph.getRoot() == null ? graph.getNodes().get(0) : graph.getRoot();
    }

    public List<Node<T>> findPath(final Node<T> from, final Node<T> to) {
        final LinkedList<Node<T>> path = new LinkedList<Node<T>>();
        final BitMap visited = new BitMap();
        final boolean found = findPath(from, to, visited, path);
        if (found) {
            return path;
        }
        else {
            return null;
        }
    }

    private boolean findPath(final Node<T> from, final Node<T> to, final BitMap visited, final LinkedList<Node<T>> path) {
        visit(from);
        visited.set(from.getIndex(), true);
        if(to == from) {
            path.addFirst(to);
            return true;
        }
        for(final Node<T> e: from.getEdges()) {
            if(!visited.get(e.getIndex())) {
                final boolean found = findPath(e, to, visited, path);
                if (found) {
                    path.addFirst(from);
                    return true;
                }
            }
        }
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
