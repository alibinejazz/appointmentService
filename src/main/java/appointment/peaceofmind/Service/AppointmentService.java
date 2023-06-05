package appointment.peaceofmind.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
            return "Appointment deleted !";
        }else{
            return "No appointments exist";
        }
    }

    @Override
    public String updateAppointment(Appointment appointment, Long id) {
        Optional<Appointment> tobeupd = appointmentRepo.findById(id);
        Appointment appointment2 = tobeupd.get();
        if(!tobeupd.isEmpty()){
            appointment2.setPatient_id(appointment.getPatient_id());
            appointment2.setUpdated(Date.valueOf(LocalDate.now()));
            appointment2.setAvailability_id(appointment.getAvailability_id());
            System.out.println(appointment.is_confirmed());
            appointment2.set_confirmed(appointment.is_confirmed());
            appointmentRepo.save(appointment2);
            return "Appointment Updated !";
                }
        else{
            return "No appointments exist";
            }
    }

//     @Override
// public String updateAppointment(Appointment appointment, Long id) {
//     Optional<Appointment> tobeupd = appointmentRepo.findById(id);
//     if (tobeupd.isPresent()) {
//         Appointment appointment2 = tobeupd.get();
//         appointment2.setUpdated(appointment.getUpdated());
//         appointmentRepo.save(appointment2);
//         return "Appointment Updated";
//     } else {
//         return "No appointments exist";
//     }
// }


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
        Optional<Appointment> getAppointment = appointmentRepo.findById(id);
        return getAppointment.get();
    }
    
}
