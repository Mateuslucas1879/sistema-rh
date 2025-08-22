package sistema_rh.sistema_rh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 16, message = "A idade mínima é 16 anos")
    private Integer idade;

    @NotBlank(message = "O cargo é obrigatório")
    private String cargo;

    @NotNull(message = "O salário é obrigatório")
    @Positive(message = "O salário deve ser maior que zero")
    private Double salario;

    @NotBlank(message = "O tipo de contratação é obrigatório")
    private String tipoContratacao;

    @NotBlank(message = "O departamento é obrigatório")
    private String departamento;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAdmissao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDesligamento;

    @NotBlank(message = "O contato é obrigatório")
    private String contato;

    @NotBlank(message = "O nome da empresa é obrigatório")
    private String empresa;

    public Funcionario() {}

    public Funcionario(String nome, Integer idade, String cargo, Double salario,
                       String tipoContratacao, String departamento, LocalDate dataAdmissao,
                       LocalDate dataDesligamento, String contato, String empresa) {
        this.nome = nome;
        this.idade = idade;
        this.cargo = cargo;
        this.salario = salario;
        this.tipoContratacao = tipoContratacao;
        this.departamento = departamento;
        this.dataAdmissao = dataAdmissao;
        this.dataDesligamento = dataDesligamento;
        this.contato = contato;
        this.empresa = empresa;
    }

    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }

    public String getTipoContratacao() { return tipoContratacao; }
    public void setTipoContratacao(String tipoContratacao) { this.tipoContratacao = tipoContratacao; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(LocalDate dataAdmissao) { this.dataAdmissao = dataAdmissao; }

    public LocalDate getDataDesligamento() { return dataDesligamento; }
    public void setDataDesligamento(LocalDate dataDesligamento) { this.dataDesligamento = dataDesligamento; }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", tipoContratacao='" + tipoContratacao + '\'' +
                ", departamento='" + departamento + '\'' +
                ", dataAdmissao=" + dataAdmissao +
                ", dataDesligamento=" + dataDesligamento +
                ", contato='" + contato + '\'' +
                ", empresa='" + empresa + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
