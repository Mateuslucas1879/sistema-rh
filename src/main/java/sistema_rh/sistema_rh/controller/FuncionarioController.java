package sistema_rh.sistema_rh.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import sistema_rh.sistema_rh.model.Funcionario;
import sistema_rh.sistema_rh.service.FuncionarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RH', 'USUARIO')")
    public String listarFuncionarios(Model model, @ModelAttribute("mensagem") String mensagem) {
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        model.addAttribute("mensagem", mensagem);
        return "funcionarios";
    }


    @GetMapping("/novo")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH')")
    public String abrirFormularioNovo(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "funcionario_form";
    }


    @GetMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH')")
    public String editarFuncionario(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Funcionario funcionario = funcionarioService.buscarPorId(id).orElse(null);
        if (funcionario == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Funcionário não encontrado");
            return "redirect:/funcionarios";
        }
        model.addAttribute("funcionario", funcionario);
        return "funcionario_form";
    }


    @PostMapping("/salvar")
    @PreAuthorize("hasAnyRole('ADMIN', 'RH')")
    public String salvarFuncionario(@Valid @ModelAttribute Funcionario funcionario,
                                    BindingResult result,
                                    RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "funcionario_form";
        }

        if (funcionario.getId() == null) {
            funcionarioService.salvar(funcionario);
            redirectAttributes.addFlashAttribute("mensagem", "Funcionário criado com sucesso!");
        } else {
            funcionarioService.atualizar(funcionario.getId(), funcionario);
            redirectAttributes.addFlashAttribute("mensagem", "Funcionário atualizado com sucesso!");
        }
        return "redirect:/funcionarios";
    }


    @GetMapping("/deletar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletarFuncionario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean removido = funcionarioService.remover(id);
        if (removido) {
            redirectAttributes.addFlashAttribute("mensagem", "Funcionário removido com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Funcionário não encontrado");
        }
        return "redirect:/funcionarios";
    }
}


