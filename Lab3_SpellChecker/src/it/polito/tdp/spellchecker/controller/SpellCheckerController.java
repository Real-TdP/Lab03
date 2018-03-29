package it.polito.tdp.spellchecker.controller;
import  it.polito.tdp.spellchecker.model.Dictionary;

import java.net.URL;
import java.util.*;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SpellCheckerController {
	
	private Dictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> lang;

    @FXML
    private TextArea txtInsert;

    @FXML
    private Button btnSpell;

    @FXML
    private TextArea txtWrong;

    @FXML
    private Text err;

    @FXML
    private Button btnClear;

    @FXML
    private Text time;

    @FXML
    void clearTxt(ActionEvent event) {
    	model.setNerr(0);
    	txtWrong.clear();
    	txtInsert.clear();
    	model.setTim(0);
    }
    
    @FXML
    void spellCheck(ActionEvent event) {
    	txtWrong.clear();
    	txtInsert.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]\\\"", "");
    	String[] txt = txtInsert.getText().toLowerCase().split(" ");
    	List<String> wrong =model.checkText(Arrays.asList(txt), lang.getValue());
    	for(String w:wrong)
    		txtWrong.appendText(w+"\n");
    }

    @FXML
    void initialize() {
        assert lang != null : "fx:id=\"lang\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert err != null : "fx:id=\"err\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        lang.getItems().addAll("Italian","English");

    }
    
    public void setModel(Dictionary model) {
		this.model = model;
		err.textProperty().bind(Bindings.convert(model.NerrProperty()));
		time.textProperty().bind(Bindings.concat(Bindings.convert(model.TimProperty())));  // Bindings.convert(model.TimProperty())
	}
}
