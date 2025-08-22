package sistema_rh.sistema_rh.service;

import org.springframework.stereotype.Service;
import sistema_rh.sistema_rh.model.Usuario;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioService() {

        usuarios.add(new Usuario("admin", "1234"));
        usuarios.add(new Usuario("funcionario", "abcd"));
    }

    public Usuario autenticar(String login, String senha) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}

