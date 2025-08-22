package sistema_rh.sistema_rh.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    private Double salario;
    private Double descontos;
    private Double beneficios;
    private LocalDate dataPagamento;

    public Pagamento() {}

    public Long getId() { return id; }
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public Double getDescontos() { return descontos; }
    public void setDescontos(Double descontos) { this.descontos = descontos; }

    public Double getBeneficios() { return beneficios; }
    public void setBeneficios(Double beneficios) { this.beneficios = beneficios; }

    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }
}
