package master_details_service.repository;

import master_details_service.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Long> {

    boolean existsByNumber(Integer number);

}
