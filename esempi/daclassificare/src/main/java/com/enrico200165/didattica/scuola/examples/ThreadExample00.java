/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.enrico200165.didattica.scuola.examples;


class Automobile extends Thread {
    @Override
    public void run() { 
        for (int i = 0; i < 1000; i++)
            for (int j = 0; j < 1; j++)
                System.out.println("auto in esecuzione");
    }
}

public class ThreadExample00 {
    
    public static void main(String[] args) {
        Automobile yaris = new Automobile();
        yaris.start();
    }   
}
