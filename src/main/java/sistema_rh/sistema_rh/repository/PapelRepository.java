package sistema_rh.sistema_rh.repository;

import sistema_rh.sistema_rh.model.Papel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PapelRepository extends JpaRepository<Papel, Long> {
    Papel findByNome(String nome);
}
