package translator;

import http.Downloader;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class GoogleTranslate implements TranslateAPI{
	String source, target;

	public GoogleTranslate(String source, String target){
		this.source = source;
		this.target = target;
	}
	
	private String buildQuery(String str){
		String encodedStr = null;
		
		try{
			encodedStr = URLEncoder.encode(str, java.nio.charset.StandardCharsets.UTF_8.toString());
		}
		catch (UnsupportedEncodingException e){
			System.out.printf("Charset not suported: %s\n", str);
			return null;
		}
		return String.format("https://translate.google.com/m?hl=%s&sl=%s&q=%s", target, source, encodedStr);
	}
	
	public String translate(String str){
		String url = buildQuery(str);
		String content = null;
		
		try{
			content = Downloader.download(url);
			System.out.println(content);
		}
		catch (IOException e){
			System.out.printf("Error while downloading %s (%s)\n", url, e.getMessage());
		}
		
		return "";
	}
}
