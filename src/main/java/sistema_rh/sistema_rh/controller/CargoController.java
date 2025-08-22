package sistema_rh.sistema_rh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sistema_rh.sistema_rh.model.Cargo;
import sistema_rh.sistema_rh.service.CargoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
    private final CargoService service;

    private CargoController(CargoService service){
        this.service = service;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("cargos", service.listarTodos());
        return "cargos";
    }

    @GetMapping("/novo")
    public String novoCargo(Model model){
        model.addAttribute("cargo",new Cargo());
        return "cargo_form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Cargo cargo){
        service.salvar(cargo);
        return "redirect>/cargos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id){
        service.remover(id);
        return "redirect:/cargos";
    }
}
