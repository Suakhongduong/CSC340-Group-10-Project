package com.homefix.backend_api.controller;

import com.homefix.backend_api.entity.Appointment;
import com.homefix.backend_api.service.AppointmentService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    private final AppointmentService appointmentService;

    // Connect the controller to the service
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Get all appointments
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Get one appointment by ID
    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Schedule an appointment for a service request
    @PostMapping("/service-requests/{requestId}/appointments")
    public ResponseEntity<Appointment> createAppointment(
            @PathVariable Long requestId,
            @Valid @RequestBody Appointment appointment) {

        return appointmentService.createAppointment(requestId, appointment)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete an appointment
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {

        if (appointmentService.deleteAppointment(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
    
    // Reschedule an appointment
    @PutMapping("/appointments/{id}")
    public ResponseEntity<Appointment> rescheduleAppointment(
            @PathVariable Long id,
            @RequestBody Appointment updatedAppointment) {

        return appointmentService
                .rescheduleAppointment(id, updatedAppointment)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Cancel an appointment
    @PutMapping("/appointments/{id}/cancel")
    public ResponseEntity<Appointment> cancelAppointment(
            @PathVariable Long id) {

        return appointmentService.cancelAppointment(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all appointments belonging to a customer
@GetMapping("/customers/{customerId}/appointments")
public List<Appointment> getAppointmentsByCustomer(
        @PathVariable Long customerId) {

    return appointmentService.getAppointmentsByCustomer(customerId);
}

}
