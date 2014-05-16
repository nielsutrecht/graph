package com.nibado.graph.world;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;

public class SvgWorldReaderTest {
    @Test
    public void testRead() throws Exception {
        final World world = readWorld();
    }

    public static World readWorld() throws Exception {
        return new SvgWorldReader().read(new FileInputStream(new File("/home/niels/test.svg")));
    }
}
