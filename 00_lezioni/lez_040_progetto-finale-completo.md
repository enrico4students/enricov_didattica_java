
Di seguito un **progetto completo** (minimale ma funzionante) per:

AI Life Companion – assistente personale per:

* organizzare vita e obiettivi
* autoanalisi psicologica
* autoanalisi fisica
  con storicizzazione su file JSON e uso di API ChatGPT (OpenAI) tramite HTTP.

Tutto è pensato per essere:

* un normale progetto Maven
* a riga di comando
* con chiamata reale all’endpoint `/v1/chat/completions` (da attivare inserendo la propria API key).

Formato:

* indicazione del **percorso file**
* codice con **indentazione a 4 spazi** (senza ```).

Consiglio: creare la struttura esattamente come indicato, poi usare `mvn compile` e `mvn exec:java`.

---   

## Comando maven per generare il progetto **vuoto iniziale**

mvn archetype:generate -DgroupId=it.school -DartifactId=ai-life-companion -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false

---

1. Struttura del progetto

---

Cartella radice, ad esempio:

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
```

I file JSON verranno creati nella cartella di esecuzione:

* goals.json
* journal.json
* physical.json
* ai_summaries.json

Per semplicità non si usa sottocartella `data/` (così non serve crearla).

---

2. pom.xml

---

Percorso: `ai-life-companion/pom.xml`

```
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
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
            <version>2.20.1</version>
        </dependency>

        <!-- Supporto Java 8+ Date/Time (LocalDate, LocalDateTime) -->
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-jsr310</artifactId>
            <version>2.20.1</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.14.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>

            <!-- Per eseguire con: mvn exec:java -->
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.5.1</version>
                <configuration>
                    <mainClass>it.school.ailife.cli.App</mainClass>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

---

3. MODEL – package it.school.ailife.model

---

3.1 `SessionType.java`

```
package it.school.ailife.model;

public enum SessionType {
    GOAL_PLANNING,
    PSYCH_SELF_ANALYSIS,
    PHYSICAL_SELF_ANALYSIS
}
```

3.2 `GoalType.java`

```
package it.school.ailife.model;

public enum GoalType {
    LONG_TERM,
    MID_TERM,
    SHORT_TERM
}
```

3.3 `Task.java`

```
package it.school.ailife.model;

import java.time.LocalDate;
import java.util.UUID;

public class Task {

    private final String id;
    private String title;
    private boolean completed;
    private LocalDate dueDate;

    public Task() {
        this.id = UUID.randomUUID().toString();
    }

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

    public void setTitle(String title) {
        this.title = title;
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

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
```

3.4 `Goal.java`

```
package it.school.ailife.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Goal {

    private final String id;
    private String title;
    private String description;
    private GoalType type;
    private LocalDate targetDate;
    private List<Task> tasks = new ArrayList<>();

    public Goal() {
        this.id = UUID.randomUUID().toString();
    }

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GoalType getType() {
        return type;
    }

    public void setType(GoalType type) {
        this.type = type;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }
}
```

3.5 `JournalEntry.java`

```
package it.school.ailife.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class JournalEntry {

    private final String id;
    private LocalDateTime timestamp;
    private String text;
    private String moodLabel;

    public JournalEntry() {
        this.id = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
    }

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

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMoodLabel() {
        return moodLabel;
    }

    public void setMoodLabel(String moodLabel) {
        this.moodLabel = moodLabel;
    }
}
```

3.6 `PhysicalMetrics.java`

```
package it.school.ailife.model;

import java.time.LocalDate;
import java.util.UUID;

public class PhysicalMetrics {

    private final String id;
    private LocalDate date;
    private Double weightKg;
    private Integer steps;
    private Double sleepHours;
    private Integer energyLevel; // 1..10

    public PhysicalMetrics() {
        this.id = UUID.randomUUID().toString();
    }

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

    public void setDate(LocalDate date) {
        this.date = date;
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
}
```

3.7 `AiSummary.java`

```
package it.school.ailife.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class AiSummary {

    private final String id;
    private SessionType sessionType;
    private LocalDateTime timestamp;
    private String inputSummary;
    private String aiText;

    public AiSummary() {
        this.id = UUID.randomUUID().toString();
    }

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

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getInputSummary() {
        return inputSummary;
    }

    public void setInputSummary(String inputSummary) {
        this.inputSummary = inputSummary;
    }

    public String getAiText() {
        return aiText;
    }

    public void setAiText(String aiText) {
        this.aiText = aiText;
    }
}
```

---

4. PERSISTENZA – package it.school.ailife.persistence

---

4.1 `JsonStorage.java`

```
package it.school.ailife.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage<T> {

    private final File file;
    private final ObjectMapper mapper;
    private final TypeReference<List<T>> typeRef;

    public JsonStorage(String filePath, TypeReference<List<T>> typeRef) {
        this.file = new File(filePath);
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
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

4.2 `GoalRepository.java`

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
}
```

4.3 `JournalRepository.java`

```
package it.school.ailife.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import it.school.ailife.model.JournalEntry;

import java.util.ArrayList;
import java.util.List;

public class JournalRepository {

    private final JsonStorage<JournalEntry> storage;
    private List<JournalEntry> cache = new ArrayList<>();

    public JournalRepository(String filePath) {
        this.storage = new JsonStorage<>(filePath, new TypeReference<List<JournalEntry>>() {});
        this.cache = storage.loadAll();
    }

    public List<JournalEntry> findAll() {
        return new ArrayList<>(cache);
    }

    public void add(JournalEntry entry) {
        cache.add(entry);
        storage.saveAll(cache);
    }
}
```

4.4 `PhysicalRepository.java`

```
package it.school.ailife.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import it.school.ailife.model.PhysicalMetrics;

import java.util.ArrayList;
import java.util.List;

public class PhysicalRepository {

    private final JsonStorage<PhysicalMetrics> storage;
    private List<PhysicalMetrics> cache = new ArrayList<>();

    public PhysicalRepository(String filePath) {
        this.storage = new JsonStorage<>(filePath, new TypeReference<List<PhysicalMetrics>>() {});
        this.cache = storage.loadAll();
    }

    public List<PhysicalMetrics> findAll() {
        return new ArrayList<>(cache);
    }

    public void add(PhysicalMetrics metrics) {
        cache.add(metrics);
        storage.saveAll(cache);
    }
}
```

4.5 `AiSummaryRepository.java`

```
package it.school.ailife.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import it.school.ailife.model.AiSummary;

import java.util.ArrayList;
import java.util.List;

public class AiSummaryRepository {

    private final JsonStorage<AiSummary> storage;
    private List<AiSummary> cache = new ArrayList<>();

    public AiSummaryRepository(String filePath) {
        this.storage = new JsonStorage<>(filePath, new TypeReference<List<AiSummary>>() {});
        this.cache = storage.loadAll();
    }

    public List<AiSummary> findAll() {
        return new ArrayList<>(cache);
    }

    public void add(AiSummary summary) {
        cache.add(summary);
        storage.saveAll(cache);
    }
}
```

---

5. LAYER AI – package it.school.ailife.ai

---

5.1 `AiClient.java`

```
package it.school.ailife.ai;

public interface AiClient {

    String generateText(String systemPrompt, String userPrompt);
}
```

5.2 `OpenAiClient.java`
(usa HttpClient standard verso `/v1/chat/completions`, vedi documentazione:
[https://platform.openai.com/docs/api-reference/chat](https://platform.openai.com/docs/api-reference/chat) ([OpenAI Platform][1]))

```
package it.school.ailife.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenAiClient implements AiClient {

    private final String apiKey;
    private final String model;
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public OpenAiClient(String apiKey, String model) {
        this.apiKey = apiKey;
        this.model = model;
        this.httpClient = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    @Override
    public String generateText(String systemPrompt, String userPrompt) {
        if (apiKey == null || apiKey.isBlank()) {
            return "API key non configurata. Impostare la variabile di ambiente OPENAI_API_KEY.";
        }

        try {
            Map<String, Object> body = new HashMap<>();

            Map<String, String> sysMsg = new HashMap<>();
            sysMsg.put("role", "system");
            sysMsg.put("content", systemPrompt);

            Map<String, String> userMsg = new HashMap<>();
            userMsg.put("role", "user");
            userMsg.put("content", userPrompt);

            body.put("model", model);
            body.put("messages", List.of(sysMsg, userMsg));

            String json = mapper.writeValueAsString(body);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return "Errore chiamata OpenAI, HTTP " + response.statusCode() + ": " + response.body();
            }

            JsonNode root = mapper.readTree(response.body());
            JsonNode choices = root.get("choices");
            if (choices != null && choices.isArray() && choices.size() > 0) {
                JsonNode message = choices.get(0).get("message");
                if (message != null && message.get("content") != null) {
                    return message.get("content").asText();
                }
            }
            return "Risposta AI non nel formato atteso.";
        } catch (IOException | InterruptedException e) {
            return "Eccezione nella chiamata OpenAI: " + e.getMessage();
        }
    }
}
```

5.3 `PromptBuilder.java`

```
package it.school.ailife.ai;

import it.school.ailife.model.Goal;
import it.school.ailife.model.JournalEntry;
import it.school.ailife.model.PhysicalMetrics;

import java.util.List;

public class PromptBuilder {

    public static String buildGoalSystemPrompt() {
        return "Sei un assistente che aiuta a definire obiettivi realistici, " +
               "a chiarirli e a scomporli in passi concreti. " +
               "Non fornire consigli medici o psicologici clinici. " +
               "Usare un tono pratico e incoraggiante.";
    }

    public static String buildGoalUserPrompt(List<Goal> goals) {
        StringBuilder sb = new StringBuilder();
        sb.append("Questi sono i miei obiettivi e la loro scadenza:\n");
        for (Goal g : goals) {
            sb.append("- ").append(g.getTitle())
              .append(" (").append(g.getType()).append(")")
              .append(" scadenza: ").append(g.getTargetDate())
              .append(" descrizione: ").append(g.getDescription())
              .append("\n");
        }
        sb.append("\nIndividuare priorità, suggerire eventuali modifiche e proporre un piano operativo in passi concreti.");
        return sb.toString();
    }

    public static String buildPsychSystemPrompt() {
        return "Sei un assistente che aiuta alla riflessione personale. " +
               "Non sei uno psicoterapeuta, non formulare diagnosi, non dare consigli medici. " +
               "Aiutare a mettere a fuoco emozioni, pensieri ricorrenti e possibili spunti di auto-riflessione.";
    }

    public static String buildPsychUserPrompt(List<JournalEntry> entries) {
        StringBuilder sb = new StringBuilder();
        sb.append("Questi sono alcuni pensieri/emozioni recenti:\n");
        for (JournalEntry e : entries) {
            sb.append("- [").append(e.getTimestamp()).append("] ")
              .append(e.getText())
              .append(" (umore: ").append(e.getMoodLabel()).append(")")
              .append("\n");
        }
        sb.append("\nSintetizzare temi ricorrenti, possibili fonti di stress e suggerire alcune domande di auto-riflessione utili.");
        return sb.toString();
    }

    public static String buildPhysicalSystemPrompt() {
        return "Sei un assistente che commenta abitudini fisiche generali (sonno, movimento, energia). " +
               "Non fornire consigli medici, non parlare di diagnosi o terapie. " +
               "Limitarsi a osservazioni generali e suggerimenti di abitudini salutari di base.";
    }

    public static String buildPhysicalUserPrompt(List<PhysicalMetrics> metrics) {
        StringBuilder sb = new StringBuilder();
        sb.append("Questi sono alcuni dati fisici recenti (peso, passi, sonno, livello di energia):\n");
        for (PhysicalMetrics m : metrics) {
            sb.append("- ").append(m.getDate())
              .append(" peso: ").append(m.getWeightKg())
              .append(", passi: ").append(m.getSteps())
              .append(", sonno (ore): ").append(m.getSleepHours())
              .append(", energia (1-10): ").append(m.getEnergyLevel())
              .append("\n");
        }
        sb.append("\nFornire una sintesi generale degli andamenti e suggerire abitudini base (non mediche) per migliorare il benessere.");
        return sb.toString();
    }
}
```

---

6. SERVIZI – package it.school.ailife.service

---

6.1 `GoalService.java`

```
package it.school.ailife.service;

import it.school.ailife.ai.AiClient;
import it.school.ailife.ai.PromptBuilder;
import it.school.ailife.model.AiSummary;
import it.school.ailife.model.Goal;
import it.school.ailife.model.SessionType;
import it.school.ailife.persistence.AiSummaryRepository;
import it.school.ailife.persistence.GoalRepository;

import java.util.List;

public class GoalService {

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

    public void addGoal(Goal goal) {
        goalRepo.add(goal);
    }

    public List<Goal> listGoals() {
        return goalRepo.findAll();
    }

    public AiSummary analyzeGoalsWithAi() {
        List<Goal> goals = goalRepo.findAll();
        if (goals.isEmpty()) {
            return new AiSummary(SessionType.GOAL_PLANNING,
                    "Nessun obiettivo presente",
                    "Inserire almeno un obiettivo prima di chiedere analisi AI.");
        }
        String system = PromptBuilder.buildGoalSystemPrompt();
        String user = PromptBuilder.buildGoalUserPrompt(goals);
        String aiText = aiClient.generateText(system, user);

        AiSummary summary = new AiSummary(
                SessionType.GOAL_PLANNING,
                "Analisi degli obiettivi attuali",
                aiText
        );
        aiRepo.add(summary);
        return summary;
    }
}
```

6.2 `PsychService.java`

```
package it.school.ailife.service;

import it.school.ailife.ai.AiClient;
import it.school.ailife.ai.PromptBuilder;
import it.school.ailife.model.AiSummary;
import it.school.ailife.model.JournalEntry;
import it.school.ailife.model.SessionType;
import it.school.ailife.persistence.AiSummaryRepository;
import it.school.ailife.persistence.JournalRepository;

import java.util.List;

public class PsychService {

    private final JournalRepository journalRepo;
    private final AiSummaryRepository aiRepo;
    private final AiClient aiClient;

    public PsychService(JournalRepository journalRepo,
                        AiSummaryRepository aiRepo,
                        AiClient aiClient) {
        this.journalRepo = journalRepo;
        this.aiRepo = aiRepo;
        this.aiClient = aiClient;
    }

    public void addEntry(JournalEntry entry) {
        journalRepo.add(entry);
    }

    public List<JournalEntry> listEntries() {
        return journalRepo.findAll();
    }

    public AiSummary analyzeJournalWithAi() {
        List<JournalEntry> entries = journalRepo.findAll();
        if (entries.isEmpty()) {
            return new AiSummary(SessionType.PSYCH_SELF_ANALYSIS,
                    "Nessuna voce di diario",
                    "Inserire almeno una voce di diario prima di chiedere analisi AI.");
        }
        String system = PromptBuilder.buildPsychSystemPrompt();
        String user = PromptBuilder.buildPsychUserPrompt(entries);
        String aiText = aiClient.generateText(system, user);

        AiSummary summary = new AiSummary(
                SessionType.PSYCH_SELF_ANALYSIS,
                "Sintesi delle voci di diario recenti",
                aiText
        );
        aiRepo.add(summary);
        return summary;
    }
}
```

6.3 `PhysicalService.java`

```
package it.school.ailife.service;

import it.school.ailife.ai.AiClient;
import it.school.ailife.ai.PromptBuilder;
import it.school.ailife.model.AiSummary;
import it.school.ailife.model.PhysicalMetrics;
import it.school.ailife.model.SessionType;
import it.school.ailife.persistence.AiSummaryRepository;
import it.school.ailife.persistence.PhysicalRepository;

import java.util.List;

public class PhysicalService {

    private final PhysicalRepository physicalRepo;
    private final AiSummaryRepository aiRepo;
    private final AiClient aiClient;

    public PhysicalService(PhysicalRepository physicalRepo,
                           AiSummaryRepository aiRepo,
                           AiClient aiClient) {
        this.physicalRepo = physicalRepo;
        this.aiRepo = aiRepo;
        this.aiClient = aiClient;
    }

    public void addMetrics(PhysicalMetrics metrics) {
        physicalRepo.add(metrics);
    }

    public List<PhysicalMetrics> listMetrics() {
        return physicalRepo.findAll();
    }

    public AiSummary analyzePhysicalWithAi() {
        List<PhysicalMetrics> data = physicalRepo.findAll();
        if (data.isEmpty()) {
            return new AiSummary(SessionType.PHYSICAL_SELF_ANALYSIS,
                    "Nessun dato fisico",
                    "Inserire almeno una giornata di dati fisici prima di chiedere analisi AI.");
        }
        String system = PromptBuilder.buildPhysicalSystemPrompt();
        String user = PromptBuilder.buildPhysicalUserPrompt(data);
        String aiText = aiClient.generateText(system, user);

        AiSummary summary = new AiSummary(
                SessionType.PHYSICAL_SELF_ANALYSIS,
                "Sintesi dei dati fisici recenti",
                aiText
        );
        aiRepo.add(summary);
        return summary;
    }
}
```

---

7. CLI – package it.school.ailife.cli

---

7.1 `App.java` – main e menù testuale

```
package it.school.ailife.cli;

import it.school.ailife.ai.AiClient;
import it.school.ailife.ai.OpenAiClient;
import it.school.ailife.model.AiSummary;
import it.school.ailife.model.Goal;
import it.school.ailife.model.GoalType;
import it.school.ailife.model.JournalEntry;
import it.school.ailife.model.PhysicalMetrics;
import it.school.ailife.persistence.AiSummaryRepository;
import it.school.ailife.persistence.GoalRepository;
import it.school.ailife.persistence.JournalRepository;
import it.school.ailife.persistence.PhysicalRepository;
import it.school.ailife.service.GoalService;
import it.school.ailife.service.PhysicalService;
import it.school.ailife.service.PsychService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String apiKey = System.getenv("OPENAI_API_KEY");
        // modello da usare, ad esempio "gpt-4o-mini" o altro modello disponibile
        String model = "gpt-4o-mini";

        AiClient aiClient = new OpenAiClient(apiKey, model);

        GoalRepository goalRepo = new GoalRepository("goals.json");
        JournalRepository journalRepo = new JournalRepository("journal.json");
        PhysicalRepository physicalRepo = new PhysicalRepository("physical.json");
        AiSummaryRepository aiRepo = new AiSummaryRepository("ai_summaries.json");

        GoalService goalService = new GoalService(goalRepo, aiRepo, aiClient);
        PsychService psychService = new PsychService(journalRepo, aiRepo, aiClient);
        PhysicalService physService = new PhysicalService(physicalRepo, aiRepo, aiClient);

        boolean running = true;
        while (running) {
            System.out.println("=== AI Life Companion ===");
            System.out.println("1. Gestione obiettivi");
            System.out.println("2. Autoanalisi psicologica (diario)");
            System.out.println("3. Autoanalisi fisica");
            System.out.println("4. Visualizza ultime sintesi AI");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    handleGoals(scanner, goalService);
                    break;
                case "2":
                    handlePsych(scanner, psychService);
                    break;
                case "3":
                    handlePhysical(scanner, physService);
                    break;
                case "4":
                    handleSummaries(aiRepo);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
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
                    List<Goal> goals = goalService.listGoals();
                    if (goals.isEmpty()) {
                        System.out.println("Nessun obiettivo.");
                    } else {
                        for (Goal g : goals) {
                            System.out.println("- " + g.getTitle() +
                                    " (" + g.getType() + ") scadenza: " + g.getTargetDate());
                        }
                    }
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
                    AiSummary s = goalService.analyzeGoalsWithAi();
                    System.out.println("Sintesi AI:");
                    System.out.println(s.getAiText());
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    private static void handlePsych(Scanner scanner, PsychService psychService) {
        boolean back = false;
        while (!back) {
            System.out.println("--- Autoanalisi psicologica (diario) ---");
            System.out.println("1. Elenco voci diario");
            System.out.println("2. Aggiungi voce diario");
            System.out.println("3. Chiedi sintesi AI");
            System.out.println("0. Indietro");
            System.out.print("Scelta: ");
            String c = scanner.nextLine();
            switch (c) {
                case "1":
                    List<JournalEntry> entries = psychService.listEntries();
                    if (entries.isEmpty()) {
                        System.out.println("Nessuna voce di diario.");
                    } else {
                        for (JournalEntry e : entries) {
                            System.out.println("- [" + e.getTimestamp() + "] "
                                    + e.getText() + " (umore: " + e.getMoodLabel() + ")");
                        }
                    }
                    break;
                case "2":
                    System.out.print("Testo (pensiero/emozione): ");
                    String text = scanner.nextLine();
                    System.out.print("Etichetta umore (facoltativa): ");
                    String mood = scanner.nextLine();
                    if (mood.isBlank()) {
                        mood = "non specificato";
                    }
                    JournalEntry je = new JournalEntry(text, mood);
                    psychService.addEntry(je);
                    System.out.println("Voce di diario aggiunta.");
                    break;
                case "3":
                    System.out.println("Richiesta all'AI in corso...");
                    AiSummary s = psychService.analyzeJournalWithAi();
                    System.out.println("Sintesi AI:");
                    System.out.println(s.getAiText());
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    private static void handlePhysical(Scanner scanner, PhysicalService physService) {
        boolean back = false;
        while (!back) {
            System.out.println("--- Autoanalisi fisica ---");
            System.out.println("1. Elenco dati");
            System.out.println("2. Aggiungi dati per una giornata");
            System.out.println("3. Chiedi sintesi AI");
            System.out.println("0. Indietro");
            System.out.print("Scelta: ");
            String c = scanner.nextLine();
            switch (c) {
                case "1":
                    List<PhysicalMetrics> data = physService.listMetrics();
                    if (data.isEmpty()) {
                        System.out.println("Nessun dato fisico.");
                    } else {
                        for (PhysicalMetrics m : data) {
                            System.out.println("- " + m.getDate()
                                    + " peso: " + m.getWeightKg()
                                    + ", passi: " + m.getSteps()
                                    + ", sonno (ore): " + m.getSleepHours()
                                    + ", energia: " + m.getEnergyLevel());
                        }
                    }
                    break;
                case "2":
                    System.out.print("Data (YYYY-MM-DD): ");
                    LocalDate d = LocalDate.parse(scanner.nextLine());
                    PhysicalMetrics pm = new PhysicalMetrics(d);
                    System.out.print("Peso in kg (facoltativo, invio per saltare): ");
                    String w = scanner.nextLine();
                    if (!w.isBlank()) {
                        pm.setWeightKg(Double.parseDouble(w));
                    }
                    System.out.print("Passi (facoltativo): ");
                    String steps = scanner.nextLine();
                    if (!steps.isBlank()) {
                        pm.setSteps(Integer.parseInt(steps));
                    }
                    System.out.print("Ore di sonno (facoltativo): ");
                    String sh = scanner.nextLine();
                    if (!sh.isBlank()) {
                        pm.setSleepHours(Double.parseDouble(sh));
                    }
                    System.out.print("Livello di energia 1-10 (facoltativo): ");
                    String en = scanner.nextLine();
                    if (!en.isBlank()) {
                        pm.setEnergyLevel(Integer.parseInt(en));
                    }
                    physService.addMetrics(pm);
                    System.out.println("Dati fisici aggiunti.");
                    break;
                case "3":
                    System.out.println("Richiesta all'AI in corso...");
                    AiSummary s2 = physService.analyzePhysicalWithAi();
                    System.out.println("Sintesi AI:");
                    System.out.println(s2.getAiText());
                    break;
                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
    }

    private static void handleSummaries(AiSummaryRepository aiRepo) {
        System.out.println("--- Ultime sintesi AI ---");
        List<AiSummary> list = aiRepo.findAll();
        if (list.isEmpty()) {
            System.out.println("Nessuna sintesi AI salvata.");
        } else {
            list.stream()
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .limit(10)
                .forEach(s -> {
                    System.out.println("[" + s.getTimestamp() + "] " + s.getSessionType());
                    System.out.println(s.getAiText());
                    System.out.println("-----");
                });
        }
    }
}
```

---

8. Come eseguire e cosa configurare

---

1. Impostare la variabile di ambiente OPENAI_API_KEY con la propria chiave:
   istruzioni ufficiali:
   OpenAI API Reference (autenticazione) – [https://platform.openai.com/docs/api-reference/introduction](https://platform.openai.com/docs/api-reference/introduction) ([OpenAI Platform][2])

2. Dalla cartella del progetto:

   mvn compile
   mvn exec:java

3. Il modello usato nella classe `App` è indicato nella variabile `model`.
   Adeguare a un modello effettivamente disponibile sul proprio account (es. `"gpt-4o-mini"` o simili, secondo la pagina modelli).

Se si vuole, nel passo successivo si possono aggiungere:

* test JUnit per i servizi e per JsonStorage
* gestione più robusta degli errori di rete
* una versione con Gemini (aggiungendo un altro `AiClient` e uno switch di provider).

[1]: https://platform.openai.com/docs/api-reference/chat?utm_source=chatgpt.com "Chat Completions | OpenAI API Reference"
[2]: https://platform.openai.com/docs/api-reference/introduction?utm_source=chatgpt.com "API Reference - OpenAI API"
