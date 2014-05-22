package com.nibado.chess;

public class Move {
    private final Board _board;
    private final int _xFrom;
    private final int _xTo;
    private final int _yFrom;
    private final int _yTo;

    public Move(final Board board, final int xFrom, final int yFrom, final int xTo, final int yTo) {
        _board = board;
        _xFrom = xFrom;
        _xTo = xTo;
        _yFrom = yFrom;
        _yTo = yTo;
    }

    public Board getBoard() {
        return _board;
    }

    public int getxFrom() {
        return _xFrom;
    }

    public int getxTo() {
        return _xTo;
    }

    public int getyFrom() {
        return _yFrom;
    }

    public int getyTo() {
        return _yTo;
    }

    public Board getResult() {
        final Board board = new Board(_board);
        final Piece p = _board.get(_xFrom, _yFrom);
        board.set(_xFrom, _yFrom, null);
        board.set(_xTo, _yTo, p);
        return board;
    }
}
