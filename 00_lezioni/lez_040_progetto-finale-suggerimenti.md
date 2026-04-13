
DESCRIZIONE GENERALE DEL PROGETTO

Titolo (proposto):
AI Life Companion – assistente personale per obiettivi, autoanalisi psicologica e fisica, con memoria delle sessioni.

Funzionalità principali:

1. Organizzazione vita e obiettivi

   * Definire obiettivi (a lungo, medio, breve termine).
   * Chiedere all’AI di analizzarli (chiarezza, realismo, priorità).
   * Chiedere all’AI di proporre un piano operativo (passi, scadenze, criteri di progresso).
   * Registrare stato di avanzamento e note.

2. Autoanalisi psicologica

   * Registrare brevi resoconti giornalieri (journaling guidato).
   * Chiedere all’AI di sintetizzare stato emotivo, temi ricorrenti, possibili punti di attenzione.
   * Mantenere una “sintesi di stato psicologico” aggiornata che riassume le ultime sessioni.

3. Autoanalisi fisica

   * Inserire metriche di base (peso, ore di sonno, passi, livello di energia percepito, ecc.).
   * Chiedere all’AI un commento sintetico sugli andamenti e suggerimenti di abitudini generali.
   * Tenere una sintesi dello stato fisico (trend generale, rischi evidenti, aree migliorabili).

4. Memoria delle sessioni

   * Salvare su file (JSON o simile) obiettivi, voci di diario, metriche fisiche, e le sintesi AI.
   * Ricostruire al riavvio lo “stato corrente” e permettere all’AI di tenerne conto.

Interfaccia:

* Per semplicità: interfaccia a riga di comando (CLI) con menù testuale.
* In futuro estendibile a REST / GUI.

Linguaggio e strumenti:

* Java
* Maven per la gestione del progetto
* Integrazione con API di un LLM (ad esempio OpenAI) tramite un client HTTP o SDK
  (con chiavi e URL lette da variabili d’ambiente o file di configurazione).

────────────────────────────────────────
STRUTTURA DEL PROGETTO MAVEN
────────────────────────────────────────

Struttura directory:

```
ai-life-companion/
    pom.xml
    src/
        main/
            java/
                it/school/ailife/
                    model/
                    persistence/
                    ai/
                    service/
                    cli/
        main/resources/
            config.properties (ad es. per modello AI, endpoint ecc.)
        test/java/...
```

Package suggeriti:

* it.school.ailife.model

  * Goal, GoalType, Task, JournalEntry, PhysicalMetrics, AiSummary, SessionType, UserProfile

* it.school.ailife.persistence

  * JsonStorage, GoalRepository, JournalRepository, PhysicalRepository, AiSummaryRepository

* it.school.ailife.ai

  * AiProvider (enum)
  * AiClient (interfaccia)
  * OpenAiClient (implementazione reale o semi-reale)
  * PromptBuilder (per comporre i prompt per i 3 domini: obiettivi, psicologia, fisico)

* it.school.ailife.service

  * GoalService
  * PsychService
  * PhysicalService
  * SummaryService (mantiene una sintesi generale dello stato)

* it.school.ailife.cli

  * MainMenu (menù testuale principale)
  * GoalCli, PsychCli, PhysicalCli (schermate/menù specifici)
  * App (classe con main)

────────────────────────────────────────
FASE 1 – CREAZIONE PROGETTO MAVEN
────────────────────────────────────────

1. Creare cartella del progetto:
   ai-life-companion

2. Da terminale, dentro la cartella, creare un progetto Maven base (o usare l’IDE con wizard).

3. Impostare nel pom.xml:

   * groupId: it.school
   * artifactId: ai-life-companion
   * versione Java (17 o successiva).
   * dipendenze:

     * libreria HTTP (se non si usa un SDK specifico; ad esempio HttpClient di Java 11 basta).
     * eventuale libreria JSON (Jackson) per persistenza.

Esempio pom.xml minimizzato (senza versioni specifiche):

```
<project ...>
    <modelVersion>4.0.0</modelVersion>

    <groupId>it.school</groupId>
    <artifactId>ai-life-companion</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- Jackson per JSON -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>VERSIONE_CORRENTE</version>
        </dependency>

        <!-- JUnit (facoltativo, per test) -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>VERSIONE_CORRENTE</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>VERSIONE_CORRENTE</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>

            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>VERSIONE_CORRENTE</version>
                <configuration>
                    <mainClass>it.school.ailife.cli.App</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

Le versioni si possono impostare in base allo stato corrente dei repository Maven.

Per l’AI si può:

* usare chiamata HTTP diretta verso l’endpoint dell’API di ChatGPT (OpenAI) o Gemini, oppure
* usare un SDK dedicato.

Nel progetto didattico, la cosa più chiara è definire un’interfaccia AiClient e scrivere una implementazione che incapsula tutte le chiamate verso l’AI.

────────────────────────────────────────
FASE 2 – MODELLO DOMINIO (PACKAGE model)
────────────────────────────────────────

Esempi di classi principali (sintetiche ma utilizzabili).

1. Tipi di sessione AI (per distinguere goal / psicologia / fisico):

   package it.school.ailife.model;

   public enum SessionType {
   GOAL_PLANNING,
   PSYCH_SELF_ANALYSIS,
   PHYSICAL_SELF_ANALYSIS
   }

2. Tipo di obiettivo:

   package it.school.ailife.model;

   public enum GoalType {
   LONG_TERM,
   MID_TERM,
   SHORT_TERM
   }

3. Obiettivo e Task:

   package it.school.ailife.model;

   import java.time.LocalDate;
   import java.util.ArrayList;
   import java.util.List;
   import java.util.UUID;

   public class Goal {

   ```
    private final String id;
    private String title;
    private String description;
    private GoalType type;
    private LocalDate targetDate;
    private List<Task> tasks = new ArrayList<>();

    public Goal(String title, String description, GoalType type, LocalDate targetDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.type = type;
        this.targetDate = targetDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public GoalType getType() {
        return type;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task t) {
        tasks.add(t);
    }
   ```

   }

   package it.school.ailife.model;

   import java.time.LocalDate;
   import java.util.UUID;

   public class Task {

   ```
    private final String id;
    private String title;
    private boolean completed;
    private LocalDate dueDate;

    public Task(String title, LocalDate dueDate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
   ```

   }

4. Journal (autoanalisi psicologica):

   package it.school.ailife.model;

   import java.time.LocalDateTime;
   import java.util.UUID;

   public class JournalEntry {

   ```
    private final String id;
    private LocalDateTime timestamp;
    private String text;           // cosa si è scritto
    private String moodLabel;      // es: "sereno", "ansioso", ecc. (anche opzionale)

    public JournalEntry(String text, String moodLabel) {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.text = text;
        this.moodLabel = moodLabel;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getText() {
        return text;
    }

    public String getMoodLabel() {
        return moodLabel;
    }
   ```

   }

5. Metriche fisiche:

   package it.school.ailife.model;

   import java.time.LocalDate;
   import java.util.UUID;

   public class PhysicalMetrics {

   ```
    private final String id;
    private LocalDate date;
    private Double weightKg;
    private Integer steps;
    private Double sleepHours;
    private Integer energyLevel; // 1..10

    public PhysicalMetrics(LocalDate date) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Double getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(Double sleepHours) {
        this.sleepHours = sleepHours;
    }

    public Integer getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(Integer energyLevel) {
        this.energyLevel = energyLevel;
    }
   ```

   }

6. Sintesi generata dall’AI (memoria compatta dello stato):

   package it.school.ailife.model;

   import java.time.LocalDateTime;
   import java.util.UUID;

   public class AiSummary {

   ```
    private final String id;
    private SessionType sessionType;
    private LocalDateTime timestamp;
    private String inputSummary;   // sintesi di cosa è stato fornito all'AI
    private String aiText;         // risposta dell'AI

    public AiSummary(SessionType sessionType, String inputSummary, String aiText) {
        this.id = UUID.randomUUID().toString();
        this.sessionType = sessionType;
        this.timestamp = LocalDateTime.now();
        this.inputSummary = inputSummary;
        this.aiText = aiText;
    }

    public String getId() {
        return id;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getInputSummary() {
        return inputSummary;
    }

    public String getAiText() {
        return aiText;
    }
   ```

   }

────────────────────────────────────────
FASE 3 – PERSISTENZA SEMPLICE (PACKAGE persistence)
────────────────────────────────────────

Scelta semplice: usare un file JSON per ogni “categoria” oppure un unico file complessivo.

Esempio di helper JSON generico (usando Jackson) che salva/legge una lista di oggetti:

```
package it.school.ailife.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage<T> {

    private final File file;
    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeReference<List<T>> typeRef;

    public JsonStorage(String filePath, TypeReference<List<T>> typeRef) {
        this.file = new File(filePath);
        this.typeRef = typeRef;
    }

    public List<T> loadAll() {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(file, typeRef);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveAll(List<T> data) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (IOException e) {
            // in un'app reale loggare
        }
    }
}
```

Repository per gli obiettivi (analoghi per journaling, metriche, sintesi):

```
package it.school.ailife.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import it.school.ailife.model.Goal;

import java.util.ArrayList;
import java.util.List;

public class GoalRepository {

    private final JsonStorage<Goal> storage;
    private List<Goal> cache = new ArrayList<>();

    public GoalRepository(String filePath) {
        this.storage = new JsonStorage<>(filePath, new TypeReference<List<Goal>>() {});
        this.cache = storage.loadAll();
    }

    public List<Goal> findAll() {
        return new ArrayList<>(cache);
    }

    public void add(Goal goal) {
        cache.add(goal);
        storage.saveAll(cache);
    }

    // eventuale ricerca per id, aggiornamento, ecc.
}
```

Stesso pattern per JournalRepository, PhysicalRepository, AiSummaryRepository, con file diversi.

────────────────────────────────────────
FASE 4 – LAYER AI (PACKAGE ai)
────────────────────────────────────────

1. Scelta provider:

   package it.school.ailife.ai;

   public enum AiProvider {
   OPENAI,
   GEMINI
   }

2. Interfaccia comune:

   package it.school.ailife.ai;

   public interface AiClient {

   ```
    String generateText(String systemPrompt, String userPrompt);
   ```

   }

* systemPrompt: istruzioni generali per il modello (es. “comportarsi come coach delicato e non medico”).
* userPrompt: contenuto specifico della sessione (obiettivi, testo diario, metriche fisiche).

3. Implementazione per un provider (esempio concettuale per OpenAI, senza dettagli di libreria):

   package it.school.ailife.ai;

   public class OpenAiClient implements AiClient {

   ```
    private final String apiKey;
    private final String model;

    public OpenAiClient(String apiKey, String model) {
        this.apiKey = apiKey;
        this.model = model;
    }

    @Override
    public String generateText(String systemPrompt, String userPrompt) {
        // Pseudocodice:
        // 1. costruire JSON della richiesta con role "system" e "user"
        // 2. fare POST all'endpoint delle Chat Completions
        // 3. leggere la risposta e estrarre il contenuto
        //
        // In un progetto didattico si può implementare usando HttpClient
        // e la documentazione ufficiale dell'API scelta.

        String fakeResponse =
            "Questa è una risposta di test dell'AI. " +
            "Integrare qui la chiamata reale all'API di ChatGPT/Gemini.";

        return fakeResponse;
    }
   ```

   }

In un laboratorio reale, sostituire il contenuto di generateText con la chiamata effettiva all’API (usando l’SDK o HttpClient).

4. PromptBuilder: genera prompt diversi a seconda dell’ambito.

   package it.school.ailife.ai;

   import it.school.ailife.model.Goal;
   import it.school.ailife.model.JournalEntry;
   import it.school.ailife.model.PhysicalMetrics;

   import java.util.List;

   public class PromptBuilder {

   ```
    public static String buildGoalSystemPrompt() {
        return "Sei un assistente che aiuta a definire obiettivi realistici, " +
               "scomponendoli in passi concreti e monitorabili. " +
               "Non fornire consigli medici o psicologici clinici.";
    }

    public static String buildGoalUserPrompt(List<Goal> goals) {
        StringBuilder sb = new StringBuilder();
        sb.append("Questi sono i miei obiettivi e il loro stato:\n");
        for (Goal g : goals) {
            sb.append("- ").append(g.getTitle())
              .append(" (").append(g.getType()).append(") ")
              .append(" scadenza: ").append(g.getTargetDate())
              .append("\n");
        }
        sb.append("Aiuta a migliorare la chiarezza, le priorità e proponi un piano operativo.");
        return sb.toString();
    }

    public static String buildPsychSystemPrompt() {
        return "Sei un assistente che aiuta alla riflessione personale, " +
               "non uno psicoterapeuta. Usa un tono rispettoso e non fare diagnosi.";
    }

    public static String buildPsychUserPrompt(List<JournalEntry> entries) {
        StringBuilder sb = new StringBuilder();
        sb.append("Questi sono alcuni pensieri recenti:\n");
        for (JournalEntry e : entries) {
            sb.append("- [").append(e.getTimestamp()).append("] ")
              .append(e.getText()).append(" (umore: ")
              .append(e.getMoodLabel()).append(")\n");
        }
        sb.append("Fornisci una sintesi dei temi ricorrenti e suggerimenti di riflessione personale.");
        return sb.toString();
    }

    public static String buildPhysicalSystemPrompt() {
        return "Sei un assistente che commenta in modo generale abitudini fisiche. " +
               "Non fornire consigli medici, non parlare di diagnosi o terapie.";
    }

    public static String buildPhysicalUserPrompt(List<PhysicalMetrics> metrics) {
        StringBuilder sb = new StringBuilder();
        sb.append("Questi sono alcuni dati fisici recenti (peso, passi, sonno, livello di energia):\n");
        for (PhysicalMetrics m : metrics) {
            sb.append("- ").append(m.getDate())
              .append(" peso: ").append(m.getWeightKg())
              .append(", passi: ").append(m.getSteps())
              .append(", sonno: ").append(m.getSleepHours())
              .append(", energia: ").append(m.getEnergyLevel())
              .append("\n");
        }
        sb.append("Fornisci una sintesi generale e consigli di abitudini di base, senza contenuti medici.");
        return sb.toString();
    }
   ```

   }

────────────────────────────────────────
FASE 5 – SERVIZI DI DOMINIO (PACKAGE service)
────────────────────────────────────────

Esempi:

1. GoalService:

   package it.school.ailife.service;

   import it.school.ailife.ai.AiClient;
   import it.school.ailife.ai.PromptBuilder;
   import it.school.ailife.model.AiSummary;
   import it.school.ailife.model.Goal;
   import it.school.ailife.model.SessionType;
   import it.school.ailife.persistence.GoalRepository;
   import it.school.ailife.persistence.AiSummaryRepository;

   import java.util.List;

   public class GoalService {

   ```
    private final GoalRepository goalRepo;
    private final AiSummaryRepository aiRepo;
    private final AiClient aiClient;

    public GoalService(GoalRepository goalRepo,
                       AiSummaryRepository aiRepo,
                       AiClient aiClient) {
        this.goalRepo = goalRepo;
        this.aiRepo = aiRepo;
        this.aiClient = aiClient;
    }

    public void addGoal(Goal g) {
        goalRepo.add(g);
    }

    public List<Goal> listGoals() {
        return goalRepo.findAll();
    }

    public AiSummary analyzeGoalsWithAi() {
        List<Goal> goals = goalRepo.findAll();
        String system = PromptBuilder.buildGoalSystemPrompt();
        String user = PromptBuilder.buildGoalUserPrompt(goals);
        String aiText = aiClient.generateText(system, user);

        AiSummary summary = new AiSummary(
            SessionType.GOAL_PLANNING,
            "Analisi obiettivi attuali",
            aiText
        );
        aiRepo.add(summary);
        return summary;
    }
   ```

   }

Analoghi PsychService e PhysicalService, che recuperano journaling/metriche, costruiscono prompt e salvano i risultati in AiSummaryRepository.

────────────────────────────────────────
FASE 6 – INTERFACCIA CLI (PACKAGE cli)
────────────────────────────────────────

Esempio di classe App con main e un semplice menù:

```
package it.school.ailife.cli;

import it.school.ailife.ai.AiClient;
import it.school.ailife.ai.OpenAiClient;
import it.school.ailife.model.Goal;
import it.school.ailife.model.GoalType;
import it.school.ailife.persistence.AiSummaryRepository;
import it.school.ailife.persistence.GoalRepository;
import it.school.ailife.service.GoalService;

import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // In un caso reale, leggere apiKey e model da variabili d'ambiente o config
        String apiKey = System.getenv("OPENAI_API_KEY");
        String model = "MODEL_NAME"; // es. "gpt-4.1"

        AiClient aiClient = new OpenAiClient(apiKey, model);

        GoalRepository goalRepo = new GoalRepository("goals.json");
        AiSummaryRepository aiRepo = new AiSummaryRepository("ai_summaries.json");
        GoalService goalService = new GoalService(goalRepo, aiRepo, aiClient);

        boolean running = true;
        while (running) {
            System.out.println("=== AI Life Companion ===");
            System.out.println("1. Gestione obiettivi");
            System.out.println("2. Autoanalisi psicologica");
            System.out.println("3. Autoanalisi fisica");
            System.out.println("4. Ultime sintesi AI");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    handleGoals(scanner, goalService);
                    break;
                // analoghi per 2,3,4
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Scelta non valida");
            }
        }

        System.out.println("Chiusura applicazione.");
    }

    private static void handleGoals(Scanner scanner, GoalService goalService) {
        boolean back = false;
        while (!back) {
            System.out.println("--- Gestione Obiettivi ---");
            System.out.println("1. Elenco obiettivi");
            System.out.println("2. Aggiungi obiettivo");
            System.out.println("3. Chiedi analisi AI");
            System.out.println("0. Indietro");
            System.out.print("Scelta: ");
            String c = scanner.nextLine();
            switch (c) {
                case "1":
                    goalService.listGoals().forEach(g ->
                        System.out.println(g.getTitle() + " (" + g.getType() + ") scadenza: " + g.getTargetDate())
                    );
                    break;
                case "2":
                    System.out.print("Titolo: ");
                    String title = scanner.nextLine();
                    System.out.print("Descrizione: ");
                    String desc = scanner.nextLine();
                    System.out.print("Tipo (LONG_TERM/MID_TERM/SHORT_TERM): ");
                    GoalType type = GoalType.valueOf(scanner.nextLine());
                    System.out.print("Scadenza (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    Goal g = new Goal(title, desc, type, date);
                    goalService.addGoal(g);
                    System.out.println("Obiettivo aggiunto.");
                    break;
                case "3":
                    System.out.println("Richiesta all'AI in corso...");
                    var summary = goalService.analyzeGoalsWithAi();
                    System.out.println("Risposta AI:");
                    System.out.println(summary.getAiText());
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Scelta non valida");
            }
        }
    }
}
```

AiSummaryRepository è analogo a GoalRepository, ma lavora su AiSummary.

────────────────────────────────────────
COME PROCEDERE OPERATIVAMENTE
────────────────────────────────────────

1. Creare il progetto Maven e impostare pom.xml.
2. Creare i package e le classi del modello (Goal, Task, JournalEntry, PhysicalMetrics, AiSummary, enum).
3. Implementare la persistenza minimale con JsonStorage e i repository.
4. Implementare l’interfaccia AiClient e una prima versione di OpenAiClient che inizialmente restituisce risposte “fake”; in un secondo momento integrare la chiamata reale all’API.
5. Scrivere PromptBuilder per i tre ambiti.
6. Implementare i servizi (GoalService, PsychService, PhysicalService).
7. Implementare la CLI con un menù semplice.
8. Testare passo passo:

   * prima la parte di obiettivi + AI, poi journaling, poi autoanalisi fisica.
9. Solo alla fine attivare l’uso reale dell’API AI (gestendo chiave, errori di rete, limiti).

Se si desidera, nel passo successivo è possibile:

* dettagliare la parte di integrazione reale con l’API (con codice HTTP completo),
* aggiungere i servizi PsychService e PhysicalService con tutto il relativo codice,
* aggiungere test JUnit e script Maven per eseguire l’app.
