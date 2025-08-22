package sistema_rh.sistema_rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_rh.sistema_rh.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}
