package sistema_rh.sistema_rh.controller;


import sistema_rh.sistema_rh.model.Papel;
import sistema_rh.sistema_rh.model.Usuario;
import sistema_rh.sistema_rh.repository.UsuarioRepository;
import sistema_rh.sistema_rh.repository.PapelRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/usuarios")
@PreAuthorize("hasRole('ADMIN')")  // só ADMIN acessa qualquer método
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PapelRepository papelRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PapelRepository papelRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.papelRepository = papelRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Listar todos os usuários
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/listar";
    }

    // Formulário para criar novo usuário
    @GetMapping("/novo")
    public String formularioNovoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("todosPapeis", papelRepository.findAll());
        return "usuarios/formulario";
    }

    // Salvar novo usuário
    @PostMapping
    public String salvarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult resultado, @RequestParam List<Long> papeisIds, Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("todosPapeis", papelRepository.findAll());
            return "usuarios/formulario";
        }

        // Criptografa senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        // Carrega papéis selecionados e atribui ao usuário
        Set<Papel> papeis = papeisIds.stream()
                .map(id -> papelRepository.findById(id).orElse(null))
                .filter(p -> p != null)
                .collect(Collectors.toSet());

        usuario.setPapeis(papeis);
        usuarioRepository.save(usuario);

        return "redirect:/usuarios";
    }

    // Formulário para editar usuário
    @GetMapping("/editar/{id}")
    public String formularioEditarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário inválido"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("todosPapeis", papelRepository.findAll());
        return "usuarios/formulario";
    }

    // Atualizar usuário
    @PostMapping("/editar/{id}")
    public String atualizarUsuario(@PathVariable Long id, @Valid @ModelAttribute Usuario usuarioAtualizado, BindingResult resultado, @RequestParam List<Long> papeisIds, Model model) {
        if (resultado.hasErrors()) {
            model.addAttribute("todosPapeis", papelRepository.findAll());
            return "usuarios/formulario";
        }

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário inválido"));

        usuario.setLogin(usuarioAtualizado.getLogin());

        // Só atualiza a senha se foi preenchida no formulário
        if (!usuarioAtualizado.getSenha().isBlank()) {
            usuario.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
        }

        Set<Papel> papeis = papeisIds.stream()
                .map(pid -> papelRepository.findById(pid).orElse(null))
                .filter(p -> p != null)
                .collect(Collectors.toSet());

        usuario.setPapeis(papeis);

        usuarioRepository.save(usuario);

        return "redirect:/usuarios";
    }


    @GetMapping("/deletar/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário inválido"));
        usuarioRepository.delete(usuario);
        return "redirect:/usuarios";
    }
}
