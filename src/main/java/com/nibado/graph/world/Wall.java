package com.nibado.graph.world;

import com.badlogic.gdx.math.Rectangle;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Wall {
    private final Rectangle _rect;

    public Wall(final Rectangle rect) {
        _rect = rect;
    }

    public Wall(@JsonProperty("x") final float x, @JsonProperty("y") final float y, @JsonProperty("width") final float width, @JsonProperty("height") final float height) {
        _rect = new Rectangle(x, y, width, height);
    }

    public Rectangle getRectangle() {
        return _rect;
    }
}
