import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNASA implements ExtratorDeConteudo{
	public List<Conteudo> extraiConteudos (String json){
		
		// Passo 2 da app: extrair somente os dados que interessam
		var parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos = parser.parse(json);

		List<Conteudo> conteudos = new ArrayList<>();

		// popular a lista de conteúdos
		for (Map<String, String> atributos : listaDeAtributos) {
			String titulo = atributos.get("title").replace(":", " -"); // Windows não aceita o caracter ":"
			String urlImagem = atributos.get("url");
			var conteudo = new Conteudo(titulo, urlImagem);

			conteudos.add(conteudo);
		}
				
		return conteudos;
	
	}
}
