package gererFournisseurPane.listePane;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;

import CRUD.FournisseurCRUD;
import CRUD.NewHibernateUtil;
import ICON.ICONE;
import Modele.Fournisseur;
import UTILS.JfxUtils;
import UTILS.MyCompenents.MyToolTip;
import UTILS.Traduction.G;
import eu.hansolo.enzo.notification.Notification.Notifier;
import gererFournisseurPane.gererElementPane.GererFournisseurPaneController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class ListeFournisseurPaneController extends StackPane implements Initializable {
	@FXML
	private TableView<Fournisseur> table;
	@FXML
	private TableColumn column1;
	@FXML
	private TableColumn column2;

	@FXML
	private TextField txtRecherche;
	@FXML
	private Button btnRechercher;

	@FXML
	private TabPane tabPane;

	@FXML
	private SplitPane splitPane;

	@FXML
	private Tab tabAjouter;

	// ==============
	@FXML
	private Button btnModifier;

	@FXML
	private Button btnAjouter;

	@FXML
	private Button btnAnnuler;

	@FXML
	private Button btnImprimer;

	@FXML
	private Button btnExit;

	@FXML
	private Button btnValider;
	@FXML
	private Button btnSupprimer;

	@FXML
	private ToggleButton btnCorbeille;

	// ======================
	public int compteurNew = 0;

	@FXML
	void corbeille(ActionEvent event) {
		remplirTable();
	}

	@FXML
	void imprimer(ActionEvent event) {

	}

	@FXML
	void exit(ActionEvent event) {

	}

	@FXML
	void valider(ActionEvent event) {

	}

	@FXML
	void annuler(ActionEvent event) {

	}

	@FXML
	void modifier(ActionEvent event) {
		chargerFournisseur(table.getSelectionModel().getSelectedItem());

	}

	@FXML
	void ajouter(ActionEvent event) {
		AjouterAjouterTab();
	}

	@FXML
	void supprimer(ActionEvent event) {

		Fournisseur fournisseur = table.getSelectionModel().getSelectedItem();
		if (fournisseur == null) {
			UTILS.UTILS.runAlert(AlertType.ERROR, "تنبيه", "اختر العنصر التي تريد حذفه", "اختر العنصر التي تريد حذفه");
			return;
		}
		Alert alert = UTILS.UTILS.runAlert(AlertType.CONFIRMATION, "تنبيه", "هل انت متأكد من الحذف ؟",
				"هل انت متأكد من الحذف ؟");
		if (alert.getResult() == ButtonType.OK) {
			boolean supprime = fournisseurCRUD.supprimer(fournisseur.getIdFournisseur());
			if (supprime) {
				Notifier.INSTANCE.notifySuccess(G.fait_, G.a_ete_supprime);
			}
		}
		remplirTable();

	}

	// ===========================================
	@FXML
	private StackPane stackFilter;
	// private FiliereNiveauAnneeFilterPaneController FilterFNA;
	// private ClassFilterController classeFilter;

	private Session session = NewHibernateUtil.getSessionfactory().openSession();

	private FournisseurCRUD fournisseurCRUD = new FournisseurCRUD(session);

	// Event Listener on Button[#btnRechercher].onAction
	@FXML
	public void rechercher(ActionEvent event) {

		remplirTable();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
		tabAjouter.setClosable(false);
		column1.setCellValueFactory(new PropertyValueFactory<>("idFournisseur"));
		column2.setCellValueFactory(new PropertyValueFactory<>("raisonSociale"));

		column1.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
		column2.prefWidthProperty().bind(table.widthProperty().multiply(0.8));

		table.setRowFactory(tv -> {
			TableRow<Fournisseur> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					chargerSelected();

				}
			});
			return row;
		});

		Label label = new Label("لا يوجد أي ممون");
		table.setPlaceholder(label);

		tabPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (tabAjouter.isSelected()) {
					System.out.println("tabAjouter Selected ");
					AjouterAjouterTab();
				}
			}
		});

		table.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {

				if (arg0.getCode() == KeyCode.DELETE) {
					supprimer(null);
				} else if (arg0.getCode() == KeyCode.ENTER) {
					chargerSelected();
				} else if (arg0.getCode() == KeyCode.UP) {
					if (table.getSelectionModel().getSelectedIndex() == 0) {
						txtRecherche.requestFocus();
					}
				}
			}
		});

		txtRecherche.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {

				if (arg0.getCode() == KeyCode.DOWN) {
					table.requestFocus();
					table.getSelectionModel().select(0);
				} else {
					remplirTable();
				}
			}
		});

		btnAjouter.setGraphic(new ImageView(ICONE.ajouter32));
		btnImprimer.setGraphic(new ImageView(ICONE.imprimer32));
		btnSupprimer.setGraphic(new ImageView(ICONE.Supprimer32));
		btnModifier.setGraphic(new ImageView(ICONE.modifier16));
		btnRechercher.setGraphic(new ImageView(ICONE.recherche24));
		splitPane.setDividerPositions(0.60);

		addToolTips();
	}

	private void chargerSelected() {

		Fournisseur f = table.getSelectionModel().getSelectedItem();
		chargerFournisseur(f);
	}

	protected void AjouterAjouterTab() {
		compteurNew++;
		Tab tab = new Tab();
		tab.setClosable(true);
		tab.setText(" ممون جديد " + "(" + compteurNew + "(");

		GererFournisseurPaneController gererFournisseurPaneController = new GererFournisseurPaneController() {

			@Override
			public void refresh(Fournisseur c) {
				if ((c.getNomAr() + c.getPrenomAr()).isEmpty()) {
					tab.setText("ممون  جديد ");

				} else {
					tab.setText(c.getRaisonSociale());
				}
			}

			@Override
			public FournisseurCRUD getFournisseurCRUD() {
				// TODO Auto-generated method stub
				return fournisseurCRUD;
			}

			@Override
			public void refreshListe() {

				ListeFournisseurPaneController.this.remplirTable();
			}
		};
		gererFournisseurPaneController.pourAjouter();
		tab.setContent(gererFournisseurPaneController);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);

	}

	protected void chargerFournisseur(Fournisseur c) {

		if (exist(c)) {
			focusIn(c);
			return;
		}
		Tab tab = new Tab();

		GererFournisseurPaneController root = new GererFournisseurPaneController() {

			@Override
			public void refresh(Fournisseur c) {
				tab.setText(c.getRaisonSociale());
			}

			@Override
			public FournisseurCRUD getFournisseurCRUD() {
				return fournisseurCRUD;
			}

			@Override
			public void refreshListe() {
				ListeFournisseurPaneController.this.remplirTable();

			}
		};
		root.pourModifier();
		root.chargerElement(c);

		tab.setClosable(true);
		tab.setText(c.getRaisonSociale());

		tab.setContent(root);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);

	}

	private void focusIn(Fournisseur c) {

		ObservableList<Tab> list = tabPane.getTabs();
		for (Tab tab : list) {
			if (tab.getContent() instanceof GererFournisseurPaneController) {
				GererFournisseurPaneController controller = (GererFournisseurPaneController) tab.getContent();

				if (controller.fournisseur.getIdFournisseur() == c.getIdFournisseur()) {
					tabPane.getSelectionModel().select(tab);
				}
			}

		}

	}

	private boolean exist(Fournisseur f) {
		ObservableList<Tab> list = tabPane.getTabs();
		for (Tab tab : list) {
			if (tab.getContent() instanceof GererFournisseurPaneController) {
				GererFournisseurPaneController controller = (GererFournisseurPaneController) tab.getContent();

				try {
					if (controller.fournisseur.getIdFournisseur() == f.getIdFournisseur()) {
						return true;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		return false;
	}

	public ListeFournisseurPaneController() {
		JfxUtils.setAll("ListePane.fxml", this);
		remplirTable();
	}

	private void remplirTable() {
		int deleteFilter = btnCorbeille.isSelected() ? UTILS.UTILS.DELETED_ONLY : UTILS.UTILS.NON_DELETED_ONLY;
		table.setItems(fournisseurCRUD.getCollection(txtRecherche.getText(), deleteFilter));

	}

	private void addToolTips() {

		btnAjouter.setTooltip(new MyToolTip("اضافة ممون جديد"));
		btnModifier.setTooltip(new MyToolTip("تعديل الممون"));
		btnSupprimer.setTooltip(new MyToolTip("حذف الممون"));
		btnImprimer.setTooltip(new MyToolTip("طباعة"));
		btnRechercher.setTooltip(new MyToolTip("بحث"));
		btnCorbeille.setTooltip(new MyToolTip("اظهار الممونين المحذوفين"));
	}
}
