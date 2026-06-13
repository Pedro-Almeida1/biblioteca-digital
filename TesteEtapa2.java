public class TesteEtapa2 {

    public static void main(String[] args) {

        Catalogo catalogo = new Catalogo();

        Livro livro = new Livro("978-85-430-0067-8", "Estruturas de Dados", "Goodrich", 2013);
        catalogo.cadastrar(livro);

        GestorEmprestimos gestor = new GestorEmprestimos(catalogo);

        Usuario u1 = new Usuario(1, "Ana Souza", "ana@email.com");
        Usuario u2 = new Usuario(2, "Bruno Lima", "bruno@email.com");
        Usuario u3 = new Usuario(3, "Carla Diniz", "carla@email.com");

        System.out.println("== Estado inicial ==");
        System.out.println(livro);

        System.out.println("\n== Ana solicita o livro (disponivel) ==");
        gestor.solicitarEmprestimo(livro.getIsbn(), u1);
        System.out.println(livro);

        System.out.println("\n== Bruno solicita o mesmo livro (indisponivel, entra na fila) ==");
        gestor.solicitarEmprestimo(livro.getIsbn(), u2);

        System.out.println("\n== Carla tambem solicita o mesmo livro (entra na fila) ==");
        gestor.solicitarEmprestimo(livro.getIsbn(), u3);

        System.out.println("\n== Fila de espera atual ==");
        gestor.listarFilaDeEspera(livro.getIsbn());

        System.out.println("\n== Ana devolve o livro (Bruno deve ser atendido) ==");
        gestor.devolverLivro(livro.getIsbn());
        System.out.println(livro);

        System.out.println("\n== Fila de espera apos atendimento de Bruno ==");
        gestor.listarFilaDeEspera(livro.getIsbn());

        System.out.println("\n== Bruno devolve o livro (Carla deve ser atendida) ==");
        gestor.devolverLivro(livro.getIsbn());
        System.out.println(livro);

        System.out.println("\n== Fila de espera apos atendimento de Carla ==");
        gestor.listarFilaDeEspera(livro.getIsbn());

        System.out.println("\n== Carla devolve o livro (fila vazia agora) ==");
        gestor.devolverLivro(livro.getIsbn());
        System.out.println(livro);

        System.out.println("\n== Fila de espera com fila vazia ==");
        gestor.listarFilaDeEspera(livro.getIsbn());
    }
}
