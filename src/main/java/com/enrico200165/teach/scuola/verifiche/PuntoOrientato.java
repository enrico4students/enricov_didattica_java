/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enrico200165.teach.scuola.verifiche;

/**
 *
 * @author enric
 */
class Punto {
    
    public boolean equals(Punto altroPunto){
        return true;
    }
}


public class PuntoOrientato extends Punto{

    public boolean equals(PuntoOrientato altroPunto){
        boolean ret = super.equals(altroPunto);
        boolean ret2 =  ret && true;
        return ret2;
    }
    
    public static void main(String[] argv) {
    
        PuntoOrientato p1 = new PuntoOrientato();
        PuntoOrientato p2 = new PuntoOrientato();
        System.out.println(p1.equals(p2));
    } 
}