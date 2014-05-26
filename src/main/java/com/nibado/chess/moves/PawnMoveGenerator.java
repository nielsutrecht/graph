package com.nibado.chess.moves;

import java.util.List;

import com.nibado.chess.Board;
import com.nibado.chess.GameState;
import com.nibado.chess.Move;
import com.nibado.chess.MoveGenerator;
import com.nibado.chess.Piece;

public class PawnMoveGenerator extends MoveGenerator {

    @Override
    public void addMoves(final List<Move> moves, final Board board, final GameState gameState, final int x, final int y) {

        final Piece p = getPiece(board, x, y, Piece.Type.PAWN);

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
    }

}
