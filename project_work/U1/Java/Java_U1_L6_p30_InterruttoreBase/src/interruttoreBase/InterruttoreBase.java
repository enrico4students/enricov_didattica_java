/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interruttoreBase;

/**
 *
 * @author 
 */
public class InterruttoreBase {

    private boolean acceso;
    

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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creazione interruttore stampante con costruttore senza parametri: spento
        InterruttoreBase interruttoreStampante = new InterruttoreBase();
        System.out.println(interruttoreStampante.getAcceso());
        
        
        // accensione interruttore stampante
        interruttoreStampante.accendi();
        System.out.println(interruttoreStampante.getAcceso());

        // spegnimento interruttore stampante
        interruttoreStampante.spegni();
        System.out.println(interruttoreStampante.getAcceso());
    }
    
    
}
