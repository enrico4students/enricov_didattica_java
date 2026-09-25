/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package geometria;

import static java.lang.Math.sqrt;

/**
 * Classe che rappresenta un rettangolo nel piano cartesiano con i lati paralleli agli assi
 * @author
 */
public class Rettangolo {

    private Punto pt;   // vertice in basso a sinistra
    private double base;   // base
    private double altezza;   // altezza

    /**
     * Crea un nuovo rettangolo a partire dal vertice in basso a sinistra, base e altezza
     * @param pt vertice in basso a sinistra
     * @param base base del rettangolo
     * @param altezza altezza del rettangolo
     */
    public Rettangolo(Punto pt, double base, double altezza) {
        this.pt = new Punto(pt);
        this.base = base;
        this.altezza = altezza;
    }

    /**
     * Crea un nuovo rettangolo a partire dal vertice in basso a sinistra e quello in alto a destra
     * @param pt vertice in basso a sinistra
     * @param pt2 vertice in alto a destra
     */
    public Rettangolo(Punto pt, Punto pt2) {
        this.pt = new Punto(pt);
        base = pt2.getX() - pt.getX();
        altezza = pt2.getY() - pt.getY();
    }

    /**
     * Crea un nuovo rettangolo come copia di un altro
     * @param r rettangolo di cui creare una copia
     */
    public Rettangolo(Rettangolo r) {
        // creo un nuovo punto copia del punto in basso a sinistra del rettangolo r
        Punto newPt = new Punto(r.getPt());
        pt = newPt;
        base = r.getBase();
        altezza = r.getAltezza();
    }

    /**
     * Restituisce il vertice in basso a sinistra
     * @return vertice in basso a sinistra
     */
    public Punto getPt() {
        return pt;
    }

    /**
     * Imposta il vertice in basso a sinistra
     * @param pt vertice in basso a sinistra
     */
    public void setPt(Punto pt) {
        this.pt = pt;
    }

    /**
     * Restituisce la base
     * @return base
     */
    public double getBase() {
        return base;
    }

    /**
     * Imposta la base
     * @param base base
     */
    public void setBase(double base) {
        this.base = base;
    }

    /**
     * Restituisce l'altezza
     * @return altezza
     */
    public double getAltezza() {
        return altezza;
    }

    /**
     * Imposta l'altezza
     * @param altezza altezza
     */
    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }
    
    /**
     * restituisce il punto in alto a destra
     * @return punto in alto a destra
     */
    public Punto getPt2() {
        return new Punto(pt.getX() + base, pt.getY() + altezza);
    }

    /**
     * Calcola l'area del rettangolo
     * @return area
     */
    public double getArea() {
        return base * altezza;
    }

    /**
     * Calcola il perimetro del rettangolo
     * @return perimetro
     */
    public double getPerimetro() {
        return 2 *(base + altezza);
    }
    
    /**
     * Calcola la diagonale del rettangolo
     * @return diagonale
     */
    public double getDiagonale() {
        return sqrt(base*base + altezza*altezza);
    }
    
    /**
     * Trasla il rettangolo
     * @param tx valore di traslazione lungo l'asse delle x (orizzontale)
     * @param ty valore di traslazione lungo l'asse delle y (verticale)
     */
    public void trasla(double tx, double ty) {
        pt.trasla(tx, ty);
    }

    /**
     * Restituisce il punto centrale del rettangolo
     * @return punto centrale
     */
    public Punto getCentro() {
        return new Punto(pt.getX() + base/2.0, pt.getY() + altezza/2.0);
    }
    
   @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

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
        final Rettangolo other = (Rettangolo) obj;
        if (this.base != other.base) {
            return false;
        }
        if (this.altezza != other.altezza) {
            return false;
        }
        return this.pt.equals(other.pt);
    }

    /**
     * Restituisce una descrizione del rettangolo
     * @return stringa che descrive il rettangolo
     */
    @Override
    public String toString() {
        return "Rettangolo{" + "Vertice inferiore sinistro: " + pt + ", base=" + base + ", altezza=" + altezza + '}';
    }

// Togliere i commenti al metodo main() per testare la classe
//    /**
//     *
//     * @param args
//     */
    public static void main(String[] args) {
        // crea il primo rettangolo con vertice in basso a sinistra in (1,1), base 3 e altezza 4
        Punto p1 = new Punto(1., 1.);
        Rettangolo r1 = new Rettangolo(p1, 3.0, 4.0);

        // crea il secondo rettangolo con vertice in basso a sinistra in (1,1) e in alto a destra (4,5)
        Punto p2 = new Punto(4.0, 5.0);        
        Rettangolo r2 = new Rettangolo(p1, p2);
        
        // crea il terzo rettanglo come copia di r2
        Rettangolo r3 = new Rettangolo(r2);
        // trasla r3
        r3.trasla(-1, -1);

        // stampa i tre rettangoli
        System.out.println("R1=" + r1.toString());
        System.out.println("R2=" + r2.toString());
        System.out.println("R3=" + r3.toString());

        // calcola e stampa area, perimetro, diagonale e centro di R1
        System.out.println("Area di R1: " + r1.getArea());
        System.out.println("Perimetro di R1: " + r1.getPerimetro());
        System.out.println("Diagonale di R1: " + r1.getDiagonale());
        System.out.println("Centro di R1: " + r1.getCentro());
        
        // controlla se R1 e R2 sono uguali
        if (r1.equals(r2)) {
            System.out.println("R1 e R2 sono uguali");
        } else {
            System.out.println("R1 e R2 non sono uguali");
        }
    }
}
