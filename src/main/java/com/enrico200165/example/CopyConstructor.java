/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.enrico200165.example;

// classi non public possono stare con altre classi nel file

class Intero {
    public int val;
    public Intero(int val) { this.val = val;} 
}

class Base {
    Intero a,b,c;
    Base() {
        a = new Intero(1); 
        b = new Intero(2); 
        c = new Intero(3);
    }
    
    public void dump() { System.out.println("a="+a.val+ " b="+b.val+" c="+c.val);}
}


// Non e' public, quindi puo' stare con altre classi nel file
class CopyConstructorFromUser extends Base {
    
    public CopyConstructorFromUser() {
        super();
    }
    
    CopyConstructorFromUser(CopyConstructorFromUser altroObj) {
        System.out.println("Copy constr definito da utente, clona i campi");
        this.a = new Intero(altroObj.a.val); 
        this.b = new Intero(altroObj.b.val); 
        this.c = new Intero(altroObj.c.val); 
    }
}

class CopyConstructorDefault extends Base {    
}





/**
 *
 * @author enrico
 */
public class CopyConstructor {

    public static void main(String argv[]) {

        CopyConstructorFromUser fromUser1 = new CopyConstructorFromUser(),
                fromUser2 = null;
        
        // copy constructor definito esplicitamente, controllare codice
        // per vedere cosa fa
        fromUser2 = new CopyConstructorFromUser(fromUser1);
        fromUser1.a = new Intero(100); // cambiamo un valore
        System.out.println("Copy Constructor da user, vediamo se il secondo oggetto e' cambiato");
        fromUser2.dump();


        CopyConstructorDefault default1 = new CopyConstructorDefault(), 
                default2 = null;

        default2 = default1; // copy constructor di default, copia tutti i membri
        default1.a = new Intero(100);
        System.out.println("Copy constructor di default, vediamo se il secondo oggetto e' cambiato");
        default2.dump();
    }
}
