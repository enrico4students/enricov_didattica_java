package com.enrico200165.teach.scuola.verifiche;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;

abstract class Animale {
    String specie;
    int vita;
    Animale(String specie) { this.specie = specie; }
    abstract void migra();
};

class Mammifero extends Animale {
    
    Mammifero(String ID) { super(ID);}
    @Override
    void migra() {}
};

class Uccello extends Animale {    
    Uccello(String ID) { super(ID);}
    @Override
    void migra() {}
};

public class verificaObjOrBis {
  
    static void verificaStdUnica() {

        { // A 1: non corretto
            // Animale is abstract; cannot be instantiated
            // Animale anim00 = new Animale("cane");
        }
        { // a 2:  corretta
            Animale animale02 = new Uccello("cane");
        }
        { // A 3: corretto
            Animale veic01 = new Mammifero("0001");
        }
        { // A 4: corretto
            Animale anim01 = null;
            Animale mammif02 = new Mammifero("0002");
            anim01 = mammif02;        
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
            // dovrebbe essere
            int[] mioVettore = new int[10];
            System.out.println("");
        }
        
        { // A 7: codice migliorabile? Si, versoine migliorata
            int[] mioVettore2 = new int[10];
            for (int val: mioVettore2) {
                System.out.println(val);            
            }
        }
        
        { // A 8: errato
            int[] mioVettore = new int[10];
//            for (int i = 0; i < mioVettore.length(); i++) {
//                System.out.println(mioVettore[i]);
//            }
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
            // incompatible types: java.util.LinkedList<Uccello> cannot be converted to java.util.Collection<Animale>
            // Collection<Animale> stormo = new LinkedList<Uccello>();
            // dovrebbe essere
            Collection<Animale> stormo = new LinkedList<Animale>();
        }
        { // A 14: Corretta
            Collection<Uccello> stormo = new LinkedList<Uccello>();        
        }
        { // 15 : errato 
            Collection<Uccello> stormo = new LinkedList<Uccello>();
            // ncompatible types: java.util.Iterator<Uccello> cannot be converted to java.util.Iterator<Animale>
            // Iterator<Animale> it = stormo.iterator();
        }

        { // A 16: scrivere codice che itera su flotta
            Collection<Uccello> flotta = new LinkedList<>();
            for (Uccello cur: flotta) {}
        }
    }
    
    
    static void verificaAccBis() {
       
        
        {  // 1
            // Animale is abstract; cannot be instantiated
            // Animale anim00 = new Animale("cane");
        }
        
        
        { // 2: non corretta
            // Animale is abstract; cannot be instantiated
            // Animale anim02 = new Animale("0001");
            //  cannot find symbo symbol:   variable anim01
            // anim01 = anim02;   

        }
        
        { // 3: errato
            //  ']' expected
            // float mioVettore[4];
        }
        
        {   // 4: errato
            int[] mioVettore = new int[10];
            // cannot find symbol symbol:   method size()
//            for (int i = 0; i < mioVettore.size(); i++) {
//                System.out.println(mioVettore[i]);
//            }
        }
        
        { // 5 
            
        }
        { // 6
        }
        
        { // 7 errato
            // incompatible types: java.util.LinkedList<Mammifero> cannot be converted to java.util.Collection<Animale>
            // Collection<Animale> flotta = new LinkedList<Mammifero>();
            
            // Ciaschini considera corretto, OK
            List<Mammifero> flotta = new LinkedList<>();
        }

        { // 8 errato
            Collection<Uccello> flotta = new LinkedList<Uccello>();
            // incompatible types: java.util.Iterator<Uccello> cannot be converted to java.util.Iterator<Animale>
            // Iterator<Animale> it = flotta.iterator();
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

        verificaStdUnica();
        accessibile();
    }
}
