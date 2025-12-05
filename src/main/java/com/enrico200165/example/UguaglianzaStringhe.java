/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enrico200165.example;

/**
 *
 * @author enrico
 */
public class UguaglianzaStringhe {
    public static void main(String[] argv) {
        String s1 = "10";
        String s2 = "10";
    
        if (s1 == s2) {
            System.out.println("per == sono uguali");
        } else {
            System.out.println("per == sono diversi");
        }

        if (s1.equals(s2)) {
            System.out.println("per == sono uguali");
        } else {
            System.out.println("per == sono diversi");
        }

        
    }
}
