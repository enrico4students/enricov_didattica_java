package com.enrico2001665.dattica.tpsit.y4.lab10;

public class ProvaNani1{
    public static void main(String args[]){
        ContaINani1 thr1 = new ContaINani1();
        thr1.start();
        // o in una unica istruzione : new ContaINani().start();
        System.out.println("thread name: "+Thread.currentThread().getName());
    }
}


