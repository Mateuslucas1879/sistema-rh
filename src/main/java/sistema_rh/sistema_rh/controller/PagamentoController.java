package sistema_rh.sistema_rh.controller;

import sistema_rh.sistema_rh.model.Pagamento;
import sistema_rh.sistema_rh.model.Funcionario;
import sistema_rh.sistema_rh.service.PagamentoService;
import sistema_rh.sistema_rh.service.FuncionarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final FuncionarioService funcionarioService;

    public PagamentoController(PagamentoService pagamentoService, FuncionarioService funcionarioService){
        this.pagamentoService = pagamentoService;
        this.funcionarioService = funcionarioService;

    }

    @GetMapping
    public String listar(Model model, @ModelAttribute("mensagem") String mensagem) {
        model.addAttribute("pagamentos", pagamentoService.listarTodos());
        model.addAttribute("mensagem", mensagem);
        return "pagamentos_list";
    }

    @GetMapping("/novo")
    public String abrirFomularioNovo(Model model){
        model.addAttribute("pagamento", new Pagamento());
        model.addAttribute("funcionarios",funcionarioService.listarTodos());
        return "pagamento_form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Pagamento pag = pagamentoService.buscarPorId(id).orElse(null);
        if(pag == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Pagamento não encontrado");
            return "redirect:/pagamentos";
        }
        model.addAttribute("pagamento", pag);
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "pagamento_form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Pagamento pagamento, RedirectAttributes redirectAttributes) {
        if(pagamento.getId() == null) {
            pagamentoService.salvar(pagamento);
            redirectAttributes.addFlashAttribute("mensagem", "Pagamento registrado com sucesso!");
        } else {
            pagamentoService.atualizar(pagamento.getId(), pagamento);
            redirectAttributes.addFlashAttribute("mensagem", "Pagamento atualizado com sucesso!");
        }
        return "redirect:/pagamentos";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean removido = pagamentoService.remover(id);
        if(removido) redirectAttributes.addFlashAttribute("mensagem", "Pagamento removido com sucesso!");
        else redirectAttributes.addFlashAttribute("mensagem", "Pagamento não encontrado");
        return "redirect:/pagamentos";
    }


}
