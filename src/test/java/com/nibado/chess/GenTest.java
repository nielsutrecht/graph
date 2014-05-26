package com.nibado.chess;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GenTest {

    private Board _board;

    @Before
    public void setup() {
        _board = new Board();
    }
    @Test
    public void testQ() {
        final List<Move> moves = new ArrayList<>();
        _board.set(4, 4, Piece.get(Piece.Type.QUEEN, true));
        _board.set(7, 7, Piece.get(Piece.Type.PAWN, false));
        _board.set(4, 7, Piece.get(Piece.Type.PAWN, false));
        MoveGenerator.addDiagonalMoves(moves, _board, 4, 4);
        MoveGenerator.addHorizontalVerticalMoves(moves, _board, 4, 4);

        System.out.println(moves);
    }
}
