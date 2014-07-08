package com.nibado.nrow;

import java.util.Arrays;

public class Board {
    private final byte[] _board;
    private final int _width;
    private final int _height;

    public Board(final int width, final int height) {
        _width = width;
        _height = height;

        _board = new byte[width * height];
    }

    public Board(final Board other) {
        _width = other._width;
        _height = other._height;
        _board = Arrays.copyOf(other._board, other._board.length);
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public int get(final int x, final int y) {
        return _board[getIndex(x, y)];
    }

    public void set(final int x, final int y, final int player) {
        _board[getIndex(x, y)] = (byte) player;
    }

    private int getIndex(final int x, final int y) {
        assertInBounds(x, y);
        return y * _width + x;
    }

    public int getEmptyFields() {
        int count = 0;
        for (int i = 0; i < _board.length; i++) {
            if (_board[i] == 0) {
                count++;
            }
        }
        return count;
    }

    public int getSolution(final int length) {
        for (int y = 0; y < _height; y++) {
            for (int x = 0; x < _width; x++) {
                int result;
                result = checkRow(x, y, length, 1, 0);
                if (result > 0) {
                    return result;
                }
                result = checkRow(x, y, length, 0, 1);
                if (result > 0) {
                    return result;
                }
                result = checkRow(x, y, length, 1, 1);
                if (result > 0) {
                    return result;
                }
                result = checkRow(x, y, length, 1, -1);
                if (result > 0) {
                    return result;
                }
            }
        }
        if (getEmptyFields() == 0) {
            return 0;
        }
        else {
            return -1;
        }
    }

    public int checkRow(final int x, final int y, final int len, final int dirX, final int dirY) {
        if (!inBounds(x, y)) {
            return 0;
        }
        else if (len == 1) {
            return get(x, y);
        }
        else {
            final int sub = checkRow(x + dirX, y + dirY, len - 1, dirX, dirY);
            if (sub != get(x, y)) {
                return 0;
            }
            else {
                return sub;
            }
        }
    }

    private boolean inBounds(final int x, final int y) {
        return x >= 0 && x < _width && y >= 0 && y < _height;
    }

    private void assertInBounds(final int x, final int y) {
        if (x < 0 || x >= _width) {
            throw new IndexOutOfBoundsException("X is out of bounds: " + x);
        }
        if (y < 0 || y >= _height) {
            throw new IndexOutOfBoundsException("Y is out of bounds: " + y);
        }
    }
}
