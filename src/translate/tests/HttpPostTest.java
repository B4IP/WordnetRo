package translate.tests;


import java.io.IOException;
import java.net.MalformedURLException;
import translate.http.HttpPost;

public class HttpPostTest {
	public static void main(String args[]){
		String url = "https://api.datamarket.azure.com/Bing/MicrosoftTranslator/v1/Translate?Text=%27hello%27&To=%27ro%27&From=%27en%27";
		String content = null;
	
		try {
			content = HttpPost.download(url, "berendeanicolae@gmail.com", "IGCynGBUnY83apRHqQ2fut9OqUdw0x7oEl0SZNyxJR8");
			System.out.println(content);
		}
		catch (MalformedURLException e){
			System.out.printf("%s is an invalid URL\n", url);
		}
		catch (IOException e){	
			System.out.printf("Error transfering %s because %s\n", url, e.getMessage());
		}
	}
}
