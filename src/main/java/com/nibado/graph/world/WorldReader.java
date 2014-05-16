package com.nibado.graph.world;


public abstract class WorldReader<T> {
    abstract World read(T input) throws Exception;

    protected void addRectPoly(final World world, final float x, final float y, final float width, final float height) {
        world.getNavMesh().newPoly().addPoint(x, y).addPoint(x + width, y).addPoint(x + width, y + height).addPoint(x, y + height);
    }
}
