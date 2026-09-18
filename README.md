# CSC340-Group-10-Project
## Title
> HomeFix Now

## Team Members
> Khanh Hoang Cao
 
>Jerney Whitaker
## Course: 340-01

## Description 
> HomeFix Now is an on-demand home maintenance app made to connect homeowners and renters with local licensed pros like plumbers, electricians, and handymen. The platform helps users find reliable service providers, ask for price quotes, schedule home repair appointments, and leave reviews. On the other side, service providers can show their qualifications, post available services, keep track of job stats, and respond to client reviews through a clean, simple dashboard.
>
> **Glossary:** Terms used in the project

* **Provider:** A licensed professional or handyman who provides home repair and maintenance services.
* **Customer:** A homeowner or renter who uses HomeFix Now to find and schedule home repair services.
* **Profile:** A collection of information about a customer or provider, including contact information, address, qualifications, and services.
* **Service:** A specific home repair or maintenance offering provided by a provider, such as plumbing, electrical, or handyman work.
* **Quote:** The estimated price provided by a service provider for a requested service.
* **Appointment:** A scheduled time between a customer and provider for a home repair or maintenance service.
* **Review:** A rating and written feedback submitted by a customer after completing a service.

**Primary Users and Roles:**

* **Customer** — Find local providers, compare services, schedule appointments, and manage home repair services.
* **Provider** — Offer home repair services, manage appointments, and respond to customer reviews.

**Scope (this semester):**

* Customer and provider profiles
* Search and browse home repair services and providers
* Provider information, ratings, and reviews
* Requesting and accepting service quotes
* Booking and managing appointments
* Customer reviews and ratings

**Out of scope (deferred):**

* Online payment processing
* Real-time GPS tracking of providers
* In-app video calls or consultations
* Automated emergency repair services

## 1. Overview
## App Functions
1. Customer:
    1. Create/modify customer profile - customers can create an account and update their name, contact info, and address.
    2. View available services - customers can browse services such as plumbing, electrical, and handyman work.
    3. Leave a review – After a completed service, customers can rate the provider and write a review.
    4. Subscribe to available services - customers can select a provider, accept a quote, and schedule an appointment 
    5. Write reviews for subscribed service - After service is completed, the customer can write a review and give a rating.
    6. View appointments and cancel them - Customers can see their upcoming and previous appointments as well as cancel them if needed.
    7. Book a service - Customers can select an available date and time for their repair appointment.
3. Provider (Handyman / Trade Professional):
    1. Create/modify/remove provider profile - Sign up as a contractor or handyman, listing licenses, skills, hourly rates, and working hours.
    2. Create services - Post repair services (like plumbing checks or outlet repairs) with upfront prices and available time slots.
    3. View customer statistics - Check booking histories, total service requests, finished jobs, and overall earnings.
    4. Reply to reviews - Leave professional responses to customer reviews and ratings on finished jobs.


## 2. Functional Requirements (User Stories)
## 2.1 Customer Stories
* US-1 - Create and Modify Customer Profile

Story: As a customer, I want to be able to create and modify my profile, so that my personal and contact information is kept up to date

Acceptance:
```gherkin
Scenario: Customer create a profile
  Given the customer does not have a profile and is on the account creation page
  When the customer enters their name, contact info, and address and clicks "Create Account"
  Then the system creates and save the profile displays the customer's account
  And the customer can view their profile
```

* US-2 - Search for Services

Story: As a customer, I want to search for home repair services, so that I can quickly find the type of service I need.

Acceptance:
```gherkin
Scenario: Customer searches for a service 
 Given the customer is on the HomeFix Now home page 
 When the customer enters "plumbing" into the search bar and clicks the search button 
 Then the system displays available plumbing services and providers
 And the customer can pick which service they want
```

* US-3 - Filter Service Providers

Story: As a customer, I want to filter service providers by price and rating, so that I can find providers that match my preferences.

Acceptance:
```gherkin
Scenario: Customer filters providers 
 Given the customer has searched for a home repair service 
 When the customer selects a price or rating filter 
 Then the system displays providers matching the selected filter
 And the customer can pick which service they want based off the rating or price
```

* US-4 -View Provider Profile

Story: As a customer, I want to view a provider's profile, so that I can review their qualifications and services before booking.

Acceptance:
```gherkin
Scenario: Customer views a provider profile 
 Given the customer is viewing a list of service providers 
 When the customer clicks on a provider's name or profile
 Then the system displays the provider's services, qualifications, ratings, and available appointments
 And the customer can view the provider's profile
```

* US-5 -Request a Quote
 Story: As a customer, I want to request a price quote from a provider, so that I can know the estimated cost before booking a service.

 Acceptance:
```gherkin
Scenario: Customer requests a quote
  Given the customer is viewing a provider's profile
  When the customer selects a service and clicks "Request Quote"
  Then the system sends the quote request to the selected provider
  And the customer can request a quote
```
* US-6 -Accept a Quote and Schedule a Service
Story: As a customer, I want to accept a provider's quote and choose an appointment time, so that I can schedule my home repair service.

Acceptance:
```gherkin
Scenario: Customer accepts a quote
  Given the customer has received a quote from a provider
  When the customer clicks "Accept Quote" and selects an available date and time
  Then the system creates the customer's appointment
```
* US-7 -View Appointments
Story: As a customer, I want to view my upcoming and previous appointments, so that I can keep track of my services.

Acceptance:
```gherkin
Scenario: Customer views appointments
  Given the customer has at least one appointment
  When the customer clicks "My Appointments"
  Then the system displays the customer's upcoming and previous appointments
```
* US-8 -Cancel an Appointment
Story: As a customer, I want to cancel an upcoming appointment, so that I can manage appointments I no longer need.

Acceptance:
```gherkin
Scenario: Customer cancels an appointment
  Given the customer has an upcoming appointment
  When the customer clicks "Cancel Appointment" and confirms the cancellation
  Then the system cancels the appointment and removes it from the upcoming appointments list
```
* US-9 -Leave a Review
Story: As a customer, I want to rate a provider and write a review after a completed service, so that I can share my experience.

Acceptance:
```gherkin
Scenario: Customer leaves a review
  Given the customer's service has been completed
  When the customer selects "Leave a Review," chooses a rating, writes a review, and submits it
  Then the system saves the rating and review to the provider's profile
```
* US-10 -Return Home Using Logo

Story: As a customer, I want to click the HomeFix Now logo, so that I can quickly return to the home page.
Acceptance:
```gherkin
Scenario: Customer returns to the home page
  Given the customer is on another page of homefix now
  When the customer clicks the HomeFix Now logo
  Then the system redirects the customer to the home page
```
### 2.2 Provider (Trainer) Stories

- *US-5 - 
