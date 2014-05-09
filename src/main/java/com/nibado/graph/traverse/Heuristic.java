package com.nibado.graph.traverse;

public interface Heuristic<T> {
    double calc(T from, T to);
}
