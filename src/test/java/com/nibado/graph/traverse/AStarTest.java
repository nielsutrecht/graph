package com.nibado.graph.traverse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.nibado.graph.Graph;
import com.nibado.graph.GraphBuilderTest;
import com.nibado.graph.Node;
import com.nibado.graph.Point;

public class AStarTest {
    @Test
    public void testFindPointPath() {
        final Graph<Point> g = GraphBuilderTest.buildPointGraph().getGraph();
        final Heuristic<Point> heuristic = new Heuristic<Point>() {
            public double calc(final Point from, final Point to) {
                return from.distanceTo(to);
            }
        };
        final AStar<Point> pathFind = new AStar<Point>(heuristic);
        pathFind.addListener(new NodeListener<Point>() {
            public void nodeVisited(final Node<Point> node) {
                System.out.println(node);
            }
        });
        final List<Node<Point>> path = pathFind.findPath(g.getNodes().get(0), g.getNodes().get(3));
        assertNotNull("No path", path);

        final String result = path.toString();
        assertEquals("[(0:{0,0}), (4:{1,1}), (3:{0,2})]", result);
    }
}
