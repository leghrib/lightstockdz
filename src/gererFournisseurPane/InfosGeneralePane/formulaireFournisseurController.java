package gererFournisseurPane.InfosGeneralePane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import ICON.ICONE;
import Modele.Client;
import Modele.Fournisseur;
import THIS_APPLICATION.CodeBarUTILS;
import UTILS.JfxUtils;
import UTILS.UTILS;
import UTILS.ImagePane.ImagePaneController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public abstract class formulaireFournisseurController extends StackPane implements Initializable {

	@FXML
	private TextField txtNumeroInscription;
	@FXML
	private TextField txtNomAr;
	@FXML
	private TextField txtPrenomAr;
	@FXML
	private DatePicker chooserDateNaissance;
	@FXML
	private ToggleGroup sexe;
	@FXML
	private RadioButton rdnMasculin;
	@FXML
	private RadioButton rdnFeminin;
	@FXML
	private ImageView imageViewCodeBar;
	@FXML
	private Button btnCodeBar;
	@FXML
	private TextField txtTel;
	@FXML
	private TextField txtRaisonSociale;
	@FXML
	private TextField txtEmail;
	@FXML
	private StackPane stackPanePhoto;
	private ImagePaneController imagePane;

	private int POUR=UTILS.POUR_AJOUTER;

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

		txtRaisonSociale.setOnKeyReleased(eventHandler);
		rdnFeminin.setSelected(true);

	}

	public abstract void refresh();

	public formulaireFournisseurController() {

		JfxUtils.setAll("/gererFournisseurPane/InfosGeneralePane/InfosGeneralePane.fxml", this);

		imagePane = new ImagePaneController(ICONE.personneMasculin){
			@Override
			public void changed() {
				// TODO Auto-generated method stub
				
			}
		};
		stackPanePhoto.getChildren().add(imagePane);

	}

	public void chargerElement(Fournisseur personne) {

		txtNomAr.setText(personne.getNomAr());
		txtPrenomAr.setText(personne.getPrenomAr());
		txtNumeroInscription.setText("" + personne.getIdFournisseur());
		rdnMasculin.setSelected(personne.getSexe() == 0);
		imagePane.chargerImage(personne.getImageUrl());
		txtTel.setText(personne.getTel());
		txtEmail.setText(personne.getEmail());
		txtRaisonSociale.setText(personne.getRaisonSociale());
		UTILS.setDateToChooser(personne.getDt(), chooserDateNaissance);
	}

	public Fournisseur setElement(Fournisseur personne) {
		personne.setNomAr(txtNomAr.getText());
		personne.setPrenomAr(txtPrenomAr.getText());

		personne.setSexe(rdnMasculin.isSelected() ? 0 : 1);
		personne.setImageUrl(imagePane.getImageURL());
		personne.setEmail(txtEmail.getText());
		personne.setTel(txtTel.getText());
		personne.setRaisonSociale(txtRaisonSociale.getText());
		personne.setDt(UTILS.getDateFromchooser(chooserDateNaissance));
		return personne;
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
