import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

// obs.: atalho shift+alt+o = importa tudo o que for necessário par ao código funcionar

// AULA 1 = Consumindo uma API de filmes com Java

public class App {
    public static void main(String[] args) throws Exception {

    // Passo 1: fazer uma conexão HTTP e buscar os top 250 filmes através da API do IMDB:
        //"https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm" // IMDB com instabilidade
        //"https://api.mocki.io/v2/549a5d8b"; // alt1 criada pelo instrutor @Alexandre Aquiles - Alura
        //"https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060"; // alt2 criada pelo @rezendecas
        //"https://alura-imdb-api.herokuapp.com/movies"; // alt3 criada pelo @Jhon Santana
        //"https://alura-filmes.herokuapp.com/conteudos"; // alt4 4criada pela instrutora @Jacqueline Oliveira

        String url = "https://alura-filmes.herokuapp.com/conteudos"; //utilizando alternativa 4
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //atalho "SOUT" =  System.out.println
        
    // Passo 2: extrair somente os dados que interessam (título, poster, classificação) - "parcear o texto/JSON" através de expressões regulares (para não precisar instalar nenhuma lib/biblioteca)

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // System.out.println(listaDeFilmes.size()); //verificar se há 250 itens na lista
        // System.out.println(listaDeFilmes.get(0)); //mostrar o 1ª da lista


    // Passo 3: exibir e manipular os dados

        for (Map<String,String> filme : listaDeFilmes) {
            //System.out.println("\u1F3AC"); //emoji de claquete (preciso verificar)
            System.out.println("\u001b[1m" + filme.get("title") + "\u001b[m"); //título em negrito; desafio pessoal: colocar emoji de claquete na mesma linha (\u1F3AC)
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating")); //desafio alura: colocar estrelinhas de acordo com as notas
            System.out.println();

            
        }
    }
}
