package com.nibado.chess;

import java.io.PrintStream;
import java.util.Arrays;

import com.nibado.collections.BitMap;

public class Board {
    public static final int FLAG_KING_MOVED_WHITE = 0;
    public static final int FLAG_KING_MOVED_BLACK = 1;
    public static final int FLAG_QS_ROOK_MOVED_WHITE = 2;
    public static final int FLAG_QS_ROOK_MOVED_BLACK = 3;
    public static final int FLAG_KS_ROOK_MOVED_WHITE = 4;
    public static final int FLAG_KS_ROOK_MOVED_BLACK = 5;

    private final byte[] _board;
    private final BitMap _flags;

    public Board() {
        _board = new byte[32];
        _flags = new BitMap(8);
    }

    public Board(final Board other) {
        _board = Arrays.copyOf(other._board, 32);
        _flags = new BitMap(other._flags);
    }

    /**
     *
     * @param x X loc (A-H left to right)
     * @param y Y loc (1-8 bottom (white) to top)
     * @return
     */
    public Piece get(final int x, final int y) {
        int idx = x + y * 8;
        final boolean highNibble = idx % 2 == 1;
        idx = idx / 2;
        final int mask = highNibble ? 0xF0 : 0x0F;
        boolean white = true;
        int val = _board[idx] & mask;
        if (val == 0) {
            return null;
        }
        if (highNibble) {
            val = val >> 4;
        }
        if (val > 8) {
            white = false;
            val = val - 8;
        }
        return Piece.get(val, white);
    }

    public Board set(final int x, final int y, final Piece p) {
        int idx = x + y * 8;
        final boolean highNibble = idx % 2 == 1;
        idx = idx / 2;
        final int mask = highNibble ? 0x0F : 0xF0;
        int val;
        if (p == null) {
            val = 0;
        }
        else {
            val = p.getType().getId();
        }
        if(!p.isWhite()) {
            val += 8;
        }
        if(highNibble) {
            val = val << 4;
        }

        _board[idx] = (byte) (val + (_board[idx] & mask));
        return this;
    }

    public void setFlag(final int flag) {
        _flags.set(flag, true);
    }

    public boolean getFlag(final int flag) {
        return _flags.get(flag);
    }

    public void print(final PrintStream out) {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                final Piece p = get(x, y);
                if (x > 0) {
                    out.print(" ");
                }
                if(p != null) {
                    out.print(p.getType().getShortName() + (p.isWhite() ? "w" : "b"));
                }
                else {
                    out.print("  ");
                }
            }
            out.println();
        }
    }

    public void print() {
        print(System.out);
    }

    public static Board create() {
        final Board b = new Board();

        b.set(0, 0, Piece.get(Piece.Type.ROOK, true));
        b.set(1, 0, Piece.get(Piece.Type.KNIGHT, true));
        b.set(2, 0, Piece.get(Piece.Type.BISHOP, true));
        b.set(3, 0, Piece.get(Piece.Type.QUEEN, true));
        b.set(4, 0, Piece.get(Piece.Type.KING, true));
        b.set(5, 0, Piece.get(Piece.Type.BISHOP, true));
        b.set(6, 0, Piece.get(Piece.Type.KNIGHT, true));
        b.set(7, 0, Piece.get(Piece.Type.ROOK, true));

        b.set(0, 7, Piece.get(Piece.Type.ROOK, false));
        b.set(1, 7, Piece.get(Piece.Type.KNIGHT, false));
        b.set(2, 7, Piece.get(Piece.Type.BISHOP, false));
        b.set(3, 7, Piece.get(Piece.Type.QUEEN, false));
        b.set(4, 7, Piece.get(Piece.Type.KING, false));
        b.set(5, 7, Piece.get(Piece.Type.BISHOP, false));
        b.set(6, 7, Piece.get(Piece.Type.KNIGHT, false));
        b.set(7, 7, Piece.get(Piece.Type.ROOK, false));

        for (int i = 0; i < 8; i++) {
            b.set(i, 1, Piece.get(Piece.Type.PAWN, true));
            b.set(i, 6, Piece.get(Piece.Type.PAWN, false));
        }

        return b;
    }
}
