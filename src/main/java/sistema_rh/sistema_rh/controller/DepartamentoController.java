package sistema_rh.sistema_rh.controller;

import sistema_rh.sistema_rh.model.Departamento;
import sistema_rh.sistema_rh.service.DepartamentoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/deparmentos")
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService){
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public String listar(Model model, @ModelAttribute("mensagem") String mensagem){
        model.addAttribute("departamentos", departamentoService.listarTodos());
        model.addAttribute("mensagem", mensagem);
        return "departamentos_list";
    }

    @GetMapping("/novo")
    public String abrirFormularioNovo(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "departamento_form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Departamento dep = departamentoService.buscarPorId(id).orElse(null);
        if(dep == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Departamento não encontrado");
            return "redirect:/departamentos";
        }
        model.addAttribute("departamento", dep);
        return "departamento_form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Departamento departamento, RedirectAttributes redirectAttributes) {
        if(departamento.getId() == null) {
            departamentoService.salvar(departamento);
            redirectAttributes.addFlashAttribute("mensagem", "Departamento criado com sucesso!");
        } else {
            departamentoService.atualizar(departamento.getId(), departamento);
            redirectAttributes.addFlashAttribute("mensagem", "Departamento atualizado com sucesso!");
        }
        return "redirect:/departamentos";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean removido = departamentoService.remover(id);
        if(removido) redirectAttributes.addFlashAttribute("mensagem", "Departamento removido com sucesso!");
        else redirectAttributes.addFlashAttribute("mensagem", "Departamento não encontrado");
        return "redirect:/departamentos";
    }



}
