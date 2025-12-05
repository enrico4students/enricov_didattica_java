
---

# LIVELLO 3 — ESERCIZI PROGRESSIVI CON SOLUZIONI

---

## LEZIONE 22 — Lambda e Programmazione Funzionale  

---

## ESERCIZIO 22.1 — Lambda semplice (base)

Scrivere una lambda che implementa una interfaccia funzionale Somma con metodo int calcola(int a, int b).

Soluzione:

```
@FunctionalInterface
interface Somma {
    int calcola(int a, int b);
}

public class TestLambda {
    public static void main(String[] args) {
        Somma s = (a, b) -> a + b;
        System.out.println(s.calcola(3, 4)); // 7
    }
}
```

---

## ESERCIZIO 22.2 — Lambda con blocco (intermedio)

Creare una lambda che calcola il massimo tra due valori.

Soluzione:

```
Somma maxFun = (a, b) -> {
    if (a > b) return a;
    return b;
};

System.out.println(maxFun.calcola(10, 4)); // 10
```

---

## ESERCIZIO 22.3 — Method reference (avanzato)

Usare una reference a metodo per stampare gli elementi di una lista.

Soluzione:

```
List<String> lista = List.of("A", "B", "C");
lista.forEach(System.out::println);
```

---  

## LEZIONE 23 — Stream API

---

## ESERCIZIO 23.1 — Stream di numeri (base)

Dato un elenco List<Integer>, filtrare solo i numeri pari.

Soluzione:

```
List<Integer> numeri = List.of(1, 2, 3, 4, 5, 6);
List<Integer> pari = numeri.stream()
                           .filter(n -> n % 2 == 0)
                           .toList();
System.out.println(pari); // [2, 4, 6]
```

---

## ESERCIZIO 23.2 — Map e sorted (intermedio)

Dato un elenco di parole, convertirle tutte in maiuscolo e ordinarle.

Soluzione:

```
List<String> parole = List.of("java", "stream", "lambda");
List<String> risultato = parole.stream()
                               .map(String::toUpperCase)
                               .sorted()
                               .toList();
System.out.println(risultato); // [JAVA, LAMBDA, STREAM]
```

---

## ESERCIZIO 23.3 — Reduce (avanzato)

Trovare il prodotto di tutti i numeri in una lista.

Soluzione:

```
int prodotto = numeri.stream()
                     .reduce(1, (acc, x) -> acc * x);
System.out.println(prodotto); // 720
```

---  

## LEZIONE 24 — Generics  

---

## ESERCIZIO 24.1 — Classe generica (base)

Creare classe Box<T> che memorizza un elemento.

Soluzione:

```
class Box<T> {
    private T valore;

    public void set(T v) { valore = v; }
    public T get() { return valore; }
}

Box<String> b = new Box<>();
b.set("Ciao");
System.out.println(b.get());
```

---

## ESERCIZIO 24.2 — Metodo generico (intermedio)

Scrivere un metodo generico che stampa tutti gli elementi di un array.

Soluzione:

```
public static <T> void stampa(T[] arr) {
    for (T x : arr) {
        System.out.println(x);
    }
}

Integer[] nums = {1, 2, 3};
stampa(nums);
```

---

## ESERCIZIO 24.3 — Bounded generic (avanzato)

Creare un metodo che accetti solo liste di Number.

Soluzione:

```
public static void stampaNumeri(List<? extends Number> lista) {
    for (Number n : lista) {
        System.out.println(n);
    }
}
```

---  

## LEZIONE 25 — Enum avanzate

---

## ESERCIZIO 25.1 — Enum con campi (base)

Creare enum Priorita con livelli numerici.

Soluzione:

```
public enum Priorita {
    BASSA(1), MEDIA(2), ALTA(3);

    private int livello;
    Priorita(int livello) { this.livello = livello; }
    public int getLivello() { return livello; }
}
```

---

## ESERCIZIO 25.2 — Metodo astratto nell’enum (intermedio)

Creare enum StatoOrdine con metodo astratto puoModificare.

Soluzione:

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

---

## ESERCIZIO 25.3 — Switch su enum (avanzato)

Usare Enum in uno switch.

Soluzione:

```
public static void stampa(Priorita p) {
    switch (p) {
        case BASSA -> System.out.println("Poca urgenza");
        case MEDIA -> System.out.println("Media urgenza");
        case ALTA  -> System.out.println("Massima urgenza");
    }
}
```

---  

LEZIONE 26 — Programmazione concorrente

---

## ESERCIZIO 26.1 — Thread semplice (base)

Creare un thread che stampa un messaggio.

Soluzione:

```
Thread t = new Thread(() -> {
    System.out.println("Thread in esecuzione");
});
t.start();
```

---

## ESERCIZIO 26.2 — Sezione critica sincronizzata (intermedio)

Creare un contatore thread-safe.

Soluzione:

```
class Contatore {
    private int valore = 0;

    public synchronized void incrementa() {
        valore++;
    }

    public int getValore() {
        return valore;
    }
}
```

---

## ESERCIZIO 26.3 — ExecutorService con 3 task (avanzato)

Creare un pool di thread e inviare 3 task.

Soluzione:

```
ExecutorService es = Executors.newFixedThreadPool(3);

for (int i = 1; i <= 3; i++) {
    int n = i;
    es.submit(() -> System.out.println("Task " + n));
}

es.shutdown();
```

---  

LEZIONE 27 — Sistema Modulare (Java 9+)

---

## ESERCIZIO 27.1 — Creare un modulo (base)

Scrivere un file module-info.java per un modulo scuola.model che esporta il package scuola.model.

Soluzione:

```
module scuola.model {
    exports scuola.model;
}
```

---

## ESERCIZIO 27.2 — Modulo che richiede un altro modulo (intermedio)

Scrivere module-info per un modulo scuola.app che richiede scuola.model.

Soluzione:

```
module scuola.app {
    requires scuola.model;
}
```

---

## ESERCIZIO 27.3 — Comprendere exports vs opens (avanzato)

Domanda: differenza?

Soluzione:

* exports rende visibili le classi a compilazione ed esecuzione;
* opens permette l’accesso tramite riflessione (necessario per framework come Spring).

---  

## LEZIONE 28 — Applicazioni moderne (JavaFX, REST)

---

## ESERCIZIO 28.1 — JavaFX base (costruzione scena) (base)

Creare un’applicazione che mostra un pulsante.

Soluzione essenziale:

```
public class HelloFX extends Application {
    @Override
    public void start(Stage stage) {
        Button btn = new Button("Clicca");
        Scene scene = new Scene(new StackPane(btn), 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
```

---

## ESERCIZIO 28.2 — REST controller concettuale (intermedio)

Scrivere un semplice controller REST che risponde a GET /hello.

Soluzione:

```
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String saluta() {
        return "Ciao dal server!";
    }
}
```

---

## ESERCIZIO 28.3 — Descrizione JSON (avanzato)

Scrivere classe Utente convertibile in JSON tramite Spring Boot.

Soluzione:

```
public class Utente {
    private String nome;
    private int eta;

    public Utente(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }

    public String getNome() { return nome; }
    public int getEta() { return eta; }
}
```

Spring lo converte automaticamente in JSON tramite Jackson.

---  

## LEZIONE 29 — Testing con JUnit

---

## ESERCIZIO 29.1 — Testare un metodo semplice (base)

Scrivere test per metodo somma.

Soluzione:

```
@Test
void testSomma() {
    assertEquals(7, new Calcolatrice().somma(3, 4));
}
```

---

## ESERCIZIO 29.2 — Test eccezione (intermedio)

Verificare che venga lanciata un’eccezione ArithmeticException.

Soluzione:

```
@Test
void testDivisionePerZero() {
    assertThrows(ArithmeticException.class, () -> {
        int x = 10 / 0;
    });
}
```

---

## ESERCIZIO 29.3 — Fixture (avanzato)

Inizializzare una calcolatrice prima di ogni test.

Soluzione:

```
class CalcTest {

    Calcolatrice c;

    @BeforeEach
    void setup() {
        c = new Calcolatrice();
    }

    @Test
    void testSomma() {
        assertEquals(5, c.somma(2, 3));
    }
}
```

---  

## LEZIONE 30 — Build Tools (Maven e Gradle)

---

## ESERCIZIO 30.1 — Creare un POM minimo (base)

Scrivere pom.xml minimo per un progetto Java.

Soluzione:

```
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>it.scuola</groupId>
    <artifactId>demo</artifactId>
    <version>1.0</version>
</project>
```

---

## ESERCIZIO 30.2 — Aggiungere dipendenza JUnit (intermedio)

Inserire JUnit 5 nel POM.

Soluzione:

```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

---

## ESERCIZIO 30.3 — Script Gradle per progetto Java (avanzato)

Scrivere build.gradle minimo.

Soluzione:

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

---

# Fine