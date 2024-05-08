import java.util.ArrayList;

public class Departamento {
    private String nome;
    private long numeroFuncionarios;
    private ArrayList<Funcionario> listaFuncionarios;

    public Departamento(String nome) {
        this.nome = nome;
        this.listaFuncionarios = new ArrayList<>();
        this.numeroFuncionarios = 0;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public long getNumeroFuncionarios(){
        return this.numeroFuncionarios;
    }

    public ArrayList<Funcionario> getListaFuncionarios() {
        return this.listaFuncionarios;
    }

    public boolean cadastrarFuncionario(Funcionario novoFuncionario) {
        if(listaFuncionarios.contains(novoFuncionario)){
            System.out.println("Este funcionário já está cadastrado neste Departamento.");
            return false;
        } else {
            listaFuncionarios.add(novoFuncionario);
            numeroFuncionarios += 1;
            return true;
        }
    }
}
