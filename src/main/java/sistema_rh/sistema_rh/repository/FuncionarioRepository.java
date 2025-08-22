package sistema_rh.sistema_rh.repository;

import sistema_rh.sistema_rh.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}