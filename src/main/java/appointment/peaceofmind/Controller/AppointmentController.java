package appointment.peaceofmind.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import appointment.peaceofmind.Model.Appointment;
import appointment.peaceofmind.Repository.IAppointmentRepo;
import appointment.peaceofmind.Service.AppointmentService;

import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RequestMapping("/appointments")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private IAppointmentRepo iAppointmentRepo;

    @PostMapping(value = "")
    public ResponseEntity<Appointment> postAppointment(@RequestBody Appointment appointment) {
        try {
            Appointment createdAppointment = appointmentService.createAppointment(appointment);
            return ResponseEntity.ok().body(createdAppointment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "/getall")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            return ResponseEntity.ok().body(appointments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // -----------
    //getByDays
    // -----------



    @GetMapping(value="/get/{id}")
    public ResponseEntity<Appointment> getOneAppointment (@PathVariable Long id ) {
         try {
            Appointment appointment =  appointmentService.getAppointment(id);
            if (appointment != null) {
                return ResponseEntity.ok(appointment);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteOneAppointment(@PathVariable Long id) {
        try {
            String result = appointmentService.deleteAppointment(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete appointment");
        }
    }


    @PostMapping(value="/update")
    public ResponseEntity<String> updateAppointment(@RequestBody Appointment appointment) {
        try {
            String updatedAppointment = appointmentService.updateAppointment(appointment);
            return ResponseEntity.ok().body(updatedAppointment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update appointment");
        }
    }  

    

    @GetMapping(value = "/getByAvailability/{availabilityId}")
    public ResponseEntity<List<Appointment>> findByAvailabilityId(@PathVariable("availabilityId") Long availabilityId) {
        List<Appointment> appointments = appointmentService.findByAvailabilityId(availabilityId);
        if (!appointments.isEmpty()) {
            return ResponseEntity.ok(appointments);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    @GetMapping(value = "/getByPatientid/{patientid}")
    public ResponseEntity<List<Appointment>> findBypatientid(@PathVariable("patientid") Long patientid) {
        List<Appointment> appointments = appointmentService.findBypatientid(patientid);
        if (!appointments.isEmpty()) {
            return ResponseEntity.ok(appointments);
        } else {
            return ResponseEntity.notFound().build();  
        }
    }

    //delete all appointments
    @DeleteMapping("/deleteall")
    public void deleteAllAppointments(){
        appointmentService.deleteAllAppointments();
    }


}
