package com.nibado.graph.traverse;

import java.util.List;

import com.nibado.graph.Graph;
import com.nibado.graph.Node;

public interface GraphFind<T> {
    Node<T> find(Graph<T> graph, T value);

    List<Node<T>> findAll(Graph<T> graph, T value);
}
