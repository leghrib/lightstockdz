package gererFournisseurPane.gererElementPane;

import java.net.URL;
import java.util.ResourceBundle;

import CRUD.ClientCRUD;
import CRUD.FournisseurCRUD;
import Modele.Fournisseur;
import UTILS.JfxUtils;
import UTILS.UTILS;
import UTILS.Traduction.G;
import eu.hansolo.enzo.notification.Notification.Notifier;
import gererFournisseurPane.InfosGeneralePane.formulaireFournisseurController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class GererFournisseurPaneController extends StackPane implements Initializable {
	@FXML
	private Tab tabInfosGenerale;

	@FXML
	private javafx.scene.control.Button btnAnnuler;
	@FXML
	private javafx.scene.control.Button btnValider;

	private formulaireFournisseurController formulaireFournisseurController;

	public Fournisseur fournisseur = new Fournisseur();
	public int POUR = UTILS.POUR_DEFAULT;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnValider.setGraphic(new ImageView(ICON.ICONE.save32));
		btnAnnuler.setGraphic(new ImageView(ICON.ICONE.exit32));

		setStyleAll();

	}

	private void setStyleAll() {

		setStyle(tabInfosGenerale);

	}

	public abstract FournisseurCRUD getFournisseurCRUD();

	private void setStyle(Tab tab) {
		tab.setStyle("-fx-border-color:black;");
		tab.setStyle("-fx-font-size:16;");
		// tab.setStyle("-fx-font-style:gras;");
		tab.setGraphic(new Label());
		tab.getGraphic().setStyle("-fx-text-fill: #c4d8de;");
	}

	public abstract void refresh(Fournisseur insc2);

	public GererFournisseurPaneController() {

		JfxUtils.setAll("/gererFournisseurPane/gererElementPane/gererElementPane.fxml", this);
		formulaireFournisseurController = new formulaireFournisseurController() {
			@Override
			public void refresh() {
				GererFournisseurPaneController.this.refresh(getInsc());
			}
		};

		tabInfosGenerale.setContent(formulaireFournisseurController);

	}

	public void chargerElement(Fournisseur insc) {
		this.fournisseur = insc;
		formulaireFournisseurController.chargerElement(insc);

	}

	public void pourAjouter() {
		formulaireFournisseurController.pourAjouter();

		POUR = UTILS.POUR_AJOUTER;
		this.fournisseur = new Fournisseur();

	}

	@FXML
	void valider(ActionEvent event) {

		if (isValide()) {
			if (POUR == UTILS.POUR_AJOUTER) {
				if (getFournisseurCRUD().ajouter(getInsc())) {
					Notifier.INSTANCE.notifySuccess(G.fait_, G.fournisseur_a_ete_ajoute);
 
					refreshListe();

				} else {
					Notifier.INSTANCE.notifyError(G.un_erreur, G.il_y_a_un_erreur);
				}
			} else if (POUR == UTILS.POUR_MODIFIER) {
				if (getFournisseurCRUD().modifier(getInsc())) {
					Notifier.INSTANCE.notifySuccess(G.fait_, G.a_ete_modifie);

				} else {
					Notifier.INSTANCE.notifyError(G.un_erreur, G.il_y_a_un_erreur);
					refreshListe();
				}
			}

		}
	}

	public abstract void refreshListe();

	private Fournisseur getInsc() {

		// TODO Auto-generated method stub
		fournisseur = formulaireFournisseurController.setElement(fournisseur);

		return fournisseur;
	}

	private boolean isValide() {
		// TODO Auto-generated method stub
		return true;
	}

	@FXML
	void annuler(ActionEvent event) {

	}

	public void pourModifier() {
		POUR = UTILS.POUR_MODIFIER;

	}
}
