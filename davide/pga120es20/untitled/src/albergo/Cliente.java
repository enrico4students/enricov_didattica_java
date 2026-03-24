package albergo;

public class Cliente {
    private String nominativo;

    public Cliente (String nominativo){
        this.nominativo=nominativo;
    }
    public Cliente (Cliente cliente){
        this.nominativo=cliente.getNominativo();
    }
    public String getNominativo(){
        return nominativo;
    }
}
