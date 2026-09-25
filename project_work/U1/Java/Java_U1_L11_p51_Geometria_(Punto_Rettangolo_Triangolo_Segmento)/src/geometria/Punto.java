package geometria;

import static java.lang.Math.sqrt;

/**
 * Classe che rappresenta un punto del piano cartesiano
 * @author
 */
public class Punto {

    private double x;
    private double y;

    /**
     * Crea un punto con coordinate nell'origine degli assi
     */
    public Punto() {
        this(0.0, 0.0);
    }

    /**
     * Crea un punto a partire dalle sue coordinate
     * @param x ascissa del punto
     * @param y ordinata del punto
     */
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Crea un nuovo punto copia di un altro
     * @param p punto di cui creare la copia
     */
    public Punto(Punto p) {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Imposta il valore dell'ascissa del punto
     * @param x ascissa del punto
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Imposta il valore dell'ordinata del punto
     * @param y ordinata del punto
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Restituisce l'ascissa del punto
     * @return ascissa del punto
     */
    public double getX() {
        return x;
    }

    /**
     * Restituisce l'ordinata del punto
     * @return l'ordinata del punto
     */
    public double getY() {
        return y;
    }

    /**
     * Calcola la getDistanza da un altro punto
     * @param p punto rispetto al quale calcolare la getDistanza
     * @return getDistanza
     */
    public double getDistanza(Punto p) {
        double dx = x - p.getX();
        double dy = y - p.getY();
        return sqrt((dx * dx) + (dy * dy));
    }
    
    /**
     * Restituisce il quadrante in cui si trova il punto 
     * @return quadrante in cui si trova il punto
     * 1-4 --> numero del quadrante
     * 0 --> il punto giace su uno degli assi
     */
    public int getQuadrante() {
        int quadrante =0;
        if (x > 0 && y > 0)
            quadrante = 1;
        else if (x < 0 && y > 0)
            quadrante = 2;
        else if (x < 0 && y < 0)
            quadrante = 3;
        else if (x > 0 && y < 0)
            quadrante = 4;
            
        return quadrante;
    }

    /**
     * Confronta le coordinate di due punti
     * @param obj oggetto rispetto al quale effettuare il confronto
     * @return true se i due punti sono uguali, false altrimenti
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Punto altroPunto = (Punto) obj;

        return (x == altroPunto.x) && (y == altroPunto.y);
    
    }

    /**
     * Trasla un punto 
     * @param tx valore di traslazione in X
     * @param ty valore di traslazione in Y
     */
    public void trasla(double tx, double ty) {
        x += tx;
        y += ty;
    }

    /**
     * Restituisce una descrizione del punto
     * @return stringa che descrive il punto, nel formato (X,Y)
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    // Togliere i commenti al metodo main() per testare la classe
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
   
        // crea due punti
        Punto p1 = new Punto(1.0, 0.0);
        Punto p2 = new Punto(5.0, -2.0);
    
        // trasla i due punti
        p1.trasla(1.0, 0.0);
        p2.trasla(1.0, 1.0);
        
        System.out.println("P1=" + p1.toString());
        System.out.println("P2=" + p2.toString());

        System.out.println("Distanza P1-P2: " + p1.getDistanza(p2));

        System.out.println("Quadrante di P1: " + p1.getQuadrante());
        System.out.println("Quadrante di P2: " + p2.getQuadrante());
        
        // controlla se P1 e P2 sono uguali
        if (p1.equals(p2)) {
            System.out.println("P1 e P2 sono uguali");
        } else {
            System.out.println("P1 e P2 non sono uguali");
        }        
    }
}
