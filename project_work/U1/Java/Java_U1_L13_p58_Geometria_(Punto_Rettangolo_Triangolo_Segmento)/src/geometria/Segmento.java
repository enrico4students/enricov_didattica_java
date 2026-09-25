package geometria;

/**
 * Classe che rappresenta un segmento nel piano cartesiano
 * @author 
 */
public class Segmento {
    private Punto p1;
    private Punto p2;
    
    /**
     * Crea un nuovo segmento a partire dai suoi estremi
     * @param p1 primo estremo
     * @param p2 secondo estremo
     */
    public Segmento(Punto p1, Punto p2) {
        this.p1 = new Punto(p1);
        this.p2 = new Punto(p2);
    }

    /**
     * Crea un nuovo segmento a partire dalle coordinate dei suoi punti estremi
     * @param x1 x del primo punto
     * @param y1 y del primo punto
     * @param x2 x del secondo punto
     * @param y2 y del secondo punto
     */
    public Segmento(double x1, double y1, double x2, double y2) {
        this.p1 = new Punto(x1, y1);
        this.p2 = new Punto(x2, y2);
    }

/**
     * Crea un nuovo segmento come copia di un altro segmento
     * @param s2 segmento di cui creare una copia
     */
    public Segmento(Segmento s2) {
        this.p1 = new Punto(s2.getP1());
        this.p2 = new Punto(s2.getP2());
    }

    /**
     * Restituisce il primo estremo del segmento
     * @return primo estremo del segmento
     */
    public Punto getP1() {
        return p1;
    }

    /**
     * Imposta il primo estremo del segmento
     * @param p1 primo estremo del segmento
     */
    public void setP1(Punto p1) {
        this.p1 = p1;
    }

    /**
     * Restituisce il secondo estremo del segmento
     * @return secondo estremo del segmento     
     */
    public Punto getP2() {
        return p2;
    }

    /**
    * Imposta il secondo estremo del segmento
     * @param p2 secondo estremo del segmento
     */
    public void setP2(Punto p2) {
        this.p2 = p2;
    }
    
    /**
     * calcola la lunghezza del segmento
     * @return lunghezza del segmento
     */
    public double getLunghezza() {
        return p1.getDistanza(p2);
    }
    
    /**
     * Calcola il punto medio del segmento 
     * @return punto medio del segmento
     */
    public Punto getPuntoMedio() {
        double xm = (p1.getX() + p2.getX()) / 2.0;
        double ym = (p1.getY() + p2.getY()) / 2.0;
        
        return new Punto(xm, ym);
    }
    
    /**
     * Trasla un segmento 
     * @param tx valore di traslazione in X
     * @param ty valore di traslazione in Y
     */  
    public void trasla(double tx, double ty) {
        p1.trasla(tx, ty);
        p2.trasla(tx, ty);
    }

    /**
     * Confronta due segmenti
     * @param s2 segmento rispetto a cui confrontare
     * @return true se i due segmenti sono uguali, false altrimenti
     */
    public boolean equals(Segmento s2) {
        return p1.equals(s2.getP1()) && p2.equals(s2.getP2());
    }

    /**
     * Restituisce una descrizione del segmento
     * @return stringa che descrive il segmento
     */
    @Override
    public String toString() {
        return "p1=" + p1 + " --- p2=" + p2;
    }
    
    // Togliere i commenti al metodo main() per testare la classe
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Punto p1 = new Punto(0.0, 10.0);
        Punto p2 = new Punto(4.0, 13.0);
        // crea il primo segmento
        Segmento s1 = new Segmento(p1, p2);

        // crea il secondo segmento
        Segmento s2 = new Segmento(new Punto(10.0, 0.0), new Punto(10.0, 20.0));

        // stampa i due segmenti
        System.out.println("S1=" + s1.toString());
        System.out.println("S2=" + s2.toString());

        // calcola e stampa lunghezza e punto medio di S1
        System.out.println("Lunghezza di S1: " + s1.getLunghezza());
        System.out.println("Punto medio di S1: " + s1.getPuntoMedio());

        // trasla il segmento S1
        s1.trasla(10.0, 10.0);
        
        // controlla se S1 e S2 sono uguali
        if (s1.equals(s2)) {
            System.out.println("S1 e S2 sono uguali");
        } else {
            System.out.println("S1 e S2 non sono uguali");
        }
    }

}
