package com.nibado.graph;

public class GraphBuilder<T> {
    private Graph<T> _graph;

    public GraphBuilder() {
        this(null);
    }

    public GraphBuilder(final Graph<T> graph) {
        setGraph(graph);
    }

    public Graph<T> getGraph() {
        return _graph;
    }

    public void setGraph(final Graph<T> graph) {
        _graph = graph;
    }

    public GraphBuilder<T> addNode(final Node<T> node) {
        ensureGraph();
        _graph.add(node);

        return this;
    }

    public GraphBuilder<T> addNode(final T value) {
        return addNode(new Node<T>(value));
    }

    public GraphBuilder<T> addNodes(final T[] values) {
        ensureGraph();
        for (final T v : values) {
            _graph.add(new Node<T>(v));
        }
        return this;
    }

    public GraphBuilder<T> connect(final Node<T> from, final Node<T> to) {
        _graph.connect(from, to);
        return this;
    }

    public GraphBuilder<T> connect(final int fromIndex, final int toIndex) {
        final Node<T> from = _graph.getNodes().get(fromIndex);
        final Node<T> to = _graph.getNodes().get(toIndex);
        return connect(from, to);
    }

    public GraphBuilder<T> connect(final int[] fromIndices, final int toIndex) {
        final Node<T> to = _graph.getNodes().get(toIndex);

        for (final int i : fromIndices) {
            final Node<T> from = _graph.getNodes().get(i);
            connect(from, to);
        }

        return this;
    }

    public GraphBuilder<T> connect(final int fromIndex, final int[] toIndices) {
        final Node<T> from = _graph.getNodes().get(fromIndex);

        for (final int i : toIndices) {
            final Node<T> to = _graph.getNodes().get(i);
            connect(from, to);
        }
        return this;
    }

    private void ensureGraph() {
        if (_graph == null) {
            _graph = new Graph<T>();
        }
    }

}
