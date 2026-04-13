// due thread con nome 

package com.enrico200165.didattica.scuola.tpsit.y4.lab10;

public class ProvaNani2{
  public static void main(String args[]){
    Thread thr1 = new ContaINani2("topolino");
    Thread thr2 = new ContaINani2("pippo");
    thr1.start(); 
    thr2.start(); 
  }
}

