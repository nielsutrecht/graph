package com.nibado.chess;

import java.util.Arrays;

public class Board {
    private final byte[] _board;

    public Board() {
        _board = new byte[32];
        _board[0] = (byte) (1 + 1 << 4);
    }

    public Board(final Board other) {
        _board = Arrays.copyOf(other._board, 32);
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
            val = val >> 3;
        }
        if (val > 8) {
            white = false;
            val = val - 8;
        }
        System.out.println(val);
        return Piece.get(val, white);
    }
}
