import java.time.LocalDate;
import java.util.ArrayList;

public class Gerente extends Funcionario{
    public Gerente(String cpf, String nome, LocalDate dataNascimento, ArrayList<String> telefones,
                   int numero, String rua, String cep,
                   Integer idFuncionario, LocalDate dataAdmissao, Departamento setor, String cargo) {
        super(cpf, nome, dataAdmissao, telefones, numero, rua, cep, idFuncionario, dataAdmissao, setor, cargo);
    }

}
