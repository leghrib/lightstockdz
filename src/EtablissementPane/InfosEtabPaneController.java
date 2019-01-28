package EtablissementPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import CRUD.EtabCRUD;
import ICON.ICONE;
import Modele.Etab;
import THIS_APPLICATION.Loaded;
import UTILS.JfxUtils;
import UTILS.UTILS;
import UTILS.ImagePane.ImagePaneController;
import UTILS.Traduction.G;
import eu.hansolo.enzo.notification.Notification.Notifier;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class InfosEtabPaneController extends BorderPane implements Initializable {

	@FXML
	private HBox hboxMinistere1;

	@FXML
	private Label lblTitle;

	@FXML
    private TextArea txtEntete;

	@FXML
	private HBox hboxNom;

	@FXML
	private TextField txtNom;

	@FXML
	private HBox hboxAdresse;

	@FXML
	private TextField txtAdresse;

	@FXML
	private HBox hboxtel;

	@FXML
	private TextField txtTel;

	@FXML
	private HBox hboxSlogan;

	@FXML
	private TextField txtSlogan;

	@FXML
	private TextField txtSiteWeb;

	@FXML
	private StackPane stackPanePhoto;

	@FXML
	private HBox hboxMinistere11;

	@FXML
	private Label lblTitle1;

	@FXML
	private HBox hboxEmail;

	@FXML
	private TextField txtEmail;

	@FXML
	private HBox hboxPassword;

	@FXML
	private PasswordField txtpasswordEmail;

	@FXML
	private HBox hbowServerIP;

	@FXML
	private TextField txtsmtp;

	@FXML
	private TextField txtPort;

	@FXML
	private HBox hboxNomDirecteur1;

	@FXML
	private Button btnExit;

	@FXML
	private Button btnSave;

	private ImagePaneController imagePaneController;

	private boolean changeHappened;

	private Etab etab = new Etab();

	// Event Listener on Button[#btnExit].onAction
	@FXML
	public void exit(ActionEvent event) {

		if (changeHappened)
			save(null);
		JfxUtils.closeStage(btnExit);

	}

	// Event Listener on Button[#btnSave].onAction
	@FXML
	public void save(ActionEvent event) {
		if (UTILS.runAlertSaveChanges() != ButtonType.OK || !changeHappened)
			return;
		boolean done = new EtabCRUD().modifier(getEtablissement());
		if (done) {
			changeHappened = false;
			Notifier.INSTANCE.notifySuccess(G.fait_, G.a_ete_modifie);
		} else
			Notifier.INSTANCE.notifyError(G.un_erreur, G.il_y_a_un_erreur);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imagePaneController = new ImagePaneController(ICONE.LogoDefault) {

			@Override
			public void changed() {
				// TODO Auto-generated method stub
				InfosEtabPaneController.this.changeHappened = true;

			}
		};
		stackPanePhoto.getChildren().add(imagePaneController);
	}

	public InfosEtabPaneController() {

		JfxUtils.setAll("/EtablissementPane/InfosEtabPane.fxml", this);
		remplirEtab(Loaded.getEtab());
		addListenerToAll();
	}

	private void remplirEtab(Etab etablissement) {
		etab = etablissement;

		txtAdresse.setText(etab.getAdresse());
		txtEmail.setText(etab.getEmail());
		txtNom.setText(etab.getNom());
		txtpasswordEmail.setText(etab.getPassword());
		txtPort.setText(etab.getPort());
		txtSiteWeb.setText(etab.getSiteWeb());
		txtSlogan.setText(etab.getSlogan());
		txtsmtp.setText(etab.getServeurSmtp());
		txtTel.setText(etab.getTel());
		txtEntete.setText(etab.getRepEntete());
		imagePaneController.chargerImage(etab.getUrlLogo());

	}

	public Etab getEtablissement() {

		etab.setAdresse(txtAdresse.getText());
		etab.setDescription("");
		etab.setEmail(txtEmail.getText());
		etab.setNom(txtNom.getText());
		etab.setPassword(txtpasswordEmail.getText());
		etab.setPort(txtPort.getText());
		etab.setServeurSmtp(txtsmtp.getText());
		etab.setSiteWeb(txtSiteWeb.getText());
		etab.setTel(txtTel.getText());
		etab.setSlogan(txtSlogan.getText());
		etab.setUrlLogo(new File(imagePaneController.getImageURL()).getPath());
		etab.setRepEntete(txtEntete.getText());
		return etab;

	}

	public void addListenerToAll() {

		ChangeListener<String> changeListener = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				changeHappened = true;
			}
		};

		txtAdresse.textProperty().addListener(changeListener);
		txtEmail.textProperty().addListener(changeListener);
		txtNom.textProperty().addListener(changeListener);
		txtpasswordEmail.textProperty().addListener(changeListener);
		txtPort.textProperty().addListener(changeListener);
		txtSiteWeb.textProperty().addListener(changeListener);
		txtSlogan.textProperty().addListener(changeListener);
		txtsmtp.textProperty().addListener(changeListener);
		txtTel.textProperty().addListener(changeListener);
		txtEntete.textProperty().addListener(changeListener);

	}

	public void setupSize() {

		setHeight(550);
		setWidth(550);
	}
}
