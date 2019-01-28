package MenuPane.GridMenuPane;

import java.net.URL;
import java.util.ResourceBundle;

import ICON.ICONE;
import Main.MainController;
import MenuPane.GridMenuPane.AbstractMenuItem.AbstractItem;
import UTILS.JfxUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MyMenuPaneController extends BorderPane implements Initializable {
	@FXML
	private Button btnRetour;
	@FXML
	private Button btnTest;
	@FXML
	private StackPane stackPaneGrid;
	private ObservableList<AbstractItem> menuSequence = FXCollections.observableArrayList();
	@FXML
	private HBox hyperLinkHbox;
	private MainController mainController;

	// Event Listener on Button[#btnRetour].onAction
	@FXML
	public void retour(ActionEvent event) {

		try {
			((Hyperlink) hyperLinkHbox.getChildren().get(hyperLinkHbox.getChildren().size() - 2)).fire();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnTest.setGraphic(new ImageView(ICONE.home24));
		btnTest.setStyle("-fx-background-color: transparent;");
		btnRetour.setGraphic(new ImageView(ICONE.back24));
		btnRetour.setStyle("-fx-background-color: transparent;");

		addGridMenu();
		btnTest.setOnAction(e -> {
			((Hyperlink) hyperLinkHbox.getChildren().get(0)).fire();
		});
	}

	private void addGridMenu() {
		addSubMenu(MenuData.getPrincipaleMenu(mainController));

	}

	private void addSubMenu(AbstractItem item) {
		GridSubMenuPaneController subMenu = new GridSubMenuPaneController(item) {
			@Override
			public void showSubMenu(AbstractItem item, GridSubMenuPaneController gridSubMenuPaneController) {

				MyMenuPaneController.this.addSubMenu(item);

			}
		};
		stackPaneGrid.getChildren().clear();
		stackPaneGrid.getChildren().add(subMenu);
		menuSequence.add(item);
		loadHyperLinks();
	}

	private void loadHyperLinks() {
		hyperLinkHbox.getChildren().clear();
		for (AbstractItem abstractItem : menuSequence) {
			Hyperlink hyperlink = new Hyperlink(abstractItem.getTitle());
			hyperlink.setOnAction(al -> {

				menuSequence.remove(hyperLinkHbox.getChildren().indexOf((Hyperlink) al.getSource()),
						menuSequence.size());
				addSubMenu(abstractItem);
				System.out.println("action");

			});
			hyperLinkHbox.getChildren().add(hyperlink);
		}

	}

	public MyMenuPaneController(MainController mainController) {
		this.mainController = mainController;
		JfxUtils.setAll("/MenuPane/GridMenuPane/MyMenuPane.fxml", this);
	}

}
