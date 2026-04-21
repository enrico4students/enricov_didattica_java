package Viali;

public class Guerriero {

    private String nome;
    private String clan;
    private Arma[] armi;
    private int nrArmi = 0;

    public Guerriero(String nome, String clan) {
        this.nome = nome;
        this.clan = clan;
        this.armi = new Arma[5];
    }
    public void aggiungiArma(Arma arma){
        if(nrArmi == 5){
            throw new IllegalStateException("Array pieno!");
        }
        armi[nrArmi] = arma;
        nrArmi++;
    }
    public float potenzaMedia(){
        float a = 0;
        for(int i = 0; i<nrArmi; i++){
            a = a + armi[i].getPotenza();
        }
        return a/nrArmi;
    }

    public String getNome() {
        return nome;
    }

    public String getClan() {
        return clan;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public void stampaInfo(){
        System.out.println("Nome guuerriero: " + nome + "Clan guerriero: " + clan);
        for(int i = 0; i < nrArmi; i++){
            System.out.println(armi[i]);
        }
        System.out.println(potenzaMedia());
    }
}
