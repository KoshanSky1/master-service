package master_details_service.repository;

import master_details_service.model.Detail;
import master_details_service.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Long> {
    boolean existsByMasterAndName(Master master, String name);

    List<Detail> findAllByMaster(Master master);
}
