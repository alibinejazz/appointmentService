package appointment.peaceofmind.Repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import appointment.peaceofmind.Model.Appointment;


@Repository
public interface IAppointmentRepo {
    
    public String deleteAppointment(Long id);
    public String updateAppointment(Appointment appointment);
    public List<Appointment> getAllAppointments();
    public Appointment getAppointment(Long id);
    public Appointment createAppointment(Appointment appointment);
    public String deleteAllAppointments();
    
     public List<Appointment> findByAvailabilityId(Long availability_id);

     public List<Appointment> findBypatientid(Long patientid);
    }
    

