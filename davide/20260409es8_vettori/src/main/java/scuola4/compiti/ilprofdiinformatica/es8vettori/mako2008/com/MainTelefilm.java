package scuola4.compiti.ilprofdiinformatica.mako2008.com;
/*
https://www.ilprofdinformatica.it/le-mie-lezioni/programmazione-java/vettore-di-oggetti

Esercizio 8: Piattaforma Streaming

Si intende progettare un'applicazione per gestire i telefilm.
In particolare, di ogni telefilm si intende memorizzare:
    Il nome del telefilm;
    Il genere del telefilm;
    Se la produzione del telefilm è in corso oppure è terminata;
    L'insieme delle stagioni da cui è composto.
    Per ogni stagione si è interessati a tenere traccia delle seguenti informazioni:
        numero di episodi;
        nome dello sceneggiatore;
        la trama;
        numero della stagione (se è la prima il numero è 1, se è la seconda il numero è 2...).

Creare un'applicazione in java contenente i seguenti file:

"Stagione.java" contenente:
(a) Gli attributi specificati nel testo, il costruttore di default, il costruttore parametrizzato, i metodi get e set, il metodo toString;
(b) Il metodo inserisciDati che consente di inserire da tastiera tutti i valori da attribuire agli attributi di una stagione.

2."Telefilm.java" contenente:
(a) Gli attributi specificati nel testo, il costruttore di default, il costruttore parametrizzato, i metodi get e set, il metodo toString;
(b) Il metodo inserisciDati che consente di inserire da tastiera tutti i valori da attribuire agli attributi di un telefilm;
(c) Un metodo in grado di calcolare il numero medio di puntate presenti nel telefilm per ogni stagione;
(d) Un metodo che preso come parametro il nome di uno sceneggiatore ritorna true se tale sceneggiatore è presente in almeno una stagione del telefilm, altrimenti ritorna false.
(e) ** Un metodo che ordina il vettore delle stagioni in base al numero della stagione, dalla prima all'ultima.

3. "MainTelefilm.java" in grado di:
(a) Creare un telefilm con input gestito da tastiera;
(b) Mostrare a schermo il telefilm creato;
(c) Indicare il numero medio di puntate presenti nel telefilm per ogni stagione;
(d) Verificare se "Luca Galli" è stato sceneggiatore di almeno una stagione del telefilm creato;
(e) ** Ordinare il vettore e mostrare nuovamente a schermo il telefilm.

*/

import compiti.ilprofdiinformatica.es8vettori.mako2008.com.Telefilm;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainTelefilm {

    public static Telefilm creaTelefilm() {
        Telefilm t = new Telefilm();
        t.inserisciDati();

        return t;
    }

    public static Telefilm mostraTelefilm(Telefilm t) {
        System.out.println(t);

        return t; // non strettamente necessario, fatto per consentire chaining
    }

    public static Telefilm mostraPuntateMedie(Telefilm t) {
        System.out.println(t.getNrMedioPuntate());
        return t; // non strettamente necessario, fatto per consentire chaining
    }


    public static void main(String[] args) {

        Telefilm t1 = new Telefilm();

        /* creazione manuale per non dover digitare durinate lo sviluppo
        t1 = new Telefilm("zorro","avventura",true);
        Stagione s = new Stagione(1,50,"Viali", "c'era una volta"); t1.addStagione(s);
        s = new Stagione(2,100,"Luca Galli", "c'era una volta");    t1.addStagione(s);
        s = new Stagione(3,30,"Luca", "c'era una volta");           t1.addStagione(s);
        */

        t1.inserisciDati();
        System.out.println("il telefilm inserito è: " + t1);
        System.out.println("Media puntate:"+t1.getNrMedioPuntate());

        System.out.print("Luca Galli è ");
        if (t1.sceneggiatorePresente("Luca Galli")) {
            System.out.println("presente");
        } else {
            System.out.println("assente");
        }

        t1.ordinaStagioni();
        System.out.println("Telefilm inserito dopo ordinamento stagioni:\n"+t1);
    }
}