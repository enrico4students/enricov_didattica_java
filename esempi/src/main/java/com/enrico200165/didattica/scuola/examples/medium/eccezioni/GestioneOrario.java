/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.enrico200165.didattica.scuola.examples.medium.eccezioni;


/**
 *
 * @author enric
 */
public class GestioneOrario {
    

    static void checkOrarioNonValido(int h, int min, int sec) 
    throws ExcOrarioNonValido // provare a escludere questa riga con commento e compilare
    {
        ExcOrarioNonValido eccez = new ExcOrarioNonValido(h, min, sec, null);

        if (h < 0 || h > 23) {
            eccez.setMessaggio("valore ore non legale");
            throw eccez;
        }
        
        if (min < 0 || min > 59) {
            eccez.setMessaggio("valore minuti non legale");
            throw eccez;
        }
        
        if (sec < 0 || sec > 59) {
            eccez.setMessaggio("valore minuti non legale");
            throw eccez;
        }
    }

    public static int differenzaOrari(int h1, int min1, int sec1,
        int h2, int min2, int sec2) {

        try {
            checkOrarioNonValido(h1,min1, sec1);
            checkOrarioNonValido(h2,min2, sec2);
            
            int orarioInSec1 = h1*3600+min1*60+sec1;
            int orarioInSec2 = h2*3600+min2*60+sec2;
            
            return orarioInSec2 - orarioInSec1;
        } catch (ExcOrarioNonValido exc) {
            System.err.println("getMessage: exc.getMessage()");
            System.err.println("getMessaggio: "+exc.getMessaggio());
            System.err.println("toString: "+exc);
            throw new Error();
        }
    }
    
    
    public static void main(String[] argv) {
        
        int diff = differenzaOrari(10,1,1,
                9,1,1);        
        System.out.println("differenza: "+diff);
        
        diff = differenzaOrari(10,1,1,
                24,1,1);

        }
    
}
