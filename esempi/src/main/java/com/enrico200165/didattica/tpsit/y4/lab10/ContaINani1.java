package com.enrico200165.didattica.tpsit.y4.lab10;// utilizzato in com.enrico2001665.dattica.tpsit.y4.lab10.ProvaNani1

public class ContaINani1 extends Thread{
    public void run(){
        setName("settenani");
        System.out.println("thread name: "+Thread.currentThread().getName());
        for(int i = 0; i < 7; i++){
            System.out.print("nano "+(i + 1)+", ");
        }
    }
}