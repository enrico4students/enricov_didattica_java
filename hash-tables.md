Ecco una lezione pensata per uno studente di 17 anni. Cerca di essere chiara, pratica e con esempi reali. Per capire bene il concetto di **tabella hash** non serve un linguaggio specifico, ma alla fine vedremo come si usa in Java con `HashMap`.

---

## 📚 Lezione: Le tabelle hash – come trovare i dati alla velocità della luce

### 1. Il problema: cercare velocemente

Immagina di dover memorizzare i numeri di telefono dei tuoi amici.  
Se li scrivi in lista, per trovare il numero di “Marco” devi scorrere tutta la lista (nel peggiore dei caso leggi tutti i nomi).  
Se usi un array con indice numerico, come fai a sapere che “Marco” corrisponde alla posizione 3?

Servirebbe un modo per **convertire il nome “Marco” in un indice** dell’array.  
Ecco l’idea geniale della **tabella hash**.

---

### 2. Cos’è una tabella hash (o mappa hash)

Una **hash table** è una struttura dati che memorizza coppie **chiave → valore**.

- **Chiave** → è l’identificatore (es. nome “Marco”)
- **Valore** → è il dato che voglio recuperare (es. numero “333 1234567”)

Per trovare velocemente il valore associato a una chiave, la tabella hash usa una **funzione hash**.

---

### 3. La funzione hash – il mago che trasforma le chiavi

La **funzione hash** prende una chiave (di qualsiasi tipo: stringa, numero, oggetto) e restituisce un numero intero (l’**hash code**).  
Poi si riduce questo numero alla dimensione della tabella (es. con l’operatore `%`) per ottenere un **indice dell’array**.

**Esempio molto semplice** (solo per capire):

```
Chiave = "Marco"
Funzione hash: somma i codici ASCII delle lettere e prendi il modulo 10.

M → 77
a → 97
r → 114
c → 99
o → 111
Somma = 498
Indice = 498 % 10 = 8
```

Quindi “Marco” andrà nella **bucketa** (secchio) numero 8 dell’array.

---

### 4. I bucket e l’array interno

La tabella hash contiene un **array di bucket**.  
Ogni bucket può contenere zero, una o più coppie chiave‑valore.

Quando due chiavi diverse producono lo stesso indice (collisione), le coppie vengono messe nello stesso bucket.

---

### 5. Overflow list – gestiamo le collisioni

Un modo semplice per gestire le collisioni è usare, per ogni bucket, una **lista concatenata** (overflow list).  
Se due chiavi finiscono nello stesso bucket, si aggiunge un nuovo nodo alla lista di quel bucket.

Esempio:

- Indice 8: prima chiave “Marco” → valore “333..”
- Indice 8: chiave “Marta” → somma hash dà anch’essa 8 → aggiungiamo in fondo alla lista

Per cercare “Marta”:
1. Calcoli l’indice (8)
2. Vai al bucket 8
3. Scorri la lista finché trovi la chiave “Marta”

Le liste rimangono corte se la tabella è grande e la funzione hash distribuisce bene.

> 🧠 **Ricapitolando**:  
> `chiave → funzione hash → indice bucket → (se collisione) lista di overflow`

---

### 6. Vantaggi e svantaggi

| Vantaggi | Svantaggi |
|----------|------------|
| In media cerca, inserisce e cancella in **O(1)** (tempo costante) | In casi rari (molte collisioni) può diventare **O(n)** |
| Perfetta per dizionari, cache, database | La funzione hash deve essere ben progettata |
| Molto flessibile (qualsiasi tipo di chiave) | Consuma più memoria di un array semplice |

---

### 7. Tabelle hash in Java: HashMap

Java fornisce già un’implementazione pronta all’uso: **`HashMap`**.  
Usa internamente un array di bucket, funzioni hash efficienti e liste di overflow (dopo una certa soglia le liste diventano alberi, ma per noi va bene sapere che funziona così).

#### Creare una HashMap

```java
import java.util.HashMap;

public class EsempioHashMap {
    public static void main(String[] args) {
        // Chiavi: String, Valori: Integer (es. età delle persone)
        HashMap<String, Integer> eta = new HashMap<>();

        // Inserire coppie chiave → valore
        eta.put("Alice", 17);
        eta.put("Marco", 18);
        eta.put("Giulia", 16);

        // Leggere un valore
        int etaMarco = eta.get("Marco");  // restituisce 18
        System.out.println("Marco ha " + etaMarco + " anni");

        // Se la chiave non esiste, get() restituisce null
        Integer etaLuca = eta.get("Luca");
        System.out.println("Età di Luca: " + etaLuca);  // null

        // Rimuovere una coppia
        eta.remove("Alice");

        // Controllare se esiste una chiave
        if (eta.containsKey("Giulia")) {
            System.out.println("Giulia c'è!");
        }

        // Stampare tutta la mappa
        System.out.println(eta);
    }
}
```

#### Cosa succede dentro?

Quando chiami `put("Marco", 18)`:
- Java calcola l’hash code della stringa `"Marco"` (con `"Marco".hashCode()`)
- Riduce l’hash code alla dimensione interna dell’array (es. con `(n - 1) & hash`)
- Trova il bucket e, se già esiste una coppia con stessa chiave, sovrascrive il valore; altrimenti aggiunge una nuova coppia nella lista di overflow.

#### Attenzione: chiavi e `hashCode()`/`equals()`

Per usare oggetti personalizzati come chiavi, devi **sovrascrivere correttamente** i metodi `hashCode()` e `equals()`.  
Con le classi standard (String, Integer, ecc.) è già tutto fatto.

Esempio semplice con chiave intera e valore stringa:

```java
HashMap<Integer, String> studenti = new HashMap<>();
studenti.put(101, "Luca");
studenti.put(102, "Martina");
System.out.println(studenti.get(101));  // Luca
```

---

### 8. Un esempio completo – rubrica telefonica

```java
import java.util.HashMap;

public class Rubrica {
    public static void main(String[] args) {
        HashMap<String, String> rubrica = new HashMap<>();

        rubrica.put("Mario Rossi", "333 1234567");
        rubrica.put("Laura Bianchi", "349 9876543");
        rubrica.put("Carlo Verdi", "388 5551234");

        // Cerca il numero di Laura
        String numero = rubrica.get("Laura Bianchi");
        System.out.println("Laura: " + numero);

        // Cambiare numero a Mario
        rubrica.put("Mario Rossi", "320 1112223");

        // Elencare tutti i contatti
        for (String nome : rubrica.keySet()) {
            System.out.println(nome + " → " + rubrica.get(nome));
        }
    }
}
```

---

### 9. Riepilogo per il tuo esame / compito

| Concetto | Spiegazione breve |
|----------|-------------------|
| **Tabella hash** | Struttura dati che associa chiavi a valori |
| **Chiave** | Identificatore unico (es. nome, ID) |
| **Funzione hash** | Trasforma la chiave in un indice numerico |
| **Bucket** | Posizione nell’array interno dove finisce la coppia |
| **Lista di overflow** | Lista collegata dentro un bucket per gestire le collisioni |
| **HashMap (Java)** | Classe che implementa una tabella hash; offre `put`, `get`, `remove` |

---

### 10. Cosa ricordare per usare bene le hash table

1. Scegli una **buona funzione hash** (in Java è già fatta per te).
2. La tabella si **ridimensiona automaticamente** quando è troppo piena (Java lo fa da solo).
3. Non ordinano le chiavi (se vuoi ordine, usa `TreeMap`).
4. Sono **velocissime** nella pratica.

Ora prova a scrivere un piccolo programma Java che conti quante volte appare ogni parola in una frase, usando una `HashMap<String, Integer>`.

Buon coding! 🚀