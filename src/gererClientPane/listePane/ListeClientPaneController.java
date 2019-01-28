package gererClientPane.listePane;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;

import CRUD.ClientCRUD;
import CRUD.NewHibernateUtil;
import ICON.ICONE;
import Modele.Client;
import UTILS.JfxUtils;
import UTILS.MyCompenents.MyToolTip;
import UTILS.Traduction.G;
import eu.hansolo.enzo.notification.Notification.Notifier;
import gererClientPane.gererElementPane.GererClientPaneController;
import impression.impressionCarteClient;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class ListeClientPaneController extends StackPane implements Initializable {
	@FXML
	private TableView<Client> table;
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
	public int compteurNewEleves = 0;

	@FXML
	void corbeille(ActionEvent event) {
		remplirTable();
	}

	@FXML
	void imprimer(ActionEvent event) {

		try{
			impressionCarteClient.imprimer(table.getSelectionModel().getSelectedItem());
		} catch (Exception e) {
			// TODO: handle exception
		}
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
		chargerEleve(table.getSelectionModel().getSelectedItem());

	}

	@FXML
	void ajouter(ActionEvent event) {
		AjouterAjouterTab();
	}

	@FXML
	void supprimer(ActionEvent event) {

		Client client = table.getSelectionModel().getSelectedItem();
		if (client == null) {
			UTILS.UTILS.runAlert(AlertType.ERROR, "تنبيه", "اختر العنصر التي تريد حذفه", "اختر العنصر التي تريد حذفه");
			return;
		}
		Alert alert = UTILS.UTILS.runAlert(AlertType.CONFIRMATION, "تنبيه", "هل انت متأكد من الحذف ؟",
				"هل انت متأكد من الحذف ؟");
		if (alert.getResult() == ButtonType.OK) {
			boolean supprime = clientCRUD.supprimer(client.getIdClient());
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
	
	private Session session= NewHibernateUtil.getSessionfactory().openSession();

	private ClientCRUD clientCRUD = new ClientCRUD(session);

	// Event Listener on Button[#btnRechercher].onAction
	@FXML
	public void rechercher(ActionEvent event) {

		remplirTable();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
		tabAjouter.setClosable(false);
		column1.setCellValueFactory(new PropertyValueFactory<>("idClient"));
		column2.setCellValueFactory(new PropertyValueFactory<>("raisonSociale"));

		column1.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
		column2.prefWidthProperty().bind(table.widthProperty().multiply(0.8));

		// FilterFNA = new FiliereNiveauAnneeFilterPaneController(true, true,
		// true) {
		//
		// @Override
		// public void filtreFNAChanged() {
		// classeFilter.chargerListClasse();
		// }
		// };
		// FilterFNA.setParametres(false, true, true);
		// FilterFNA.showFilters(true, true, true);
		// stackFilter.getChildren().add(FilterFNA);
		// ListChangeListener<Insc> multiSelection = new
		// ListChangeListener<Insc>() {
		// @Override
		// public void onChanged(ListChangeListener.Change<? extends Insc>
		// changed) {
		// for (Insc eleve : changed.getList()) {
		// chargerEleve(eleve);
		// }
		// }
		// };

		table.setRowFactory(tv -> {
			TableRow<Client> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					chargerSelected();

				}
			});
			return row;
		});
		// table.getSelectionModel().getSelectedItems().addListener(multiSelection);

		Label label = new Label("لا يوجد أي زبون");
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

		Client client = table.getSelectionModel().getSelectedItem();
		chargerEleve(client);
	}

	protected void AjouterAjouterTab() {
		compteurNewEleves++;
		Tab tab = new Tab();
		tab.setClosable(true);
		tab.setText(" زبون جديد " + "(" + compteurNewEleves + "(");

		GererClientPaneController gererElevePaneController = new GererClientPaneController() {

			@Override
			public void refresh(Client c) {
				if ((c.getNomAr() + c.getPrenomAr()).isEmpty()) {
					tab.setText("زبون جديد ");

				} else {
					tab.setText(c.getRaisonSociale());
				}
			}

			@Override
			public ClientCRUD getInscCrud() {
				// TODO Auto-generated method stub
				return clientCRUD;
			}

			@Override
			public void refreshListe() {

				ListeClientPaneController.this.remplirTable();
			}
		};
		gererElevePaneController.pourAjouter();
		tab.setContent(gererElevePaneController);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);

	}

	protected void chargerEleve(Client c) {

		if (exist(c)) {
			focusIn(c);
			return;
		}
		Tab tab = new Tab();

		GererClientPaneController root = new GererClientPaneController() {

			@Override
			public void refresh(Client c) {
				tab.setText(c.getRaisonSociale());
			}

			@Override
			public ClientCRUD getInscCrud() {
				return clientCRUD;
			}

			@Override
			public void refreshListe() {
				ListeClientPaneController.this.remplirTable();

			}
		};
		root.pourModifier();
		root.chargerInsc(c);

		tab.setClosable(true);
		tab.setText(c.getRaisonSociale());

		tab.setContent(root);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);

	}

	private void focusIn(Client c) {
		System.out.println("fooooooooooooooooooooooooocus");
		ObservableList<Tab> list = tabPane.getTabs();
		for (Tab tab : list) {
			if (tab.getContent() instanceof GererClientPaneController) {
				GererClientPaneController controller = (GererClientPaneController) tab.getContent();

				if (controller.Client.getIdClient() == c.getIdClient()) {
					tabPane.getSelectionModel().select(tab);
				}
			}

		}

	}

	private boolean exist(Client insc) {
		ObservableList<Tab> list = tabPane.getTabs();
		for (Tab tab : list) {
			if (tab.getContent() instanceof GererClientPaneController) {
				GererClientPaneController controller = (GererClientPaneController) tab.getContent();

				try {
					if (controller.Client.getIdClient() == insc.getIdClient()) {
						return true;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		return false;
	}

	public ListeClientPaneController() {
		JfxUtils.setAll("ListePane.fxml", this);
		remplirTable();
	}

	private void remplirTable() {
		int deleteFilter = btnCorbeille.isSelected() ? UTILS.UTILS.DELETED_ONLY : UTILS.UTILS.NON_DELETED_ONLY;
		table.setItems(clientCRUD.getCollection(txtRecherche.getText(), deleteFilter));

	}

	private void addToolTips() {

		btnAjouter.setTooltip(new MyToolTip("اضافة زبون جديد"));
		btnModifier.setTooltip(new MyToolTip("تعديل الزبون"));
		btnSupprimer.setTooltip(new MyToolTip("حذف الزبون"));
		btnImprimer.setTooltip(new MyToolTip("طباعة"));
		btnRechercher.setTooltip(new MyToolTip("بحث"));
		btnCorbeille.setTooltip(new MyToolTip("اظهار الزبائن المحذوفين"));
	}
}
