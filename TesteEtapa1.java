public class TesteEtapa1 {

    public static void main(String[] args) {

        ListaDupla acervo = new ListaDupla();

        Livro l1 = new Livro("978-85-430-0067-8", "Estruturas de Dados", "Goodrich", 2013);
        Livro l2 = new Livro("978-85-7522-411-2", "Algoritmos", "Cormen", 2012);
        Livro l3 = new Livro("978-85-216-2258-9", "Java Como Programar", "Deitel", 2017);
        Livro l4 = new Livro("978-85-7522-781-6", "Padroes de Projeto", "Gamma", 2000);

        System.out.println("== Inserindo livros no acervo ==");
        acervo.insereFim(l1);
        acervo.insereFim(l2);
        acervo.insereInicio(l3);
        acervo.insereFim(l4);

        System.out.println("Tamanho do acervo: " + acervo.tamanho());

        System.out.println("\n== Listagem do inicio para o fim ==");
        acervo.listarDoInicio();

        System.out.println("\n== Listagem do fim para o inicio ==");
        acervo.listarDoFim();

        System.out.println("\n== Busca por ISBN ==");
        String isbnBusca = "978-85-7522-411-2";
        Livro encontrado = acervo.buscarPorIsbn(isbnBusca);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado);
        } else {
            System.out.println("Livro nao encontrado.");
        }

        System.out.println("\n== Busca por ISBN inexistente ==");
        Livro naoEncontrado = acervo.buscarPorIsbn("000-00-0000-000-0");
        System.out.println("Resultado: " + naoEncontrado);

        System.out.println("\n== Removendo o primeiro livro ==");
        Livro removidoInicio = acervo.removePrimeiro();
        System.out.println("Removido: " + removidoInicio);

        System.out.println("\n== Removendo o ultimo livro ==");
        Livro removidoFim = acervo.removeUltimo();
        System.out.println("Removido: " + removidoFim);

        System.out.println("\n== Estado final do acervo ==");
        acervo.listarDoInicio();
        System.out.println("Tamanho final: " + acervo.tamanho());
    }
}
