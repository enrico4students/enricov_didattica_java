/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enrico2001665.medium.eccezioni;

/**
 *
 * @author enric
 */
public class ExcOrarioNonValido extends Exception {
    
    int ora, minuti, secondi;
    String messaggio;

    public ExcOrarioNonValido(int ora, int minuti, int secondi, String messaggio) {
        setOrario(ora, minuti, secondi);
        this.messaggio = messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getMessaggio() {
        return this.messaggio;
    }

    
    
    public void setOrario(int ora, int minuti, int secondi) {
        this.ora = ora;
        this.minuti= minuti;
        this.secondi = secondi;
    }

    @Override
    public String toString() {
        String msg;
        msg = super.toString();
        msg += "\n" + this.ora+":"+this.minuti+":"+this.secondi;
        return msg;
    }    
}

