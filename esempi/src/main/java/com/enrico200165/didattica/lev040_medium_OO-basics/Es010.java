

class Persona {

    public void mangia() {
        System.out.println("Sono "+ nome + " "+ cognome+ " sto mangiando");
    }
    public void lavora() {
        System.out.println("Sono "+ nome + " "+ cognome+ " sto facendo il mio lavoro");
    }
    public String nome;
    public String cognome;
    public int eta;
}


class Studente extends Persona {
    
    @Override
    public void lavora() { 
        super.lavora();
        System.out.println("e lo faccio studiando");
    }
    
    public String tipoScuola;
}

class Pippo extends Persona {
    
}

public class Es010 {
    
    public static void main(String[] args) {

        Persona p1 = new Persona();
        p1.nome = "Enrico";
        p1.cognome = "Rocchi";
        p1.eta = 58;

        // p1.lavora();
        //p1.mangia();
        
        
        Studente s1 = new Studente();
        
        p1 = s1;
        s1.nome = "Davide Makoto";
        s1.cognome = "Viali";
        s1.eta = 16;
        
        p1.lavora();
        p1.mangia();
        p1 = new Pippo();
    }
}