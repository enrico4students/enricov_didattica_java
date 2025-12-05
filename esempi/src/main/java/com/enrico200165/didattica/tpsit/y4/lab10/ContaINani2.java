package com.enrico200165.didattica.tpsit.y4.lab10;// thread con parametro il nome dello stesso

public class ContaINani2 extends Thread{
  public ContaINani2(String nome){                // costruttore
    super();
    setName(nome);  
  }
  
  public void run(){
    for(int i=0;i<7;i++){
      System.out.println((i+1)+" "+getName());
    }
  }
}

