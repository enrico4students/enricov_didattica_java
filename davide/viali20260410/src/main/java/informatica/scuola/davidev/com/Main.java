
package informatica.scuola.davidev.com;

public class Main {
    public static void main(String[] args) {

        Guerriero goldrake = new Guerriero("Goldrake", "stella Sirio");

        try {
            goldrake.aggiungiArma(new Arma("Raggio laser", 10));
            goldrake.aggiungiArma(new Arma("Maglio perforante", 20));
            goldrake.aggiungiArma(new Arma("Alabarda spaziale", 90));
        } catch (ExcpArmiFull e) {
            System.err.println("thrown eccezione"+e);
        }

        System.out.println("C'è un guerriero: "+goldrake);

    }
}