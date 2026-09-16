package com.enrico200165.didattica.scuola.examples.basic;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


class Fattoriale {

    int fatt(int n) {

        if (n < 1) throw new IllegalArgumentException("value must be non negative, you passed " + n);

        if (n == 1)
            return 1;

        return n * fatt(n - 1);
    }
}


public class MainArrayList {

    public static void main(String[] args) {

        final int nr = 20;

        Fattoriale fat = new Fattoriale();
        ArrayList<Integer> v = new ArrayList<>();

        for (int i = 0; i < nr; i++) {
            v.add(ThreadLocalRandom.current().nextInt(0, 20));
        }

        v.remove(0);
        v.remove(5);
        v.remove(15);
        v.remove(16);

        v.add(1001);
        v.add(1011);
        v.add(0, 999);

        for (int i : v) {
            System.out.println(i + "! = " + fat.fatt(i));
        }

    }

}
