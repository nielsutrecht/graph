package com.nibado.chess.moves;

import java.util.ArrayList;
import java.util.List;

import com.nibado.chess.Board;
import com.nibado.chess.GameState;
import com.nibado.chess.Move;
import com.nibado.chess.MoveGenerator;
import com.nibado.chess.Piece;

public class PawnMoveGenerator extends MoveGenerator {

    @Override
    public List<Move> getMoves(final Board board, final GameState gameState, final int x, final int y) {
        final List<Move> moves = new ArrayList<>();
        final Piece p = board.get(x, y);
        if (p == null) {
            throw new IllegalArgumentException("No piece on " + x + "," + y + ".");
        }
        if (p.getType() != Piece.Type.PAWN) {
            throw new IllegalArgumentException("Piece on " + x + "," + y + " is not a pawn.");
        }

        final int dir = p.isWhite() ? 1 : -1;

        int newX = x + dir;
        if (inBounds(newX, y) && board.isEmpty(newX, y)) {
            moves.add(new Move(board, x, y, newX, y, false));
        }
        if (pawnOnHomeRow(board, x, y)) {
            newX += dir;
            if (inBounds(newX, y) && board.isEmpty(newX, y)) {
                moves.add(new Move(board, x, y, newX, y, false));
            }
        }

        return moves;
    }

}
