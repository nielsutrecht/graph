package com.nibado.graph.traverse;

import java.util.Arrays;

class BitMap {
    private byte[] _map;
    private int _size;
    private final static int GROWTH_FACTOR = 2;

    BitMap() {
        this(1000);
    }

    BitMap(final int size) {
        _size = size;
        _map = new byte[byteSize(size)];
    }



    void set(final int index, final boolean value) {
        while (index >= _size)
            grow();

        final int byteIndex = index / 8;
        final int bitIndex = index % 8;

        byte mask = (byte) (1 << bitIndex);

        if (value) {
            _map[byteIndex] = (byte) (_map[byteIndex] | mask);
        }
        else {
            mask = (byte) (mask ^ 0xff);
            _map[byteIndex] = (byte) (_map[byteIndex] & mask);
        }
    }

    boolean get(final int index) {
        if(index >= _size) {
            throw new IndexOutOfBoundsException("Index " + index + " while size is " + _size);
        }

        final int byteIndex = index / 8;
        final int bitIndex = index % 8;

        final byte mask = (byte) (1 << bitIndex);

        return (byte) (_map[byteIndex] & mask) == mask;
    }

    int size() {
        return _size;
    }

    private int byteSize(final int size) {
        return (int) Math.ceil(size / 8.0);
    }

    private void grow() {
        final int newSize = _size * GROWTH_FACTOR;
        _map = Arrays.copyOf(_map, byteSize(newSize));
        _size = newSize;
    }

}
