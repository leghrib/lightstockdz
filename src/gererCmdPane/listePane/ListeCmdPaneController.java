package gererCmdPane.listePane;

import gererCmdPane.gererElementPane.GererCmdPaneController;
import impression.impressionCmd;

import java.net.URL;
import java.util.ResourceBundle;

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
import net.sf.jasperreports.engine.JRException;

import org.hibernate.Session;

import CRUD.CmdCRUD;
import CRUD.NewHibernateUtil;
import ICON.ICONE;
import Modele.Cmd;
import UTILS.JfxUtils;
import UTILS.UTILS;
import UTILS.MyCompenents.MyToolTip;
import UTILS.Traduction.G;
import eu.hansolo.enzo.notification.Notification.Notifier;

public class ListeCmdPaneController extends StackPane implements Initializable {
	@FXML
	private TableView<Cmd> table;
	@FXML
	private TableColumn<Cmd, Integer> column1;
	@FXML
	private TableColumn<Cmd, String> column2;
	@FXML
	private TableColumn<Cmd, Double> column3;

	@FXML
	private TextField txtRecherche;

	@FXML
	private TabPane tabPane;

	@FXML
	private SplitPane splitPane;

	@FXML
	private Tab tabAjouter;

	// ==============

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
	public int compteurNewElements = 0;

	@FXML
	void corbeille(ActionEvent event) {
		remplirTable();
	}

	@FXML
	void imprimer(ActionEvent event) {

		try {
			impressionCmd.imprimerCmd(table.getSelectionModel().getSelectedItem().getIdCmd());
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		chargerElement(table.getSelectionModel().getSelectedItem());

	}

	@FXML
	void ajouter(ActionEvent event) {
		AjouterAjouterTab();
	}

	@FXML
	void supprimer(ActionEvent event) {

		Cmd cmd = table.getSelectionModel().getSelectedItem();
		if (cmd == null) {
			UTILS.runAlert(AlertType.ERROR, "تنبيه", "اختر العنصر التي تريد حذفه", "اختر العنصر التي تريد حذفه");
			return;
		}
		Alert alert = UTILS.runAlert(AlertType.CONFIRMATION, "تنبيه", "هل انت متأكد من الحذف ؟",
				"هل انت متأكد من الحذف ؟");
		if (alert.getResult() == ButtonType.OK) {
			boolean supprime = cmdCRUD.supprimer(cmd.getIdCmd());
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

	private CmdCRUD cmdCRUD = new CmdCRUD(session);

	// Event Listener on Button[#btnRechercher].onAction

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
		tabAjouter.setClosable(false);

		column1.setCellValueFactory(new PropertyValueFactory<Cmd, Integer>("idCmd"));
		column2.setCellValueFactory(new PropertyValueFactory<Cmd, String>("nomClient"));
		column3.setCellValueFactory(new PropertyValueFactory<Cmd, Double>("totalCmd"));

		column1.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
		column2.prefWidthProperty().bind(table.widthProperty().multiply(0.40));
		column3.prefWidthProperty().bind(table.widthProperty().multiply(0.40));

		UTILS.setColumnTextFieldConverter(UTILS.getConverterMoney(), column3);

		table.setRowFactory(tv -> {
			TableRow<Cmd> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					chargerSelected();

				}
			});
			return row;
		});
		// table.getSelectionModel().getSelectedItems().addListener(multiSelection);

		Label label = new Label("لا يوجد أي طلب");
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
		splitPane.setDividerPositions(0.60);

		addToolTips();
	}

	private void chargerSelected() {

		Cmd a = table.getSelectionModel().getSelectedItem();
		chargerElement(a);
	}

	protected void AjouterAjouterTab() {
		compteurNewElements++;
		Tab tab = new Tab();
		tab.setClosable(true);
		tab.setText(" طلب  جديد " + "(" + compteurNewElements + "(");

		GererCmdPaneController gererCmdPaneController = new GererCmdPaneController() {

			@Override
			public void refresh(Cmd c) {
				if (c.getIdCmd() == null || c.getIdCmd() == 0) {
					tab.setText("طلب  جديد ");

				} else {
					tab.setText(c.getIdCmd() + "");
				}
			}

			@Override
			public CmdCRUD getCmdCRUD() {
				// TODO Auto-generated method stub
				return cmdCRUD;
			}

			@Override
			public void refreshListe() {

				ListeCmdPaneController.this.remplirTable();
			}

			@Override
			public Session getHibernateSession() {
				// TODO Auto-generated method stub
				return session;
			}
		};
		gererCmdPaneController.pourAjouter();
		tab.setContent(gererCmdPaneController);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);

	}

	protected void chargerElement(Cmd a) {

		if (exist(a)) {
			focusIn(a);
			return;
		}
		Tab tab = new Tab();

		GererCmdPaneController root = new GererCmdPaneController() {

			@Override
			public CmdCRUD getCmdCRUD() {
				return cmdCRUD;
			}

			@Override
			public void refresh(Cmd e) {
				tab.setText(e.getIdCmd() + "");

			}

			@Override
			public void refreshListe() {
				ListeCmdPaneController.this.remplirTable();

			}

			@Override
			public Session getHibernateSession() {
				// TODO Auto-generated method stub
				return session;
			}
		};
		root.pourModifier();
		root.charger(a);

		tab.setClosable(true);
		tab.setText(a.getIdCmd() + "");

		tab.setContent(root);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);

	}

	private void focusIn(Cmd c) {
		System.out.println("fooooooooooooooooooooooooocus");
		ObservableList<Tab> list = tabPane.getTabs();
		for (Tab tab : list) {
			if (tab.getContent() instanceof GererCmdPaneController) {
				GererCmdPaneController controller = (GererCmdPaneController) tab.getContent();

				if (controller.getCommande().getIdCmd() == c.getIdCmd()) {
					tabPane.getSelectionModel().select(tab);
				}
			}

		}

	}

	private boolean exist(Cmd c) {
		ObservableList<Tab> list = tabPane.getTabs();
		for (Tab tab : list) {
			if (tab.getContent() instanceof GererCmdPaneController) {
				GererCmdPaneController controller = (GererCmdPaneController) tab.getContent();

				try {
					if (controller.getCommande().getIdCmd() == c.getIdCmd()) {
						return true;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		return false;
	}

	public ListeCmdPaneController() {
		JfxUtils.setAll("ListePane.fxml", this);
		remplirTable();
	}

	private void remplirTable() {
		int deleteFilter = btnCorbeille.isSelected() ? UTILS.DELETED_ONLY : UTILS.NON_DELETED_ONLY;
		table.setItems(cmdCRUD.getCollection(txtRecherche.getText(), deleteFilter));
		table.refresh();

	}

	private void addToolTips() {

		btnAjouter.setTooltip(new MyToolTip("طلب جديد"));
		btnSupprimer.setTooltip(new MyToolTip("حذف طلب"));
		btnImprimer.setTooltip(new MyToolTip("طباعة"));
		btnCorbeille.setTooltip(new MyToolTip("اظهار الطلبات المحذوفة"));
	}


}
