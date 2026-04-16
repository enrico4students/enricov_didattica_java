package eccezioni.esempi.informatica.scuola.davidev.com;

/*

    PREREQUISITO: avere chiaro cosa è lo stack

    Senza eccezioni quando c'era un problema si ritornava un codice di errore, per questo in C non ci sono
    procedure ma solo funzioni. Con questo approccio si mischiava codice dedicato alle normali funzionalità
    e codice per la gestione di errori e situazioni eccezionali

    Le eccezioni sono state create per separare il codice che gestisce i casi normali dai casi di errore.

    Quando è lanciata un'eccezione c'è lo "stack unwinding" si esce immediatamente dalla funzione in cui
    è stata lanciata e da tutta la catena di chiamate in corso a meno che una delle funzioni non faccia
    il catch di QUEL TIPO (classe dell'oggetto eccezione lanciato) di eccezione o di una classe derivata

 */


public class EccezioniEsempio {

    // se non facciamo il catch dobbiamo mettere il throws
    static int dummy3(int x) throws MiaEccezione {

        // variabili dummy create solo per mostrarle nello stack di chiamate
        int inutile31;
        int inutile32;

        // operazioni senza senso
        inutile31 = 10;
        inutile32 = inutile31+10;

        if (x > 100)
            throw new MiaEccezione("E' un errore fatto apposta, e comunque sei un Negus");

        return x+inutile32;
    }

    // se non facciamo il catch dobbiamo mettere il throws
    static void dummy2(int y) throws MiaEccezione {
        // variabili dummy create solo per mostrarle nello stack di chiamate
        float pippo1;
        float pippo2;

        dummy3(y);
    }

    // se non facciamo il catch dobbiamo mettere il throws
    static int dummy1(int z)  throws MiaEccezione {

        // variabili dummy create solo per mostrarle nello stack di chiamate
        int pippo4;
        int pippo5;

        // dummy
        pippo5 = 3;
        pippo4 = pippo5+z;

        dummy2(z);

        return pippo4;
    }



    public static void main(String[] argv) {

        try {
            dummy1(10);
            System.out.println("ok, non c'è stata eccezione");
            dummy1(101);
            System.out.println("ok, non c'è stata eccezione");
        } catch (MiaEccezione e) {
            System.err.println("Ho catturato un oggetto eccezione (di classe MiaEccezione) contiene queste info:\n"
                    + e.infoAddizionaliErrore);
        }

    }
}
