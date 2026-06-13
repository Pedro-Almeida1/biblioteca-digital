import java.util.Scanner;

public class BibliotecaDigital {

    private Catalogo catalogo;
    private ListaDupla acervo;
    private GestorEmprestimos gestor;
    private Scanner leitor;

    public BibliotecaDigital() {
        this.catalogo = new Catalogo();
        this.acervo = new ListaDupla();
        this.gestor = new GestorEmprestimos(catalogo);
        this.leitor = new Scanner(System.in);
    }

    public static void main(String[] args) {
        BibliotecaDigital sistema = new BibliotecaDigital();
        sistema.carregarDadosIniciais();
        sistema.executarMenu();
    }

    private void carregarDadosIniciais() {
        cadastrarLivroInterno(new Livro("978-85-430-0067-8", "Estruturas de Dados", "Goodrich", 2013));
        cadastrarLivroInterno(new Livro("978-85-7522-411-2", "Algoritmos", "Cormen", 2012));
        cadastrarLivroInterno(new Livro("978-85-216-2258-9", "Java Como Programar", "Deitel", 2017));
    }

    private void cadastrarLivroInterno(Livro livro) {
        acervo.insereFim(livro);
        catalogo.cadastrar(livro);
    }

    private void executarMenu() {
        int opcao = -1;

        while (opcao != 8) {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    buscarLivro();
                    break;
                case 3:
                    System.out.println("\n== Acervo do inicio ao fim ==");
                    acervo.listarDoInicio();
                    break;
                case 4:
                    System.out.println("\n== Acervo do fim ao inicio ==");
                    acervo.listarDoFim();
                    break;
                case 5:
                    solicitarEmprestimo();
                    break;
                case 6:
                    devolverLivro();
                    break;
                case 7:
                    verFilaDeEspera();
                    break;
                case 8:
                    System.out.println("Encerrando o sistema. Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }

        leitor.close();
    }

    private void exibirMenu() {
        System.out.println("\n==================================");
        System.out.println("       BIBLIOTECA DIGITAL");
        System.out.println("==================================");
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Buscar livro por ISBN");
        System.out.println("3 - Listar acervo do inicio ao fim");
        System.out.println("4 - Listar acervo do fim ao inicio");
        System.out.println("5 - Solicitar emprestimo");
        System.out.println("6 - Devolver livro");
        System.out.println("7 - Ver fila de espera de um livro");
        System.out.println("8 - Sair");
        System.out.println("==================================");
    }

    private void cadastrarLivro() {
        System.out.println("\n== Cadastro de novo livro ==");

        String isbn = lerTexto("ISBN: ");

        if (catalogo.existe(isbn)) {
            System.out.println("Ja existe um livro cadastrado com este ISBN.");
            return;
        }

        String titulo = lerTexto("Titulo: ");
        String autor = lerTexto("Autor: ");
        int ano = lerInteiro("Ano de publicacao: ");

        Livro novo = new Livro(isbn, titulo, autor, ano);
        cadastrarLivroInterno(novo);

        System.out.println("Livro cadastrado com sucesso!");
    }

    private void buscarLivro() {
        System.out.println("\n== Busca de livro por ISBN ==");
        String isbn = lerTexto("Informe o ISBN: ");

        Livro livro = catalogo.buscar(isbn);
        if (livro != null) {
            System.out.println("Encontrado: " + livro);
        } else {
            System.out.println("Nenhum livro encontrado com este ISBN.");
        }
    }

    private void solicitarEmprestimo() {
        System.out.println("\n== Solicitacao de emprestimo ==");

        String isbn = lerTexto("ISBN do livro: ");
        if (!catalogo.existe(isbn)) {
            System.out.println("Livro nao encontrado no catalogo.");
            return;
        }

        int matricula = lerInteiro("Matricula do usuario: ");
        String nome = lerTexto("Nome do usuario: ");
        String email = lerTexto("E-mail do usuario: ");

        Usuario usuario = new Usuario(matricula, nome, email);
        gestor.solicitarEmprestimo(isbn, usuario);
    }

    private void devolverLivro() {
        System.out.println("\n== Devolucao de livro ==");
        String isbn = lerTexto("ISBN do livro: ");

        if (!catalogo.existe(isbn)) {
            System.out.println("Livro nao encontrado no catalogo.");
            return;
        }

        gestor.devolverLivro(isbn);
    }

    private void verFilaDeEspera() {
        System.out.println("\n== Fila de espera ==");
        String isbn = lerTexto("ISBN do livro: ");

        if (!catalogo.existe(isbn)) {
            System.out.println("Livro nao encontrado no catalogo.");
            return;
        }

        gestor.listarFilaDeEspera(isbn);
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return leitor.nextLine().trim();
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = leitor.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Digite um numero inteiro.");
            }
        }
    }
}
