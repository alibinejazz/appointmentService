package appointment.peaceofmind.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import appointment.peaceofmind.Model.PatientCounselor;

public interface PatientCounselorRepository  extends JpaRepository<PatientCounselor,Long> {
    
    List<PatientCounselor> findByCouncillorId(Long councillorId);

}
