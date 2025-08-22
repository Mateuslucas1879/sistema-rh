package sistema_rh.sistema_rh.service;

import sistema_rh.sistema_rh.model.Funcionario;
import sistema_rh.sistema_rh.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    // Listar todos os funcionários
    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }

    // Salvar novo funcionário
    public Funcionario salvar(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    // Atualizar funcionário existente
    public Funcionario atualizar(Long id, Funcionario dados) {
        return repository.findById(id).map(funcionario -> {
            funcionario.setNome(dados.getNome());
            funcionario.setIdade(dados.getIdade());
            funcionario.setCargo(dados.getCargo());
            funcionario.setSalario(dados.getSalario());
            funcionario.setTipoContratacao(dados.getTipoContratacao());
            funcionario.setDepartamento(dados.getDepartamento());
            funcionario.setDataAdmissao(dados.getDataAdmissao());
            funcionario.setDataDesligamento(dados.getDataDesligamento());
            funcionario.setEmpresa(dados.getEmpresa());
            funcionario.setContato(dados.getContato());
            return repository.save(funcionario);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    // Buscar funcionário por ID
    public Optional<Funcionario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Remover funcionário
    public boolean remover(Long id) {
        Optional<Funcionario> funcionario = repository.findById(id);
        if (funcionario.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
