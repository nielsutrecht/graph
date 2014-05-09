package com.nibado.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PointTest {
    @Test
    public void testEquals() {
        final Point p1 = new Point(10, 10);
        final Point p2 = new Point(10, 10);

        assertEquals(p1, p2);
        assertTrue(p1.equals(p2));
    }

    @Test
    public void testDistance() {
        final Point p1 = new Point(-16, -16);
        final Point p2 = new Point(16, 16);

        final double dist = p1.distanceTo(p2);
        assertEquals(45.25, dist, 0.01);
    }
}
