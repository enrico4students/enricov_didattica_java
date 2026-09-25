/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geometria;

/**
 * Classe che rappresenta un triangolo nel piano cartesiano
 *
 * @author
 */
public class Triangolo {

    private Punto p1;
    private Punto p2;
    private Punto p3;


    /**
     * Crea un triangolo p1 partire dai tre vertici
     *
     * @param p1 primo vertice
     * @param p2 secondo vertice
     * @param p3 terzo vertice
     */
    public Triangolo(Punto p1, Punto p2, Punto p3) {
        this.p1 = new Punto(p1);
        this.p2 = new Punto(p2);
        this.p3 = new Punto(p3);
    }

    /**
     * Crea un triangolo come copia di un altro
     *
     * @param t triangolo di cui creare una copia
     */
    public Triangolo(Triangolo t) {
        p1 = new Punto(t.p1);
        p2 = new Punto(t.p2);
        p3 = new Punto(t.p3);
    }

    /**
     * Imposta il primo vertice
     *
     * @param p1 primo vertice
     */
    public void setP1(Punto p1) {
        this.p1 = p1;
    }

    /**
     * Imposta il secondo vertice
     *
     * @param p2 secondo vertice
     */
    public void setP2(Punto p2) {
        this.p2 = p2;
    }

    /**
     * Imposta il terzo vertice
     *
     * @param p3 terzo vertice
     */
    public void setP3(Punto p3) {
        this.p3 = p3;
    }

    /**
     * Restituisce il primo vertice
     *
     * @return primo vertice
     */
    public Punto getP1() {
        return p1;
    }

    /**
     * Restituisce il secondo vertice
     *
     * @return secondo vertice
     */
    public Punto getP2() {
        return p2;
    }

    /**
     * Restituisce il terzo vertice
     *
     * @return terzo vertice
     */
    public Punto getP3() {
        return p3;
    }

    /**
     * Calcola l'area del triangolo
     *
     * @return area del triangolo
     */
    public double getArea() {
        double p = getPerimetro() / 2;
        return Math.sqrt(p * (p - p1.getDistanza(p2)) * (p - p2.getDistanza(p3)) * (p - p3.getDistanza(p1)));
    }

    /**
     * Calcola il perimetro del triangolo
     *
     * @return perimetro del triangolo
     */
    public double getPerimetro() {
        return p1.getDistanza(p2) + p2.getDistanza(p3) + p3.getDistanza(p1);
    }

    /**
     * Trasla il triangolo
     *
     * @param tx valore di traslazione lungo l'asse delle x (orizzontale)
     * @param ty valore di traslazione lungo l'asse delle y (verticale)
     */
    public void trasla(double tx, double ty) {
        this.p1.trasla(tx, ty);
        this.p2.trasla(tx, ty);
        this.p3.trasla(tx, ty);
    }

    /**
     * Restitusce il getBaricentro del triangolo
     *
     * @return getBaricentro
     */
    public Punto getBaricentro() {
        double xb = (p1.getX() + p2.getX() + p3.getX()) / 3.0;
        double yb = (p1.getY() + p2.getY() + p3.getY()) / 3.0;
        return new Punto(xb, yb);
    }

    /**
     * Restituisce una descrizione del triangolo
     *
     * @return stringa che descrive il triangolo
     */
    @Override
    public String toString() {
        return " P1: " + p1.toString() + " P2: " + p2.toString() + " P3: " + p3.toString();
    }

    /**
     * Confronta due triangoli
     * @param t triangolo rispetto p1 cui confrontare
     * @return true se i due triangoli sono uguali, false altrimenti
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
        final Triangolo other = (Triangolo) obj;
        return ( this.p1.equals(other.p1) && this.p2.equals(other.p2) && this.p3.equals(other.p3) );
    }

    // Togliere i commenti al metodo main() per testare la classe
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Punto p1 = new Punto(1., 1.);
        Punto p2 = new Punto(4., 5.);
        Punto p3 = new Punto(4., 1.);
        Punto p4 = new Punto(1., 4.);

        Triangolo t1 = new Triangolo(p1, p2, p3);
        Triangolo t2 = new Triangolo(p1, p3, p4);
        Triangolo t3 = new Triangolo(p1, p2, p2);
        Triangolo t4 = new Triangolo(t1);

        System.out.println("Triangolo T1: " + t1.toString());
        System.out.println("Perimetro triangolo T1:" + t1.getPerimetro());
        System.out.println("Area triangolo T1: " + t1.getArea());
        System.out.println("Triangolo T2: " + t2.toString());
        System.out.println("Perimetro triangolo T2:" + t2.getPerimetro());
        System.out.println("Area triangolo T2: " + t2.getArea());
        System.out.println("Triangolo T3: " + t3.toString());
        System.out.println("Perimetro triangolo T3:" + t3.getPerimetro());
        System.out.println("Area triangolo T3: " + t3.getArea());
        System.out.println("Triangolo T4: " + t4.toString());
        System.out.println("Perimetro triangolo T4:" + t4.getPerimetro());
        System.out.println("Area triangolo T4: " + t4.getArea());

        if (t1.equals(t4)) {
            System.out.println("I triangoli T1 e T4 sono uguali");
        } else {
            System.out.println("I triangoli T1 e T4 non sono uguali");
        }

        if (t1.equals(t2)) {
            System.out.println("I triangoli T1 e T2 sono uguali");
        } else {
            System.out.println("I triangoli T1 e T2 non sono uguali");
        }

    }
}
