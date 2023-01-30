package com.enrico2001665.dattica.tpsit.y4.lab10;// utilizzato in com.enrico2001665.dattica.tpsit.y4.lab10.ProvaNani1

import java.util.Random;

public class ContaINaniMettitiAllaProva extends Thread {

    int nrNani = 1000;

    public ContaINaniMettitiAllaProva(String nome) {                // costruttore
        super();
        setName(nome);
    }

    public void run() {
        Random r = new Random();
        try {
            while (!interrupted()) {
                for (int i = 0; i < 1000; i++) {
                    int randomNumber = r.ints(1, 0, 11).findFirst().getAsInt();
                    Thread.sleep(100 * randomNumber); // 100 = decimi di secondo
                    System.out.println((i + 1) + " " + getName());
                }
            }
        } catch (InterruptedException consumed) {
            System.err.println("thread: " + getName() + " è stato interrotto, esco");
            return;
        }
    }


}


