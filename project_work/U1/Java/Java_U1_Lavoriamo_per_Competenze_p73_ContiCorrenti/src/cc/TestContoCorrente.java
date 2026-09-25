
package cc;

import java.util.Scanner;


public class TestContoCorrente 
{
    static Scanner sc;
    
    public static void main(String[] args) 
    {
        int scelta;
        String nome;
        int numeroConto;
        
        // prende input di nome e numero conto corrente
        sc = new Scanner(System.in);
 
        System.out.println("Inserisci il nome: ");
        nome = sc.nextLine();
        System.out.println("Inserisci il numero conto: ");
        numeroConto = sc.nextInt();

        // istanzia il nuvo conto corrente
        ContoCorrente conto = new ContoCorrente(nome, numeroConto);

        do 
        {
            System.out.println(" 1. Prelievo\n 2. Versamento\n 3. Stampa\n 0. Esci\nFai una scelta: ");
            scelta = sc.nextInt();
            switch (scelta) 
            {
                case 0:
                    System.out.println("Arrivederci " + conto.getCorrentista());
                    break;
                case 1:
                    if (effettuaPrelievo(conto) == true) 
                    {
                        System.out.println("Prelievo effettuato\nSaldo = " + conto.getSaldo());
                    }
                    else
                    {
                        System.out.println("Saldo inferiore alla cifra richiesta\nSaldo = " + conto.getSaldo());
                    }        
                    break;
                case 2:
                    effettuaVersamento(conto);
                    break;
                case 3:
                    System.out.println(conto);
                    break;
            }
        } while (scelta != 0);
    }
    
    private static boolean effettuaPrelievo(ContoCorrente conto)
    {
        float importo;

        System.out.println("Inserisci importo da prelevare: ");
        importo = sc.nextFloat();
        
        return conto.prelievo(importo);
    }    
    
    private static void effettuaVersamento(ContoCorrente conto)
    {
        float importo;
                
        System.out.println("Inserisci importo da versare: ");
        importo = sc.nextFloat();
        conto.versamento(importo);
        
    }
}
