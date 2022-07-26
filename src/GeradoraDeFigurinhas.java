import java.awt.Graphics2D; //import manual
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Font; //import manual
import javax.imageio.ImageIO;


// Aula 2: Gerando figurinhas dos filmes para envio via WhatsApp

public class GeradoraDeFigurinhas {
	
	//public void cria() throws Exception{
	public void cria(InputStream inputStream, String nomeArquivo) throws Exception{ //utilizando método 3 de leitura

		// Passo 1: leitura da imagem
			// Método 1: salvar a imagem da API em diretório local (pasta entrada)
				//link poster Star Wars ep.4 = https://m.media-amazon.com/images/M/MV5BNzg4MjQxNTQtZmI5My00YjMwLWJlMjUtMmJlY2U2ZWFlNzY1XkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_UX128_CR0,3,128,176_AL_.jpg   
				//link poster Interstellar = https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX128_CR0,3,128,176_AL_.jpg   
				//link poster Alien 1 = https://m.media-amazon.com/images/M/MV5BOGQzZTBjMjQtOTVmMS00NGE5LWEyYmMtOGQ1ZGZjNmRkYjFhXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_UX128_CR0,3,128,176_AL_.jpg     
					// obs.: para obter a imagem com resolução original ao invés de "tumbnail", retirar do link os caracteres entre o "@" e a extensão do arquivo

				// BufferedImage imagemOriginal = ImageIO.read(new File("entrada/filme-maior.jpg")); //quando der unhandled exception, usar "throw declaration" ou "try/catch"
				// ou
				// FileInputStream inputStream = new FileInputStream(new File("entrada/filme-maior.jpg"));
				// BufferedImage imagemOriginal = ImageIO.read(inputStream);

			// Método 2: acessar imagem através de uma URL específica (para eliminar necessidade de arquivos locais)

				// InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNzg4MjQxNTQtZmI5My00YjMwLWJlMjUtMmJlY2U2ZWFlNzY1XkEyXkFqcGdeQXVyODk4OTc3MTY@.jpg").openStream();
				// BufferedImage imagemOriginal = ImageIO.read(inputStream);
			
			// Método 3: acessar imagem através de múltiplas URLs para criar múltiplas figurinhas

			BufferedImage imagemOriginal = ImageIO.read(inputStream);

		// Passo 3: criar uma nova imagem em memória com transparência e com tamanho novo

			int largura = imagemOriginal.getWidth();
			int altura = imagemOriginal.getHeight();
			int novaAltura = altura + 200;
			BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

		// Passo 4: copiar a imagem original para nova imagem (em memória)

			Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
			graphics.drawImage(imagemOriginal, 0, 0, null);

		// Passo 5.0: configurar a fonte

			Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 70); // desafio: fazer outline das letras na cor preta
			graphics.setColor(Color.BLUE);
			graphics.setFont(fonte);

		// Passo 5.1: escrever uma frase na imagem

			// graphics.drawString("Uma Nova Esperança", 65, novaAltura - 80); // para imagem do filme Star Wars ep.4
			
			// escrever o texto centralizado automaticamente

			String frase = "TOP 10"; // frase genérica
			int tamanhoFrase = graphics.getFontMetrics().stringWidth(frase);
			int localFrase = (novaImagem.getWidth() - tamanhoFrase) / 2;
			graphics.drawString(frase, localFrase, novaAltura - 80);
			
		// Passo 6: escrever a imagem nova em um arquivo
			//ImageIO.write(novaImagem, "png", new File("saida/figurinha.png")); //essa linha de código não serve para gerar mais de uma figurinha
			ImageIO.write(novaImagem, "png", new File("saida/"+nomeArquivo));
			
	}
	// OBS.: Utilizando o método 3 podemos eliminar o main pois usaremos o App.java

	// public static void main(String[] args) throws Exception {
		// var geradora = new GeradoraDeFigurinhas();
		// geradora.cria(); // usar "throw declaration"
		
	// }
}