import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


// obs.: atalho shift+alt+o = importa tudo o que for necessário par ao código funcionar
// obs.: usar ctrl+shift+p e ctrl+. para agilizar
// obs.: atalho "SOUT" =  System.out.println

// AULA 1 = Consumindo uma API de filmes com Java

public class App {
	public static void main(String[] args) throws Exception {

	// Passo 1: fazer uma conexão HTTP e buscar os top 250 filmes através da API do IMDB:
		//"https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm" // IMDB com instabilidade
		//"https://api.mocki.io/v2/549a5d8b"; // alt1 criada pelo instrutor @Alexandre Aquiles - Alura
		//"https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060"; // alt2 criada pelo @rezendecas
		//"https://alura-imdb-api.herokuapp.com/movies"; // alt3 criada pelo @Jhon Santana
		//"https://alura-filmes.herokuapp.com/conteudos"; // alt4 4criada pela instrutora @Jacqueline Oliveira

		String url = "https://api.mocki.io/v2/549a5d8b"; //utilizando alternativa 1
		URI endereco = URI.create(url);
		var client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String body = response.body();
		
	// Passo 2: extrair somente os dados que interessam (título, poster, classificação) - "parcear o texto/JSON" através de expressões regulares (para não precisar instalar nenhuma lib/biblioteca)

		var parser = new JsonParser();
		List<Map<String, String>> listaDeFilmes = parser.parse(body);
		// System.out.println(listaDeFilmes.size()); //verificar quantos itens há na lista
		// System.out.println(listaDeFilmes.get(0)); //mostrar o 1ª da lista

	// Passo 3: exibir e manipular os dados

		// Método inicial:
			//for (Map<String,String> filme : listaDeFilmes) {
				//System.out.println(filme.get("title"));
				//System.out.println(filme.get("image"));
				//System.out.println(filme.get("imDbRating")); //desafio alura: colocar estrelinhas de acordo com as notas
				//System.out.println();

		// Utilizando GeradoraDeFigurinhas.java para gerar várias figurinhas:
			var geradora = new GeradoraDeFigurinhas();
			for (Map<String,String> filme : listaDeFilmes) {

				String urlImage = filme.get("image");

				InputStream inputStream = new URL(urlImage).openStream();
				
				//fazendo cada figurinha tenha um nome único, de acordo com o nome do filme e seja salva no diretório saida (criando a pasta caso ainda não exista):
				String titulo = filme.get("title");
				String tituloCorrigido = titulo.replace(":", " -"); // para evitar erros pois Windows não aceita o caracter ":"
				
				Path saida = Paths.get("saida");
				Files.createDirectories(saida);
				String nomeArquivo = tituloCorrigido  + ".png";
				
				geradora.cria(inputStream, nomeArquivo);
				System.out.println("\uD83C\uDFAC" + "\u001b[1m" + titulo + "\u001b[m"); // título em negrito + emoji de claquete (https://www.fileformat.info/info/unicode/char/1f3ac/index.htm & https://en.wikipedia.org/wiki/Emoji#UTS_#51_and_modern_emoji_(2015%E2%80%93present))
				System.out.println(nomeArquivo);
				System.out.println();
		}
	}
}
