package com.nibado.graph.traverse;

import java.util.List;

import com.nibado.graph.Node;

public interface PathFind<T> {
    List<Node<T>> findPath(final Node<T> from, final Node<T> to);
}
