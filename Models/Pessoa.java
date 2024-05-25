import java.time.LocalDate;
import java.time.Period;

public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;
    private String telefone;

    public Pessoa() {
    }

    public Pessoa(String nome, LocalDate dataNascimento, String telefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate novaData) {
        this.dataNascimento = novaData;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int calcularIdade() {
        LocalDate dataAtual = LocalDate.now();
        Period periodoEntreDatas = Period.between(dataNascimento, dataAtual);
        return periodoEntreDatas.getYears();
    }

    public void listarDados() {
        System.out.println("Nome: " + this.getNome());
        System.out.println("Data de nascimento: " + this.getDataNascimento());
        System.out.println("Idade: " + this.calcularIdade());
        System.out.println("Telefones: " + this.getTelefone());
        System.out.print("\n");
    }
}
