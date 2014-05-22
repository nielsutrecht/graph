package com.nibado.graph.traverse;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.nibado.collections.BitMap;

public class BitMapTest {
    @Test
    public void testMap() {
        final BitMap map = new BitMap(32);

        for (int i = 0; i < 32; i += 2) {
            map.set(i, true);
            map.set(i + 1, false);
        }
        for (int i = 0; i < 32; i += 2) {

            assertEquals(true, map.get(i));
            assertEquals(false, map.get(i + 1));
        }
    }

    @Test
    public void testGrow() {
        final BitMap map = new BitMap(32);
        assertEquals(32, map.size());
        map.set(40, true);
        assertEquals(64, map.size());
    }
}
