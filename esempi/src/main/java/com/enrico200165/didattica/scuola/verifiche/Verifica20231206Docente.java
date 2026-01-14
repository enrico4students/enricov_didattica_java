/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enrico200165.didattica.scuola.verifiche;

import java.lang.Math;

/**
 *
 * @author enric
 */


class ReturnData {
    
    public int position1;
    public int position2;
    public int value1;
    public int value2;
    public int differenza;
    
    public String toString() {
        String ret = "";
        String sep = ", ";
        ret += "position1: "+position1;
        ret += sep+"position2: "+position2;
        ret += sep+"valore1: "+value1;
        ret += sep+"valore2: "+value2;
        ret += sep+"differenza: "+differenza;
        
        return ret;
    }
}

public class Verifica20231206Docente {
    
    
    static ReturnData analizzaVettore(int [] vett) {
        
        ReturnData ret = new ReturnData();
        int maxDiff = -1;
        for (int i = 0; i < vett.length-1; i++) {
            int differenza = java.lang.Math.abs(vett[i]-vett[i+1]);
            System.out.println("i="+i+ " differenza: "+differenza);
            if (differenza> maxDiff) {
                System.out.println("nuova max differenza: "+differenza);
                maxDiff = differenza;
                ret.position1 = i;
                ret.position2 = i+1;
                ret.value1 = vett[i];
                ret.value2 = vett[i+1];
                ret.differenza = differenza;
            }
        }
        
        return ret;
    }
    
    public static void main(String[] args) {
        int[] v = {2,4,1,6,4,8,4,9};
        //Verifica20231206Docente o = new Verifica20231206Docente());
        ReturnData ret = null;
        ret = analizzaVettore(v);

        System.out.println("\nstampo il vettore:");        
        for (int e:  v) {
            System.err.print(e+ ", ");
        }
        System.out.println("\nstampo i risultati dell'analisi del vettore:");        
        System.out.println(ret);
    }
    
}