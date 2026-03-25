package albergo;

public class Portachiavi {
    private Chiave vettoreChiavi[];
    final static int NUMMAX=5;
    private int lunghezza;
    public Portachiavi(){
        vettoreChiavi= new Chiave[NUMMAX];
        lunghezza=0;
    }
    public void restituisciChiave (Chiave chiave){
        if (lunghezza<NUMMAX){
            vettoreChiavi[lunghezza]= chiave;
            lunghezza++;}
        else if (lunghezza>NUMMAX-1)
            System.out.println("ERRORE");
    }
    public Chiave assegnaChiave(String nominativo) throws ChiaveNonTrovataException{
        for (int i =0;i <lunghezza;i++){
            if(vettoreChiavi[i].getNominativo().equals(nominativo)){
                Chiave appoggio= vettoreChiavi[i];
                for (int j=i; j<lunghezza-1;j++)
                    vettoreChiavi[j]=vettoreChiavi[j+1];
                lunghezza--;
                vettoreChiavi[lunghezza]=null;
                return appoggio;
            }}throw new ChiaveNonTrovataException();}

    public Chiave assegnaChiave(int numeroCamera) throws ChiaveNonTrovataException{
        for (int i =0;i <lunghezza;i++){
            if(vettoreChiavi[i].getNumeroCamera()==numeroCamera){
                Chiave appoggio= vettoreChiavi[i];
                for (int j=i; j<lunghezza-1;j++)
                    vettoreChiavi[j]=vettoreChiavi[j+1];
                lunghezza--;
                vettoreChiavi[lunghezza]=null;
                return appoggio;}}
        throw new ChiaveNonTrovataException();
    }
    public int getLunghezza(){
        return lunghezza;
    }
    public Chiave getChiave(int i){
        return vettoreChiavi[i];
    }

}
