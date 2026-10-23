package com.homefix.backend_api.repository;

import com.homefix.backend_api.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

      // Find all appointments belonging to a customer
    List<Appointment> findByServiceRequest_Customer_CustomerId(
            Long customerId);

}

