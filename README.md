# 🌱 SproutRoot

A Montessori-inspired preschool prep app built for parents who want more than a random game.

SproutRoot lets a parent curate a learning experience for their child — choosing activities they trust — while the app ties every session to real **Head Start ELOF developmental milestones** and tracks preschool readiness over time.

---

## The Problem

Preparing a child for preschool is overwhelming. Generic apps are cluttered with ads and disconnected from real developmental standards. There is no single tool that lets a parent hand-pick Montessori-aligned learning materials, tie them to actual milestones, and track whether their child is truly ready for preschool — all in one calm, focused place.

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

SproutRoot uses the **Head Start Early Learning Outcomes Framework (ELOF)** — a federal public domain standard covering birth to age 5, organized by domain and age range. Every game session is mapped to a specific ELOF milestone and stored per child.

---

## Project Structure *(coming soon)*

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

## Getting Started *(coming soon)*

```bash
# Clone the repo
git clone https://github.com/YOUR_USERNAME/sproutroot.git

# Create the database
createdb sproutroot

# Add your config
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Fill in your DB password and Pixabay API key

# Run the app
./mvnw spring-boot:run
```

---

## Built by

Nishat — Zip Code Wilmington, PyJava2025 cohort  
Capstone passion project 🌱

## also going to seed in a book by beth wood which will be extending activities from the game itself.

mvn compile shortcut
./mvnw compile
lsof -ti:8080 | xargs kill -9
./mvnw clean spring-boot:run

psql -d sproutroot -c "SELECT type, COUNT(*) FROM curriculum_content GROUP BY type ORDER BY type;"
54 rows of real Montessori curriculum content in the database:

26 letters — Gettman phonetic sequence, difficulty 1-4
10 numbers — quantity before symbol, 1-5 then 6-10
8 shapes — basic four first, then complex
10 categories — living/non-living → land/air/water → food groups

psql -d sproutroot -c "SELECT code, domain FROM elof_milestone ORDER BY code;"
code  |              domain             
 
-------+---------------------------------
-
 ATL-1 | Approaches to Learning
 COG-1 | Cognition
 COG-2 | Cognition
 COG-3 | Cognition
 COG-4 | Cognition
 COG-5 | Cognition
:


some brainstorming
Phase 1 — the 4 games (what we're building now)
Phase 2 — real-world activity interface
Phase 3 — MontessoriConnect co-op layer

we are also going to be using david gettman's book as a reference for seeding in the data.
Step 6: Game Engine. This is the Java logic that powers each game — picking questions, validating answers, scoring, detecting mistakes. The most interesting part of the backend.