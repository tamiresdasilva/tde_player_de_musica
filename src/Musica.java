public class Musica {
    String titulo;
    String artista;
    String album;
    int duracao;

    public Musica(String titulo, String artista, String album, int duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return "\nTítulo da Música: " + titulo + "\nArtista: " + artista + "\nÁlbum: " + album + "\nDuração: " + duracao;
    }
}
