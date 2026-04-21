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
