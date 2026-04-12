package informatica.scuola.davidev.com;

public class Arma {

    String nome;
    int potenza;


    public Arma(String nome, int potenza) {
        this.nome = nome;
        this.potenza = potenza;
    }

    public Arma() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPotenza() {
        return potenza;
    }

    public void setPotenza(int potenza) {
        this.potenza = potenza;
    }

    @Override
    public String toString() {
        return "Arma{" +
                "nome='" + nome + '\'' +
                ", potenza=" + potenza +
                '}';
    }
}
