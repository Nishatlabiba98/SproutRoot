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