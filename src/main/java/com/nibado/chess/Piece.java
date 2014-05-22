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

    public boolean isOpponent(final Piece other) {
        return _white != other._white;
    }

    @Override
    public String toString() {
        return (_white ? "W:" : "B:") + _type.toString();
    }

    public enum Type {
        KING(1, "king", "K"),
        QUEEN(2, "queen", "Q"),
        BISHOP(3, "bishop", "B"),
        KNIGHT(4, "knight", "N"),
        ROOK(5, "rook", "R"),
        PAWN(6, "pawn", "P");

        private final int _id;
        private final String _name;
        private String _short;

        Type(final int id, final String name, final String shortName) {
            _id = id;
            _name = name;
            _short = shortName;
        }

        public int getId() {
            return _id;
        }

        @Override
        public String toString() {
            return _name;
        }

        public String getShortName() {
            return _short;
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

    public static Piece get(final Type type, final boolean white) {
        return get(type.getId(), white);
    }

    public static Piece get(final int type, final boolean white) {
        if(white) {
            return whites[type - 1];
        }
        else {
            return blacks[type - 1];
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
