import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        LocalDate dataNascimento1 = LocalDate.of(1993, 5, 23);
        ArrayList<String> telefones1 = new ArrayList<>();
        telefones1.add("11111-1111");
        telefones1.add("55555-5555");
        Pessoa pessoa1 = new Pessoa("2222222","Maria", dataNascimento1, telefones1, 250, "das flores", "5555");
        pessoa1.listarDados();
    }
}