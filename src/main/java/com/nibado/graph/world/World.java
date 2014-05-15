package com.nibado.graph.world;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class World {
    private List<Wall> _walls;
    private NavMesh _navMesh;

    public World() {

    }

    @JsonProperty("walls")
    public List<Wall> getWalls() {
        return _walls;
    }

    public void setWalls(final List<Wall> walls) {

    }

    @JsonProperty("navmesh")
    public NavMesh getNavMesh() {
        return _navMesh;
    }

    public void setNavMesh(final NavMesh navMesh) {

    }

    public static World loadFrom(final File input) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(input, World.class);
    }


}
