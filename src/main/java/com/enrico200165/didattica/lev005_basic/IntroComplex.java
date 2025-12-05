package com.enrico200165.didattica.lev00_basic;


import java.util.Scanner;

// Classe 1: Prodotto (rappresenta un oggetto reale con prezzo, nome, IVA)
class Prodotto {
    private String nome;
    private double prezzoBase;   // prezzo senza IVA
    private double iva;          // percentuale IVA, es. 22.0

    public Prodotto(String nome, double prezzoBase, double iva) {
        this.nome = nome;
        this.prezzoBase = prezzoBase;
        this.iva = iva;
    }

    public double prezzoFinale() {
        return prezzoBase + (prezzoBase * iva / 100.0);
    }

    public String descrizione() {
        return "Prodotto: " + nome + ", prezzo base: " + prezzoBase + "€, IVA " + iva + "%";
    }
}


// Classe 2: Cliente (mostra costruttori, metodi, campi)
class Cliente {
    private String nome;
    private String email;

    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String descrizione() {
        return "Cliente: " + nome + " (" + email + ")";
    }
}


// Classe 3: Ordine (contiene un cliente e un prodotto)
class Ordine {
    private Cliente cliente;
    private Prodotto prodotto;
    private int quantita;

    public Ordine(Cliente cliente, Prodotto prodotto, int quantita) {
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public double totale() {
        return prodotto.prezzoFinale() * quantita;
    }

    public String riepilogo() {
        return "Ordine di " + cliente.getNome() +
                ": " + quantita + " x " + prodotto.prezzoFinale() +
                "€ = " + totale() + "€";
    }
}


// Classe 4: OrdinePremium (eredita da Ordine, mostra override)
class OrdinePremium extends Ordine {
    private double scontoPercentuale;

    public OrdinePremium(Cliente cliente, Prodotto prodotto, int quantita, double scontoPercentuale) {
        super(cliente, prodotto, quantita);
        this.scontoPercentuale = scontoPercentuale;
    }

    @Override
    public double totale() {
        double prezzoOriginale = super.totale();
        double sconto = prezzoOriginale * scontoPercentuale / 100.0;
        return prezzoOriginale - sconto;
    }

    @Override
    public String riepilogo() {
        return "Ordine PREMIUM (sconto " + scontoPercentuale + "%): " + super.riepilogo() +
                " -> totale scontato: " + totale() + "€";
    }
}


// Classe principale MAIN (gestisce input e dimostrazioni)
public class IntroComplex {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserire nome cliente:");
        String nome = scanner.nextLine();

        System.out.println("Inserire email cliente:");
        String email = scanner.nextLine();

        Cliente cliente = new Cliente(nome, email);

        // Esempio di prodotto didattico
        Prodotto p1 = new Prodotto("Mouse USB", 20.0, 22.0);

        System.out.println("Inserire quantità da acquistare:");
        int q = scanner.nextInt();

        Ordine ordineNormale = new Ordine(cliente, p1, q);
        OrdinePremium ordineVip = new OrdinePremium(cliente, p1, q, 15.0);

        System.out.println("\n--- RISULTATI ---");
        System.out.println(cliente.descrizione());
        System.out.println(p1.descrizione());
        System.out.println("\nOrdine normale:");
        System.out.println(ordineNormale.riepilogo());

        System.out.println("\nOrdine premium:");
        System.out.println(ordineVip.riepilogo());

        scanner.close();
    }
}
