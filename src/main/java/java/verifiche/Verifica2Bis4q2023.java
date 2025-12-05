/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java.verifiche;

/**
 *
 * @author enric
 */
abstract class Animale {

    public String specie;
    public int vita;

    public Animale(String specie) {
        this.specie = specie;
    }

    public abstract void migra();
}

class Mammifero extends Animale {

    public int nrGambe;

    public Mammifero(String specie) {
        super(specie);
    }

    @Override
    public void migra() {
    }
}

class Uccello extends Animale {

    public boolean puoVolare;

    public Uccello(String specie) {
        super(specie);
    }

    @Override
    public void migra() {
    }
}

public class Verifica2Bis4q2023 {

    
    void verificaAcc() {
        
        { //2 
            //Animale anim02 = new Animale("0001");
            // anim01 = anim02;
        }
    }
    
}
