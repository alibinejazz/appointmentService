package appointment.peaceofmind.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import appointment.peaceofmind.Model.Appointment;
import appointment.peaceofmind.Repository.AppointmentRepository;
import appointment.peaceofmind.Repository.IAppointmentRepo;

@Service
public class AppointmentService implements IAppointmentRepo  {

    @Autowired
    private AppointmentRepository appointmentRepo; 

   

    @Override
    public String deleteAppointment(Long id) {
        Optional<Appointment> tobedel = appointmentRepo.findById(id);
        if(tobedel.isPresent()){
            appointmentRepo.deleteById(id);
            return "Appointment deleted";
        }else{
            return "No appointments exist";
        }
    }

    @Override
    public String updateAppointment(Appointment appointment) {
        Appointment toBeUpdated = appointmentRepo.findById(appointment.getId()).orElse(null);

        if(!Objects.isNull(toBeUpdated)){
            toBeUpdated.setPatient_id(appointment.getPatient_id());
            toBeUpdated.setUpdated(Date.valueOf(LocalDate.now()));
            toBeUpdated.setAvailabilityId(appointment.getAvailabilityId());
            toBeUpdated.setConfirmed(appointment.isConfirmed());
            
            appointmentRepo.save(toBeUpdated);
            return "Appointment Updated !";

            }else{
                return "No appointments exist";
            }
    }


    @Override
    public Appointment createAppointment(Appointment appointment){
        appointment.setCreated(Date.valueOf(LocalDate.now()));
        appointment.setUpdated(Date.valueOf(LocalDate.now()));
        return appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
     return appointmentRepo.findAll();
    }

    @Override
    public Appointment getAppointment(Long id) {
        Appointment getAppointment = appointmentRepo.findById(id).orElse(null);

        return getAppointment;
    }

    
    @Override
    public List<Appointment> findByAvailabilityId(Long availabilityId) {
        return appointmentRepo.findByAvailabilityId(availabilityId);
    }
    
}
