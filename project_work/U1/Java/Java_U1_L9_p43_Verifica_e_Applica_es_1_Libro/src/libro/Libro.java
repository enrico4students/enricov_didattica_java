/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package libro;

/**
 * Classe che descrive il libro di una boiblioteca, con uno o più copie
 * @author 
 */
public class Libro {
    // definizione degli attributi 
    private String titolo;
    private String codice;
    private float prezzo;
    private String autore;
    private int numeroCopie;

    /**
     * Crea un nuovo libro
     * @param titolo titolo del libro
     * @param codice codice del libro assegnato dalla biblioteca
     * @param prezzo prezzo del libro
     * @param autore autore del libro
     * @param numeroCopie numero di copie
     */
    public Libro(String titolo,String codice, float prezzo, String autore,int numeroCopie) {
        this.titolo = titolo;
        this.codice = codice;
        this.prezzo = prezzo;
        this.autore = autore;
        this.numeroCopie = numeroCopie;
    }

    /**
     * esegue il prestito di un libro
     * @return indica se il prestito è andato a buon fine o meno
     */
    public boolean prestito() {
        boolean prestito = false;
        if (numeroCopie > 0) {
            numeroCopie--;
            prestito = true;
        }
        return prestito;   
    }
    
    /**
     * esegue la restituzione di un libro
     */
    public void restituzione() {
            numeroCopie++;
    }
    
    /**
     * restituisce una stringa che rappresenta il libro
     * @return stringa contenente la rappresentazione dell'oggetto
     */
    @Override
    public String toString() {
            String s = "";
            s = s + "titolo: " + titolo + "\t" + 
                    "codice: " + codice + "\t" + 
                    "prezzo: " + prezzo + "\t" +
                    "autore: " + autore + "\t" + 
                    "copie disponibili: " + numeroCopie;
            return s;
    }  
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // creazione del libro promessi sposi con 4 copie
        Libro sposi = new Libro("Promessi sposi", "A123", 10.0f, "Alessandro Manzoni", 4);

        // primo prestito
        if (!sposi.prestito()) {
            System.out.println("Prestito impossibile: copie esaurite");
        }
        System.out.println(sposi);
        
        // secondo prestito
        if (!sposi.prestito()) {
            System.out.println("Prestito impossibile: copie esaurite");
        }
        System.out.println(sposi);
        
        // terzo prestito
        if (!sposi.prestito()) {
            System.out.println("Prestito impossibile: copie esaurite");
        }
        System.out.println(sposi);
        
        // quarto prestito
        if (!sposi.prestito()) {
            System.out.println("Prestito impossibile: copie esaurite");
        }
        System.out.println(sposi);
        
        // creazione del libro Il Fu Mattia Pascal copn una copia
        Libro mattia = new Libro("Il Fu Mattia Pascal", "A124", 12.0f, "Luigi Pirandello", 1);

        // primo prestito
        if (!mattia.prestito()) {
            System.out.println("Prestito impossibile: copie esaurite");
        }
        System.out.println(mattia);
        
        // secondo prestito: IMPOSSIBILE perché l'unica copia è già in prestito
        if (!mattia.prestito()) {
            System.out.println("Prestito impossibile: copie esaurite");
        }
        System.out.println(mattia);

        // restituzione
        mattia.restituzione();
        System.out.println(mattia);
        
        // prestito: OK perché la copia è appena stata restituita
        if (!mattia.prestito()) {
            System.out.println("Prestito impossibile: copie esaurite");
        }
        System.out.println(mattia);

    }
}
