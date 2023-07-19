package appointment.peaceofmind.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import appointment.peaceofmind.Model.PriceLog;

public interface PriceLogRepository extends JpaRepository<PriceLog,Long> {

}
