import albergo.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("Portachiavi");
        Cliente c1=new Cliente ("Tizio");
        Cliente c2=new Cliente ("Caio");
        Cliente c3=new Cliente ("Sempronio");
        Chiave key1=new Chiave(1);
        Chiave key2=new Chiave(2);
        Chiave key3=new Chiave(3);
        key1.setCliente(c1);
        key2.setCliente(c2);
        key3.setCliente(c3);
        Portachiavi p=new Portachiavi ();
        p.restituisciChiave(key1);
        p.restituisciChiave(key2);
        p.restituisciChiave(key3);
        for (int i=1; i<p.getLunghezza()+1;i++){
            Chiave appoggio = p.getChiave(i-1);
            System.out.println("posizione "+i+"->"+appoggio.toString());
        }
        try{
            String nome="Tizia";
            Chiave darestituire=p.assegnaChiave(nome);
            for (int i=1; i<p.getLunghezza()+1;i++){
                Chiave appoggio = p.getChiave(i-1);
                System.out.println("posizione "+i+"->"+appoggio.toString());
            }
        }
        catch(ChiaveNonTrovataException e){System.out.println("Cliente non trovato");}
    }
}