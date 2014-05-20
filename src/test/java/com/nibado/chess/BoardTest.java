package com.nibado.chess;

import org.junit.Test;

public class BoardTest {
    @Test
    public void test() {
        final Board b = new Board();

        System.out.println(b.get(0, 0));
        System.out.println(b.get(0, 1));
    }

}
