import java.time.LocalDate;

public class Gerente extends Funcionario {
    public Gerente() {
    }

    public Gerente(String nome, LocalDate dataNascimento, String telefone,
            int idFuncionario, LocalDate dataAdmissao, Departamento setor, String cargo) {
        super(nome, dataAdmissao, telefone, idFuncionario, dataAdmissao, setor, cargo);
    }
}
