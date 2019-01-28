package gererArticlePane.listePane;

import java.net.URL;
import java.util.ResourceBundle;

import CRUD.ArticleCRUD;
import ICON.ICONE;
import Modele.Article;
import UTILS.JfxUtils;
import UTILS.UTILS;
import UTILS.MyCompenents.MyToolTip;
import UTILS.Traduction.G;
import eu.hansolo.enzo.notification.Notification.Notifier;
import gererArticlePane.gererElementPane.GererArticlePaneController;
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

public class ListeArticlePaneController extends StackPane implements Initializable {
	@FXML
	private TableView<Article> table;
	@FXML
	private TableColumn column1;
	@FXML
	private TableColumn column2;
	@FXML
	private TableColumn column3;

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
	public int compteurNewElements = 0;

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
		chargerElement(table.getSelectionModel().getSelectedItem());

	}

	@FXML
	void ajouter(ActionEvent event) {
		AjouterAjouterTab();
	}

	@FXML
	void supprimer(ActionEvent event) {

		Article article = table.getSelectionModel().getSelectedItem();
		if (article == null) {
			UTILS.runAlert(AlertType.ERROR, "تنبيه", "اختر العنصر التي تريد حذفه", "اختر العنصر التي تريد حذفه");
			return;
		}
		Alert alert = UTILS.runAlert(AlertType.CONFIRMATION, "تنبيه", "هل انت متأكد من الحذف ؟",
				"هل انت متأكد من الحذف ؟");
		if (alert.getResult() == ButtonType.OK) {
			boolean supprime = articleCRUD.supprimer(article.getIdArticle());
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
	private ArticleCRUD articleCRUD = new ArticleCRUD();

	// Event Listener on Button[#btnRechercher].onAction
	@FXML
	public void rechercher(ActionEvent event) {

		remplirTable();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
		tabAjouter.setClosable(false);
		column1.setCellValueFactory(new PropertyValueFactory<Article,Integer>("idArticle"));
		column2.setCellValueFactory(new PropertyValueFactory<Article,String>("ref"));
		column3.setCellValueFactory(new PropertyValueFactory<Article,Double>("qte"));

		column1.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
		column2.prefWidthProperty().bind(table.widthProperty().multiply(0.7));
		column3.prefWidthProperty().bind(table.widthProperty().multiply(0.2));

		UTILS.setColumnTextFieldConverter(UTILS.getConverterQuantite(), column3);


		table.setRowFactory(tv -> {
			TableRow<Article> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					chargerSelected();

				}
			});
			return row;
		});
		// table.getSelectionModel().getSelectedItems().addListener(multiSelection);

		Label label = new Label("لا يوجد أي تعيين");
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

		Article a = table.getSelectionModel().getSelectedItem();
		chargerElement(a);
	}

	protected void AjouterAjouterTab() {
		compteurNewElements++;
		Tab tab = new Tab();
		tab.setClosable(true);
		tab.setText(" تعيين  جديد " + "(" + compteurNewElements + "(");

		GererArticlePaneController gererArticlePaneController = new GererArticlePaneController() {

			@Override
			public void refresh(Article c) {
				if (c.getRef().isEmpty()) {
					tab.setText("تعيين  جديد ");

				} else {
					tab.setText(c.getRef());
				}
			}

			@Override
			public ArticleCRUD getArticleCRUD() {
				// TODO Auto-generated method stub
				return articleCRUD;
			}

			@Override
			public void refreshListe() {

				ListeArticlePaneController.this.remplirTable();
			}
		};
		gererArticlePaneController.pourAjouter();
		tab.setContent(gererArticlePaneController);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);

	}

	protected void chargerElement(Article a) {

		if (exist(a)) {
			focusIn(a);
			return;
		}
		Tab tab = new Tab();

		GererArticlePaneController root = new GererArticlePaneController() {

			@Override
			public ArticleCRUD getArticleCRUD() {
				return articleCRUD;
			}
			@Override
			public void refresh(Article e) {
				tab.setText(e.getRef());
				
			}
			 

			 
			@Override
			public void refreshListe() {
				ListeArticlePaneController.this.remplirTable();

			}
		};
		root.pourModifier();
		root.chargerInsc(a);

		tab.setClosable(true);
		tab.setText(a.getRef());

		tab.setContent(root);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);

	}

	private void focusIn(Article a) {
		System.out.println("fooooooooooooooooooooooooocus");
		ObservableList<Tab> list = tabPane.getTabs();
		for (Tab tab : list) {
			if (tab.getContent() instanceof GererArticlePaneController) {
				GererArticlePaneController controller = (GererArticlePaneController) tab.getContent();

				if (controller.Article.getIdArticle() == a.getIdArticle()) {
					tabPane.getSelectionModel().select(tab);
				}
			}

		}

	}

	private boolean exist(Article a) {
		ObservableList<Tab> list = tabPane.getTabs();
		for (Tab tab : list) {
			if (tab.getContent() instanceof GererArticlePaneController) {
				GererArticlePaneController controller = (GererArticlePaneController) tab.getContent();

				try {
					if (controller.Article.getIdArticle() == a.getIdArticle()) {
						return true;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
		return false;
	}

	public ListeArticlePaneController() {
		JfxUtils.setAll("ListePane.fxml", this);
		remplirTable();
	}

	private void remplirTable() {
		int deleteFilter = btnCorbeille.isSelected() ? UTILS.DELETED_ONLY : UTILS.NON_DELETED_ONLY;
		table.setItems(articleCRUD.getCollection(txtRecherche.getText(), deleteFilter));

	}

	private void addToolTips() {

		btnAjouter.setTooltip(new MyToolTip("اضافة عنصر جديد"));
		btnModifier.setTooltip(new MyToolTip("تعديل السلعة"));
		btnSupprimer.setTooltip(new MyToolTip("حذف سلعة"));
		btnImprimer.setTooltip(new MyToolTip("طباعة"));
		btnRechercher.setTooltip(new MyToolTip("بحث"));
		btnCorbeille.setTooltip(new MyToolTip("اظهار السلع المحذوفين"));
	}
}
