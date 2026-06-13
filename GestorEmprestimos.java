public class GestorEmprestimos {

    private NossoHash<String, Fila<Usuario>> filasDeEspera;
    private Catalogo catalogo;

    public GestorEmprestimos(Catalogo catalogo) {
        this.catalogo = catalogo;
        this.filasDeEspera = new NossoHash<String, Fila<Usuario>>();
    }

    public void solicitarEmprestimo(String isbn, Usuario u) {
        Livro livro = catalogo.buscar(isbn);

        if (livro == null) {
            System.out.println("Livro com ISBN " + isbn + " nao encontrado no catalogo.");
            return;
        }

        if (livro.isDisponivel()) {
            livro.setDisponivel(false);
            System.out.println("Emprestimo realizado: " + livro.getTitulo() + " para " + u.getNome());
        } else {
            Fila<Usuario> fila = filasDeEspera.get(isbn);

            if (fila == null) {
                fila = new Fila<Usuario>();
                filasDeEspera.put(isbn, fila);
            }

            fila.enfileira(u);
            System.out.println("Livro indisponivel. " + u.getNome() + " entrou na fila de espera de \""
                    + livro.getTitulo() + "\".");
        }
    }

    public void devolverLivro(String isbn) {
        Livro livro = catalogo.buscar(isbn);

        if (livro == null) {
            System.out.println("Livro com ISBN " + isbn + " nao encontrado no catalogo.");
            return;
        }

        livro.setDisponivel(true);
        System.out.println("Livro devolvido: " + livro.getTitulo());

        Fila<Usuario> fila = filasDeEspera.get(isbn);

        if (fila != null && !fila.filaVazia()) {
            try {
                Usuario proximo = fila.desenfileira();
                livro.setDisponivel(false);
                System.out.println("Atendimento automatico: " + proximo.getNome()
                        + " agora esta com o livro \"" + livro.getTitulo() + "\".");
            } catch (FilaVaziaException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Nenhum usuario na fila de espera para este livro.");
        }
    }

    public void listarFilaDeEspera(String isbn) {
        Livro livro = catalogo.buscar(isbn);
        String nomeLivro = (livro != null) ? livro.getTitulo() : isbn;

        Fila<Usuario> fila = filasDeEspera.get(isbn);

        System.out.println("Fila de espera de \"" + nomeLivro + "\":");
        if (fila == null || fila.filaVazia()) {
            System.out.println("\\ (fila vazia)");
        } else {
            System.out.println(fila);
        }
    }
}
