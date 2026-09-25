/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cassaautomatica;

/**
 *
 * @author I.L.
 */
public class CassaAutomatica {

    /**
     * prezzo totale dei prodotti inseriti
     */
    private float prezzoTotale;
    
    /**
     * costruisce una cassa automatica
     */
    public CassaAutomatica() {
        prezzoTotale = (float) 0.0;
    }
    
    /**
     * aggiunge il prezzo di un prodotto al prezzo totale del conto
     * @param prezzoProdotto prezzo del singolo prodotto da aggiungere
     */
    public void registraPrezzo(float prezzoProdotto) {
        prezzoTotale += prezzoProdotto;
    }
    
    /**
     * calcola il prezzo dei prodotti inseriti
     * @return prezzo totale dei prodotti inseriti
     */
    public float calcolaTotale() {
        return prezzoTotale;
    }
    
    /**
     * riceve il pagamento e fornisce l'eventuale resto
     * @param pagamento importo pagato
     * @return resto dovuto
     */
    public float riceviPagamento(float pagamento) {
        float resto = pagamento - prezzoTotale;
        prezzoTotale = (float) 0.0;
        return resto;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CassaAutomatica cassa = new CassaAutomatica();
        
        cassa.registraPrezzo(2.56f);
        cassa.registraPrezzo(3.44f);
        cassa.registraPrezzo(5.50f);
        cassa.registraPrezzo(4.50f);
        
        System.out.println("Spesa totale: " + cassa.calcolaTotale());
        System.out.println("Pagamanto di 20 euro");
        double resto = cassa.riceviPagamento(20.0f);
        if (resto == 0) {
            System.out.println("Pagamento eseguito");
        }
        else if (resto > 0) {
            System.out.println("Pagamento eseguito.\nResto: " + resto);
        }
        else {
            System.out.println("Pagamento NON eseguito: importo insufficiente");
        }
    }

}
