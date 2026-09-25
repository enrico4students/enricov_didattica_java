/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interruttore;

/**
 *
 * @author 
 */
public class Interruttore {

    private boolean acceso;
    
    public Interruttore() {
        setAcceso(false);
    }
    
    public Interruttore(boolean a) {
        setAcceso(a);
    }
    
    public Interruttore(Interruttore i) {
        this.acceso = i.acceso;
    }

    private void setAcceso(boolean a) {
	acceso = a;
    }
    
    public boolean getAcceso() {
	return acceso;
    }

    public void accendi() {
        setAcceso(true);
    }
    
    public void spegni() {
        setAcceso(false);
    }
    
    @Override
    public String toString() {
        if (acceso) {
            return "Interruttore acceso";
        }
        else {
            return "Interruttore spento";
        } 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creazione interruttore stampante con costruttore senza parametri: spento
        Interruttore interruttoreStampante = new Interruttore();
        System.out.println(interruttoreStampante);
        
        
        // accensione interruttore stampante
        interruttoreStampante.accendi();
        System.out.println(interruttoreStampante);

        // spegnimento interruttore stampante
        interruttoreStampante.spegni();
        System.out.println(interruttoreStampante);

        // creazione interruttore stampante accesso con costruttore con un parametro        
        Interruttore interruttoreLampada = new Interruttore(true);
        System.out.println(interruttoreLampada);
    }
    
    
}
