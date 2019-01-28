package MoteurDeRecherche;

import java.net.URL;
import java.util.ResourceBundle;

import UTILS.JfxUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ListView;

public class MoteurRecherchePaneController extends BorderPane implements Initializable {
	@FXML
	private TextField txtRecherche;
	@FXML
	private ListView listView;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	public MoteurRecherchePaneController() {
		JfxUtils.setAll("/MoteurDeRecherche/MoteurRecherchePane.fxml", this);
	}

}
