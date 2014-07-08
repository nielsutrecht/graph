package com.nibado.nrow;

import java.util.Random;

public class RandomAI implements AI {

    private final Random _random = new Random();
    @Override
    public int[] pick(final Board board) {
        for (;;) {
            final int x = _random.nextInt(board.getWidth());
            final int y = _random.nextInt(board.getHeight());
            if (board.get(x, y) == 0) {
                return new int[]{x, y};
            }
        }
    }

}
