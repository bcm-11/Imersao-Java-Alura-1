import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// obs.: atalho shift+alt+o = importa tudo o que for necessário par ao código funcionar e limpa imports não utilizados
// obs.: usar ctrl+shift+p e ctrl+. para agilizar
// obs.: atalho "SOUT" =  System.out.println

// AULA 3 = Ligando as pontas, refatoração e orientação a objetos: limpeza do cógigo, encapsulamento, deixar o código utilizável também para outros webservices
// * retomar video em 23 minutos *;

public class App {
	public static void main(String[] args) throws Exception {

	// Passo 1: fazer uma conexão HTTP e obter dados através da API de filmes ou API "Astronomic Picture Of the Day" da Nasa (http://api.nasa.gov)
		
		//String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060"; //utilizando alternativa 2
		//ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

		String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
		ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNASA();

		var http = new ClienteHTTP();
		String json = http.buscaDados(url);
		
	// Passo 3: exibir e manipular os dados usando os novos extratores de conteúdo
		List<Conteudo> conteudos = extrator.extraiConteudos(json);

		var geradora = new GeradoraDeFigurinhas();
		for (int i=0; i<3; i++) { //para obter apenas os 3 primeiros itens

			Conteudo conteudo = conteudos.get(i);

			InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
						
			Path saida = Paths.get("saida");
			Files.createDirectories(saida);
			String nomeArquivo = conteudo.getTitulo()  + ".png";
			
			geradora.cria(inputStream, nomeArquivo);
			System.out.println("\u001b[1m" + conteudo.getTitulo() + "\u001b[m");
			System.out.println(nomeArquivo);
			System.out.println();
		}
	}
}
