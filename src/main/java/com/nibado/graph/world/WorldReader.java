package com.nibado.graph.world;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;


public class WorldReader {
    public World read(final File input) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(input, World.class);
    }

    public World generate() {
        final World world = new World();
        world.getWalls().add(new Wall(0f,0f,800f,10f));
        world.getWalls().add(new Wall(0f,590f,800f,10f));
        world.getWalls().add(new Wall(0f,0f,10f,600f));
        world.getWalls().add(new Wall(790f,0f,10f,600f));
        world.setNavMesh(new NavMesh());


        return world;
    }

    private void addRectPoly(final World world, final float x, final float y, final float width, final float height) {
        //world.getNavMesh().newPoly().addPoint(x, y).addPoint(x + wi, y).addPoint(x, y)
    }
}
