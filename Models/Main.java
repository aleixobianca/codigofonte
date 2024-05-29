import java.sql.ResultSet;
import java.util.Scanner;
import Data.DbContext;

public class Main {
    public static void main(String[] args) {

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
        System.out.print(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o nome do funcionário a ser cadastrado: ");

        if (scanner.hasNextLine()) {
            String aux = scanner.nextLine();
            if (aux.toUpperCase().equals("SAIR"))
                return;
            novoFuncionario.setNome(aux);
        }

        // data de nascimento--------------------
        System.out.print(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme a data de nascimento do funcionário (AAAA-MM-DD): ");

        while (!scanner.hasNext("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            if (scanner.next().toUpperCase().equals("SAIR"))
                return;
            System.out.print(
                    "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe  data de nascimento do funcionário (AAAA-MM-DD): ");
        }

        novoFuncionario.setDataNascimento(scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}"));

        // telefone------------------
        System.out.print(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o telefone do funcionário (somente numeros): ");

        if (scanner.hasNext()) {
            String aux = scanner.next();
            if (aux.toUpperCase().equals("SAIR"))
                return;
            novoFuncionario.setTelefone(aux);
        }

        // data de admissão ------------------------
        System.out.print(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme a data de admissao do funcionário (AAAA-MM-DD): ");

        while (!scanner.hasNext("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            if (scanner.next().toUpperCase().equals("SAIR"))
                return;
            System.out.println(
                    "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe data de admissao do funcionário (AAAA-MM-DD): ");
        }

        novoFuncionario.setDataAdmissao(scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}"));
        scanner.nextLine();

        // Departamento ------------------
        System.out.print(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o Departamento do funcionário: ");

        if (scanner.hasNextLine()) {
            String aux = scanner.nextLine();
            if (aux.toUpperCase().equals("SAIR"))
                return;
            novoFuncionario.setDepartamento(aux);
        }

        // cargo ------------------------------
        System.out.print(
                "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o cargo do funcionário: ");

        if (scanner.hasNextLine()) {
            String aux = scanner.nextLine();
            if (aux.toUpperCase().equals("SAIR"))
                return;
            novoFuncionario.setCargo(aux);
        }

        // cadastro no banco de dados

        DbContext database = new DbContext();

        try {
            database.conectarBanco();

            boolean statusQuery = database
                    .executarUpdateSql(
                            "INSERT INTO public.funcionario(nome, dataNascimento, telefone, dataAdmissao, departamento, cargo) VALUES ('"
                                    + novoFuncionario.getNome() + "', '"
                                    + novoFuncionario.getDataNascimento() + "', '"
                                    + novoFuncionario.getTelefone() + "', '"
                                    + novoFuncionario.getDataAdmissao() + "', '"
                                    + novoFuncionario.getNomeDepartamento() + "', '"
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
                System.out.println(
                        "----------------------------------------------------------------");
                System.out.println("ID - " + resultadoConsulta.getString("idFuncionario") + " | Nome - "
                        + resultadoConsulta.getString("nome") + " | Data Nascimento - "
                        + resultadoConsulta.getString("dataNascimento") + " | Telefone - "
                        + resultadoConsulta.getString("telefone")
                        + " | Data Admissao - " + resultadoConsulta.getString("dataAdmissao")
                        + " | Departamento - " + resultadoConsulta.getString("departamento") + " | Cargo - "
                        + resultadoConsulta.getString("cargo"));
                ;
            }
            System.out.println(
                    "--------------------------------------------------------------------");
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

                boolean statusQuery = database
                        .executarUpdateSql("DELETE FROM Funcionario WHERE idFuncionario = " + idFuncionario);

                if (statusQuery) {
                    mensagemStatus("Funcionario deletado com sucesso !");
                }

                database.desconectarBanco();

            } catch (Exception e) {
            }
            
        } else {
            mensagemStatus("Opção Inválida.");
        }

        inicio();
    }

    public static void atualizarFuncionario() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nInforme o ID do funcionário a ser atualizado: ");

        while (!scanner.hasNextInt()) {
            if (scanner.next().toUpperCase().equals("SAIR"))
                return;
            System.out.print(
                    "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe  o ID do funcionário: ");
        }
        Funcionario novoFuncionario = new Funcionario();
        novoFuncionario.setIdFuncionario(scanner.nextInt());
        scanner.nextLine();

        System.out.print("\nQuais dados serão atualizados?\n\n"
                            + "\t1 - Nome\n"
                            + "\t2 - Data de nascimento\n"
                            + "\t3 - Telefone\n"
                            + "\t4 - Data de admissão\n"
                            + "\t5 - Departamento\n"
                            + "\t6 - Cargo\n"
                            + "\t7 - Todos os dados listados acima\n"
                            + "\t\nOu, digite SAIR para retornar ao menu inicial.\n"
                            + "\t\nEscolha uma opção: ");

        while (!scanner.hasNextInt()) {
            if (scanner.next().toUpperCase().equals("SAIR"))
                return;
            System.out.print("\nQuais dados serão atualizados?\n\n"
                                + "\t1 - Nome\n"
                                + "\t2 - Data de nascimento\n"
                                + "\t3 - Telefone\n"
                                + "\t4 - Data de admissão\n"
                                + "\t5 - Departamento\n"
                                + "\t6 - Cargo\n"
                                + "\t7 - Todos os dados listados acima\n"
                                + "\t\nOu, digite SAIR para retornar ao menu inicial.\n"
                                + "\t\nEscolha uma opção: ");
        }                    
        
        
        int opcao2 = scanner.nextInt();
        scanner.nextLine();
        
        DbContext database = new DbContext();

        switch(opcao2) {
            case 1:
                System.out.print(
                    "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o novo nome do funcionário: ");

                if (scanner.hasNextLine()) {
                    String aux = scanner.nextLine();
                    if (aux.toUpperCase().equals("SAIR"))
                        return;
                    novoFuncionario.setNome(aux);
                }
                 // ------------------------------------------------------------------------------
                 try {
                     database.conectarBanco();
 
                     boolean statusQuery = database.executarUpdateSql(
                             "UPDATE public.funcionario SET "
                                     + "nome = '" + novoFuncionario.getNome()
                                     + "' WHERE idFuncionario = " + novoFuncionario.getIdFuncionario() + ";");
 
                     if (statusQuery) {
                         mensagemStatus("funcionário atualizado com sucesso !");
                     }
 
                     database.desconectarBanco();
 
                 } catch (Exception e) {
                 }
 
                 break;

            case 2:
                // data de nascimento--------------------
                System.out.print(
                        "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme a nova data de nascimento do funcionário (AAAA-MM-DD): ");

                while (!scanner.hasNext("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                    if (scanner.next().toUpperCase().equals("SAIR"))
                        return;
                    System.out.print(
                            "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe  a nova data de nascimento do funcionário (AAAA-MM-DD): ");
                }

                novoFuncionario.setDataNascimento(scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}"));

                // ------------------------------------------------------------------------------

                try {
                    database.conectarBanco();

                    boolean statusQuery = database.executarUpdateSql(
                            "UPDATE public.funcionario SET "
                                    + "dataNascimento = '" + novoFuncionario.getDataNascimento()
                                    + "' WHERE idFuncionario = " + novoFuncionario.getIdFuncionario() + ";");

                    if (statusQuery) {
                        mensagemStatus("funcionário atualizado com sucesso !");
                    }

                    database.desconectarBanco();

                } catch (Exception e) {
                }

                break;

            case 3:
                // telefone------------------
                System.out.print(
                        "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o novo telefone do funcionário (somente numeros): ");

                if (scanner.hasNext()) {
                    String aux = scanner.next();
                    if (aux.toUpperCase().equals("SAIR"))
                        return;
                    novoFuncionario.setTelefone(aux);
                }

                // ------------------------------------------------------------------------------

                try {
                    database.conectarBanco();

                    boolean statusQuery = database.executarUpdateSql(
                            "UPDATE public.funcionario SET "
                                    + "telefone = '" + novoFuncionario.getTelefone()
                                    + "' WHERE idFuncionario = " + novoFuncionario.getIdFuncionario() + ";");

                    if (statusQuery) {
                        mensagemStatus("funcionário atualizado com sucesso !");
                    }

                    database.desconectarBanco();

                } catch (Exception e) {
                }

                break;

            case 4:
                // data de admissão ------------------------
                System.out.print(
                        "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme a nova data de admissao do funcionário (AAAA-MM-DD): ");

                while (!scanner.hasNext("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                    if (scanner.next().toUpperCase().equals("SAIR"))
                        return;
                    System.out.print(
                            "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe a nova data de admissao do funcionário (AAAA-MM-DD): ");
                }

                novoFuncionario.setDataAdmissao(scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}"));
                scanner.nextLine();

                try {
                    database.conectarBanco();

                    boolean statusQuery = database.executarUpdateSql(
                            "UPDATE public.funcionario SET "
                                    + "dataAdmissao = '" + novoFuncionario.getDataAdmissao()
                                    + "' WHERE idFuncionario = " + novoFuncionario.getIdFuncionario() + ";");

                    if (statusQuery) {
                        mensagemStatus("funcionário atualizado com sucesso !");
                    }

                    database.desconectarBanco();

                } catch (Exception e) {
                }

                break;

            case 5:
                // Departamento ------------------
                System.out.print(
                    "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o Departamento do funcionário: ");

                if (scanner.hasNextLine()) {
                String aux = scanner.nextLine();
                if (aux.toUpperCase().equals("SAIR"))
                    return;
                novoFuncionario.setDepartamento(aux);
                }

                // ------------------------------------------------------------------------------

                try {
                    database.conectarBanco();

                    boolean statusQuery = database.executarUpdateSql(
                            "UPDATE public.funcionario SET "
                                    + "departamento = '" + novoFuncionario.getNomeDepartamento()
                                    + "' WHERE idFuncionario = " + novoFuncionario.getIdFuncionario() + ";");

                    if (statusQuery) {
                        mensagemStatus("funcionário atualizado com sucesso !");
                    }

                    database.desconectarBanco();

                } catch (Exception e) {
                }

                break;

            case 6:
                // cargo ------------------------------
                System.out.print(
                        "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o cargo do funcionário: ");

                if (scanner.hasNextLine()) {
                    String aux = scanner.nextLine();
                    if (aux.toUpperCase().equals("SAIR"))
                        return;
                    novoFuncionario.setCargo(aux);
                }

                // ------------------------------------------------------------------------------

                try {
                    database.conectarBanco();

                    boolean statusQuery = database.executarUpdateSql(
                            "UPDATE public.funcionario SET "
                                    + "cargo = '" + novoFuncionario.getCargo()
                                    + "' WHERE idFuncionario = " + novoFuncionario.getIdFuncionario() + ";");

                    if (statusQuery) {
                        mensagemStatus("funcionário atualizado com sucesso !");
                    }

                    database.desconectarBanco();

                } catch (Exception e) {
                }

                break;

            case 7:
                // obtendo os dados atualizados do funcionário

                // Nome -------------------
                System.out.print(
                        "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o novo nome do funcionário: ");

                if (scanner.hasNextLine()) {
                    String aux = scanner.nextLine();
                    if (aux.toUpperCase().equals("SAIR"))
                        return;
                    novoFuncionario.setNome(aux);
                }

                // data de nascimento--------------------
                System.out.print(
                        "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme a data de nascimento do funcionário (AAAA-MM-DD): ");

                while (!scanner.hasNext("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                    if (scanner.next().toUpperCase().equals("SAIR"))
                        return;
                    System.out.print(
                            "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe  data de nascimento do funcionário (AAAA-MM-DD): ");
                }

                novoFuncionario.setDataNascimento(scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}"));

                // telefone------------------
                System.out.print(
                        "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o telefone do funcionário (somente numeros): ");

                if (scanner.hasNext()) {
                    String aux = scanner.next();
                    if (aux.toUpperCase().equals("SAIR"))
                        return;
                    novoFuncionario.setTelefone(aux);
                }

                // data de admissão ------------------------
                System.out.print(
                        "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme a data de admissao do funcionário (AAAA-MM-DD): ");

                while (!scanner.hasNext("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                    if (scanner.next().toUpperCase().equals("SAIR"))
                        return;
                    System.out.print(
                            "Opção Inválida! Caso deseje retornar ao menu inicial, digite SAIR.\n Informe data de admissao do funcionário (AAAA-MM-DD): ");
                }

                novoFuncionario.setDataAdmissao(scanner.next("[0-9]{4}-[0-9]{2}-[0-9]{2}"));
                scanner.nextLine();

                // Departamento ------------------
                System.out.print(
                        "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o Departamento do funcionário: ");

                if (scanner.hasNextLine()) {
                    String aux = scanner.nextLine();
                    if (aux.toUpperCase().equals("SAIR"))
                        return;
                    novoFuncionario.setDepartamento(aux);
                }

                // cargo ------------------------------
                System.out.print(
                        "\nCaso deseje retornar ao menu inicial, digite SAIR.\nInforme o cargo do funcionário: ");

                if (scanner.hasNextLine()) {
                    String aux = scanner.nextLine();
                    if (aux.toUpperCase().equals("SAIR"))
                        return;
                    novoFuncionario.setCargo(aux);
                }

                // ------------------------------------------------------------------------------

                try {
                    database.conectarBanco();

                    boolean statusQuery = database.executarUpdateSql(
                            "UPDATE public.funcionario SET "
                                    + "nome = '" + novoFuncionario.getNome()
                                    + "', dataNascimento = '" + novoFuncionario.getDataNascimento()
                                    + "', telefone = '" + novoFuncionario.getTelefone()
                                    + "', dataAdmissao = '" + novoFuncionario.getDataAdmissao()
                                    + "', departamento = '" + novoFuncionario.getNomeDepartamento()
                                    + "', cargo = '" + novoFuncionario.getCargo()
                                    + "' WHERE idFuncionario = " + novoFuncionario.getIdFuncionario() + ";");

                    if (statusQuery) {
                        mensagemStatus("funcionário atualizado com sucesso !");
                    }

                    database.desconectarBanco();

                } catch (Exception e) {
                }

                break;
            
            default:
                mensagemStatus("Opção inválida");
                break;
        }        
        
        inicio();

    }

    // MÉTODO RESPONSÁVEL SOMENTE POR EXIBIR UMA MENSAGEM NA TELA
    public static void mensagemStatus(String mensagem) {
        System.out.print("\n");
        System.out.print("--------------------------------");
        System.out.print("\n " + mensagem + " \n");
        System.out.print("--------------------------------");
        System.out.print("\n");
    }

}