# Event Tracker - TV Show Watchlist Tracker

![Project Image](https://static.hbo.com/2024-01/the-sopranos-25th-anniversary-ka.jpg)

## Overview

This project is designed to demonstrate the use of JavaScript Fetch API and XMLHttpRequests for making HTTP requests. It includes examples of both methods to perform GET and POST requests, showcasing their usage in interacting with REST APIs. The project is a simplified model aiming to manage TV shows and their ratings.

## Application Overview

The project aims to achieve the following objectives:

- Fetching and displaying TV shows and their ratings from a backend API.
- Allowing users to add new TV shows and ratings through a web interface.
- Demonstrating the use of both Fetch API and XMLHttpRequest for HTTP requests in a JavaScript application.

## Project Requirements

### Technologies Used

- JavaScript (Fetch API, XMLHttpRequest)
- HTML & CSS for the front-end
- A backend API to manage TV shows and ratings

## API Endpoints

### Users

| HTTP Method | Endpoint      | Description                | Request Body | Response Codes          |
|-------------|---------------|----------------------------|--------------|-------------------------|
| GET         | `/api/users`  | Retrieves all users.       | N/A          | 200 (OK), 404 (Not Found)|
| GET         | `/api/users/{userId}` | Retrieves a specific user by ID. | N/A | 200 (OK), 404 (Not Found) |
| POST        | `/api/users`  | Creates a new user.        | Yes          | 201 (Created), 404 (Not Found) |
| PUT         | `/api/users/{userId}` | Updates an existing user. | Yes | 200 (OK), 404 (Not Found), 400 (Bad Request) |
| DELETE      | `/api/users/{userId}` | Deletes a user.           | N/A          | 204 (No Content), 404 (Not Found), 400 (Bad Request) |

### TV Shows

| HTTP Method | Endpoint            | Description                   | Request Body | Response Codes          |
|-------------|---------------------|-------------------------------|--------------|-------------------------|
| GET         | `/api/shows`        | Retrieves all TV shows.       | N/A          | 200 (OK), 404 (Not Found)|
| GET         | `/api/shows/{showId}` | Retrieves a specific TV show by ID. | N/A | 200 (OK), 404 (Not Found) |
| POST        | `/api/shows`        | Creates a new TV show.        | Yes          | 201 (Created), 404 (Not Found) |
| PUT         | `/api/shows/{showId}` | Updates an existing TV show. | Yes | 200 (OK), 404 (Not Found), 400 (Bad Request) |
| DELETE      | `/api/shows/{showId}` | Deletes a TV show.           | N/A          | 204 (No Content), 404 (Not Found), 400 (Bad Request) |
| GET         | `/api/shows/search/{keyword}` | Searches TV shows by keyword. | N/A | 200 (OK), 404 (Not Found) |

### Ratings

| HTTP Method | Endpoint                      | Description                          | Request Body | Response Codes          |
|-------------|-------------------------------|--------------------------------------|--------------|-------------------------|
| GET         | `/api/ratings`                | Retrieves all ratings.               | N/A          | 200 (OK), 404 (Not Found)|
| GET         | `/api/ratings/{ratingId}`     | Retrieves a specific rating by ID.  | N/A          | 200 (OK), 404 (Not Found) |
| GET         | `/api/users/{userId}/ratings` | Retrieves all ratings for a user.   | N/A          | 200 (OK), 404 (Not Found) |
| GET         | `/api/shows/{showId}/ratings` | Retrieves all ratings for a TV show.| N/A          | 200 (OK), 404 (Not Found) |
| POST        | `/api/ratings`                | Creates a new rating.                | Yes          | 201 (Created), 404 (Not Found) |
| PUT         | `/api/ratings/{ratingId}`     | Updates an existing rating.          | Yes          | 200 (OK), 404 (Not Found), 400 (Bad Request) |
| DELETE      | `/api/{showId}/ratings/{ratingId}` | Deletes a rating.                | N/A          | 204 (No Content), 404 (Not Found), 400 (Bad Request) |

## Response Codes

- `200 OK`: The request has succeeded.
- `201 Created`: The request has been fulfilled, resulting in the creation of a new resource.
- `204 No Content`: The server has successfully fulfilled the request and there is no additional content to send in the response payload body.
- `400 Bad Request`: The server could not understand the request due to invalid syntax.
- `404 Not Found`: The server can't find the requested resource.
### Frontend Overview

The frontend utilizes basic HTML forms for data submission and JavaScript for dynamic content manipulation and API interaction.

### Deployment

This project is intended to be run on AWS Cloud services.

http://18.190.80.171:8080/RESTTvTracker/#

## Lessons Learned
Don't bite off more than you can chew!
I started out working on a job tracker for potential interviews with like 8-10 tables and tons of bi-directional relationships, however, that got far too complicated for this exercise. Doing all of that on one html and one js file felt impossible, so last minute I had to pivot and this is what I came up with. I'm not super proud of this product as is, but I definitely plan on making this more robust throughout the remainder of the course.

I still had a lot of problems with the relationships and mappings, which definitely frustrates me, however, this does show me something I need to spend more time with studying so it's definitely not a loss in that regards. 

### JavaScript HTTP Requests

Gained practical experience in making HTTP requests using both REST API and XMLHttpRequests.

### Dynamic Content Manipulation

Enhanced skills in dynamically updating the DOM based on the data fetched from the backend API.

### Debugging JavaScript

Developed a deeper understanding of debugging JavaScript, especially in the context of asynchronous operations and API interaction.

