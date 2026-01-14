package com.enrico200165.didattica.scuola.verifiche;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

abstract class Veicolo {
    String ID;
    Veicolo(String ID) { this.ID = ID; }
    abstract void viaggia();
};

class Automobile extends Veicolo {
    
    Automobile(String ID) { super(ID);}
    @Override
    void viaggia() {}
};

class Ciclo extends Veicolo {    
    Ciclo(String ID) { super(ID);}
    @Override
    void viaggia() {}
};

public class verifica2quartaq {
  
    static void verificaA() {

        { // A 1: non corretto
            //Veicolo veic00 = new Veicolo("");
        }
        { // a 2:  non corretto, uguale alla precedente
            // Veicolo veicolo02 = new Veicolo("mio ciclo");
        }
        { // A 3: corretto
            Animale veic01 = new Mammifero("0001");
        }
        { // A 4: Nono corretto
            Animale veic02 = new Mammifero("0001");
            // auto01 = veico02; auto01 non dichiarata
            
            // Esposito
            // Automobile auto01 = veic02;
        }
        
        { // a 5
            Animale speciale = new Animale("UFO") {
            @Override
            public void migra() { System.out.println("UFO viaggia");}
            };
            speciale.migra();
            System.out.println("");
        }
        
        
        { // A 6: non corretto
            // int mioVettore[10];
            // Esposito, errato: int mioVettore[10] = new int[10];
            // dovrebbe essere
            int[] mioVettore = new int[10];
            System.out.println("");
        }
        
        { // A 7: codice migliorabile? Si, versoine migliorata
            int[] mioVettore2 = new int[10];
            for (int val: mioVettore2) {}
            System.out.println("");            
        }
        
        { // A 8: stampata male
        }
        
        { // A 9: iterare sul vettore e stampare i valori senza usare un indice
            int[] mioVettore3 = new int[10];
            for (int val: mioVettore3) {
                System.out.println(val);
            }
            System.out.println();
            
        }
        
        { // A 10: ok
            ArrayList<Integer> vettore_ok = new ArrayList<>();
        }
        { // A 11: ok
            List<Uccello> flotta = new ArrayList<Uccello>();
        }
        { // A 12: errato
            // incompatible types: java.util.ArrayList<Veicolo> cannot be converted to java.util.List<Ciclo>
            // List<Ciclo> flotta = new ArrayList<Veicolo>();
        }
        
        
        { // A 13: errato
            // Collection<Veicolo> flotta = new LinkedList<Ciclo>();
            // codice corretto di Meloni
            Collection<Animale> flotta = new LinkedList<Animale>();
        }
        { // A 14: Corretta
            Collection<Uccello> flotta2 = new LinkedList<Uccello>();
        }
        { // A : errato 
            Collection<Uccello> flotta = new LinkedList<Uccello>();
            // incompatible types: java.util.Iterator<Ciclo> cannot be converted to java.util.Iterator<Veicolo>
            // Iterator<Veicolo> it = flotta.iterator();
        }

        { // A 16: scrivere codice che itera su flotta
            Collection<Uccello> flotta = new LinkedList<>();
            for (Uccello cur: flotta) {}
        }
    }
    
    
    static void verificaB() {
       
        
        {  // B: 1
        Collection<Uccello> flotta = new LinkedList<>();
        for (int i = 0; i < 3; i++) { flotta.add(new Uccello(""+i)); }
            Iterator<Uccello> it = flotta.iterator();
            // migliore
            for (Uccello curCiclo: flotta) {
                System.out.println(curCiclo.specie);
            }
            while (it.hasNext()) {
                Uccello curCiclo = it.next();
                System.out.println(curCiclo.specie);

            }
        }
        
        
        { // B 2: non corretto, tipi parametrizzati non compatibili 
            //Collection<Ciclo> flotta = new LinkedList<Ciclo>();
            // Iterator<Veicolo> it = flotta.iterator();

            //  verifica di affermazione errata di Marcolini
            Collection<Uccello> flotta = new LinkedList<Uccello>();
            Iterator<Uccello> it = flotta.iterator();
        }
        
        { // B3: errato
            //Collection<Veicolo> flotta = new LinkedList<Ciclo>();
            
            //  Marcolini
            Collection<Animale> flotta = new LinkedList<Animale>();
        }
        
        {   // B 4: errato
            //Veicolo veicolo02 = new Veicolo("kt781va");
            
            // avrebbe dovuto essere
            Animale veicolo02 = new Mammifero("kt781va");
        }


        
        
    }


    static void accessibile() {
    
        // ACC 3: non corretta
        //int mioVettore[10];
        // corretta
        int[] mioVettore = new int[10];
        
        
        // ACC 4: non corretto
        // for (int i = 0; i < mioVettore.length(); i++)         
        // avrebbe dovuto essere
        for (int i = 0; i < mioVettore.length; i++) 
        {}
        
        
        // ACC 5: riempire le condizioni del for senza usare indice numerico
        for (int val: mioVettore) 
        { System.out.println("val: "+val);}
        
        // ACC 6: non corretta
        {
            // LinkedList<Ciclo> flotta = new LinkedList<Veicolo>();            
        }
        
        {
            // ACC 7: non corretto
            // Collection<Veicolo> flotta = new LinkedList<Ciclo>();
            // incompatible types: java.util.LinkedList<Ciclo> cannot be converted to java.util.Collection<Veicolo>
        }
        
        
        { // ACC 8: non corretto
            
            //Collection<Ciclo> flotta = new LinkedList<Ciclo>();
            //Iterator<Veicolo> it = flotta.iterator();
            
            // Guarracino
            // java.util.Collection is abstract; cannot be instantiated
            // Collection<Ciclo> flotta = new Collection<Ciclo>();
            
        }
        
        {   // ACC 9: non corretto
            // Veicolo veicolo02 = new Veicolo("");
        }
        { // ACC 10
            Animale veicolo02 = new Mammifero("");
        }
        
    }
    
    public static void main(String[] argv) {

        verificaA();
        accessibile();
    }
}
