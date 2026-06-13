public class Catalogo {

    private NossoHash<String, Livro> indice;

    public Catalogo() {
        this.indice = new NossoHash<String, Livro>();
    }

    public void cadastrar(Livro livro) {
        indice.put(livro.getIsbn(), livro);
    }

    public Livro buscar(String isbn) {
        return indice.get(isbn);
    }

    public boolean existe(String isbn) {
        return indice.containsKey(isbn);
    }

    public void exibirCatalogo() {
        indice.exibeMap();
    }
}
