package Main;

import java.net.URL;
import java.util.ResourceBundle;

import ICON.ICONE;
import MenuPane.MenuPaneController;
import MenuPane.GridMenuPane.MyMenuPaneController;
import MoteurDeRecherche.MoteurRecherchePaneController;
import ProfilPane.ProfilPaneController;
import UTILS.JfxUtils;
import UTILS.ResizeHelper;
import UTILS.UTILS;
import UTILS.Clock.EffectUtilities;
import UTILS.signalerProblemPane.SignalerProbPaneController;
import UTILS.software.MyCampanyController;
import UtilisateurPane.LoginPane.LoginPaneController;
import eu.hansolo.enzo.notification.Notification.Notifier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController extends BorderPane implements Initializable {

	@FXML
	private MenuItem menuItemSignaler;

	@FXML
	private MenuItem menuItemAPropos;

	@FXML
	void Apropos(ActionEvent event) {
		// MyCampanyController conrool = new MyCampanyController();
		MyCampanyController controller = new MyCampanyController();
		Stage stage = JfxUtils.displayIn(controller, stageMain);
		stage.showAndWait();
	}

	@FXML
	void signaler(ActionEvent event) {
		SignalerProbPaneController controller = new SignalerProbPaneController();
		Stage stage = JfxUtils.displayIn(controller, stageMain);
		stage.showAndWait();
	}

	@FXML
	private Button btnBackMenu;
	@FXML
	private MenuButton btnMenuAutre;
	@FXML
	private StackPane stackProfile;
	@FXML
	private StackPane stackPaneMenu;
	@FXML
	private StackPane stackPaneCenter;
	@FXML
	private VBox vboxTools;

	private MenuPaneController menuPaneController;
	private ProfilPaneController profilPaneController;
	// private WebEngine webEngine;
	private MyMenuPaneController menu;
	public Stage stageMain;
	public final static String lblStyle1 = "-fx-text-fill : #000000; -fx-background-color : transparent ";
	public final static String lblStyle2 = "-fx-text-fill : #ffffff; -fx-background-color : red ";
	public final static String lblStyle3 = "-fx-text-fill : #ffffff; -fx-background-color : #aaaaaa ";

	MoteurRecherchePaneController MDR = new MoteurRecherchePaneController();
	@FXML
	private StackPane stackPaneMove;
	@FXML
	private Button btnMinimize;

	@FXML
	private Button btnClose;

	public int showing;
	public final static int showing_menu = 0;
	public final static int showing_MDR = 1;
	public final static int showing_Login = 2;

	@FXML
	void close(ActionEvent event) {

		if (UTILS.runAlertAskForExit(stageMain) == ButtonType.OK) {

			System.exit(0);
		}
	}

	@FXML
	void backMenu(ActionEvent event) {

		if (showing == showing_MDR) {
			showMenu();
		} else if (showing == showing_menu) {
			showMoteurRecherche();
		}

	}

	@FXML
	private MenuButton btnStat;
	@FXML
	private Button btnexitFullScreen;
	private LoginPaneController loginPane;

	@FXML
	void exitFullScreen(ActionEvent event) {

		if (stageMain.isFullScreen()) {
			JfxUtils.displayPrimaryScreen(stageMain);
			stageMain.setFullScreen(false);

		} else {
			stageMain.setFullScreen(true);

		}

	}

	@FXML
	void minimize(ActionEvent event) {
		((Stage) ((Button) event.getSource()).getScene().getWindow()).setIconified(true);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		stageMain.setFullScreenExitHint("للخروج من وضعية الشاشة الكاملة اضغط على echap");

		menuPaneController = new MenuPaneController();

		EffectUtilities.makeDraggable(stageMain, stackPaneMove);

		btnClose.setStyle(lblStyle1);
		btnMinimize.setStyle(lblStyle1);
		btnexitFullScreen.setStyle(lblStyle1);
		btnexitFullScreen.setGraphic(new ImageView(ICONE.fullscreen16));
		btnStat.setGraphic(new ImageView(ICONE.chart32));
		btnMenuAutre.setGraphic(new ImageView(ICONE.autre32));
		btnexitFullScreen.setOnMouseEntered(e -> {
			btnexitFullScreen.setStyle(lblStyle3);

		});
		btnexitFullScreen.setOnMouseExited(e -> {
			btnexitFullScreen.setStyle(lblStyle1);
		});
		btnClose.setOnMouseEntered(e -> {
			btnClose.setStyle(lblStyle2);

		});
		btnClose.setOnMouseExited(e -> {
			btnClose.setStyle(lblStyle1);
		});
		btnMinimize.setOnMouseEntered(e -> {
			btnMinimize.setStyle(lblStyle3);

		});
		btnMinimize.setOnMouseExited(e -> {
			btnMinimize.setStyle(lblStyle1);
		});

		btnBackMenu.setStyle(lblStyle1);
		btnBackMenu.setGraphic(new ImageView(ICONE.menu24));

		setupLogin();
	}

	public MainController(Stage stage) {
		this.stageMain = stage;

		JfxUtils.setAll("/Main/Main.fxml", this);
	}

	private void setTransparentStage() {
		stageMain.initStyle(StageStyle.TRANSPARENT);
		stageMain.getScene().setFill(Color.TRANSPARENT);
		stageMain.setOpacity(0.85);
	}

	public static void createWindow() {
		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");

		Stage primaryStage = new Stage();
		primaryStage.initStyle(StageStyle.UNDECORATED);

		MainController root = new MainController(primaryStage);

		Scene scene = new Scene(root);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		// primaryStage.setMaximized(true);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		JfxUtils.displayPrimaryScreen(primaryStage);
		ResizeHelper.addResizeListener(primaryStage);

		//

		//
		 primaryStage.getIcons().add(ICON.ICONE.lightStock320);
		primaryStage.show();
		loadConfig();
	}

	private static void loadConfig() {
		Notifier.INSTANCE.setPopupLocation(null, Pos.TOP_LEFT);

	}

	public void showMoteurRecherche() {
		showing = showing_MDR;
		btnBackMenu.setGraphic(new ImageView(ICONE.menu24));
		stackPaneCenter.getChildren().clear();
		stackPaneCenter.getChildren().add(MDR);
	}

	public void setupLogin() {
		loginPane = new LoginPaneController() {
			@Override
			public void logged() {

				setupLogged();

			}

		};

		showing = showing_Login;
		stackPaneCenter.getChildren().clear();
		stackPaneCenter.getChildren().add(loginPane);
		vboxTools.getChildren().remove(btnBackMenu);
	}

	protected void setupLogged() {

		stackPaneMenu.getChildren().add(menuPaneController);
		profilPaneController = new ProfilPaneController();
		stackProfile.getChildren().add(profilPaneController);
		profilPaneController.setUtilisateur(LoginPaneController.utilisateur);

		// webEngine=webView.getEngine();
		// webEngine.load("http://www.facebook.com");
		menu = new MyMenuPaneController(this);
		showMenu();
		vboxTools.getChildren().add(1, btnBackMenu);
		btnBackMenu.setVisible(true);

	}

	public void showMenu() {
		showing = showing_menu;

		btnBackMenu.setGraphic(new ImageView(ICONE.recherche24));

		stackPaneCenter.getChildren().clear();
		stackPaneCenter.getChildren().add(menu);
	}

}
