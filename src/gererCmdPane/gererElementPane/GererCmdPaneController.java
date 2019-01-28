package gererCmdPane.gererElementPane;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;

import CRUD.ArticleCRUD;
import CRUD.CmdCRUD;
import Modele.Article;
import Modele.Cmd;
import UTILS.JfxUtils;
import UTILS.UTILS;
import gererArticlePane.InfosGeneralePane.formulaireArticlePrincipalController;
import gererCmdPane.InfosGeneralePane.formulaireCmdController;
import impression.impressionCmd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import net.sf.jasperreports.engine.JRException;

public abstract class GererCmdPaneController extends StackPane implements Initializable {

	@FXML
	private Button btnAnnuler;
	@FXML
	private Button btnValider;
	@FXML
	private Button btnImprimer;

	private formulaireCmdController formulairePrincipalController;

	public int POUR = UTILS.POUR_DEFAULT;

	@FXML
	private StackPane stackContent;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnValider.setGraphic(new ImageView(ICON.ICONE.save32));
		btnAnnuler.setGraphic(new ImageView(ICON.ICONE.exit32));

		setStyleAll();

	}

	private void setStyleAll() {

	}

	public abstract CmdCRUD getCmdCRUD();

	private void setStyle(Tab tab) {
		tab.setStyle("-fx-border-color:black;");
		tab.setStyle("-fx-font-size:16;");
		// tab.setStyle("-fx-font-style:gras;");
		tab.setGraphic(new Label());
		tab.getGraphic().setStyle("-fx-text-fill: #c4d8de;");
	}

	public abstract void refresh(Cmd e);

	public abstract Session getHibernateSession();

	public GererCmdPaneController() {

		JfxUtils.setAll("/gererCmdPane/gererElementPane/gererCmdPane.fxml", this);
		formulairePrincipalController = new formulaireCmdController() {
			@Override
			public void refresh() {
				GererCmdPaneController.this.refresh(getCommande());
			}

			@Override
			public Session getHibernateSession() {

				return GererCmdPaneController.this.getHibernateSession();
			}
		};

		stackContent.getChildren().add(formulairePrincipalController);

	}

	public void charger(Cmd e) {

		formulairePrincipalController.chargerElement(e);

	}

	public void pourAjouter() {
		formulairePrincipalController.pourAjouter();

		POUR = UTILS.POUR_AJOUTER;

	}

	@FXML
	void valider(ActionEvent event) {

		if (isValide()) {
			if (POUR == UTILS.POUR_AJOUTER) {
				if (getCmdCRUD().modifier(getCommande())) {

					JfxUtils.runAlert(AlertType.INFORMATION, "ملاحظة", "تم اضافة الطلب بنجاح", "تم اضافة الطلب بنجاح");
					refreshListe();

				} else {
					JfxUtils.runAlert(AlertType.ERROR, "ملاحظة", "حدث خطأ", "حدث خطأ");
				}
			} else if (POUR == UTILS.POUR_MODIFIER) {
				if (getCmdCRUD().modifier(getCommande())) {

					JfxUtils.runAlert(AlertType.INFORMATION, "ملاحظة", "تم تعديل الطلب بنجاح", "تم تعديل الطلب  بنجاح");
				} else {
					JfxUtils.runAlert(AlertType.ERROR, "ملاحظة", "حدث خطأ", "حدث خطأ");
					refreshListe();
				}
			}

		}
	}

	public abstract void refreshListe();

	public Cmd getCommande() {

		// TODO Auto-generated method stub
		return formulairePrincipalController.getCmd();

	}

	private boolean isValide() {
		// TODO Auto-generated method stub
		return true;
	}

	@FXML
	void annuler(ActionEvent event) {

	}

	@FXML
	void imprimer(ActionEvent event) {
		try {
			impressionCmd.imprimerCmd(getCommande().getIdCmd());
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pourModifier() {
		POUR = UTILS.POUR_MODIFIER;

	}
}
