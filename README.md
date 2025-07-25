# Food Delivery App

<p align="center">
<img src="images/portrait_1.png" alt="Orders" width="200" height="550"/>
<img src="images/portrait_2.png" alt="Details" width="200" height="550"/>
</p>
<p align="center">
<img src="images/landscape.png" alt="Landscape" width="500" height="250"/>
</p>

- User can see a list of his orders
- User can select an order to show its details
- User can use the app online and offline
- User can see the order status change in real-time
  
# Technologies used

- Jetpack Compose
- Dagger Hilt
- Retrofit
- Room Database
- Websocket
- Coroutines
- Flow
- Clean Architecture

# Build instructions

- You need to set up a local server with the required endpoints and WebSocket and run it
- If you're running the app on a real device, head to build.gradle (App) and change the BASE_URL buildConfig field from http://10.0.2.2:8080/ to http://localhost:8080/ and vice versa
- If you're using a real backend server you also need to change the BASE_URL field
- This project is built with AGP 8.9.1 and Kotlin 2.0.21

# Architectural Decisions

I went with a Layered Architecture for high scalability that can support implementing a lot more features, Maintainability & Testability (abstraction layers), and the support of the addition of business logic (domain layer).

# Trade-offs
- Increased Boilerplate: More classes and interfaces are required even for simple features, which can feel verbose for small projects.
- Features can't be easily dragged & dropped / reused in other projects 

I was thinking about a feature-based architecture where each feature is isolated with its own layers so they can be reused in any other project but decided not to go with that since the data layer is the one that would be mostly reused in this kind of apps

# API
I used a simple Node.js server for my backend 

# AI
AI was used only to create the Node.js server


