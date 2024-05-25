import java.time.LocalDate;
import java.util.ArrayList;

public class Funcionario extends Pessoa {
    private int idFuncionario;
    private LocalDate dataAdmissao;
    private Departamento departamento;
    private String cargo;

    public Funcionario() {
    }

    public Funcionario(String cpf, String nome, LocalDate dataNascimento, String telefone,
            int numero, String rua, String cep,
            int idFuncionario, LocalDate dataAdmissao, Departamento departamento, String cargo) {
        super(cpf, nome, dataNascimento, telefone, numero, rua, cep);
        this.setIdFuncionario(idFuncionario);
        this.dataAdmissao = dataAdmissao;
        setDepartamento(departamento);
        this.cargo = cargo;
    }

    public Integer getIdFuncionario() {
        return this.idFuncionario;
    }

    public void setIdFuncionario(int novaId) {
        this.idFuncionario = novaId;

    }

    public String getNomeDepartamento() {
        return this.departamento.getNome();
    }

    public void setDepartamento(Departamento novoSetor) {
        if (departamento.cadastrarFuncionario(this)) {
            this.departamento = novoSetor;
        }
    }

    public LocalDate getDataAdmissao() {
        return this.dataAdmissao;
    }

    public void setDataAdmissao(LocalDate novaData) {
        this.dataAdmissao = novaData;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String novoCargo) {
        this.cargo = novoCargo;
    }
}
