public class NossoHash<K, V> {

    private static final int CAPACIDADE_INICIAL = 16;

    private Entrada<K, V>[] tabela;
    private int capacidade;

    @SuppressWarnings("unchecked")
    public NossoHash() {
        this.capacidade = CAPACIDADE_INICIAL;
        this.tabela = (Entrada<K, V>[]) new Entrada[capacidade];
    }

    private int hash(K key) {
        int codigo = key.hashCode();
        // garante um indice positivo dentro dos limites da tabela
        return (codigo & 0x7fffffff) % capacidade;
    }

    public void put(K key, V value) {
        int posicao = hash(key);

        Entrada<K, V> nova = new Entrada<K, V>(key, value);
        nova.proximo = tabela[posicao];
        tabela[posicao] = nova;
    }

    public V get(K key) {
        int posicao = hash(key);

        Entrada<K, V> atual = tabela[posicao];
        while (atual != null) {
            if (atual.key.equals(key)) {
                return atual.value;
            }
            atual = atual.proximo;
        }

        return null;
    }

    public boolean containsKey(K key) {
        int posicao = hash(key);

        Entrada<K, V> atual = tabela[posicao];
        while (atual != null) {
            if (atual.key.equals(key)) {
                return true;
            }
            atual = atual.proximo;
        }

        return false;
    }

    public boolean containsValue(V value) {
        // como o valor pode estar em qualquer posicao (o hash e calculado
        // a partir da chave, nao do valor), e necessario percorrer toda a
        // tabela, posicao por posicao, e cada lista encadeada de colisao
        for (int i = 0; i < capacidade; i++) {
            Entrada<K, V> atual = tabela[i];
            while (atual != null) {
                if (atual.value.equals(value)) {
                    return true;
                }
                atual = atual.proximo;
            }
        }

        return false;
    }

    public void exibeMap() {
        for (int i = 0; i < capacidade; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Posicao ").append(i).append(": ");

            Entrada<K, V> atual = tabela[i];
            if (atual == null) {
                sb.append("vazio");
            } else {
                while (atual != null) {
                    sb.append("(").append(atual.key).append(" -> ").append(atual.value).append(")");
                    if (atual.proximo != null) {
                        sb.append(" -> ");
                    }
                    atual = atual.proximo;
                }
            }

            System.out.println(sb.toString());
        }
    }
}
