package com.nibado.graph.world;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NavMesh {
    private List<NavMeshPoly> _polygons;

    @JsonProperty("polygons")
    public List<NavMeshPoly> getPolygons() {
        return _polygons;
    }

    public void setPolygons(final List<NavMeshPoly> polygons) {
        _polygons = polygons;
    }

    public void compile() {
        final List<NavMeshPoint> points = new ArrayList<>();

        for(final NavMeshPoly poly: _polygons) {
            for (int i = 0; i < poly.getPoints().size(); i++) {
                final int index = points.indexOf(poly.getPoints().get(i));
                if(index >= 0) {
                    poly.getPoints().set(i, points.get(index));
                }
                else {
                    points.add(poly.getPoints().get(i));
                }
            }
        }

    }

    public NavMeshPoly newPoly() {
        final NavMeshPoly poly = new NavMeshPoly();
        poly.setPoints(new ArrayList<NavMeshPoint>());
        _polygons.add(poly);
        return poly;
    }
}
