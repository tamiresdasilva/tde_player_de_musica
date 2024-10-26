public class Playlist<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public Playlist() {
        this.inicio = null;
        this.fim = null;
    }

    public void adicionarInicio(T dado){
        No<T> novoNo = new No<>(dado);
        if (inicio == null){
            inicio = novoNo;
            fim = novoNo;
        }
        else {
            novoNo.proximo = inicio;
            inicio.anterior = novoNo;
            inicio = novoNo;
        }
        tamanho++;
    }

    public void adicionarFinal(T dado){
        No<T> novoNo = new No<>(dado);
        if (inicio == null){
            inicio = novoNo;
            fim = novoNo;
        }
        else {
            fim.proximo = novoNo;
            novoNo.anterior = fim;
            fim = novoNo;
        }
        tamanho++;
    }

    public void adicionarPosicao(T dado, int posicao){
        if (posicao < 0 || posicao > tamanho + 1){
            System.out.println("A posição inserida é inválida.");
        }
        No<T> novoNo = new No<>(dado);

        if (posicao == 0) {
            adicionarInicio(dado);
        }else if(posicao == tamanho){
            adicionarFinal(dado);
        }
        else {
            No<T> atual = inicio;
            for (int i = 0; i < posicao -1; i++){
                atual = atual.proximo;
            }

            novoNo.proximo = atual.proximo;
            novoNo.anterior = atual;
            atual.proximo.anterior = novoNo;
            atual.proximo = novoNo;

            tamanho ++;
        }
    }

    public void verProxima() {
        if (inicio != null && inicio.proximo != null) {
            inicio = inicio.proximo;
            System.out.println("Tocando próxima música:\n " + inicio.dado);
        } else {
            System.out.println("Não há próxima música.");
            return;
        }
    }

    public void verAnterior(){
        if (inicio != null && inicio.anterior != null){
            inicio = inicio.anterior;
            System.out.println("Tocando música anterior:\n " + inicio.dado);
        } else {
        System.out.println("Não há música anterior");}
        return;
    }

    public void removerPorValor(T dado){
        if (inicio == null) {
            System.out.println("A playlist está vazia!");
            return;
        }

        No<T> atual = inicio;
        while (atual != null){
            if (atual.dado.equals(dado)){
                if (atual == inicio){
                    inicio = atual.proximo;
                    if (inicio != null) inicio.anterior = null;
                }
                else if (atual == fim){
                    fim = atual.anterior;
                    if (fim != null) fim.proximo = null;
                }else{ //Caso não esteja no começo e nem no final
                    atual.anterior.proximo = atual.proximo;
                    atual.proximo.anterior = atual.anterior;
                }
                tamanho--;
                System.out.println("Música removida.");
                return;
            }
            atual = atual.proximo;
        }
    }

    public T buscarPorTitulo(String titulo) {
        No<T> noAtual = inicio;
        while (noAtual != null) {
            Musica musica = (Musica) noAtual.dado;
            if (musica.getTitulo().equalsIgnoreCase(titulo)) {
                return (T) musica;
            }
            noAtual = noAtual.proximo;
        }
        return null;
    }


    public void listarMusicas(){
        if (inicio == null){
            System.out.println("A playlist está vazia.");
            return;
        }
        No<T> atual = inicio;
        while (atual != null){
            System.out.println(atual.dado + " ");
            atual = atual.proximo;
        }
    }

    public void ordenarMusicas(String criterio){
        if (inicio == null || inicio.proximo == null){
            System.out.println("A playlist está vazia ou contém apenas uma música. Adicione novas músicas para realizar a ordenação.");
            return;
        }
        boolean alterado;
        do {
            alterado = false;
            No<T> atual = inicio;

            while (atual.proximo != null) {
                Musica musicaAtual = (Musica) atual.dado;
                Musica proximaMusica = (Musica) atual.proximo.dado;

                boolean comparacao = false;
                if (criterio.equalsIgnoreCase("título")){
                    comparacao = musicaAtual.getTitulo().compareToIgnoreCase(proximaMusica.getTitulo()) > 0;
                } else if (criterio.equalsIgnoreCase("artista")){
                    comparacao = musicaAtual.getArtista().compareToIgnoreCase(proximaMusica.getArtista()) > 0;
                }

                if (comparacao){
                    T temp = atual.dado;
                    atual.dado = atual.proximo.dado;
                    atual.proximo.dado = temp;
                    alterado = true;
                }
                atual = atual.proximo;
            }
        } while (alterado);
        System.out.println("Playlist ordenada por " + criterio + ".");
    }

    public void tocarMusica(){
        if (inicio == null){
            System.out.println("A playlist está vazia. Não há música para tocar.");
        } else{
            Musica musicaAtual = (Musica) inicio.dado;
            System.out.println("Tocando agora: ");
            System.out.println("Título: " + musicaAtual.getTitulo());
            System.out.println("Artista: " + musicaAtual.getArtista());
            System.out.println("Álbum: " + musicaAtual.getAlbum());
            System.out.println("Duração: " + musicaAtual.getDuracao() + " segundos");
        }
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "inicio=" + inicio +
                ", fim=" + fim +
                ", tamanho=" + tamanho +
                '}';
    }
}
