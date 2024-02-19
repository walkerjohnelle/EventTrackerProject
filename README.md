# EventTrackerProject

## Overview

The **EventTrackerProject** is a solo project that revolves something that I and all of my fellow classmates will have to conquer very soon, looking for a job! I wanted this project to be something that not only I, but my fellow classmates could use when the time came. The goal of this project is to be an application where job seekers can input the data of potential employers such as salary range, location, remote work possibilities, skills required, etc.; as well as what is important to the user in order to rank jobs based on fit so that the job seeker can prioritize jobs on their job search. 

### Technologies Used

- MySQL database (MySQL Workbench)
- Java Persistence API (JPA) 
- Spring MVC 
- AWS 
- REST API

### Deployment

The project and the associated database are deployed to an AWS instance.

**[Access the deployed application here](http://18.190.80.171:8080/RESTJobTracker/)**

## REST API Endpoints

### Jobs Endpoints

| Endpoint                 | HTTP Method | Description                           |
|--------------------------|-------------|---------------------------------------|
| /api/jobs                | GET         | Retrieve all jobs                     |
| /api/jobs/{id}           | GET         | Retrieve a job by ID                  |
| /api/jobs                | POST        | Create a new job                      |
| /api/jobs/{id}           | PUT         | Update an existing job                |
| /api/jobs/{id}           | DELETE      | Delete a job by ID                    |

### Users Endpoints

| Endpoint                 | HTTP Method | Description                           |
|--------------------------|-------------|---------------------------------------|
| /api/users               | GET         | Retrieve all users                    |
| /api/users/{id}          | GET         | Retrieve a user by ID                 |
| /api/users               | POST        | Create a new user                     |
| /api/users/{id}          | PUT         | Update an existing user               |
| /api/users/{id}          | DELETE      | Delete a user by ID                   |

### Skills Endpoints

| Endpoint                 | HTTP Method | Description                           |
|--------------------------|-------------|---------------------------------------|
| /api/skills              | GET         | Retrieve all skills                   |
| /api/skills/{id}         | GET         | Retrieve a skill by ID                |
| /api/skills              | POST        | Create a new skill                    |
| /api/skills/{id}         | PUT         | Update an existing skill              |
| /api/skills/{id}         | DELETE      | Delete a skill by ID                  |

### Endpoint Details

- **GET /api/jobs:** 
  - Description: Retrieves all jobs stored in the database.
  - Response: Returns a JSON array containing details of all jobs.

- **GET /api/jobs/{id}:** 
  - Description: Retrieves a specific job by its ID.
  - Parameters: `{id}` - The unique identifier of the job.
  - Response: Returns a JSON object containing details of the specified job if found.

- **POST /api/jobs:** 
  - Description: Creates a new job entry in the database.
  - Request Body: JSON object representing the new job.
  - Response: Returns a JSON object containing details of the newly created job.

- **PUT /api/jobs/{id}:** 
  - Description: Updates an existing job entry in the database.
  - Parameters: `{id}` - The unique identifier of the job to be updated.
  - Request Body: JSON object representing the updated job.
  - Response: Returns a JSON object containing details of the updated job.

- **DELETE /api/jobs/{id}:** 
  - Description: Deletes a job entry from the database by its ID.
  - Parameters: `{id}` - The unique identifier of the job to be deleted.
  - Response: Returns a success message upon successful deletion.

- **GET /api/users:** 
  - Description: Retrieves all users stored in the database.
  - Response: Returns a JSON array containing details of all users.

- **GET /api/users/{id}:** 
  - Description: Retrieves a specific user by their ID.
  - Parameters: `{id}` - The unique identifier of the user.
  - Response: Returns a JSON object containing details of the specified user if found.

- **POST /api/users:** 
  - Description: Creates a new user entry in the database.
  - Request Body: JSON object representing the new user.
  - Response: Returns a JSON object containing details of the newly created user.

- **PUT /api/users/{id}:** 
  - Description: Updates an existing user entry in the database.
  - Parameters: `{id}` - The unique identifier of the user to be updated.
  - Request Body: JSON object representing the updated user.
  - Response: Returns a JSON object containing details of the updated user.

- **DELETE /api/users/{id}:** 
  - Description: Deletes a user entry from the database by their ID.
  - Parameters: `{id}` - The unique identifier of the user to be deleted.
  - Response: Returns a success message upon successful deletion.

- **GET /api/skills:** 
  - Description: Retrieves all skills stored in the database.
  - Response: Returns a JSON array containing details of all skills.

- **GET /api/skills/{id}:** 
  - Description: Retrieves a specific skill by its ID.
  - Parameters: `{id}` - The unique identifier of the skill.
  - Response: Returns a JSON object containing details of the specified skill if found.

- **POST /api/skills:** 
  - Description: Creates a new skill entry in the database.
  - Request Body: JSON object representing the new skill.
  - Response: Returns a JSON object containing details of the newly created skill.

- **PUT /api/skills/{id}:** 
  - Description: Updates an existing skill entry in the database.
  - Parameters: `{id}` - The unique identifier of the skill to be updated.
  - Request Body: JSON object representing the updated skill.
  - Response: Returns a JSON object containing details of the updated skill.

- **DELETE /api/skills/{id}:** 
  - Description: Deletes a skill entry from the database by its ID.
  - Parameters: `{id}` - The unique identifier of the skill to be deleted.
  - Response: Returns a success message upon successful deletion.

## Lessons Learned
### Creating a Project Based on Future Need
Initially i bit off more than i could chew and the project was way too big which meant that i had to scale down to make it work. Ultimately, i think i can make the project that i envisioned, however, there are still some bugs I need to work out. For Instance, I still have a lot of work to do regarding the logic that takes in the user details, and job details and ranks the jobs based on fit. This was a great exercise in solidifying the REST API concepts and I'm definitely looking forward to future iterations of this project. 

