
public class Funcionario extends Pessoa {
    private int idFuncionario;
    private String dataAdmissao;
    private String departamento;
    private String cargo;

    public Funcionario() {
    }

    public Funcionario(String nome, String dataNascimento, String telefone,
            int idFuncionario, String dataAdmissao, String departamento, String cargo) {
        super(nome, dataNascimento, telefone);
        this.idFuncionario = idFuncionario;
        this.dataAdmissao = dataAdmissao;
        this.departamento = departamento;
        this.cargo = cargo;
    }

    public Integer getIdFuncionario() {
        return this.idFuncionario;
    }

    public void setIdFuncionario(int novaId) {
        this.idFuncionario = novaId;

    }

    public String getNomeDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String novoSetor) {
        this.departamento = novoSetor;

    }

    public String getDataAdmissao() {
        return this.dataAdmissao;
    }

    public void setDataAdmissao(String novaData) {
        this.dataAdmissao = novaData;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String novoCargo) {
        this.cargo = novoCargo;
    }
}
