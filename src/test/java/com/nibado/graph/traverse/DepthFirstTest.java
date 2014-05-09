package com.nibado.graph.traverse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nibado.graph.Graph;
import com.nibado.graph.GraphBuilder;
import com.nibado.graph.GraphBuilderTest;
import com.nibado.graph.Node;
import com.nibado.graph.Point;

public class DepthFirstTest {
    private GraphBuilder<String> _builder;
    private NodeFind<String> _nodeFinder;
    private PathFind<String> _pathFinder;

    @Before
    public void setup() {
        _builder = GraphBuilderTest.buildStringGraph();
        _builder.addNode(GraphBuilderTest.STRING_LIST[9]);
        _builder.connect(9, 10);
        _nodeFinder = new DepthFirst<String>();
        _pathFinder = new DepthFirst<String>();
    }

    @Test
    public void testFind() {
        final Node<String> node = _nodeFinder.find(_builder.getGraph(), GraphBuilderTest.STRING_LIST[9]);
        assertNotNull("Node null", node);
        assertEquals(9, node.getIndex());
        assertEquals(GraphBuilderTest.STRING_LIST[9], node.getValue());
    }

    @Test
    public void testFindAll() {
        final List<Node<String>> nodes = _nodeFinder.findAll(_builder.getGraph(), GraphBuilderTest.STRING_LIST[9]);

        assertEquals(2, nodes.size());
        assertEquals(GraphBuilderTest.STRING_LIST[9], nodes.get(0).getValue());
        assertEquals(9, nodes.get(0).getIndex());
        assertEquals(GraphBuilderTest.STRING_LIST[9], nodes.get(1).getValue());
        assertEquals(10, nodes.get(1).getIndex());
    }

    @Test
    public void testFindPath() {
        final Graph<String> g = _builder.getGraph();
        final List<Node<String>> path = _pathFinder.findPath(g.getNodes().get(0), g.getNodes().get(9));
        final String result = path.toString();
        assertEquals("[(0:Alpha), (1:Beta), (4:Epsilon), (7:Theta), (9:Kappa)]", result);
    }

    @Test
    public void testFindPointPath() {
        final Graph<Point> g = GraphBuilderTest.buildPointGraph().getGraph();
        final PathFind<Point> pathFind = new DepthFirst<Point>();
        final List<Node<Point>> path = pathFind.findPath(g.getNodes().get(0), g.getNodes().get(3));
        final String result = path.toString();
        assertEquals("[(0:{0,0}), (1:{3,0}), (2:{3,2}), (3:{0,2})]", result);
    }

    @Test
    public void testListener() {
        final Graph<String> g = GraphBuilderTest.buildStringGraph().getGraph();
        final DepthFirst<String> df = new DepthFirst<String>();
        final List<Node<String>> nodes = new ArrayList<Node<String>>();

        df.addListener(new NodeListener<String>() {
            public void nodeVisited(final Node<String> node) {
                nodes.add(node);
            }
        });

        final List<Node<String>> result = df.findAll(g, GraphBuilderTest.STRING_LIST[9]);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(g.getNodes().size(), nodes.size());

    }

}
