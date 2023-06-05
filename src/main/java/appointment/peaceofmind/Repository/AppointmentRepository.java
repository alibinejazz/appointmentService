package appointment.peaceofmind.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import appointment.peaceofmind.Model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
    
}
