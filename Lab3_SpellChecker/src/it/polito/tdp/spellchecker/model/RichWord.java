package it.polito.tdp.spellchecker.model;

public class RichWord {
	private String input;
	private boolean check;
	
	public RichWord(String input) {
		this.input = input;
		this.check=false;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
	
	

}
