package translate.apis;

import translate.apis.TranslateAPI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import translate.http.HttpPost;

public class BingTranslator implements TranslateAPI{
	private static String user = "berendeanicolae@gmail.com";
	private static String passw = "IGCynGBUnY83apRHqQ2fut9OqUdw0x7oEl0SZNyxJR8";
	private String source, target;
	
	/*public BingTranslator(){
		source = "en";
		target = "ro";
	}*/
	
	public BingTranslator(String source, String target) {
		this.source = null;
		this.target = null;
		try{
			this.source = URLEncoder.encode(String.format("'%s'", source), java.nio.charset.StandardCharsets.UTF_8.toString());
			this.target = URLEncoder.encode(String.format("'%s'", target), java.nio.charset.StandardCharsets.UTF_8.toString());
		}
		catch (UnsupportedEncodingException e){
			System.out.printf("Could not encode arguments");
		}
	}
	
	private String buildQuery(String str){
		String encodedStr = null;
		String url = "https://api.datamarket.azure.com/Bing/MicrosoftTranslator/v1/Translate?Text=%s&To=%s&From=%s"; 
		
		try{
			encodedStr = URLEncoder.encode(String.format("'%s'", str), java.nio.charset.StandardCharsets.UTF_8.toString());
		}
		catch (Exception e){
			System.out.printf("Charset not suported: %s\n", str);
			return null;
		}
		return String.format(url, encodedStr, target, source);
	}
	
	public String translate(String str){
		if (source==null || target==null){
			return null;
		}
		
		
		String url = buildQuery(str);
		String content = null;
		
		try{
			content = HttpPost.download(url, user, passw);
		}
		catch (MalformedURLException e){
			System.out.printf("Could not encode %s\n", str);
			return null;
		}
		catch (IOException e){
			System.out.printf("Error while downloading %s (%s)\n", url, e.getMessage());
			return null;
		}
		
		Document doc = Jsoup.parse(content);
		return doc.getElementsByTag("content").text();
	}
}
