package sistema_rh.sistema_rh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sistema_rh.sistema_rh.model.Usuario;
import sistema_rh.sistema_rh.service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login,
                        @RequestParam String senha,
                        Model model) {

        Usuario usuario = usuarioService.autenticar(login, senha);

        if (usuario != null) {
            return "redirect:/funcionarios";
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login";
        }
    }
}


