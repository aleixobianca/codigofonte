
public class Gerente extends Funcionario {
    public Gerente() {
    }

    public Gerente(String nome, String dataNascimento, String telefone,
            int idFuncionario, String dataAdmissao, String setor, String cargo) {
        super(nome, dataAdmissao, telefone, idFuncionario, dataAdmissao, setor, cargo);
    }
}
