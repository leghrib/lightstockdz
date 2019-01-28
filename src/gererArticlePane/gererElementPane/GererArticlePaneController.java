package gererArticlePane.gererElementPane;

import java.net.URL;
import java.util.ResourceBundle;

import CRUD.ArticleCRUD;
import Modele.Article;
import UTILS.JfxUtils;
import UTILS.UTILS;
import UTILS.Traduction.G;
import eu.hansolo.enzo.notification.Notification.Notifier;
import gererArticlePane.InfosGeneralePane.formulaireArticlePrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class GererArticlePaneController extends StackPane implements Initializable {
	@FXML
	private Tab tabInfosGenerale;

	@FXML
	private javafx.scene.control.Button btnAnnuler;
	@FXML
	private javafx.scene.control.Button btnValider;

	private formulaireArticlePrincipalController formulairePrincipalController;

	public Article Article = new Article();
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

	public abstract ArticleCRUD getArticleCRUD();

	private void setStyle(Tab tab) {
		tab.setStyle("-fx-border-color:black;");
		tab.setStyle("-fx-font-size:16;");
		// tab.setStyle("-fx-font-style:gras;");
		tab.setGraphic(new Label());
		tab.getGraphic().setStyle("-fx-text-fill: #c4d8de;");
	}

	public abstract void refresh(Article e);

	public GererArticlePaneController() {

		JfxUtils.setAll("/gererArticlePane/gererElementPane/gererArticlePane.fxml", this);
		formulairePrincipalController = new formulaireArticlePrincipalController() {
			@Override
			public void refresh() {
				GererArticlePaneController.this.refresh(getElement());
			}
		};

		tabInfosGenerale.setContent(formulairePrincipalController);

	}

	public void chargerInsc(Article e) {
		this.Article = e;
		formulairePrincipalController.chargerElement(e);

	}

	public void pourAjouter() {
		formulairePrincipalController.pourAjouter();

		POUR = UTILS.POUR_AJOUTER;
		this.Article = new Article();

	}

	@FXML
	void valider(ActionEvent event) {

		if (isValide()) {
			if (POUR == UTILS.POUR_AJOUTER) {
				if (getArticleCRUD().ajouter(getElement())) {
					Notifier.INSTANCE.notifySuccess(G.fait_, G.article_a_ete_ajoute);

					refreshListe();

				} else {
					Notifier.INSTANCE.notifyError(G.un_erreur, G.il_y_a_un_erreur);
				}
			} else if (POUR == UTILS.POUR_MODIFIER) {

				if (getArticleCRUD().modifier(getElement())) {
					// JfxUtils.getStageFcontrol(btnAnnuler).show();
					// Notifier.setPopupLocation(JfxUtils.getStageFcontrol(btnAnnuler),
					// Pos.TOP_CENTER);
					Notifier.INSTANCE.notifySuccess(G.fait_, G.a_ete_modifie);
				} else {
					Notifier.INSTANCE.notifyError(G.un_erreur, G.il_y_a_un_erreur);

					refreshListe();
				}
			}

		}
	}

	public abstract void refreshListe();

	private Article getElement() {

		// TODO Auto-generated method stub
		Article = formulairePrincipalController.setElement(Article);

		return Article;
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
