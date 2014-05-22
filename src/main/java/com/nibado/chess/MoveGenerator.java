package com.nibado.chess;

import java.util.List;

public abstract class MoveGenerator {
    protected boolean pawnOnHomeRow(final Board board, final int x, final int y) {
        final Piece p = board.get(x, y);
        if (p.getType() != Piece.Type.PAWN) {
            throw new IllegalArgumentException("Piece on position " + x + "," + y + " is " + p.toString());
        }
        if (p.isWhite()) {
            return y == 1;
        }
        else {
            return y == 6;
        }
    }

    public abstract List<Move> getMoves(Board board, GameState gameState, int x, int y);
}
