package bigliettocinema;

public class BigliettoCinema {
    public static int nBiglietti = 0; // numero totale di biglietti
    private int numero;
    
    public BigliettoCinema()
    {
        numero = ++nBiglietti;
    }
    
    @Override
    public String toString()
    {
        return "Biglietto numero " + numero + " di " + nBiglietti;
    }

    public static void main(String[] args) {
        BigliettoCinema b1 = new BigliettoCinema();
        BigliettoCinema b2 = new BigliettoCinema();
        BigliettoCinema b3 = new BigliettoCinema();
        
        System.out.println(b1.toString());
        System.out.println(b2.toString());
        System.out.println(b3.toString());
        
        // accesso alla variabile di classe (static) tramite oggetto
        System.out.println("Biglietti totali: " + b1.nBiglietti);

        // accesso alla variabile di classe (static) tramite classe
        System.out.println("Biglietti totali: " + BigliettoCinema.nBiglietti);        
    }
    
}
