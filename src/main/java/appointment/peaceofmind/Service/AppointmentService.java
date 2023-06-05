package appointment.peaceofmind.Service;

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

    // @Override
    // public String updateAppointment(Appointment appointment, Long id) {
    //     Optional<Appointment> tobeupd = appointmentRepo.findById(id);
    //     Appointment appointment2 = tobeupd.get();
    //     if(!tobeupd.isEmpty()){
    //         appointment2.setUpdated(appointment.getUpdated());
    //         appointmentRepo.save(appointment2);
    //         return "Appointment Updated";
    //             }
    //     else{
    //         return "No appointments exist";
    //         }
    // }

    @Override
public String updateAppointment(Appointment appointment, Long id) {
    Optional<Appointment> tobeupd = appointmentRepo.findById(id);
    if (tobeupd.isPresent()) {
        Appointment appointment2 = tobeupd.get();
        appointment2.setUpdated(appointment.getUpdated());
        appointmentRepo.save(appointment2);
        return "Appointment Updated";
    } else {
        return "No appointments exist";
    }
}


    @Override
    public Appointment createAppointment(Appointment appointment){
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
