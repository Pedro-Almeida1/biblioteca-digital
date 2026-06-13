public class NoDuplo {

    private Livro info;
    private NoDuplo anterior;
    private NoDuplo proximo;

    public NoDuplo(Livro info) {
        this.info = info;
        this.anterior = null;
        this.proximo = null;
    }

    public Livro getInfo() {
        return info;
    }

    public void setInfo(Livro info) {
        this.info = info;
    }

    public NoDuplo getAnterior() {
        return anterior;
    }

    public void setAnterior(NoDuplo anterior) {
        this.anterior = anterior;
    }

    public NoDuplo getProximo() {
        return proximo;
    }

    public void setProximo(NoDuplo proximo) {
        this.proximo = proximo;
    }
}
