package com.enrico200165.didattica.scuola.examples.basic;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

class Implementation {

    public String[] sort(String[] stringsV) {

        boolean scambiato;
        do {
            scambiato = false;
            for (int i = 0; i < stringsV.length - 1; i++) {
                if (stringsV[i].compareTo(stringsV[i + 1]) > 0) {
                    String temp = stringsV[i];
                    stringsV[i] = stringsV[i + 1];
                    stringsV[i + 1] = temp;
                    scambiato = true;
                }
            }
        } while (scambiato);

        System.out.println("ordinato");
        return stringsV;
    }

}


public class MainBSortString {

    public static void main(String[] args) {

        for (String s : args) {
            System.out.println("argument " + s);
        }
        Implementation implObj = new Implementation();
        String[] v = {"zorro", "valerio", "carlo", "antonio"};
        String[] sorted = implObj.sort(v);

        for (int i = 0; i < v.length; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i + " " + sorted[i]);
        }
    }

}