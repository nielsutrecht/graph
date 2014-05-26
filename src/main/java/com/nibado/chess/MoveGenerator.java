package com.nibado.chess;

import java.util.List;

public abstract class MoveGenerator {
    protected static boolean pawnOnHomeRow(final Board board, final int x, final int y) {
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

    protected static boolean inBounds(final int x, final int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    protected static Piece getPiece(final Board board, final int x, final int y, final Piece.Type type) {
        final Piece p = board.get(x, y);
        if (p == null) {
            throw new IllegalArgumentException("No piece on " + x + "," + y + ".");
        }
        if (p.getType() != type) {
            throw new IllegalArgumentException("Piece on " + x + "," + y + " is not of type " + type.name());
        }

        return p;
    }

    protected static void addDiagonalMoves(final List<Move> moves, final Board board, final int x, final int y) {
        addMoves(moves, board, x, y, -1, -1);
        addMoves(moves, board, x, y, -1, 1);
        addMoves(moves, board, x, y, 1, -1);
        addMoves(moves, board, x, y, 1, 1);
    }

    protected static void addHorizontalVerticalMoves(final List<Move> moves, final Board board, final int x, final int y) {
        addMoves(moves, board, x, y, 1, 0);
        addMoves(moves, board, x, y, -1, 0);
        addMoves(moves, board, x, y, 0, 1);
        addMoves(moves, board, x, y, 0, -1);
    }

    protected static void addMoves(final List<Move> moves, final Board board, final int x, final int y, final int xDir, final int yDir) {
        int newX;
        int newY;
        newX = x + xDir;
        newY = y + yDir;

        while (inBounds(newX, newY)) {
            addMove(moves, board, x, y, newX, newY);
            newX += xDir;
            newY += yDir;
        }

    }

    protected static void addKnightMoves(final List<Move> moves, final Board board, final int x, final int y) {
        addMove(moves, board, x, y, x + 1, y + 2);
        addMove(moves, board, x, y, x - 1, y + 2);
        addMove(moves, board, x, y, x + 1, y - 2);
        addMove(moves, board, x, y, x - 1, y - 2);

        addMove(moves, board, x, y, x + 2, y + 1);
        addMove(moves, board, x, y, x - 2, y + 1);
        addMove(moves, board, x, y, x + 2, y - 1);
        addMove(moves, board, x, y, x - 2, y - 1);
    }

    protected static void addMove(final List<Move> moves, final Board board, final int x, final int y, final int xTo, final int yTo) {
        if (!inBounds(xTo, yTo)) {
            return;
        }
        final Piece p = board.get(x, y);
        final Piece otherPiece = board.get(xTo, yTo);
        if (otherPiece == null) {
            moves.add(new Move(board, x, y, xTo, yTo));
        }
        else if (p.isOpponent(otherPiece)) {
            moves.add(new Move(board, x, y, xTo, yTo, true));
        }
    }

    public abstract void addMoves(List<Move> moves, Board board, GameState gameState, int x, int y);
}
