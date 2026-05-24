---

# LIVELLO 2 — ESERCIZI PROGRESSIVI CON SOLUZIONI

---

# LEZIONE 11 — Approfondimento OOP

(incapsulamento, modificatori, getter/setter)

---

## ESERCIZIO 11.1 — Incapsulamento base

Creare una classe **Conto** con attributo privato saldo e metodi deposita(), preleva(), getSaldo().
Testarla dal main.

### Soluzione

```
public class Conto {
    private double saldo;

    public void deposita(double v) {
        saldo += v;
    }

    public void preleva(double v) {
        saldo -= v;
    }

    public double getSaldo() {
        return saldo;
    }
}

public class TestConto {
    public static void main(String[] args) {
        Conto c = new Conto();
        c.deposita(100);
        c.preleva(40);
        System.out.println(c.getSaldo()); // 60
    }
}
```

---

## ESERCIZIO 11.2 — Modificatori di accesso

Perché è utile rendere privati gli attributi?

### Soluzione

Perché evita che vengano modificati in modo incoerente dall’esterno, permette di controllare gli accessi tramite metodi e garantisce integrità dei dati (principio di incapsulamento).

---

## ESERCIZIO 11.3 — Classe con convalida dei dati

Creare una classe **Temperatura** con attributo privato valore.
Nel setter non permettere valori sotto -273.

### Soluzione

```
public class Temperatura {
    private double valore;

    public void setValore(double v) {
        if (v < -273) return;  // nessuna modifica
        valore = v;
    }

    public double getValore() {
        return valore;
    }
}
```

---

# LEZIONE 12 — Ereditarietà

---

## ESERCIZIO 12.1 — Gerarchia semplice

Creare classe **Persona** (nome, eta) e classe **Studente** che estende Persona con attributo classe (es. "3A").

### Soluzione

```
public class Persona {
    protected String nome;
    protected int eta;

    public Persona(String n, int e) {
        nome = n;
        eta = e;
    }
}

public class Studente extends Persona {
    private String classe;

    public Studente(String n, int e, String c) {
        super(n, e);
        classe = c;
    }
}
```

---

## ESERCIZIO 12.2 — Override metodo descrizione()

Aggiungere in Persona un metodo descrizione() e sovrascriverlo in Studente.

### Soluzione

```
public class Persona {
    ...
    public String descrizione() {
        return nome + ", " + eta + " anni";
    }
}

public class Studente extends Persona {
    ...
    @Override
    public String descrizione() {
        return nome + " (" + classe + ")";
    }
}
```

---

## ESERCIZIO 12.3 — Uso del polimorfismo

Creare un array di Persona contenente anche Studente e stampare descrizione().

### Soluzione

```
Persona[] arr = {
    new Persona("Marco", 40),
    new Studente("Luca", 17, "4B")
};

for (Persona p : arr) {
    System.out.println(p.descrizione());
}
```

Output:
Marco, 40 anni
Luca (4B)

---

# LEZIONE 13 — Polimorfismo

---

## ESERCIZIO 13.1 — Upcasting

Mostrare che un oggetto Studente può essere assegnato a una variabile Persona.

### Soluzione

```
Persona p = new Studente("Anna", 18, "5A");
```

---

## ESERCIZIO 13.2 — Downcasting sicuro

Effettuare un cast da Persona a Studente verificando tipo con instanceof.

### Soluzione

```
Persona p = new Studente("Anna", 18, "5A");

if (p instanceof Studente) {
    Studente s = (Studente) p;
    System.out.println("Cast riuscito");
}
```

---

## ESERCIZIO 13.3 — Metodo polimorfico

Creare un metodo stampa(Persona p) che richiama p.descrizione().

### Soluzione

```
public static void stampa(Persona p) {
    System.out.println(p.descrizione());
}
```

---

# LEZIONE 14 — Classi astratte e Interfacce

---

## ESERCIZIO 14.1 — Classe astratta

Creare classe astratta Veicolo con metodo astratto muovi().
Derivare Auto e Bici.

### Soluzione

```
public abstract class Veicolo {
    public abstract void muovi();
}

public class Auto extends Veicolo {
    public void muovi() {
        System.out.println("L'auto avanza");
    }
}

public class Bici extends Veicolo {
    public void muovi() {
        System.out.println("La bici pedala");
    }
}
```

---

## ESERCIZIO 14.2 — Interfaccia Pagabile

Creare interfaccia Pagabile con metodo calcolaTotale().
Implementarla in Prodotto.

### Soluzione

```
public interface Pagabile {
    double calcolaTotale();
}

public class Prodotto implements Pagabile {
    private double prezzo;
    private int quantita;

    public Prodotto(double p, int q) {
        prezzo = p;
        quantita = q;
    }

    public double calcolaTotale() {
        return prezzo * quantita;
    }
}
```

---

## ESERCIZIO 14.3 — Uso di metodi default

Aggiungere un metodo default in un’interfaccia.

### Soluzione

```
public interface Info {
    default void stampaInfo() {
        System.out.println("Informazioni generiche");
    }
}
```

---

# LEZIONE 15 — Stringhe e immutabilità

---

## ESERCIZIO 15.1 — Confronto stringhe

Perché usare equals() invece di == ?

### Soluzione

`equals()` confronta il contenuto, mentre `==` confronta i riferimenti in memoria.

---

## ESERCIZIO 15.2 — Uso di StringBuilder

Creare una concatenazione efficiente di 5 stringhe.

### Soluzione

```
StringBuilder sb = new StringBuilder();
sb.append("Uno ");
sb.append("Due ");
sb.append("Tre ");
sb.append("Quattro ");
sb.append("Cinque");
System.out.println(sb.toString());
```

---

## ESERCIZIO 15.3 — Operazioni stringa

Estrarre sottostringa "Java" da "Programmare in Java".

### Soluzione

```
String s = "Programmare in Java";
String x = s.substring(15);  // "Java"
```

---

# LEZIONE 16 — Package e organizzazione codice

---

## ESERCIZIO 16.1 — Creare un package

Creare package scuola.model con classe Studente.

### Soluzione

In alto nel file:

```
package scuola.model;
```

---

## ESERCIZIO 16.2 — Importare classe

Dal main importare Studente.

### Soluzione

```
import scuola.model.Studente;
```

---

## ESERCIZIO 16.3 — Struttura progetto

Identificare vantaggi dei package.

### Soluzione

* Organizzazione modulare
* Controllo degli accessi
* Evita conflitti di nomi

---

# LEZIONE 17 — Eccezioni

---

## ESERCIZIO 17.1 — Divisione sicura

Scrivere metodo divide che lancia eccezione per divisione per zero.

### Soluzione

```
public static int divide(int a, int b) {
    if (b == 0) throw new ArithmeticException("Divisione per zero");
    return a / b;
}
```

---

## ESERCIZIO 17.2 — try-catch

Gestire input non valido.

### Soluzione

```
try {
    int x = Integer.parseInt("abc");
} catch (NumberFormatException e) {
    System.out.println("Input non numerico");
}
```

---

## ESERCIZIO 17.3 — Eccezione personalizzata

### Soluzione

```
public class DatoNonValidoException extends Exception { }
```

---

# LEZIONE 18 — I/O File

---

## ESERCIZIO 18.1 — Leggere file riga per riga

### Soluzione

```
try (BufferedReader br = new BufferedReader(new FileReader("dati.txt"))) {
    String r;
    while ((r = br.readLine()) != null) {
        System.out.println(r);
    }
}
```

---

## ESERCIZIO 18.2 — Scrivere su file

### Soluzione

```
try (FileWriter fw = new FileWriter("out.txt")) {
    fw.write("Test");
}
```

---

## ESERCIZIO 18.3 — Copia file

### Soluzione

```
try (BufferedReader br = new BufferedReader(new FileReader("a.txt"));
     FileWriter fw = new FileWriter("b.txt")) {
    String r;
    while ((r = br.readLine()) != null) {
        fw.write(r + "\n");
    }
}
```

---

# LEZIONE 19 — Collezioni

---

## ESERCIZIO 19.1 — ArrayList di stringhe

### Soluzione

```
ArrayList<String> lista = new ArrayList<>();
lista.add("Marco");
lista.add("Luca");
lista.add("Anna");
```

---

## ESERCIZIO 19.2 — Ricerca in lista

### Soluzione

```
if (lista.contains("Luca")) {
    System.out.println("Trovato");
}
```

---

## ESERCIZIO 19.3 — HashMap nome→età

### Soluzione

```
HashMap<String,Integer> m = new HashMap<>();
m.put("Marco",18);
m.put("Luca",17);

System.out.println(m.get("Marco"));
```

---

## ESERCIZIO 19.4 — Ordinare con Comparator

### Soluzione

```
Collections.sort(lista, new Comparator<String>() {
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
});
```

---

# LEZIONE 20 — JDBC

---

## ESERCIZIO 20.1 — Connettersi a database MySQL

### Soluzione

```
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/scuola", "root", "pwd");
```

---

## ESERCIZIO 20.2 — SELECT studenti

### Soluzione

```
PreparedStatement ps = conn.prepareStatement("SELECT nome FROM studenti");
ResultSet rs = ps.executeQuery();
while (rs.next()) {
    System.out.println(rs.getString("nome"));
}
```

---

## ESERCIZIO 20.3 — INSERT parametrizzato

### Soluzione

```
PreparedStatement ps = conn.prepareStatement(
    "INSERT INTO studenti(nome,eta) VALUES (?,?)");

ps.setString(1,"Marco");
ps.setInt(2,17);
ps.executeUpdate();
```

---

# LEZIONE 21 — Progettazione e modularità

---

## ESERCIZIO 21.1 — Applicare SRP

Dato un programma che fa calcoli e stampa risultati, separare funzioni in classi diverse.

### Soluzione

Dividere in:

* Calcolatrice (logica)
* StampaRisultati (presentazione)

---

## ESERCIZIO 21.2 — Mini-progetto Rubrica

Creare classi:

* Contatto (nome, telefono)
* Rubrica (lista contatti, metodi aggiungi, cerca)

### Soluzione minima

```
public class Contatto {
    String nome;
    String tel;
    public Contatto(String n, String t) { nome = n; tel = t; }
}

public class Rubrica {
    ArrayList<Contatto> contatti = new ArrayList<>();
    public void aggiungi(Contatto c) { contatti.add(c); }
    public Contatto cerca(String nome) {
        for (Contatto c : contatti)
            if (c.nome.equals(nome)) return c;
        return null;
    }
}
```

---

# Fine  