package com.homefix.backend_api.service;

import com.homefix.backend_api.entity.Appointment;
import com.homefix.backend_api.entity.ServiceRequest;
import com.homefix.backend_api.repository.AppointmentRepository;
import com.homefix.backend_api.repository.ServiceRequestRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ServiceRequestRepository requestRepository;

    // Connect the repositories
    public AppointmentService(
            AppointmentRepository appointmentRepository,
            ServiceRequestRepository requestRepository) {

        this.appointmentRepository = appointmentRepository;
        this.requestRepository = requestRepository;
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Find one appointment by ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Schedule an appointment for an existing service request
    public Optional<Appointment> createAppointment(
            Long requestId, Appointment appointment) {

        // Check which request ID was sent
        System.out.println("Creating appointment for request: " + requestId);

        // Find the service request
        Optional<ServiceRequest> request =
                requestRepository.findById(requestId);

        // Check whether the request exists
        System.out.println("Service request found: " + request.isPresent());

        if (request.isEmpty()) {
            return Optional.empty();
        }

        // Connect the appointment to the service request
        appointment.setServiceRequest(request.get());

        // Let PostgreSQL generate the ID
        appointment.setAppointmentId(null);

        // New appointments start as scheduled
        appointment.setStatus("SCHEDULED");

        // Save the appointment to Neon
        Appointment savedAppointment =
                appointmentRepository.save(appointment);

        System.out.println("Appointment created: "
                + savedAppointment.getAppointmentId());

        return Optional.of(savedAppointment);
    }

    // Delete an appointment
    public boolean deleteAppointment(Long id) {

        // Check whether the appointment exists
        if (!appointmentRepository.existsById(id)) {
            return false;
        }

        // Delete the appointment
        appointmentRepository.deleteById(id);
        return true;
    }
}
