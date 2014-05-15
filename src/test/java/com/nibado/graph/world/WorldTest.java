package com.nibado.graph.world;

import java.net.URI;
import java.nio.file.Paths;

import org.junit.Test;

public class WorldTest {
    @Test
    public void testRead() throws Exception {
        final URI uri = getClass().getResource("/world2.json").toURI();
        World.loadFrom(Paths.get(uri).toFile());
    }
}
