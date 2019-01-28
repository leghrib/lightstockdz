package gererDepensePane.gererElementPane;

import java.net.URL;
import java.util.ResourceBundle;

import CRUD.ClientCRUD;
import Modele.Client;
import UTILS.JfxUtils;
import UTILS.UTILS;
import UTILS.Traduction.G;
import eu.hansolo.enzo.notification.Notification.Notifier;
import gererClientPane.InfosGeneralePane.formulaireElevePrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class GererDepensePaneController extends StackPane implements Initializable {
	@FXML
	private Tab tabInfosGenerale;
 
	@FXML
	private javafx.scene.control.Button btnAnnuler;
	@FXML
	private javafx.scene.control.Button btnValider;

	private formulaireElevePrincipalController formulaireElevePrincipalController;

public Client Client = new Client();
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

	public abstract ClientCRUD getInscCrud();

	private void setStyle(Tab tab) {
		tab.setStyle("-fx-border-color:black;");
		tab.setStyle("-fx-font-size:16;");
		// tab.setStyle("-fx-font-style:gras;");
		tab.setGraphic(new Label());
		tab.getGraphic().setStyle("-fx-text-fill: #c4d8de;");
	}

	public abstract void refresh(Client insc2);

	public GererDepensePaneController() {

		JfxUtils.setAll("/gererDepensePane/gererElementPane/gererElementPane.fxml", this);
		formulaireElevePrincipalController = new formulaireElevePrincipalController() {
			@Override
			public void refresh() {
				GererDepensePaneController.this.refresh(getInsc());
			}
		};
	 
		tabInfosGenerale.setContent(formulaireElevePrincipalController);
		 
	}

	public void chargerInsc(Client insc) {
		this.Client = insc;
		formulaireElevePrincipalController.chargerElement(insc);
		 
 
	}

	public void pourAjouter() {
		formulaireElevePrincipalController.pourAjouter();
		
		POUR = UTILS.POUR_AJOUTER;
		this.Client = new Client();

	}

	@FXML
	void valider(ActionEvent event) {

		if (isValide()) {
			if (POUR == UTILS.POUR_AJOUTER) {
				if (getInscCrud().ajouter(getInsc())) {
					Notifier.INSTANCE.notifySuccess(G.fait_, G.client_a_ete_ajoute);

					 
					refreshListe();

				} else {
					Notifier.INSTANCE.notifyError(G.un_erreur, G.il_y_a_un_erreur);
				}
			} else if (POUR == UTILS.POUR_MODIFIER) {
				if (getInscCrud().modifier(getInsc())) {

					Notifier.INSTANCE.notifySuccess(G.fait_, G.a_ete_modifie);

				} else {
					


					Notifier.INSTANCE.notifyError(G.un_erreur, G.il_y_a_un_erreur);
					refreshListe();
				}
			}

		}
	}

	public abstract void refreshListe();

	private Client getInsc() {

		// TODO Auto-generated method stub
		Client = formulaireElevePrincipalController.setElement(Client);
		
 		return Client;
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
