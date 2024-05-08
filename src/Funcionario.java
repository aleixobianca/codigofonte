import java.time.LocalDate;
import java.util.ArrayList;

public class Funcionario extends Pessoa {
    private Integer idFuncionario;
    private LocalDate dataAdmissao;
    private Departamento departamento;
    private String cargo;
    private static ArrayList<Integer> listaIdFuncionarios = new ArrayList<>();

    public Funcionario(String cpf, String nome, LocalDate dataNascimento, ArrayList<String> telefones,
                       int numero, String rua, String cep,
                       Integer idFuncionario, LocalDate dataAdmissao, Departamento departamento, String cargo) {
        super(cpf, nome, dataNascimento, telefones, numero, rua, cep);
        this.setIdFuncionario(idFuncionario);
        this.dataAdmissao = dataAdmissao;
        setDepartamento(departamento);
        this.cargo = cargo;
    }

    public long getIdFuncionario() {
        return this.idFuncionario;
    }

    public void setIdFuncionario(Integer novaId) {
        if (listaIdFuncionarios.contains(novaId)) {
            System.out.println("Esta idFuncionario já existe. Tente novamente com uma id diferente.");
        } else {
            this.idFuncionario = novaId;
            listaIdFuncionarios.add(novaId);
        }
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
