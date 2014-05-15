package com.nibado.graph.world;

import com.badlogic.gdx.math.Vector2;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NavMeshPoint {
    private final Vector2 _point;

    @JsonCreator
    public NavMeshPoint(@JsonProperty("x") final float x, @JsonProperty("y") final float y) {
        _point = new Vector2(x, y);
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof NavMeshPoint) {
            final Vector2 otherVec = ((NavMeshPoint) o)._point;
            return otherVec.dst2(_point) < 0.01f;
        }
        return false;
    }
}

