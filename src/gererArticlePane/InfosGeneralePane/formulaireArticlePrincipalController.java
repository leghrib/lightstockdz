package gererArticlePane.InfosGeneralePane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import ICON.ICONE;
import Modele.Article;
import Modele.Categorie;
import THIS_APPLICATION.CodeBarUTILS;
import UTILS.JfxUtils;
import UTILS.UTILS;
import UTILS.ImagePane.ImagePaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public abstract class formulaireArticlePrincipalController extends BorderPane implements Initializable {

	@FXML
	private TextField txtNumeroInscription;

	@FXML
	private TextField txtRef;

	@FXML
	private TextField txtDesciption;

	@FXML
	private TextField txtPrixAchat;

	@FXML
	private TextField txtPrixVente;

	@FXML
	private TextField txtSeuilAlerte;

	@FXML
	private TextField txtCodeBar;

	@FXML
	private ComboBox<Categorie> comboCategorie;
	@FXML
	private Button btnCodeBar;

	@FXML
	private ImageView imageViewCodeBar;

	@FXML
	private StackPane stackPanePhoto;

	private int POUR = UTILS.POUR_AJOUTER;

	private ImagePaneController imagePane;

	@FXML
	void codebar(ActionEvent event) {
		LoadCodeBar();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				refresh();
			}
		};

		txtRef.setOnKeyReleased(eventHandler);


	}

	public abstract void refresh();

	public formulaireArticlePrincipalController() {

		JfxUtils.setAll("/gererArticlePane/InfosGeneralePane/InfosGeneralePane.fxml", this);

		imagePane = new ImagePaneController(ICONE.articleDefault){
			@Override
			public void changed() {
				// TODO Auto-generated method stub
				
			}
		};
		stackPanePhoto.getChildren().add(imagePane);

	}

	public void chargerElement(Article a) {

		txtNumeroInscription.setText("" + a.getIdArticle());
		imagePane.chargerImage(a.getImageUrl());
		txtRef.setText(a.getRef());
		txtCodeBar.setText(a.getCodeBar());
		txtDesciption.setText(a.getDescription());
		txtPrixAchat.setText(UTILS.FMoney(a.getPrixAchat()));
		txtPrixVente.setText(UTILS.FMoney(a.getPrixVente()));

		
	}

	public Article setElement(Article a) {
		a.setPrixAchat(UTILS.getDoubleTXTmoney(txtPrixAchat));
		a.setPrixVente(UTILS.getDoubleTXTmoney(txtPrixVente));
		
		a.setRef(txtRef.getText());
		a.setDescription(txtDesciption.getText());

		a.setImageUrl(imagePane.getImageURL());

		return a;
	}

	public void pourAjouter() {
		POUR = UTILS.POUR_AJOUTER;
	}

	public void LoadCodeBar() {

		try {

			String ID_Object = "" + UTILS.getEntier(txtNumeroInscription);
			File f = CodeBarUTILS.createCodeBarImage(CodeBarUTILS.PERSONNE_CODE_BAR, ID_Object);
			Image image = new Image("file:" + f.getAbsolutePath());
			imageViewCodeBar.setImage(image);
		} catch (Exception e) {

		}
	}

}
