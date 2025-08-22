package sistema_rh.sistema_rh.service;

import sistema_rh.sistema_rh.model.Departamento;
import sistema_rh.sistema_rh.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {
    private final DepartamentoRepository repository;

    public DepartamentoService(DepartamentoRepository repository){
        this.repository =repository;
    }

    public List<Departamento> listarTodos(){
        return repository.findAll();
    }

    public Departamento salvar (Departamento departamento){
        return repository.save(departamento);
    }
    public Departamento atualizar(Long id, Departamento dados){
        return repository.findById(id).map(dep -> {
            dep.setNome(dados.getNome());
            dep.setDescricao(dados.getDescricao());
            return repository.save(dep);
        }).orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
    }

    public Optional<Departamento> buscarPorId(Long id){
        return repository.findById(id);
    }

    public boolean remover(Long id) {
        Optional<Departamento> dep = repository.findById(id);
        if(dep.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}