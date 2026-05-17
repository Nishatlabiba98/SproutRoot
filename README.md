# 🌱 SproutRoot

A Montessori-inspired preschool prep app built for parents who want more than a random game.

SproutRoot lets a parent curate a learning experience for their child — choosing activities they trust — while the app ties every session to real **Head Start ELOF developmental milestones**.

---

## The Problem

Preparing a child for preschool is overwhelming. Generic apps are cluttered with ads and disconnected from real developmental standards. There is no single tool that lets a parent hand-pick Montessori-aligned activities and track progress against federal learning standards.

---

## Phase 1 — Capstone MVP

### 4 Games

| Game | Skill | ELOF Domain |
|---|---|---|
| Sound Safari | Letters & Phonics | Literacy |
| Berry Basket | Numbers & Counting | Mathematics |
| Shape Village | Shapes & Colors | Science & Cognition |
| Sorting Tray | Critical Thinking | Science & Cognition |

### Java Features
- **Scoring engine** — base points + response time bonus per answer
- **Mistake pattern detector** — records which wrong answers a child repeats per concept
- **ELOF milestone tracker** — maps every session to a real Head Start milestone
- **Pixabay integration** — Spring Boot fetches and caches kid-safe images server-side

### Parent Dashboard
- Per-child progress and session history
- ELOF milestone mastery scores
- Flagged mistake patterns

---

## Phase 2 — Post-Capstone

- Memory Grove (5th game)
- Adaptive difficulty engine
- Spaced repetition scheduler (reads Phase 1 mistake pattern data)
- Dashboard v2 — analytics, PDF progress report
- Deployment: Railway (backend) + Vercel (frontend) — ~$5/month

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17 + Spring Boot |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Auth | Spring Security (BCrypt) |
| Frontend | Vanilla JS + HTML5 + CSS3 |
| Images | Pixabay REST API |
| Standards | Head Start ELOF (seeded into DB) |

---

## Milestone Standard

SproutRoot uses the **Head Start Early Learning Outcomes Framework (ELOF)** — a federal public domain standard covering birth to age 5, organized by domain and age range. Every game session is mapped to real developmental milestones.

---

## Project Structure

```
sproutroot/
├── src/main/java/rocks/zipcode/sproutroot/
│   ├── controller/
│   ├── model/
│   ├── repository/
│   ├── service/
│   └── config/
├── src/main/resources/
│   ├── application.properties
│   └── data.sql
└── pom.xml
```

---

## Getting Started

```bash
# Clone the repo
git clone https://github.com/Nishatlabiba98/sproutroot.git
cd sproutroot

# Create the database
createdb sproutroot

# Add your config
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Fill in your DB password and Pixabay API key

# Run the app
./mvnw spring-boot:run
```

### Quick Commands

```bash
# Compile
./mvnw compile

# Kill existing process on port 8080
lsof -ti:8080 | xargs kill -9

# Clean rebuild and run
./mvnw clean spring-boot:run

# Check curriculum content seeded in database
psql -d sproutroot -c "SELECT type, COUNT(*) FROM curriculum_content GROUP BY type ORDER BY type;"

# Check ELOF milestones
psql -d sproutroot -c "SELECT code, domain FROM elof_milestone ORDER BY code;"
```

---

## Curriculum Data

54 rows of real Montessori curriculum content in the database:

- **26 letters** — Gettman phonetic sequence, difficulty 1-4
- **10 numbers** — quantity before symbol, 1-5 then 6-10
- **8 shapes** — basic four first, then complex
- **10 categories** — living/non-living → land/air/water → food groups

---

## Built by

**Nishat** — Zip Code Wilmington, PyJava2025 cohort  
Capstone passion project 🌱

---

## References

- Montessori curriculum based on David Gettman's books
- Real-world activity interface planned for Phase 2
- MontessoriConnect co-op layer planned for Phase 3
