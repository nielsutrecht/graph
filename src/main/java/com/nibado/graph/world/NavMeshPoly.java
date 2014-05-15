package com.nibado.graph.world;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NavMeshPoly {
    private List<NavMeshPoint> _points;

    @JsonProperty("points")
    public List<NavMeshPoint> getPoints() {
        return _points;
    }

    public void setPoints(final List<NavMeshPoint> points) {
        _points = points;
    }

    public NavMeshPoly addPoints(final NavMeshPoint... points) {
        for (final NavMeshPoint p : points) {
            _points.add(p);
        }

        return this;
    }

    public NavMeshPoly addPoint(final float x, final float y) {
        _points.add(new NavMeshPoint(x, y));

        return this;
    }
}
