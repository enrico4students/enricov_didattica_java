package compiti.ilprofdiinformatica.es8vettori.mako2008.com;

import java.util.Scanner;

public class Stagione {

    public Stagione() {
    }

    public Stagione(int nr_stagione, int nr_episodi, String sceneggiatore, String trama) {
        this.nr_stagione = nr_stagione;
        this.nr_episodi = nr_episodi;
        this.sceneggiatore = sceneggiatore;
        this.trama = trama;
    }

    public void inserisciDati() {

        if (scanner == null) {
            scanner = new Scanner(System.in);
        }

        System.out.print("Inserire numero stagione: ");
        this.nr_stagione = scanner.nextInt();

        System.out.print("Inserire numero di episodi nella serie: ");
        this.nr_episodi = scanner.nextInt();

        System.out.print("Inserire nome sceneggiatore: ");
        this.sceneggiatore = scanner.next();

        System.out.print("Inserire trama: ");
        this.trama = scanner.next();

        System.out.println("valori inseriti: "+this);
    }


    public void setNr_stagione(int nr_stagione) {
        this.nr_stagione = nr_stagione;
    }

    public void setNr_episodi(int nr_episodi) {
        this.nr_episodi = nr_episodi;
    }

    public void setSceneggiatore(String sceneggiatore) {
        this.sceneggiatore = sceneggiatore;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public int getNr_stagione() {
        return nr_stagione;
    }

    public int getNr_episodi() {
        return nr_episodi;
    }

    public String getSceneggiatore() {
        return sceneggiatore;
    }

    public String getTrama() {
        return trama;
    }

    @Override
    public String toString() {
        return "Stagione {" +
                "nr_stagione=" + nr_stagione +
                ", nr_episodi=" + nr_episodi +
                ", sceneggiatore='" + sceneggiatore + '\'' +
                ", trama='" + trama + '\'' +
                '}';
    }

    int nr_stagione;
    int nr_episodi;
    String sceneggiatore;
    String trama;

    java.util.Scanner scanner = null;
}
