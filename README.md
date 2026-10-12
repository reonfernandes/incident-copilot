# Incident Copilot

An AI-assisted incident investigation system.

When a service starts throwing errors, Incident Copilot collects the errors, groups repeated ones into a single incident, and uses an AI agent to investigate the likely root cause. The result is shown on a dashboard with a short summary and a suggested next step.

## Tech Stack

- Java 21
- Spring Boot 4.1.1, Maven (multi-module)
- Spring Cloud Gateway
- Spring AI
- Apache Kafka
- PostgreSQL with pgvector
- Redis
- Ollama (local AI model)
- Docker Compose

## Docker Compose

The infrastructure runs locally in Docker:

| Service    | Container     | Port  | Purpose                                        |
|------------|---------------|-------|------------------------------------------------|
| PostgreSQL | `ic-postgres` | 5432  | Main database, with pgvector for vector search |
| Redis      | `ic-redis`    | 6379  | Caching and duplicate detection                |
| Kafka      | `ic-kafka`    | 9092  | Event streaming between services               |
| Ollama     | `ic-ollama`   | 11434 | Runs the AI model locally (optional)           |

Start PostgreSQL, Redis and Kafka:

```bash
  docker compose up -d
```

Ollama is optional and only starts with the `ai` profile:

```bash
  docker compose --profile ai up -d
```

Check container status:

```bash
  docker compose ps
```

Stop everything:

```bash
  docker compose down
```
