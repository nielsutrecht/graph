package com.nibado.chess;


public class Piece {
    private final Type _type;
    private final boolean _white;

    public Piece(final Type type, final boolean white) {
        _type = type;
        _white = white;
    }

    public Piece(final int type, final boolean white) {
        _type = Type.forId(type);
        _white = white;
    }

    public Type getType() {
        return _type;
    }

    public boolean isWhite() {
        return _white;
    }

    @Override
    public String toString() {
        return _white ? "W:" : "B:" + _type.toString();
    }

    public enum Type {
        KING(1, "king"),
        QUEEN(2, "queen"),
        BISHOP(3, "bishop"),
        KNIGHT(4, "knight"),
        ROOK(5, "rook"),
        PAWN(6, "pawn");

        private final int _id;
        private final String _name;

        Type(final int id, final String name) {
            _id = id;
            _name = name;
        }

        public int getId() {
            return _id;
        }

        @Override
        public String toString() {
            return _name;
        }

        public static Type forId(final int id) {
            for (final Type t : Type.values()) {
                if (t._id == id) {
                    return t;
                }
            }
            return null;
        }
    }

    public static Piece get(final int type, final boolean white) {
        if(white) {
            return whites[type];
        }
        else {
            return blacks[type];
        }
    }

    private static final Piece[] whites = new Piece[6];
    private static final Piece[] blacks = new Piece[6];

    static {
        for (int i = 0; i < whites.length; i++) {
            whites[i] = new Piece(Type.forId(i + 1), true);
            blacks[i] = new Piece(Type.forId(i + 1), false);
        }
    }
}
