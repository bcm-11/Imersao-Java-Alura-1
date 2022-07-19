import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes através da API do IMDB
           
        // var url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm" // IMDB ficou fora do ar;
        var url = "https://api.mocki.io/v2/549a5d8b"; // alt1 criada pelo instrutor @Alexandre Aquiles - Alura
        // var url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060"; // alt2 criada pelo @rezendecas
        // var url = "https://alura-imdb-api.herokuapp.com/movies"; // alt3 criada pelo @Jhon Santana
        // var url = "https://alura-filmes.herokuapp.com/conteudos"; // alt4 4criada pela instrutora @Jacqueline Oliveira
        URI uri = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).build();

        // pegar o texto JSON
        String json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        
        // imprimir o JSON
        System.out.println(json);
    }
}
