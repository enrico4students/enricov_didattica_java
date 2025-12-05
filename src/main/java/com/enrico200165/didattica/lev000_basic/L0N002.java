package com.enrico200165.didattica.lev000_basic;



import java.util.Scanner;

// Classe 1: Studente
class Studente {
    private String nome;
    private int eta;

    public Studente(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }

    public String descrizione() {
        return "Studente: " + nome + ", età: " + eta;
    }

    public boolean isMaggiorenne() {
        return eta >= 18;
    }
}

// Classe 2: Main (avvio del programma)
public class L0N002 {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserire nome studente:");
        String nome = scanner.nextLine();

        System.out.println("Inserire età studente:");
        int eta = scanner.nextInt();

        // Creazione oggetto studente
        Studente s = new Studente(nome, eta);

        System.out.println("\n--- RISULTATI ---");
        System.out.println(s.descrizione());

        if (s.isMaggiorenne()) {
            System.out.println("Lo studente è maggiorenne.");
        } else {
            System.out.println("Lo studente è minorenne.");
        }

        scanner.close();
    }
}
