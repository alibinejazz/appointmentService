package appointment.peaceofmind.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import appointment.peaceofmind.Model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
    
}
