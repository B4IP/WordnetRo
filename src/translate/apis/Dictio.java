/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package translate.apis;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Vlad
 */
public class Dictio implements WordTranslator {
    
    String source, target;
	
	public Dictio(String source, String target){
		this.source = source;
		this.target = target;
	}

	@Override
	public Translation getCandidates(String word)  {
		String url = "https://www.dictio.ro/logic.php";
		String content = null;
                Document responseDocument;
                Translation translation = new Translation(word);
		
                try {
                    responseDocument = Jsoup.connect(url).
                        data("l_from", source).
                        data("l_to", target).
                        data("sursa", word).
                        userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 "
                                + "(KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36").
                        post();
                }
                catch(IOException e) {
                    System.err.println("Could not download content from " + url);
                    return null;
                }
                Elements results = responseDocument.select("tbody td");
                for(Element e : results){
                    translation.add(e.text().split("\\[.*\\]")[0].trim());
                }

                return translation;
	}
}
