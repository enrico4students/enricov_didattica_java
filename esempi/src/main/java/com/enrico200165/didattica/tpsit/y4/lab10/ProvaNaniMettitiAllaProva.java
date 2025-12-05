// due thread con nome 
package com.enrico200165.didattica.tpsit.y4.lab10;

import java.util.ArrayList;
import java.util.Random;

public class ProvaNaniMettitiAllaProva {

    public static void main(String args[]) {

        ArrayList<Thread> threads = new ArrayList<Thread>();

        // avvia i threads
        for (int i = 0; i < 7; i++) {
            threads.add(new ContaINaniMettitiAllaProva("thread" + i));
            threads.get(i).start();
        }

        Random r = new Random();
        int threadToKill = r.ints(1, 0, threads.size()).findFirst().getAsInt();

        boolean ancoraVivi = true;
        while (ancoraVivi) {
            ancoraVivi = false;
            for (int i = 0; i < threads.size(); i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Thread t = threads.get(i);
                if (t.isInterrupted()) {
                    System.out.println("thread " + t.getName() + " è già interrotto");
                } else {
                    System.out.println("uccido il thread " + t.getName());
                    t.interrupt();
                    if (t.isInterrupted())
                        System.out.println("ok, è interrotto");
                    else {
                        System.out.println("no, NON è amcpra interrotto");
                        ancoraVivi = true;
                    }
                }

            }
        }
        System.out.print("tutti i thread sono morti");
    }

}

