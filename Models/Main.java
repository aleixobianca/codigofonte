import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.ResultSet;
import java.util.Scanner;
import Data.DbContext;

public class Main {
    public static void main(String[] args) {
        // LocalDate dataNascimento1 = LocalDate.of(1993, 5, 23);
        // ArrayList<String> telefones1 = new ArrayList<>();
        // telefones1.add("11111-1111");
        // telefones1.add("55555-5555");
        // Pessoa pessoa1 = new Pessoa("2222222","Maria", dataNascimento1, telefones1,
        // 250, "das flores", "5555");
        // pessoa1.listarDados();

        inicio();
    }

    // MÉTODO PRINCIPAL
    public static void inicio() {
        System.out.println("\n Selecione uma opção: \n");

        System.out.println("1 - Cadastrar funcionário");
        System.out.println("2 - Listar Funcionários");
        System.out.println("3 - Atualizar Funcionário");
        System.out.println("4 - Remover funcionário");

        System.out.print("\n OPÇÃO: ");

        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int opcaoSelecionada = scanner.nextInt();

            if (opcaoSelecionada >= 1 && opcaoSelecionada <= 4) {
                executaOpcao(opcaoSelecionada);
            } else {
                mensagemStatus("Opção Inválida.");
            }

            inicio();
        } else {
            mensagemStatus("Opção Inválida.");
        }

        scanner.nextLine();

        inicio();
    }

    // MÉTODO QUE VAI CHAMAR A EXECUÇÃO DAS OPERAÇÕES DE ACORDO COM A OPÇÃO
    // SELECIONADA
    public static void executaOpcao(int opcaoSelecionada) {
        switch (opcaoSelecionada) {
            case 1:
                cadastrarFuncionario();
                break;
            case 2:
                listarFuncionarios();
                break;
            case 3:
                atualizarFuncionario();
                break;
            case 4:
                removerFuncionario();
                break;
            default:
                break;
        }
    }

    public static void cadastrarFuncionario() {

        Scanner scanner = new Scanner(System.in);

        Funcionario novoFuncionario = new Funcionario();

        // obtendo os dados do novo funcionário que será cadastrado

        // Nome -------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o nome do funcionário a ser cadastrado: ");

        if (scanner.hasNextLine()) {
            String aux = scanner.nextLine();
            if (aux.toUpperCase().equals("SAIR"))
                return;
            novoFuncionario.setNome(aux);
        }

        // data de nascimento--------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme a data de nascimento do funcionário (AAAA-MM-DD): ");

        while (!scanner.hasNext("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            if (scanner.next().toUpperCase().equals("SAIR"))
                return;
            System.out.println(
                    "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe  data de nascimento do funcionário (AAAA-MM-DD): ");
        }
        String dataNascimentoString = scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        novoFuncionario.setDataNascimento(formatarData(dataNascimentoString));

        // telefone------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o telefone do funcionário (somente numeros): ");

        if (scanner.hasNext()) {
            String aux = scanner.next();
            if (aux.toUpperCase().equals("SAIR"))
                return;
            novoFuncionario.setTelefone(aux);
        }

        // data de admissão ------------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme a data de admissao do funcionário (AAAA-MM-DD): ");

        while (!scanner.hasNext("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            if (scanner.next().toUpperCase().equals("SAIR"))
                return;
            System.out.println(
                    "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe data de admissao do funcionário (AAAA-MM-DD): ");
        }
        String dataAdmissaoString = scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        novoFuncionario.setDataAdmissao(formatarData(dataAdmissaoString));
        scanner.nextLine();

        // Departamento ------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o Departamento do funcionário: ");

        String aux = scanner.nextLine();
        if (aux.toUpperCase().equals("SAIR"))
            return;
        String nomeDepartamento = aux;

        // cargo ------------------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o cargo do funcionário: ");

        if (scanner.hasNextLine()) {
            String aux2 = scanner.nextLine();
            if (aux2.toUpperCase().equals("SAIR"))
                return;
            novoFuncionario.setCargo(aux2);
        }

        // ------------------------------------------------------------------------------

        DbContext database = new DbContext();

        try {
            database.conectarBanco();

            boolean statusQuery = database
                    .executarUpdateSql(
                            "INSERT INTO public.funcionario(nome, dataNascimento, telefone, dataAdmissao, departamento, cargo) VALUES ('"
                                    + novoFuncionario.getNome() + "', '"
                                    + dataNascimentoString + "', '"
                                    + novoFuncionario.getTelefone() + "', '"
                                    + dataAdmissaoString + "', '"
                                    + nomeDepartamento + "', '"
                                    + novoFuncionario.getCargo()
                                    + "');");

            if (statusQuery) {
                mensagemStatus("Novo funcionario cadastrado com sucesso !");
            }

            database.desconectarBanco();

        } catch (Exception e) {
        }

        inicio();

    }

    public static void listarFuncionarios() {

        DbContext database = new DbContext();

        try {
            database.conectarBanco();

            ResultSet resultadoConsulta = database.executarQuerySql("SELECT * FROM Funcionario");

            while (resultadoConsulta.next()) {
                System.out.println("ID - " + resultadoConsulta.getString("idFuncionario") + " | Nome - "
                        + resultadoConsulta.getString("nome") + " | Data Nascimento - "
                        + resultadoConsulta.getString("dataNascimento") + " | Telefone - "
                        + resultadoConsulta.getString("telefone")
                        + " | Data Admissao - " + resultadoConsulta.getString("dataAdmissao")
                        + " | Departamento - " + resultadoConsulta.getString("departamento") + " | Cargo - "
                        + resultadoConsulta.getString("cargo"));
            }

            database.desconectarBanco();

        } catch (Exception e) {
        }

        inicio();
    }

    public static void removerFuncionario() {

        System.out.println("\n Informe o ID do funcionario a ser deletado: ");

        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {

            int idFuncionario = scanner.nextInt();

            DbContext database = new DbContext();

            try {
                database.conectarBanco();

                boolean statusQuery = database.executarUpdateSql("DELETE FROM Funcionario WHERE idFuncionario = " + idFuncionario);

                if (statusQuery) {
                    mensagemStatus("Funcionario deletado com sucesso !");
                }

                database.desconectarBanco();

            } catch (Exception e) {
            }

            inicio();
        }
    }

    public static void atualizarFuncionario() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\n Informe o ID do funcionário a ser atualizado: ");

        while (!scanner.hasNextInt()) {
            if (scanner.next().toUpperCase().equals("SAIR"))
                return;
            System.out.println(
                    "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe  o ID do funcionário: ");
        }
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setIdFuncionario(scanner.nextInt());

        // obtendo os dados atualizados do funcionário

        // Nome -------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o novo nome do funcionário: ");

        if (scanner.hasNextLine()) {
            String aux = scanner.nextLine();
            if (aux.toUpperCase().equals("SAIR"))
                return;
            novoFuncionario.setNome(aux);
        }

        // data de nascimento--------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme a data de nascimento do funcionário (AAAA-MM-DD): ");

        while (!scanner.hasNext("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            if (scanner.next().toUpperCase().equals("SAIR"))
                return;
            System.out.println(
                    "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe  data de nascimento do funcionário (AAAA-MM-DD): ");
        }
        String dataNascimentoString = scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        novoFuncionario.setDataNascimento(formatarData(dataNascimentoString));

        // telefone------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o telefone do funcionário (somente numeros): ");

        if (scanner.hasNext()) {
            String aux = scanner.next();
            if (aux.toUpperCase().equals("SAIR"))
                return;
            novoFuncionario.setTelefone(aux);
        }

        // data de admissão ------------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme a data de admissao do funcionário (AAAA-MM-DD): ");

        while (!scanner.hasNext("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            if (scanner.next().toUpperCase().equals("SAIR"))
                return;
            System.out.println(
                    "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe data de admissao do funcionário (AAAA-MM-DD): ");
        }
        String dataAdmissaoString = scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}");
        novoFuncionario.setDataAdmissao(formatarData(dataAdmissaoString));
        scanner.nextLine();

        // Departamento ------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o Departamento do funcionário: ");

        String aux = scanner.nextLine();
        if (aux.toUpperCase().equals("SAIR"))
            return;
        String nomeDepartamento = aux;

        // cargo ------------------------------
        System.out.println(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o cargo do funcionário: ");

        if (scanner.hasNextLine()) {
            String aux2 = scanner.nextLine();
            if (aux2.toUpperCase().equals("SAIR"))
                return;
            novoFuncionario.setCargo(aux2);
        }

        // ------------------------------------------------------------------------------

        DbContext database = new DbContext();

        try {
            database.conectarBanco();

            boolean statusQuery = database.executarUpdateSql(
                    "UPDATE public.funcionario SET "
                            + "nome = '" + novoFuncionario.getNome()
                            + "', dataNascimento = '" + dataNascimentoString
                            + "', telefone = '" + novoFuncionario.getTelefone()
                            + "', dataAdmissao = '" + dataAdmissaoString
                            + "', departamento = '" + nomeDepartamento
                            + "', cargo = '" + novoFuncionario.getCargo()
                            + "' WHERE idFuncionario = " + novoFuncionario.getIdFuncionario() + ";");

            if (statusQuery) {
                mensagemStatus("funcionário atualizado com sucesso !");
            }

            database.desconectarBanco();

        } catch (Exception e) {
        }

        inicio();

    }

    // MÉTODO RESPONSÁVEL SOMENTE POR EXIBIR UMA MENSAGEM NA TELA
    public static void mensagemStatus(String mensagem) {
        System.out.print("\n");
        System.out.print("---------------------");
        System.out.print("\n " + mensagem + " \n");
        System.out.print("---------------------");
        System.out.print("\n");
    }

    public static LocalDate formatarData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Convertendo a string para um objeto LocalDate
        LocalDate dataObjeto = LocalDate.parse(data, formatter);

        return dataObjeto;
    }
}