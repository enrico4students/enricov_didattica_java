
---

## LIVELLO 3 — LEZIONI COMPLETE (Java avanzato)

Indice lezioni:

22. Lambda e programmazione funzionale
23. Stream API
24. Generics
25. Enum avanzate
26. Programmazione concorrente (thread e basi di java.util.concurrent)
27. Sistema Modulare (Java 9+)
28. Sviluppo applicazioni moderne (JavaFX e introduzione a Spring/REST)
29. Testing con JUnit
30. Build tools: Maven e Gradle

---

## LEZIONE 22 — Lambda e Programmazione Funzionale

22.1 Motivazione: perché le lambda in Java
Prima di Java 8, per passare “comportamenti” (funzioni) si usavano classi anonime verbose. Le lambda permettono di:

* passare blocchi di codice come parametri a metodi;
* ridurre il codice boilerplate;
* scrivere in stile più dichiarativo (soprattutto con Stream API).

L’obiettivo è scrivere codice più compatto e leggibile, soprattutto per operazioni su collezioni.

22.2 Interfacce funzionali
Un’interfaccia funzionale è un’interfaccia con un solo metodo astratto.

Esempi nella libreria standard:

* Runnable (metodo run)
* Comparator<T> (metodo compare)
* java.util.function.Predicate<T>
* java.util.function.Function<T, R>
* java.util.function.Consumer<T>

Esempio di interfaccia funzionale personalizzata:

```
@FunctionalInterface
public interface Operazione {
    int calcola(int a, int b);
}
```

22.3 Sintassi delle lambda
Forma generale:

```
(parametri) -> espressione
```

oppure

```
(parametri) -> {
    // blocco di codice
    return risultato;
}
```

Esempi:

```
Operazione somma = (a, b) -> a + b;

Runnable r = () -> {
    System.out.println("Esecuzione in un thread");
};
```

Caratteristiche:

* se c’è un solo parametro e il tipo è deducibile, si possono omettere le parentesi;
* se il corpo è una sola espressione, si può omettere return e le graffe.

22.4 Method reference
Sono una forma compatta di lambda quando il corpo chiama solo un metodo esistente.

Forme principali:

* Oggetto::metodo
* Classe::metodoStatico
* Classe::metodoIstanza (da usare in contesti particolari)
* Classe::new (reference a costruttore)

Esempio:

```
lista.forEach(System.out::println);
```

22.5 Vantaggi principali

* Codice più compatto per operazioni su collezioni.
* Migliore integrazione con Stream API.
* Possibilità di scrivere API più espressive (metodi che accettano funzioni come parametri).

---

## LEZIONE 23 — Stream API

23.1 Cos’è uno Stream
Uno Stream è un flusso di elementi supportato da operazioni che permettono:

* elaborazione dichiarativa (cosa fare, non come);
* concatenazione di operazioni (pipeline);
* valutazione pigra (lazy): l’elaborazione avviene solo all’operazione terminale.

Importante: uno Stream **non è** una struttura dati, ma una vista su una sorgente (collection, array, file, generatore).

23.2 Struttura di una pipeline
Una pipeline tipica contiene:

* una sorgente (Collection, array, file, Stream.generate, ecc.);
* zero o più operazioni intermedie (filter, map, sorted, distinct, ecc.);
* una operazione terminale (forEach, collect, reduce, count, ecc.).

Esempio:

```
List<String> nomi = List.of("Anna", "Marco", "Luca", "Ada");

long count = nomi.stream()
                 .filter(n -> n.length() == 4)
                 .count();
```

23.3 Operazioni intermedie principali

* filter(Predicate): seleziona elementi che soddisfano una condizione;
* map(Function): trasforma ogni elemento;
* sorted(): ordina gli elementi;
* distinct(): elimina i duplicati;
* limit(n): prende solo i primi n elementi;
* skip(n): salta i primi n elementi.

Esempio:

```
List<Integer> numeri = List.of(1, 2, 3, 4, 5, 6);

List<Integer> doppiPari = numeri.stream()
                                .filter(x -> x % 2 == 0)
                                .map(x -> x * 2)
                                .toList();
```

23.4 Operazioni terminali principali

* forEach: itera sugli elementi (effetto collaterale);
* collect: raccoglie in una collection (o altro tipo);
* reduce: combina gli elementi in un singolo risultato;
* count, min, max: operazioni riassuntive.

Esempio con reduce:

```
int somma = numeri.stream()
                  .reduce(0, (acc, x) -> acc + x);
```

23.5 Stream primitivi e parallel streams
Esistono stream specializzati per tipi primitivi: IntStream, LongStream, DoubleStream.

```
IntStream.range(1, 10)
         .sum();
```

Parallel streams:

```
numeri.parallelStream()
      .map(x -> x * 2)
      .forEach(System.out::println);
```

Da usare con cautela: il parallelismo non sempre rende il programma più veloce; dipende da dimensione dei dati e tipo di operazioni.

---

## LEZIONE 24 — Generics

24.1 Problema che risolvono i generics
Senza generics, le collezioni lavoravano con Object, costringendo a cast espliciti e rendendo possibili errori a runtime.

I generics introducono:

* sicurezza di tipo a compile-time;
* meno cast;
* codice più leggibile.

24.2 Sintassi base

```
class Box<T> {
    private T valore;

    public void set(T v) {
        valore = v;
    }

    public T get() {
        return valore;
    }
}
```

Uso:

```
Box<String> bs = new Box<>();
bs.set("Ciao");
String s = bs.get();
```

24.3 Tipi generici in libreria standard

* List<T>, Set<T>, Map<K, V>
* Optional<T>
* Interfacce funzionali con generics, come Function<T, R>

Esempi:

```
List<Integer> lista = new ArrayList<>();
Map<String, Integer> mappa = new HashMap<>();
```

24.4 Metodi generici

```
public static <T> void stampaArray(T[] arr) {
    for (T x : arr) {
        System.out.println(x);
    }
}
```

Invocazione:

```
Integer[] nums = {1, 2, 3};
stampaArray(nums);
```

24.5 Bounded type parameters
Si possono imporre vincoli:

```
class BoxNumero<T extends Number> { ... }
```

Questo permette di usare solo sottotipi di Number (Integer, Double, ecc.).

24.6 Wildcards (?,?, ? extends, ? super)

* ? indica “tipo sconosciuto”;
* ? extends T: qualunque sottotipo di T (per leggere);
* ? super T: qualunque supertipo di T (utile per scrivere).

Esempio:

```
void stampaLista(List<? extends Number> lista) {
    for (Number n : lista) {
        System.out.println(n);
    }
}
```

---

## LEZIONE 25 — Enum avanzate

25.1 Enum base
Un enum è un tipo che rappresenta un insieme limitato di costanti:

```
public enum Giorno {
    LUNEDI, MARTEDI, MERCOLEDI, GIOVEDI, VENERDI, SABATO, DOMENICA
}
```

Uso:

```
Giorno g = Giorno.LUNEDI;
```

25.2 Enum con campi e costruttori

```
public enum Priorita {
    BASSA(1),
    MEDIA(2),
    ALTA(3);

    private int livello;

    Priorita(int livello) {
        this.livello = livello;
    }

    public int getLivello() {
        return livello;
    }
}
```

25.3 Metodi nell’enum

```
public enum StatoOrdine {
    NUOVO {
        public boolean puoModificare() { return true; }
    },
    SPEDITO {
        public boolean puoModificare() { return false; }
    };

    public abstract boolean puoModificare();
}
```

L’enum può quindi contenere logica, non solo etichette.

25.4 Vantaggi degli enum

* sostituiscono costanti int o String;
* permettono switch type-safe;
* rendono il codice più leggibile e mantenibile.

---

## LEZIONE 26 — Programmazione concorrente

26.1 Concetto di thread
Un thread è un flusso di esecuzione indipendente all’interno dello stesso programma.

Motivazioni:

* eseguire operazioni in parallelo (o pseudo-parallelo);
* non bloccare l’interfaccia utente;
* gestire più richieste contemporanee (server).

26.2 Creazione di un thread (metodo classico)

Implementando Runnable:

```
public class MioTask implements Runnable {
    public void run() {
        System.out.println("Sto lavorando in un thread");
    }
}

// nel main
Thread t = new Thread(new MioTask());
t.start();
```

Oppure con lambda:

```
Thread t2 = new Thread(() -> {
    System.out.println("Thread con lambda");
});
t2.start();
```

26.3 Stato dei thread e metodi principali

* start(): avvia l’esecuzione;
* sleep(ms): sospende il thread per un certo tempo;
* join(): attende la fine di un altro thread.

26.4 Problemi classici: race condition e sincronizzazione
Se più thread modificano la stessa variabile o struttura dati senza coordinamento, si possono avere risultati non deterministici.

Esempio di sezione critica (conteggio condiviso):

```
public class Contatore {
    private int valore = 0;

    public synchronized void incrementa() {
        valore++;
    }

    public int getValore() {
        return valore;
    }
}
```

26.5 java.util.concurrent (cenni)

* ExecutorService: gestione di thread pool;
* Future: rappresenta un risultato asincrono.

Esempio:

```
ExecutorService es = Executors.newFixedThreadPool(4);

es.submit(() -> {
    System.out.println("Task in pool");
});

es.shutdown();
```

---

## LEZIONE 27 — Sistema Modulare (Java 9+)

27.1 Motivazione
Per progetti grandi, il classpath tradizionale non è sufficiente per:

* controllare le dipendenze;
* evitare conflitti;
* definire confini chiari tra moduli.

Il sistema di moduli introduce:

* module-path;
* descrittore module-info.java;
* concetto di modulo esplicito.

27.2 Cos’è un modulo
Un modulo è un insieme di package e risorse, con un descrittore module-info.java alla radice.

Esempio di descrittore:

```
module scuola.app {
    requires scuola.model;
    requires java.sql;
}
```

Un altro modulo:

```
module scuola.model {
    exports scuola.model;
}
```

27.3 Parole chiave principali in module-info

* requires: dichiara dipendenze verso altri moduli;
* exports: rende visibili i package ad altri moduli;
* opens: apre package per riflessione (usato da alcuni framework).

27.4 Vantaggi del sistema modulare

* migliore incapsulamento dei package;
* avvio più rapido (possibilità di jlink per creare runtime custom);
* controllo esplicito delle dipendenze.

---

## LEZIONE 28 — Sviluppo applicazioni moderne

28.1 JavaFX (cenni)
JavaFX è un toolkit per creare interfacce grafiche moderne.

Concetti di base:

* Stage: finestra principale;
* Scene: contenuto grafico della finestra;
* Node: elementi grafici (bottoni, label, ecc.).

Esempio minimalissimo:

```
public class HelloFX extends Application {
    @Override
    public void start(Stage stage) {
        Button btn = new Button("Clicca");
        btn.setOnAction(e -> System.out.println("Clic!"));

        Scene scene = new Scene(new StackPane(btn), 300, 200);
        stage.setTitle("Demo JavaFX");
        stage.setScene(scene);
        stage.show();
    }
}
```

28.2 Applicazioni web con Spring (cenni)
Spring è un framework molto diffuso per applicazioni enterprise e web.

Concetti chiave:

* Inversion of Control e Dependency Injection;
* Controller, Service, Repository;
* Spring Boot semplifica configurazione e avvio.

Esempio di REST controller (schema concettuale):

```
@RestController
public class SalutoController {

    @GetMapping("/saluto")
    public String saluta() {
        return "Ciao dal server!";
    }
}
```

28.3 Web services REST
Concetto di base:

* comunicazione HTTP;
* risorse identificate da URL;
* uso di metodi HTTP: GET, POST, PUT, DELETE;
* scambio dati in JSON.

In Java, tipicamente si usano:

* Spring MVC / Spring Web;
* oppure JAX-RS (Jakarta RESTful Web Services) con annotazioni come @Path, @GET, @POST.

---

## LEZIONE 29 — Testing con JUnit

29.1 Perché il testing automatico
Motivazioni:

* verificare che il codice funzioni come previsto;
* evitare regressioni quando si modifica il codice;
* documentare il comportamento atteso.

29.2 Concetto di test unitario
Un test unitario verifica una singola “unità” di codice (tipicamente un metodo), in condizioni controllate.

Principi:

* test piccoli e veloci;
* indipendenza tra test;
* stato noto all’inizio.

29.3 JUnit: struttura di un test
Per JUnit 5, una classe di test contiene metodi annotati con @Test.

Esempio:

```
class Calcolatrice {
    public int somma(int a, int b) {
        return a + b;
    }
}

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalcolatriceTest {

    @Test
    public void testSomma() {
        Calcolatrice c = new Calcolatrice();
        int risultato = c.somma(2, 3);
        assertEquals(5, risultato);
    }
}
```

29.4 Assert principali

* assertEquals(atteso, ottenuto)
* assertTrue(condizione)
* assertFalse(condizione)
* assertThrows(TipoEccezione.class, () -> {...})

Esempio con eccezione:

```
@Test
public void testDivisionePerZero() {
    assertThrows(ArithmeticException.class, () -> {
        int x = 10 / 0;
    });
}
```

29.5 Fixture: preparazione dei dati di test
Si possono usare:

* @BeforeEach: eseguito prima di ogni test;
* @AfterEach: eseguito dopo ogni test;
* @BeforeAll, @AfterAll: prima/dopo tutti i test (metodi statici).

---

## LEZIONE 30 — Build Tools: Maven e Gradle

30.1 Perché usare un build tool
Un build tool serve a:

* gestire automaticamente le dipendenze (librerie esterne);
* compilare, eseguire test, creare pacchetti (jar/war);
* definire profili di build per ambienti diversi.

I due più diffusi in ambiente Java sono **Maven** e **Gradle**.

30.2 Maven: concetto di POM
Maven usa un file XML chiamato pom.xml per descrivere:

* coordinate del progetto (groupId, artifactId, version);
* dipendenze;
* plugin (per compilazione, test, esecuzione, packaging).

Schema essenziale di pom.xml:

```
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>it.scuola</groupId>
    <artifactId>mio-progetto</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

30.3 Ciclo di vita Maven (lifecycle)

Fasi principali:

* compile: compila il codice sorgente;
* test: esegue i test;
* package: crea il jar o war;
* install: installa il pacchetto nel repository locale;
* deploy: distribuisce in un repository remoto.

Esempi di comando:

* mvn compile
* mvn test
* mvn package

30.4 Gradle: concetto di build script
Gradle usa script (in Groovy o Kotlin) per definire:

* plugin;
* dipendenze;
* task personalizzati.

Schema minimale in Groovy:

```
plugins {
    id 'java'
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
}

test {
    useJUnitPlatform()
}
```

30.5 Differenze operative (cenni)

* Maven: configurazione dichiarativa in XML, struttura standardizzata, molto diffuso e “rigido” (in senso positivo per progetti enterprise).
* Gradle: più flessibile e scriptabile, build spesso più veloci, molto usato su Android.

---

# Fine