package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

public class Dictionary {
	
	
	
	
	
	public List<String> loadDictionary(String language) {
		
		 List<String> dicty= new LinkedList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("rsc/"+language+".txt"));
			String line="";
			while((line=br.readLine())!=null){
				dicty.add(line);
				
			}
			br.close();
		}
		catch(IOException ex) {
			
		}	
		return dicty;
	}

	public List<String> checkText(List<String> word,String language) {
		List<RichWord> bean = new LinkedList<RichWord>();
		
		List<String> dicty=this.loadDictionary(language);
		for(String w:word) {
			RichWord r = new RichWord(w);
			bean.add(r);
			if(dicty.contains(w))
				r.setCheck(true);
		}
	
		return this.getWord(bean);
		
	}
	
	public List<String> getWord(List<RichWord> wo){
		List<String> words = new LinkedList<String>();
		
		for(RichWord w:wo)
			if(!w.isCheck())
				words.add(w.getInput());
		
		return words;
	}
}
