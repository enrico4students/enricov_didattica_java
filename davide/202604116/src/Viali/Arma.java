package Viali;

public class Arma {

    private String nome;
    private float potenza;

    public Arma() {}
    public Arma(String nome, float potenza){
        this.nome = nome;
        this.potenza = potenza;
    }

    public String getNome() {
        return nome;
    }

    public float getPotenza() {
        return potenza;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPotenza(float potenza) {
        this.potenza = potenza;
    }

    @Override
    public String toString(){
        return "nome arma: " + nome + " potenza arma: " + potenza;
    }
}
