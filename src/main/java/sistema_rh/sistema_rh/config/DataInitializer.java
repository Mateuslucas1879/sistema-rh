package sistema_rh.sistema_rh.config;

import sistema_rh.sistema_rh.model.Papel;
import sistema_rh.sistema_rh.model.Usuario;
import sistema_rh.sistema_rh.repository.PapelRepository;
import sistema_rh.sistema_rh.repository.UsuarioRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            PapelRepository papelRepository,
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {

            // Cria os papéis se ainda não existirem
            Papel admin = papelRepository.findByNome("ROLE_ADMIN");
            if (admin == null) {
                admin = new Papel();
                admin.setNome("ROLE_ADMIN");
                papelRepository.save(admin);
            }

            Papel rh = papelRepository.findByNome("ROLE_RH");
            if (rh == null) {
                rh = new Papel();
                rh.setNome("ROLE_RH");
                papelRepository.save(rh);
            }

            Papel usuario = papelRepository.findByNome("ROLE_USUARIO");
            if (usuario == null) {
                usuario = new Papel();
                usuario.setNome("ROLE_USUARIO");
                papelRepository.save(usuario);
            }

            // Cria usuários se ainda não existirem
            if (usuarioRepository.findByLogin("admin").isEmpty()) {
                Usuario u = new Usuario("admin", passwordEncoder.encode("1234"));
                u.setPapeis(Set.of(admin));
                usuarioRepository.save(u);
            }

            if (usuarioRepository.findByLogin("rhuser").isEmpty()) {
                Usuario u = new Usuario("rhuser", passwordEncoder.encode("1234"));
                u.setPapeis(Set.of(rh));
                usuarioRepository.save(u);
            }

            if (usuarioRepository.findByLogin("usuario").isEmpty()) {
                Usuario u = new Usuario("usuario", passwordEncoder.encode("1234"));
                u.setPapeis(Set.of(usuario));
                usuarioRepository.save(u);
            }

            System.out.println("✅ Usuários e papéis criados com sucesso.");
        };
    }
}
