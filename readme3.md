#controller layer
The controller layer is the front door of your app. It's the first thing that receives an HTTP request and the last thing that sends a response back.

Its only two jobs:

Receive the HTTP request
Call the right service and return the result

That's it. No logic. No database calls. Just routing.

What a request looks like flowing through SproutRoot:
Browser/JS → POST /api/children → ChildController
                                       ↓
                                  ChildService (logic)
                                       ↓
                                  ChildRepository (database)
                                       ↓
                                  Returns Child object
                                       ↓
                                  ChildController sends JSON back
                                       ↓
                                  Browser gets the response

Key annotations you'll see:
@RestController — this class handles HTTP requests and returns JSON automatically
@RequestMapping("/api/children") — sets the base URL for all routes in this controller
@GetMapping — handles GET requests (read data)
@PostMapping — handles POST requests (create data)
@PathVariable — pulls a value out of the URL like /api/children/{id}
@RequestBody — pulls the JSON body from the request and converts it to a Java object

In SproutRoot we'll have:

AuthController — register, login
ChildController — create child, get children
GameController — start game, submit answer, end game
DashboardController — parent dashboard data


so what are api's and why do we write them in controllers


What is an API?

API stands for Application Programming Interface. In web development it's basically a set of URLs your frontend can call to get or send data.

Think of it like a restaurant menu. The menu lists what you can order — you don't go into the kitchen yourself, you just ask for what you want and the kitchen handles it.

In SproutRoot:

The menu = your API endpoints (/api/children, /api/game/session/start)
The customer = your vanilla JS frontend
The kitchen = your Spring Boot backend
Why do we write them in controllers?

Because the controller is the only layer that speaks HTTP. The rest of your app — services, repositories, models — doesn't know anything about HTTP requests or responses. They just work with Java objects.

The controller's job is to:

Translate an HTTP request into a Java method call
Translate the Java result back into an HTTP response (JSON)
A concrete example in SproutRoot:

When Zain finishes a game, the JS frontend calls:

POST /api/game/session/end/{id}
The GameController receives that, calls gameSessionService.updateSession(), gets the updated session back, and returns it as JSON to the browser.

The JS frontend never touches the database. It only ever talks to the controller.

The four HTTP verbs you'll use:

Verb	What it does	Example
GET	Read data	Get all children
POST	Create data	Start a game session
PUT	Update data	Update a child's name
DELETE	Delete data	Remove a child


First — the GameQuestion DTO. This is what Java sends back to the JS frontend when a game starts or after each answer:
Now the AnswerRequest DTO — what JS sends back when the child taps an answer:
Save. Now the AnswerResult DTO — what Java sends back after scoring an answer:

Now the abstract game service — shared logic all 4 games inherit:

SoundSafariService
BerryBasketService 
ShapeVillageService
SortingTrayService



That recursion error means the JSON response has an infinite loop — the GameSession has a Child which has a Parent which has children which loops back. Classic JPA circular reference.
Fix it by adding @JsonIgnore to the relationships that cause the loop. Update three files:
bashcode src/main/java/rocks/zipcode/sproutroot/model/Parent.java
Add @JsonIgnore to the children field:
javaimport com.fasterxml.jackson.annotation.JsonIgnore;

@JsonIgnore
@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Child> children;
bashcode src/main/java/rocks/zipcode/sproutroot/model/Child.java
Add @JsonIgnore to the sessions field:
javaimport com.fasterxml.jackson.annotation.JsonIgnore;

@JsonIgnore
@OneToMany(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
private List<GameSession> sessions;
bashcode src/main/java/rocks/zipcode/sproutroot/model/GameSession.java
Add @JsonIgnore to the answers field:
javaimport com.fasterxml.jackson.annotation.JsonIgnore;

@JsonIgnore
@OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
private List<GameAnswer> answers;

First go get your free Pixabay API key:

Go to pixabay.com
Create a free account
Go to pixabay.com/api/docs — your key will be shown at the top once logged in
Copy it

Then open application.properties and replace YOUR_PIXABAY_API_KEY_HERE with your real key:


# Step 1 — start a Sound Safari session
curl -s -X POST http://localhost:8080/api/game/sound-safari/start/f2000000-0000-0000-0000-000000000001

curl -s http://localhost:8080/api/game/sound-safari/question/19c49a23-ea40-4cd7-aa76-212304256886/1