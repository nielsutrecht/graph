package com.nibado.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class GraphBuilderTest {
    public static String[] STRING_LIST = new String[]{"Alpha", "Beta", "Gamma", "Delta", "Epsilon", "Zeta", "Eta", "Theta", "Iota", "Kappa"};

    @Before
    public void setup() {
    }

    @Test
    public void test01() {
        final Graph<String> g = buildStringGraph().getGraph();

        int i = 0;
        for (final Node<String> n : g.getNodes()) {
            assertEquals(i, n.getIndex());
            assertEquals(STRING_LIST[i], n.getValue());
            i++;
        }

        assertNode(g, 0, new int[] {1, 2});
        assertNode(g, 1, new int[] {4, 5});
        assertNode(g, 2, new int[] {5, 6, 3});
        assertNode(g, 3, new int[] {});
        assertNode(g, 4, new int[] {7});
        assertNode(g, 5, new int[] {8});
        assertNode(g, 6, new int[] {8});
        assertNode(g, 7, new int[] {9});
        assertNode(g, 8, new int[] {9});
        assertNode(g, 9, new int[] {});


    }

    private void assertNode(final Graph<?> g, final int index, final int[] edges) {
        final Node<?> n = g.getNodes().get(index);
        assertNotNull(n);
        assertEquals(index,n.getIndex());
        assertEquals(edges.length, n.getEdges().size());

        for(int i = 0;i < edges.length;i++) {
            assertEquals(edges[i], n.getEdges().get(i).getIndex());
        }
    }

    public static GraphBuilder<String> buildStringGraph() {
        return new GraphBuilder<String>().addNodes(STRING_LIST)
            .connect(0, new int[]{1, 2})
            .connect(1, new int[]{4, 5})
            .connect(2, new int[]{5, 6, 3})
            .connect(4, new int[]{7})
            .connect(5, new int[]{8})
            .connect(6, new int[]{8})
            .connect(7, new int[]{9})
            .connect(8, new int[]{9});
    }

    public static GraphBuilder<Point> buildPointGraph() {
        return new GraphBuilder<Point>()
            .addNodes(new Point[] { new Point(0,0), new Point(3,0), new Point(3, 2), new Point(0,2), new Point(1,1) })
            .connect(0, new int[]{1, 4})
            .connect(1, 2)
            .connect(2, 3)
            .connect(4, 3);
    }
}
