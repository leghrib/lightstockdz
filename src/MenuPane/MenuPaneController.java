package MenuPane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuPaneController extends MenuBar implements Initializable {

	@FXML
	private MenuItem menuItemFilieres;

	@FXML
	private MenuItem menuItemExtraitDeDonnees;

	@FXML
	private Menu MenuApropos;

	@FXML
	private MenuItem menuItemSauvegarderLesDonnees;

	@FXML
	private MenuItem menuItemDroitAccee;
	@FXML
	private MenuItem menuItemEleve;

	@FXML
	private Menu menuRapport;

	@FXML
	private Menu menuEmploi;

	@FXML
	private MenuItem menuItemNumeroterEleves;

	@FXML
	private MenuItem menuItemAffectationEleveClasse;

	@FXML
	private Menu menuMatiere;

	@FXML
	private MenuItem menuItemReparationDeDonnees1;

	@FXML
	private MenuItem menuItemEmploye;

	@FXML
	private MenuItem menuItemStatAcademique;

	@FXML
	private MenuItem menuItemChoisirImprimantes;

	@FXML
	private MenuItem menuItemRGF;

	@FXML
	private MenuItem menuItemPVDeliberation;

	@FXML
	private MenuItem menuItemPiramideAgeEleves;

	@FXML
	private MenuItem menuItemOrientation;

	@FXML
	private MenuItem menuItemMatiere;

	@FXML
	private Menu menuFichier;

	@FXML
	private MenuItem menuItemApropos;

	@FXML
	private MenuItem menuItemRapportQuotidien;

	@FXML
	private MenuItem menuItemChangerMotDePasse;

	@FXML
	private Menu menuEleve;

	@FXML
	private MenuItem menuItemPapierPresentQuotidien;

	@FXML
	private MenuItem menuItemFormulaireEnseignant;

	@FXML
	private Menu menuStatistiques;

	@FXML
	private MenuItem menuItemExit;

	@FXML
	private MenuItem menuItemInformationEtablissement;

	@FXML
	private MenuItem menuItemSelonEnseignant;

	@FXML
	private MenuItem menuItemOptions;

	@FXML
	private MenuItem menuItem;

	@FXML
	private MenuItem menuItemMatierePourOrientation;

	@FXML
	private MenuItem menuItemElevePresents;

	@FXML
	private Menu menuEmploye;

	@FXML
	private MenuItem menuItemNotesEleve;

	@FXML
	private Menu menuImprimes;

	@FXML
	private Menu menuDeliberation;

	@FXML
	private MenuItem menuItemFormulaireEleve;

	@FXML
	private MenuItem menuItemReparationDeDonnes;

	@FXML
	private Menu MenuPrametrages;

	@FXML
	private MenuItem menuItemEmploiSelonClasse;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

//		menuItemFilieres.setOnAction(e -> JfxUtils.show(new ListeFiliereController()));
////		menuItemEmploye.setOnAction(e -> JfxUtils.show(new GererEmployeWindowController()));
//		menuItemMatiere.setOnAction(e -> JfxUtils.show(new GererMatiereWindowController()));
//		menuItemEleve.setOnAction(e -> JfxUtils.show(new ListeElevePaneController()));
//		menuItemNotesEleve.setOnAction(e -> JfxUtils.show(new GererNoteElevePaneController()));
//		menuItemEmploiSelonClasse.setOnAction(e -> JfxUtils.show(new EmploiTempsPaneController() ));
		
	}

	public MenuPaneController() {
		UTILS.JfxUtils.setAll("/MenuPane/MenuPane.fxml", this);
		setDroitAccees();
	}

	private void setDroitAccees() {

	}

}
