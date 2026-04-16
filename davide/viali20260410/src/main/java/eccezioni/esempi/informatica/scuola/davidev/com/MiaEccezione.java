package eccezioni.esempi.informatica.scuola.davidev.com;

// Creiamo una classe per oggetti eccezione personalizzati
class MiaEccezione extends Exception {

    // in generale si fanno membri private e si usano getters e setters, qui abbiamo fretta
    public String infoAddizionaliErrore;

    // costruttore
    public MiaEccezione(String info) {
        infoAddizionaliErrore = info;
    }
}
