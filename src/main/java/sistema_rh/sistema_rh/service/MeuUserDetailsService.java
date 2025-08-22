package sistema_rh.sistema_rh.service;

import sistema_rh.sistema_rh.model.Papel;
import sistema_rh.sistema_rh.model.Usuario;
import sistema_rh.sistema_rh.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MeuUserDetailsService implements UserDetailsService  {

    private final UsuarioRepository usuarioRepository;

    public MeuUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
        Set<GrantedAuthority> authorities = usuario.getPapeis().stream()
                .map(papel -> new SimpleGrantedAuthority(papel.getNome()))
                .collect(Collectors.toSet());

        return new User(usuario.getLogin(), usuario.getSenha(), authorities);
    }

}
