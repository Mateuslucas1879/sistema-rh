package sistema_rh.sistema_rh.service;

import org.springframework.stereotype.Service;
import sistema_rh.sistema_rh.model.Cargo;
import sistema_rh.sistema_rh.repository.CargoRepository;

import java.util.List;
import java.util.Optional;

@Service

public class CargoService {
    private final CargoRepository repository;

    public CargoService(CargoRepository repository){
        this.repository = repository;
    }

    public List<Cargo> listarTodos(){
        return repository.findAll();
    }

    public Cargo salvar(Cargo cargo){
        return repository.save(cargo);
    }

    public Optional<Cargo> buscarPorId(Long id){
        return repository.findById(id);
    }

    public void remover(Long id){
        repository.deleteById(id);
    }
}
