package gererCmdFPane.gererElementPane;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;

import CRUD.CmdfCRUD;
 import Modele.Cmdf;
import UTILS.JfxUtils;
import UTILS.UTILS;
import gererCmdFPane.InfosGeneralePane.formulaireCmdFController;
import impression.impressionArticleLabels;
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

public abstract class GererCmdFPaneController extends StackPane implements Initializable {

	@FXML
	private Button btnAnnuler;
	@FXML
	private Button btnValider;
	@FXML
	private Button btnImprimer;

	private formulaireCmdFController formulairePrincipalController;

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

	public abstract CmdfCRUD getCmdCRUD();

	private void setStyle(Tab tab) {
		tab.setStyle("-fx-border-color:black;");
		tab.setStyle("-fx-font-size:16;");
		// tab.setStyle("-fx-font-style:gras;");
		tab.setGraphic(new Label());
		tab.getGraphic().setStyle("-fx-text-fill: #c4d8de;");
	}

	public abstract void refresh(Cmdf e);

	public abstract Session getHibernateSession();

	public GererCmdFPaneController() {

		JfxUtils.setAll("/gererCmdFPane/gererElementPane/gererCmdFPane.fxml", this);
		formulairePrincipalController = new formulaireCmdFController() {
			@Override
			public void refresh() {
				GererCmdFPaneController.this.refresh(getCommande());
			}

			@Override
			public Session getHibernateSession() {

				return GererCmdFPaneController.this.getHibernateSession();
			}
		};

		stackContent.getChildren().add(formulairePrincipalController);

	}

	public void charger(Cmdf e) {

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

	public Cmdf getCommande() {

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
			impressionArticleLabels.imprimerCmdF(getCommande().getIdCmdF());
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void pourModifier() {
		POUR = UTILS.POUR_MODIFIER;

	}
}
