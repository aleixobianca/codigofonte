import java.time.LocalDate;
import java.util.ArrayList;

public class Supervisor extends Funcionario {
    public Supervisor(String cpf, String nome, LocalDate dataNascimento, String telefone,
            int numero, String rua, String cep,
            Integer idFuncionario, LocalDate dataAdmissao, Departamento setor, String cargo) {
        super(cpf, nome, dataAdmissao, telefone, numero, rua, cep, idFuncionario, dataAdmissao, setor, cargo);
    }

}
