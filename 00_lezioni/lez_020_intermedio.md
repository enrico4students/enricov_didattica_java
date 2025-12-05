---
marp: false
paginate: true
---


# Java - LIVELLO 2

(Approfondimento OOP, eccezioni, I/O, collezioni, JDBC e progettazione)

---

# LEZIONE 11 — Approfondimento OOP

## 11.1 Il concetto di oggetto rivisto

Nella programmazione orientata agli oggetti, un oggetto rappresenta un'entità dotata di:

* **stato** (attributi)
* **comportamento** (metodi)
* **identità** (differenzia ogni istanza)

Una classe definisce la “forma” degli oggetti, mentre l’oggetto è l’istanza concreta.

## 11.2 Incapsulamento

È il principio che protegge i dati interni dell'oggetto.
Si realizza usando:

* attributi **private**
* metodi pubblici **getter e setter**

Esempio:

```
public class Conto {
    private double saldo;

    public void deposita(double valore) {
        saldo += valore;
    }

    public double getSaldo() {
        return saldo;
    }
}
```

## 11.3 Modificatori di accesso

* **public**: accessibile da qualsiasi classe
* **private**: accessibile solo all’interno della classe
* **protected**: accessibile da sottoclassi
* (default): accessibile nello stesso package

---

# LEZIONE 12 — Ereditarietà

## 12.1 Che cos’è l’ereditarietà

Permette di creare una nuova classe basata su una esistente, ereditandone attributi e comportamenti.

Sintassi:

```
class Sottoclasse extends Superclasse { }
```

Vantaggi:

* riuso del codice
* organizzazione gerarchica
* specializzazione delle entità

## 12.2 Costruttori e parola chiave `super`

Il costruttore della sottoclasse può richiamare quello della superclasse:

```
public Studente(String nome, int eta, String classe) {
    super(nome, eta);
    this.classe = classe;
}
```

## 12.3 Override dei metodi

La sottoclasse può ridefinire un metodo della superclasse:

```
@Override
public void descrizione() {
    System.out.println("Studente: " + nome);
}
```

---

# LEZIONE 13 — Polimorfismo

## 13.1 Tipi statici e dinamici

Un oggetto può essere visto:

* come il suo tipo reale (es. Studente)
* come uno dei suoi tipi superiori (es. Persona)

Esempio:

```
Persona p = new Studente();
```

Il tipo statico è Persona, il tipo dinamico è Studente.

## 13.2 Binding dinamico

Quando si invoca un metodo tramite un riferimento di tipo superiore, Java esegue quello dell’oggetto reale.

## 13.3 Upcasting e Downcasting

* upcasting: sicuro, verso tipo superiore
* downcasting: serve esplicita conversione

  Persona p = new Studente();
  Studente s = (Studente) p;

## 13.4 Vantaggi del polimorfismo

* metodi che accettano tipi generali
* collezioni eterogenee
* codice estensibile

---

# LEZIONE 14 — Classi astratte e Interfacce

## 14.1 Classi astratte

Una classe astratta:

* può contenere metodi implementati e non
* non può essere istanziata
* viene usata come base comune per più sottoclassi

Esempio:

```
public abstract class Veicolo {
    public abstract void muovi();
}
```

## 14.2 Interfacce

Un’interfaccia definisce solo la forma dei metodi.

```
public interface Pagabile {
    double calcolaTotale();
}
```

Una classe può implementare più interfacce.

## 14.3 Interfacce moderne

Java permette:

* metodi default
* metodi statici

---

# LEZIONE 15 — Stringhe e oggetti immutabili

## 15.1 Immutabilità della classe String

Gli oggetti String non possono essere modificati.
Ogni operazione crea una nuova stringa.

Conseguenza:

* concatenazioni in cicli sono inefficienti

## 15.2 StringBuilder

Si usa per modifiche frequenti.

```
StringBuilder sb = new StringBuilder();
sb.append("Test");
sb.append(123);
```

## 15.3 Operazioni sulle stringhe

* length()
* charAt()
* substring()
* indexOf()
* equals() vs ==

---

# LEZIONE 16 — Package e organizzazione del codice

## 16.1 Che cos’è un package

Un package è una cartella logica che contiene classi.

Dichiarazione:

```
package scuola.model;
```

Import:

```
import scuola.model.Studente;
```

## 16.2 Vantaggi

* organizzazione modulare
* evitare conflitti di nomi
* controllo degli accessi

## 16.3 Struttura tipica di progetto

* model
* controller
* service
* utils

---

# LEZIONE 17 — Gestione delle eccezioni

## 17.1 Concetto di eccezione

Un errore che avviene durante l’esecuzione.

## 17.2 try – catch – finally

```
try {
    int x = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Errore matematico");
} finally {
    System.out.println("Blocco sempre eseguito");
}
```

## 17.3 Eccezioni controllate e non

* checked: devono essere gestite (es. IOException)
* unchecked: derivano da RuntimeException

## 17.4 Eccezioni personalizzate

```
public class DatoNonValidoException extends Exception { }
```

---

# LEZIONE 18 — Input/Output (I/O)

## 18.1 File e directory

Operazioni tipiche:

* leggere file di testo
* scrivere file di testo

## 18.2 Lettura con Scanner

```
Scanner sc = new Scanner(new File("dati.txt"));
```

## 18.3 BufferedReader

```
BufferedReader br = new BufferedReader(new FileReader("file.txt"));
```

## 18.4 Scrittura con FileWriter

```
FileWriter fw = new FileWriter("out.txt");
fw.write("Test");
fw.close();
```

## 18.5 try-with-resources

```
try (BufferedReader br = new BufferedReader(new FileReader("f.txt"))) {
    System.out.println(br.readLine());
}
```

---

# LEZIONE 19 — Collezioni (Collections Framework)

## 19.1 Liste

* ArrayList
* LinkedList

  ArrayList<String> lista = new ArrayList<>();
  lista.add("a");

## 19.2 Set

Non ammettono duplicati.

```
Set<Integer> s = new HashSet<>();
s.add(10);
```

## 19.3 Map

Contiene coppie chiave-valore.

```
Map<String,Integer> m = new HashMap<>();
m.put("Marco",18);
```

## 19.4 Iterazione

```
for (String x : lista) { }
```

## 19.5 Comparable e Comparator

* Comparable: ordinamento naturale
* Comparator: ordinamento personalizzato

---

# LEZIONE 20 — JDBC (Accesso ai Database)

## 20.1 Cos’è JDBC

È l’API standard per connettersi ai database relazionali in Java (es. MySQL, PostgreSQL, Oracle).

## 20.2 Caricamento del Driver

Per MySQL, spesso non serve più:

```
Class.forName("com.mysql.cj.jdbc.Driver");
```

## 20.3 Connessione

```
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/scuola",
    "root",
    "password"
);
```

## 20.4 Query SELECT

```
PreparedStatement ps = conn.prepareStatement("SELECT * FROM studenti");
ResultSet rs = ps.executeQuery();
while (rs.next()) {
    System.out.println(rs.getString("nome"));
}
```

## 20.5 Query INSERT

```
PreparedStatement ps = conn.prepareStatement(
    "INSERT INTO studenti(nome,eta) VALUES (?,?)"
);
ps.setString(1,"Marco");
ps.setInt(2,17);
ps.executeUpdate();
```

## 20.6 Pattern DAO

Si crea una classe dedicata alla gestione dei dati:

```
public class StudenteDAO {
    public List<Studente> findAll() { ... }
}
```

---

# LEZIONE 21 — Progettazione base e modularità

## 21.1 Concetti di progettazione

* Single Responsibility: ogni classe ha un solo scopo.
* DRY: evitare duplicazioni di codice.
* Disaccoppiamento: separare logica, dati e interfacce.

## 21.2 Strutturazione di un progetto

Esempio:

* model
* dao
* service
* controller
* main

## 21.3 Piccolo caso di studio

Sistema di gestione biblioteca:

* classe Libro
* classe Utente
* classe Prestito
* DAO per ogni entità
* servizio di gestione prestiti

## 21.4 Modularità con il sistema dei package

Ogni modulo ha una responsabilità precisa.

---

# Fine