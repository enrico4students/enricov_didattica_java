package albergo;

public class Chiave {
    private int numeroCamera;
    private Cliente cliente;

    public Chiave (int numeroCamera){
        this.numeroCamera = numeroCamera;
        cliente=null;
    }
    public Chiave (Chiave chiave){
        this.numeroCamera=chiave.getNumeroCamera();
    }
    public int getNumeroCamera() {
        return numeroCamera;
    }
    public boolean uguale(Chiave c){
        if (this.numeroCamera==c.getNumeroCamera())
            return true;
        else return false;
    }
    public String getNominativo(){
        return cliente.getNominativo();
    }
    public void setCliente(Cliente cliente){
        this.cliente=cliente;
    }
    public void liberaChiave(String nomeCliente){
        if(cliente.getNominativo().equals(nomeCliente)) cliente=null;
    }
    public String toString(){
        if (cliente!=null) return "questa è la chiave della camera "+numeroCamera+" e il cliente è "+cliente.getNominativo();
        else               return "questa è la chiave della camera "+numeroCamera;
    }
}
