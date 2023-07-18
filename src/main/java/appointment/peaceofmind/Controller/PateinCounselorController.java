package appointment.peaceofmind.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import appointment.peaceofmind.Model.PatientCounselor;

import appointment.peaceofmind.Repository.PatientCounselorRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/patient_councillor")
public class PateinCounselorController {
    
    @Autowired
    private PatientCounselorRepository patientCounslorRepo;

    @PostMapping(value = "")
    public ResponseEntity<PatientCounselor> postPateintCounselor(@RequestBody PatientCounselor patientCounselor) {
        try {
            PatientCounselor createdAppointment = patientCounslorRepo.save(patientCounselor);
            return ResponseEntity.ok().body(createdAppointment);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //get all 
    @GetMapping(value = "")
    public ResponseEntity<List<PatientCounselor>> getPateintCounselorAll() {
        try {
            List<PatientCounselor> pc = patientCounslorRepo.findAll();

            return ResponseEntity.ok().body(pc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //get patient 
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<PatientCounselor> getPateintCounselor(@PathVariable Long id) {
        try {
            PatientCounselor pc = patientCounslorRepo.findById(id).orElse(null);
            
            return ResponseEntity.ok().body(pc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //get patient 
    @GetMapping(value = "/getbycounselorid/{id}")
    public ResponseEntity<List<PatientCounselor>> getPateintCounselorByCounselorId(@PathVariable Long id) {
        try {
            List<PatientCounselor> pc = patientCounslorRepo.findByCouncillorId(id);
            
            return ResponseEntity.ok().body(pc);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
