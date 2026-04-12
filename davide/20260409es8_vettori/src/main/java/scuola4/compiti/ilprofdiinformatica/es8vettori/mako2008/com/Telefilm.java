package compiti.ilprofdiinformatica.es8vettori.mako2008.com;


import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Telefilm {

    String nome;
    String genere;
    boolean prod_terminata;
    Stagione stagioni[] = null;

    int stagioniInserite = 0;

    public Telefilm() {
        stagioni = new Stagione[100]; // codice duplicato nell'altro costruttore, non va bene
        // andrebbe fattorizzato
    }

    @Override
    public String toString() {
        return "Telefilm { " +
                "nome='" + nome + '\'' +
                ", genere='" + genere + '\'' +
                ", prod_terminata=" + prod_terminata +
                ", stagioni=" + Arrays.toString(stagioni) +
                "}";
    }

    public Telefilm(String nome, String genere, boolean prod_terminata) {
        this.nome = nome;
        this.genere = genere;
        this.prod_terminata = prod_terminata;

        this.stagioni = new Stagione[100]; // duplicato nei due costruttori, non va bene
    }

    boolean yesNo(String prompt, boolean askYesNo)  {

        if (prompt != null && !prompt.isEmpty())
            if (askYesNo)
                prompt += " y/n:";
            System.out.print(prompt);


        String answer = scanner.next();

        return answer.toLowerCase(Locale.ROOT).equals("si") ||
                answer.toLowerCase(Locale.ROOT).equals("yes") ||
                answer.toLowerCase(Locale.ROOT).equals("y") ||
                answer.toLowerCase(Locale.ROOT).equals("s");
    }

    public boolean addStagione(Stagione stagione) {

        if (stagioniInserite >= stagioni.length) {
            System.err.println("Non c'è spazio per altre stagioni");
            return false;
        }

        stagioni[stagioniInserite] = stagione;
        stagioniInserite++;
        System.out.println("ora ci sono "+ stagioniInserite + " stagioni");

        return true;
    }

    public void inserisciDati() {

        if (this.scanner == null) {
            this.scanner = new Scanner(System.in);
        }
        System.out.println("Inserire il nome: ");
        this.nome = scanner.next();

        System.out.println("Inserire il genere: ");
        this.genere = scanner.next();

        prod_terminata = yesNo("Inserire se la produzione è terminata: ", true);

        while (yesNo("vuoi inserire una stagione", true)) {
            Stagione s = new Stagione();
            s.inserisciDati();
            addStagione(s);
        }
    }

    public float getNrMedioPuntate() {

        if (stagioni == null)
            return 0;

        int totEpisodi = 0;

        for (Stagione s: stagioni) {

            // se incontriamo il primo elemento non valido CONTROLLO FRAGILISSIO ED ERRATO CONCETTUALMENTE
            // in test di programmazione si viene bocciati subito
            if (s == null || s.getSceneggiatore() == null || s.getSceneggiatore().isEmpty())
                break;

            totEpisodi += s.getNr_episodi();
        }

        return (float)totEpisodi / stagioniInserite;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public boolean isProd_terminata() {
        return prod_terminata;
    }

    public void setProd_terminata(boolean prod_terminata) {
        this.prod_terminata = prod_terminata;
    }

    public Stagione[] getStagioni() {
        return stagioni;
    }

    public void setStagioni(Stagione stagioni[]) {
        this.stagioni = stagioni;
    }

    public boolean sceneggiatorePresente(String sceneggiatore) {

        for (Stagione s: stagioni) {
            if (s == null)
                continue;;
            if (sceneggiatore.equals(s.getSceneggiatore()))
                return true;
        }
        return false;
    }

    public void ordinaStagioni() {

        boolean daOrdinare = true;

        do {

            daOrdinare = false;
            for (int i = 0; i < stagioniInserite - 1; i++) {
                if (stagioni[i].nr_episodi > stagioni[i + 1].nr_episodi) {

                    // scambio di valori, necessariamente tramite variabile temporanea
                    Stagione temp = stagioni[i]; // salvo valore elemento corrente
                    stagioni[i] = stagioni[i + 1];  // assegno a corrente valore del successivo
                    stagioni[i + 1] = temp;  // assegno al successivo il valore dell'elemento corrente che avevo salvat in temp

                    daOrdinare = true;
                }
            }
        } while (daOrdinare);
    }


    java.util.Scanner scanner = null;
}
