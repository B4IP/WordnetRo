package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.dictionary.Dictionary;

public class Main {
	public static void main(String[] args){
		Dictionary d=null;
		FileWriter f;
		try {
			d = Dictionary.getDefaultResourceInstance();
			f = new FileWriter(new File("out.txt"));
			for (POS p :POS.values()){
				for (Iterator<Synset> it=d.getSynsetIterator(p); it.hasNext();){
					Synset s=it.next();
					for (Word w : s.getWords()){
						f.write(String.format("%s\n", w.toString()));
					}
				}
			}
			f.close();
		} catch (JWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
