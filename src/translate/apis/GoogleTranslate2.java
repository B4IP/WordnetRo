package translate.apis;

import translate.apis.TranslateAPI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.List;

import translate.http.HttpGet;
import translate.util.GoogleTranslate2ResultParser;
import translate.util.entity.Translation;

public class GoogleTranslate2 implements TranslateAPI{
    String source, target;
    
    public GoogleTranslate2(String source, String target){
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
        return String.format("https://translate.google.com/translate_a/single?client=t&sl=%s&tl=%s&dt=bd&dt=ex&dt=ld&dt=md&dt=qc&dt=rw&dt=rm&dt=ss&dt=t&dt=at&q=%s", source, target, encodedStr);
    }
    
    public String translate(String str){
        String url = buildQuery(str);
        String content = null;
        
        try{
            content = HttpGet.download(url);
        }
        catch (MalformedURLException e){
            System.out.printf("Could not encode %s\n", str);
            return null;
        }
        catch (IOException e){
            System.out.printf("Error while downloading %s (%s)\n", url, e.getMessage());
            return null;
        }
        
        String result = GoogleTranslate2ResultParser.extractMainResult(content);
        return result;
    }
    
    public List<Translation> getPartialTranslations(String phrase) {
        String url = buildQuery(phrase);
        String content = null;
        
        try{
            content = HttpGet.download(url);
        }
        catch (MalformedURLException e){
            System.out.printf("Could not encode %s\n", phrase);
            return null;
        }
        catch (IOException e){
            System.out.printf("Error while downloading %s (%s)\n", url, e.getMessage());
            return null;
        }
        
        return GoogleTranslate2ResultParser.extractPartialTranslations(content);
    }

	@Override
	public translate.apis.Translation getCandidates(String word) {
		// TODO Auto-generated method stub
		translate.apis.Translation translation = new translate.apis.Translation(word);
		return translation;
	}
    
}
