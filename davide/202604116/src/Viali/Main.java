package Viali;

public class Main {

    public static void main (String[] args){

        Guerriero g1 = new Guerriero("Eisen", "N/A");
        Arma arma1 = new Arma("Ascia", 100);
        Arma arma2 = new Arma("Scudo", 10);
        g1.aggiungiArma(arma1);
        g1.aggiungiArma(arma2);
        g1.aggiungiArma(new Arma("spada", 110));
        g1.stampaInfo();
    }
}
