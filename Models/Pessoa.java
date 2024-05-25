import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Pessoa {
    private String cpf;
    private static ArrayList<String> listaCpf = new ArrayList<>();
    private String nome;
    private LocalDate dataNascimento;
    private String telefone;
    private Endereco endereco;

    public Pessoa(String cpf, String nome, LocalDate dataNascimento, String telefone, int numero,
            String rua, String cep) {
        this.setCPF(cpf);
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = new Endereco(numero, rua, cep);
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCPF(String novoCpf) {
        if (listaCpf.contains(novoCpf)) {
            System.out.println("Esta Pessoa já existe");
        } else {
            this.cpf = novoCpf;
            listaCpf.add(novoCpf);
        }
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

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(int numero, String rua, String cep) {
        this.endereco.setNumero(numero);
        this.endereco.setRua(rua);
        this.endereco.setCep(cep);
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
        System.out.printf("Endereço: %s, %d, CEP: %s%n", this.endereco.getRua(), this.endereco.getNumero(),
                this.endereco.getCep());
    }
}
