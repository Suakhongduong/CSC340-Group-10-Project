# HomeFix Now Backend API

## UML Class Diagram


![HomeFix Now UML Class Diagram](UML%20class%20%281%29.png)

## API Overview

**Version:** 1.0  
**Last Updated:** October 23, 2026

### Base URLs

Local: http://localhost:8080/api

Production: https://homefix-backend-es6q.onrender.com/api

Live API Endpoints

API

Endpoint

Customer API

https://homefix-backend-es6q.onrender.com/api/customers

Provider API

https://homefix-backend-es6q.onrender.com/api/providers


The HomeFix Now backend uses Java, Spring Boot, Spring Data JPA, and PostgreSQL hosted on Neon.

The API supports customer profiles, service requests, appointments, reviews, and provider management.

## Customer Profiles

| Method | Path | Purpose | Response Code |
|---|---|---|---|
| POST | `/api/customers` | Create a customer profile | 201 Created |
| GET | `/api/customers` | View all customers | 200 OK |
| GET | `/api/customers/{id}` | View a specific customer | 200 OK |
| PUT | `/api/customers/{id}` | Update a customer profile | 200 OK |
| DELETE | `/api/customers/{id}` | Delete a customer | 204 No Content |

### Example Customer Request

**POST** `/api/customers`

```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane@example.com",
  "phoneNumber": "3365550101",
  "address": "123 Main Street"
}
```

## Service Requests

| Method | Path | Purpose | Response Code |
|---|---|---|---|
| POST | `/api/customers/{customerId}/service-requests` | Create a service request | 201 Created |
| GET | `/api/service-requests/{id}` | View a service request | 200 OK |
| PUT | `/api/service-requests/{id}` | Update a service request | 200 OK |
| PUT | `/api/service-requests/{id}/cancel` | Cancel a service request | 200 OK |
| DELETE | `/api/service-requests/{id}` | Delete a service request | 204 No Content |

## Appointments

| Method | Path | Purpose | Response Code |
|---|---|---|---|
| POST | `/api/service-requests/{requestId}/appointments` | Schedule an appointment | 201 Created |
| GET | `/api/appointments` | View all appointments | 200 OK |
| GET | `/api/appointments/{id}` | View appointment details | 200 OK |
| GET | `/api/customers/{customerId}/appointments` | View a customer's appointments | 200 OK |
| PUT | `/api/appointments/{id}` | Reschedule an appointment | 200 OK |
| PUT | `/api/appointments/{id}/cancel` | Cancel an appointment | 200 OK |
| DELETE | `/api/appointments/{id}` | Delete an appointment | 204 No Content |

## Reviews

| Method | Path | Purpose | Response Code |
|---|---|---|---|
| POST | `/api/customers/{customerId}/reviews` | Submit a review | 201 Created |
| GET | `/api/reviews` | View all reviews | 200 OK |
| GET | `/api/reviews/{id}` | View a specific review | 200 OK |
| PUT | `/api/reviews/{id}` | Update a review | 200 OK |
| DELETE | `/api/reviews/{id}` | Delete a review | 204 No Content |


