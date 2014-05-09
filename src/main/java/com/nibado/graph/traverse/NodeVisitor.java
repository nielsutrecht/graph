package com.nibado.graph.traverse;

public interface NodeVisitor<T> {
    public void addListener(NodeListener<T> listener);
}
