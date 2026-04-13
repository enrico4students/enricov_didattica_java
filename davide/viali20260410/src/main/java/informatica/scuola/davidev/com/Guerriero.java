package informatica.scuola.davidev.com;

import java.util.ArrayList;
import java.util.Arrays;

public class Guerriero {

    String nome;
    String clan;
    int nr_armi = 0;

    Arma[] armi;

    public Guerriero(String nome, String clan) {
        this.nome = nome;
        this.clan = clan;

        armi = new Arma[5];
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    void aggiungiArma(Arma arma) throws ExcpArmiFull {

        if (nr_armi >= 4)
            throw new ExcpArmiFull();

        armi[nr_armi++] = arma;
    }

    public float potenzaMedia() {

        if (nr_armi == 0)
            return 0; // evita 0/0

        int totPotenza = 0;
        for (int i = 0; i < nr_armi; i++) {
            totPotenza += armi[i].getPotenza();
        }

        return (float)totPotenza/nr_armi;
    }

    @Override
    public String toString() {
        return "Guerriero{" +
                "nome='" + nome + '\'' +
                ", clan='" + clan + '\'' +
                ", armi=" + Arrays.toString(armi) +
                '}';
    }
}
