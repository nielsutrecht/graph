package com.nibado.graph.world;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class WorldTest {
    @Test
    public void testCompile() throws Exception {
        final World world = SvgWorldReaderTest.readWorld();
        final int first = countPoints(world.getNavMesh());
        world.getNavMesh().compile();
        final int second = countPoints(world.getNavMesh());
        assertEquals(32, first);
        assertEquals(16, second);
    }

    private int countPoints(final NavMesh navMesh) {
        final List<NavMeshPoint> points = new ArrayList<>();
        for (final NavMeshPoly poly : navMesh.getPolygons()) {
            for (final NavMeshPoint point : poly.getPoints()) {
                boolean found = false;
                for (final NavMeshPoint otherPoint : points) {
                    if (otherPoint == point) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    points.add(point);
                }
            }
        }

        return points.size();
    }
}
