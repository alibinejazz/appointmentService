package appointment.peaceofmind.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import appointment.peaceofmind.Model.Appointment;
import appointment.peaceofmind.Service.AppointmentService;

import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RequestMapping("/appointments")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping(value="")
    public ResponseEntity<Appointment> postAppointment(@RequestBody Appointment appointment) {
        Appointment appointment2 = appointmentService.createAppointment(appointment);

        return ResponseEntity.ok().body(appointment2);
    }

    @GetMapping(value="/getall")
    public List<Appointment> getAllAppointments () {
        return appointmentService.getAllAppointments();

    }

    // -----------
    //getByDays
    // -----------



    @GetMapping(value="/get/{id}")
    public Appointment getOneAppointment (@PathVariable Long id ) {
        Appointment appointment =  appointmentService.getAppointment(id);
        return appointment;

    }

    @DeleteMapping(value="/delete/{id}")
    public String deleteOneAppointment (@PathVariable Long id ) {
        return appointmentService.deleteAppointment(id);

    }

    @PostMapping(value="/update")
    public ResponseEntity<String> updateAppointment(@RequestBody Appointment appointment) {
        String appointment2 = appointmentService.updateAppointment(appointment);
        return ResponseEntity.ok().body(appointment2);
    }  

}