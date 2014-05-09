package com.nibado.graph.traverse;

import com.nibado.graph.Node;

public interface NodeListener<T> {
    void nodeVisited(Node<T> node);
}
