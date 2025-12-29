

package com.enrico200165.didattica.lev000_basic;



class Persona {

    public void lavora() {
        System.out.println("Sono "+ nome + " "+ cognome+ "sto lavorando per lo stipendio");
    }
    public String nome;
    public String cognome;
    public int eta;
}



public class L0N000Main {
    public static void main(String[] args) {

        Persona p1 = new Persona();
        p1.nome = "Enrico";
        p1.cognome = "Rocchi";
        p1.eta = 58;

        p1.lavora();

        System.out.println("Hello world!");
    }
}