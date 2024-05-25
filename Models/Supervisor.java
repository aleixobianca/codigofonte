import java.time.LocalDate;

public class Supervisor extends Funcionario {
    public Supervisor() {
    }

    public Supervisor(String nome, LocalDate dataNascimento, String telefone,
            int idFuncionario, LocalDate dataAdmissao, Departamento setor, String cargo) {
        super(nome, dataAdmissao, telefone, idFuncionario, dataAdmissao, setor, cargo);
    }

}
