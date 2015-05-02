package tests;

import http.Downloader;

import java.io.IOException;
import java.net.MalformedURLException;

public class DownloaderTest {
	public static void main(String args[]){
		String url = "https://api.datamarket.azure.com/Bing/MicrosoftTranslator/v1/Translate?Text=%27hello%27&To=%27ro%27&From=%27en%27";
		String content = null;
		
		try {
			content = Downloader.download(url);
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
