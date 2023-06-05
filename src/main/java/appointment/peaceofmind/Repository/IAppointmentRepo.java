package appointment.peaceofmind.Repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import appointment.peaceofmind.Model.Appointment;

public interface IAppointmentRepo {
    
    public String deleteAppointment(Long id);
    public String updateAppointment(Appointment appointment, Long id);
    public List<Appointment> getAllAppointments();
    public Appointment getAppointment(Long id);
    public Appointment createAppointment(Appointment appointment);

}
