package com.homefix.backend_api.repository;

import com.homefix.backend_api.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}

