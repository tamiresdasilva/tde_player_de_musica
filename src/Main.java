import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Playlist<Musica> playlist = new Playlist<>();

        while (true){
            System.out.println("Bem-vindo ao seu Gerenciador de Músicas!");
            System.out.println("1 - Próxima música");
            System.out.println("2 - Música anterior");
            System.out.println("3 - Ordenar playlist");
            System.out.println("4 - Tocar música");
            System.out.println("5 - Adicionar música");
            System.out.println("6 - Remover música");
            System.out.println("7 - Listar músicas");
            System.out.println("0 - Sair");
            System.out.println("Digite a opção desejada: ");

            int opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao){
                case 1:
                    playlist.verProxima();
                    break;
                case 2:
                    playlist.verAnterior();
                    break;
                case 3:
                    System.out.println("Escolha o critério de ordenação: ");
                    System.out.println("T - Título");
                    System.out.println("A - Artista");
                    char criterio = Character.toLowerCase(leitor.nextLine().charAt(0));

                    if (criterio == 't'){
                        playlist.ordenarMusicas("título");
                    }else if (criterio == 'a'){
                        playlist.ordenarMusicas("artista");
                    } else {
                        System.out.println("Critério digitado inválido.");
                    }
                    break;
                case 4:
                    playlist.tocarMusica();
                    break;
                case 5:
                    System.out.println("Título da música: ");
                    String titulo = leitor.nextLine();

                    System.out.println("Artista: ");
                    String artista = leitor.nextLine();

                    System.out.println("Álbum: ");
                    String album = leitor.nextLine();

                    System.out.println("Tempo de duração em segundos: ");
                    int duracao = leitor.nextInt();

                    leitor.nextLine();

                    System.out.println("Você deseja adicionar a música em qual posição? ");
                    System.out.println("Digite a letra C para começo");
                    System.out.println("Digite letra F para final");
                    System.out.println("Digite a letra P para uma posição personalizada");
                    char posicao = Character.toLowerCase(leitor.nextLine().charAt(0));

                    Musica novaMusica = new Musica(titulo, artista, album, duracao);
                    if (posicao == 'c'){
                        playlist.adicionarInicio(novaMusica);
                        System.out.println("Música adicionada ao começo da playlist com sucesso!");
                    }else if (posicao == 'f'){
                        playlist.adicionarFinal(novaMusica);
                        System.out.println("Música adicionada ao final da playlist sucesso!");
                    }else if (posicao == 'p'){
                        System.out.println("Digite uma posição válida onde a música deve ser inserida: ");
                        int posicaoColocar = leitor.nextInt();
                        leitor.nextLine();
                        playlist.adicionarPosicao(novaMusica, posicaoColocar);
                        System.out.println("Música adicionada na posição \"" + posicaoColocar + "\".");
                    }else {
                        System.out.println("Posição digitada inválida.");
                        return;
                    }

                    break;
                case 6:
                    System.out.println("Digite o título da música a ser removido: ");
                    String tituloRemover = leitor.nextLine();
                    Musica musicaRemover = playlist.buscarPorTitulo(tituloRemover);
                    if (musicaRemover != null){
                        playlist.removerPorValor(musicaRemover);
                        System.out.println("A música foi removida com sucesso.");
                    } else {
                        System.out.println("Música não encontrada para remoção.");
                    }
                    break;
                case 7:
                    playlist.listarMusicas();
                    break;
                case 0:
                    System.out.println("Fechando o programa...");
                    System.out.println("...");
                    System.out.println("...");
                    System.out.println("Programa encerrado!");
                    break;
                default:
                    System.out.println("Por favor, escolha uma opção válida!");
                    break;
            }
        }

    }
}