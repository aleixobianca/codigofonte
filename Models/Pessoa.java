import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Pessoa {
    private String nome;
    private String dataNascimento;
    private String telefone;

    public Pessoa() {
    }

    public Pessoa(String nome, String dataNascimento, String telefone) {
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

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(String novaData) {
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

        Period periodoEntreDatas = Period.between(formatarData(this.dataNascimento), dataAtual);
        return periodoEntreDatas.getYears();
    }

    public void listarDados() {
        System.out.println("Nome: " + this.getNome());
        System.out.println("Data de nascimento: " + this.getDataNascimento());
        System.out.println("Idade: " + this.calcularIdade());
        System.out.println("Telefones: " + this.getTelefone());
        System.out.print("\n");
    }

    private LocalDate formatarData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Convertendo a string para um objeto LocalDate
        LocalDate dataLocalDate = LocalDate.parse(data, formatter);

        return dataLocalDate;
    }

}
