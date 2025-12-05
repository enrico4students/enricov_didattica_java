/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enrico200165.example;

class Ordinazione {

    String piatto;

    public String getPiatto() {
        return piatto;
    }

    public int getQuantita() {
        return quantita;
    }

    public String getNote() {
        return note;
    }
    int quantita;
    String note;

    public Ordinazione(String piatto, int quantita, String note) {
        this.piatto = piatto;
        this.quantita = quantita;
        this.note = note;
    }
    // costruttore di copia 
    public Ordinazione(Ordinazione ordinazione) {
        this.piatto = ordinazione.piatto;
        this.quantita = ordinazione.quantita;
        this.note = ordinazione.note;
    }
    
    // conversione in stringa 
    public String toString() {
        return piatto + note + quantita; 
    }
};

/*
public class ListaOrdinazioni { 
private Ordinazione[] ordinazioni; 
private volatile int indice inserimento; 
private volatile int indice estrazione; 
private volatile int numero ordinazionx,• 
// cost rut tore 
public ListaOrdinazioni (int numero massimo ordinazioni) { 
ordinazioni = 
new Ordinazione [numero massimo ordinazioni] ; 
indice inserimento 
indice estrazxone = 0 
numero ordinazioni = 
0; 
0; 
}





*/