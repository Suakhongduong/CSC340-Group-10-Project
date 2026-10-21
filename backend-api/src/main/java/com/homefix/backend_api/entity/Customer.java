
package com.homefix.backend_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


// These imports let us validate customer information
// before saving it to our database.
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// This tells Spring that Customer is a database entity.
// JPA uses this class to represent customer records in Neon.
@Entity
public class Customer {

    // Each customer gets their own unique ID.
    // The database automatically generates it.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    // Customers must enter their first name.
    // @NotBlank prevents empty names or names with only spaces.
    @NotBlank(message = "First name is required")
    private String firstName;

    // Same thing for the customer's last name.
    @NotBlank(message = "Last name is required")
    private String lastName;

    // Customers must provide an email address.
    // @Email checks that it follows an email format.
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    // A phone number is required.
    // We're allowing between 10 and 20 characters
    // to accommodate different phone number formats.
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 20,
          message = "Phone number must be 10 to 20 characters")
    private String phoneNumber;

    // Customers need to provide an address.
    // This helps identify where their home service is needed.
    @NotBlank(message = "Address is required")
    private String address;

    // JPA needs an empty constructor so it can
    // create Customer objects when loading database records.
    public Customer() {
    }

    // Getters let other classes read customer information.
    // Setters let other classes update customer information.

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
