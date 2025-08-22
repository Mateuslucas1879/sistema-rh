package sistema_rh.sistema_rh.service;

import sistema_rh.sistema_rh.model.Pagamento;
import sistema_rh.sistema_rh.repository.PagamentoRepository;
import sistema_rh.sistema_rh.repository.PapelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository){
        this.repository = repository;
    }

    public List<Pagamento> listarTodos(){
        return repository.findAll();
    }

    public Pagamento salvar(Pagamento pagamento){
        return repository.save(pagamento);
    }

    public Pagamento atualizar(Long id, Pagamento dados){
        return repository.findById(id).map(pagamento -> {
            pagamento.setFuncionario(dados.getFuncionario());
            pagamento.setSalario(dados.getSalario());
            pagamento.setDescontos(dados.getDescontos());
            pagamento.setBeneficios(dados.getBeneficios());
            pagamento.setDataPagamento(dados.getDataPagamento());
            return repository.save(pagamento);
        }).orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public Optional<Pagamento> buscarPorId(Long id){
        return repository.findById(id);
    }

    public boolean remover(Long id){
        Optional<Pagamento> pagamento =repository.findById(id);
        if (pagamento.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
