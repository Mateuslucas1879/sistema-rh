package sistema_rh.sistema_rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistema_rh.sistema_rh.model.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
