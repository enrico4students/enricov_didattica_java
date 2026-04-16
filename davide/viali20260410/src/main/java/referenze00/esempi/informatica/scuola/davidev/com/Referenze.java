package referenze00.esempi.informatica.scuola.davidev.com;


class Pluto { // non ci facciamo quasi nulla
    public int x;
}

class DerivataDaPluto extends Pluto {};


class Pippo {

    public int x;

    public Pippo( ) { x = 11;}

    public Pippo ciao() {
        System.out.println("Oggetto di classe " +this.getClass().getName()+" attributo x vale: "+x);
        return this; // ritorniamo una referenza allo stesso oggetto ci consentira di fare il chaining
    }
}


public class Referenze {

    static void funzioneDummy(Pippo p) {
        // faccio una stupidata, chiamo il metodo ciao
        p.ciao();
    }

    public static void main(String[] argv) {


        // le variabili il cui tipo è una classe sono referenze
        Pippo a;  // non si riferisce a nessun oggetto
        // System.out.println(a.x);  // errore, a NON "riferisce" nessun oggetto valido

        // oggetti senza referenze
        new Pippo(); // creiamo un oggetto di classe Pippo e non ci facciamo nulla

        // creiamo un oggetto di classe Pippo e stampiamo il suo attributo.x
        System.out.println(   (new Pippo()).x    );

        (new Pippo()).ciao();  // creiamo un oggetto di classe pippo e invochiamo metodi

        (new Pippo()).ciao().ciao().ciao(); // ciao ritorna l'oggetto quindi possiamo concatenare le chiamate

        // chiamiamo una funzione con un oggetto senza referenza

        funzioneDummy(new Pippo());  // mettere breakpoint ed entrare

        // normale uso
        a = new Pippo();  // creiamo oggetto di classe Pippo, e assegnamo il suo indirizzo ad a
        System.out.println(a.x);
        Pippo b = a;  // b "riferisce" lo stesso oggetto di a
        b.x = 5;
        System.out.println(a.x); // stampo l'attributo x dell'oggetto riferito da a

        // tipizzazione e polimorfismo controllato dall'ereditarietà
        Pluto p;
        // p = new Pippo();  // errore p può riferire solo oggetti di classe Pluto o derivate
        p = new Pluto();
        p = new DerivataDaPluto();


    }
}
