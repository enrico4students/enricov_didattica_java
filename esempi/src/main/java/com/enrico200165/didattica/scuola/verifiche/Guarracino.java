/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enrico200165.didattica.scuola.verifiche;

import java.io.*;
import java.util.Scanner;

public class Guarracino {

    public int v[]=new int [5];

    public static void main(String[] args) {
        Guarracino x= new Guarracino();
        Scanner y=new Scanner(System.in);
        System.out.println("inserire una sequenza di numeri chiusa da -1");
        for(int i=0; i<x.v.length; i++){
            x.v[i]=y.nextInt();
        }
       for(int z=0; z<x.v.length; z++){
           if(x.v[z] %2 !=0){
               System.out.println("il valore disparo è " +x.v[z]);
           }
       }
    }
}
