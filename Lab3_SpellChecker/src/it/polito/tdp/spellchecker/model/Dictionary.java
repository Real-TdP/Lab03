package it.polito.tdp.spellchecker.model;

import java.io.*;
import java.util.*;

import javafx.beans.property.*;

public class Dictionary {
	
	
	
	private StringProperty Nerr= new SimpleStringProperty();
	private DoubleProperty Tim = new SimpleDoubleProperty();
	private int err=0;
	private double s=0;

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
	public List<String> checkTextDico(List<String> word,String language){
		s=System.nanoTime();
		err=0;
		Nerr.set("ci sono "+err+" errori");
		List<RichWord> bean = new LinkedList<RichWord>();
		
		List<String> dicty=this.loadDictionary(language);
		List<String> tmpD= new LinkedList<String>(dicty);
		for(String w:word) {
			tmpD.clear();
			tmpD.addAll(dicty);
			RichWord r = new RichWord(w);
			bean.add(r);
			String x = tmpD.get(tmpD.size()/2);
			while(!tmpD.isEmpty()&&w.compareTo(x)!=0) {
				x = tmpD.get(tmpD.size()/2);
				//System.out.println("Parola da cercare: "+r.getInput()+ "risult: "+w.compareTo(x) +"  ricerca dciot: "+tmpD.get(tmpD.size()/2)+"  "+tmpD.size());
				if(w.compareTo(x)<0)
					tmpD=tmpD.subList(0,tmpD.size()/2);
				else if(w.compareTo(x)>0)
					tmpD=tmpD.subList(tmpD.size()/2+1,tmpD.size());
			}
			//System.out.println("la parola anal è "+r.getInput()+"  and "+tmpD.size());
			if(tmpD.isEmpty()) {
				err++;
				Nerr.set("ci sono "+err+" errori");
			}
			else
				r.setCheck(true);
		}
		
		return this.getWord(bean);
		
	}

	public List<String> checkText(List<String> word,String language) {
		s=System.nanoTime();
		err=0;
		Nerr.set("ci sono "+err+" errori");
		List<RichWord> bean = new LinkedList<RichWord>();
		
		List<String> dicty=this.loadDictionary(language);
		for(String w:word) {
			RichWord r = new RichWord(w);
			bean.add(r);
			if(dicty.contains(w))
				r.setCheck(true);
			else {
				err++;
				Nerr.set("ci sono "+err+" errori");
			}
				
		}
		
		return this.getWord(bean);
		
	}
	
	public List<String> getWord(List<RichWord> wo){
		List<String> words = new LinkedList<String>();
		
		for(RichWord w:wo)
			if(!w.isCheck())
				words.add(w.getInput());
		Tim.set(System.nanoTime()-s);
		return words;
	}

	public final StringProperty NerrProperty() {
		return this.Nerr;
	}
	

	public final String getNerr() {
		return this.NerrProperty().get();
	}
	

	public final void setNerr(final String Nerr) {
		this.NerrProperty().set(Nerr);
	}

	public final DoubleProperty TimProperty() {
		return this.Tim;
	}
	

	public final double getTim() {
		return this.TimProperty().get();
	}
	

	public final void setTim(final double Tim) {
		this.TimProperty().set(Tim);
	}
	
	
	
	
	
	
}
