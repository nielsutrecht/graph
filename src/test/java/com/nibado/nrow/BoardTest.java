package com.nibado.nrow;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private Board _board;

    @Before
    public void setup() {
        _board = new Board(3, 3);
    }

    @Test
    public void testGetRow() {
        _board.set(0, 0, 1);
        _board.set(1, 0, 1);
        _board.set(2, 0, 1);
        assertEquals(1, _board.checkRow(0, 0, 3, 1, 0));
        assertEquals(0, _board.checkRow(0, 0, 3, 1, 1));
        assertEquals(0, _board.checkRow(0, 0, 3, 0, 1));
    }

    @Test
    public void testGetSolutionHor() {
        _board.set(0, 0, 1);
        _board.set(1, 0, 1);
        _board.set(2, 0, 1);

        final int solution = _board.getSolution(3);
        assertEquals(1, solution);
    }

    @Test
    public void testGetSolutionVert() {
        _board.set(1, 0, 2);
        _board.set(1, 1, 2);
        _board.set(1, 2, 2);

        final int solution = _board.getSolution(3);
        assertEquals(2, solution);
    }

    @Test
    public void testGetSolutionDiag1() {
        _board.set(0, 0, 3);
        _board.set(1, 1, 3);
        _board.set(2, 2, 3);

        final int solution = _board.getSolution(3);
        assertEquals(3, solution);
    }

    @Test
    public void testGetSolutionDiag2() {
        _board.set(2, 0, 4);
        _board.set(1, 1, 4);
        _board.set(0, 2, 4);

        final int solution = _board.getSolution(3);
        assertEquals(4, solution);
    }

    @Test
    public void testGetNoSolution() {
        final int solution = _board.getSolution(3);
        assertEquals(-1, solution);
    }

    @Test
    public void testGetNoSolutionFull() {
        int count = 0;
        for (int y = 0; y < _board.getHeight(); y++) {
            for (int x = 0; x < _board.getWidth(); x++) {
                _board.set(x, y, ++count);
            }
        }
        final int solution = _board.getSolution(3);
        assertEquals(0, solution);
    }
}
