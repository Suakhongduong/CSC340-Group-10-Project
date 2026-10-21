package com.homefix.backend_api.repository;


// Importing our Customer class so this repository knows which type of data it's supposed to work with.
import com.homefix.backend_api.entity.Customer;

// JpaRepository gives us built-in database methods which means we don't have to write SQL ourselves for
// basic things like saving, finding, or deleting customers.
import org.springframework.data.jpa.repository.JpaRepository;

// This interface handles database operations for customers we're using an interface instead of a regular class because
// Spring Data JPA automatically creates the implementation for us.

// Customer = the entity we're working with.
// Long = the data type of the customer's ID.

// By extending JpaRepository, we automatically get methods like:
// save()       - Add a new customer or update an existing one.
// findAll()    - Get a list of all customers.
// findById()   - Find a specific customer using their ID.
// deleteById() - Delete a customer using their ID.
// existsById() - Check whether a customer exists.
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // We don't need to add anything here yet JpaRepository already provides the basic CRUD operations.
    //Later though, if we want to search for customers by email or phone number, we can add custom methods here.
}
