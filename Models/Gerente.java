import java.time.LocalDate;

public class Gerente extends Funcionario {
    public Gerente() {
    }

    public Gerente(String cpf, String nome, LocalDate dataNascimento, String telefone,
            int numero, String rua, String cep,
            int idFuncionario, LocalDate dataAdmissao, Departamento setor, String cargo) {
        super(cpf, nome, dataAdmissao, telefone, numero, rua, cep, idFuncionario, dataAdmissao, setor, cargo);
    }
}
