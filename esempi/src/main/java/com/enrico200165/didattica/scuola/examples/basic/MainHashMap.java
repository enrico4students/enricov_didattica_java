package com.enrico200165.didattica.scuola.examples.basic;

import java.util.HashMap;

class ImplemHashMap {

    public static void fai() {

    }
}


public class MainHashMap {

    public static void main(String[] argv) {

        ImplemHashMap impl = new ImplemHashMap();

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        map.put("lunedi", 1);
        map.put("martedi", 2);
        map.put("mercoledi", null);

        System.out.print(map.get("mercoledi"));
        System.out.print(map.containsKey(""));

    }

}
