package com.nibado.graph.traverse;

import java.util.ArrayList;
import java.util.List;

import com.nibado.graph.Graph;
import com.nibado.graph.Node;

public class DepthFirst<T> implements GraphFind<T> {

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
        visited.set(node.getIndex(), true);
        if (node.getValue().equals(value)) {
            nodes.add(node);
            if (returnFirst)
                return;
        }
        for (final Node<T> e : node.getEdges()) {
            if (!visited.get(node.getIndex())) {
                find(e, value, returnFirst, visited, nodes);
            }
        }
    }

    private Node<T> getStart(final Graph<T> graph) {
        return graph.getRoot() == null ? graph.getNodes().get(0) : graph.getRoot();
    }

}
