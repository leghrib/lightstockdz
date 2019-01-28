package UTILS;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import Main.MainController;
import MenuPane.GridMenuPane.GridSubMenuPaneController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class JfxUtils {
	public static String pattern = "dd-MM-yyyy";
	public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

	public static Node loadFxml(String fxml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(JfxUtils.class.getResource(fxml));
			Node root = (Node) loader.load(MainController.class.getResource(fxml).openStream());
			return root;
		} catch (IOException e) {
			throw new IllegalStateException("cannot load FXML screen", e);
		}
	}

	public static Alert runAlert(AlertType alertType, String title, String header, String content) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
		return alert;
	}

	public static void setAll(String path, Object root) {
		FXMLLoader fxmlLoader = new FXMLLoader(root.getClass().getResource(path));
		fxmlLoader.setRoot(root);
		fxmlLoader.setController(root);
		try {
			System.out.println(path);
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public static void animateSlide(GridSubMenuPaneController gridSubMenuPaneController, int i) {

		final Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.setAutoReverse(true);
		final KeyValue kv = new KeyValue(gridSubMenuPaneController.layoutXProperty(), 300, Interpolator.EASE_BOTH);
		final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}

	public static void animateEnter(Node node, int time) {
		ScaleTransition scal = new ScaleTransition(Duration.millis(time), node);
		scal.setFromX(0.7);
		scal.setToX(1);
		scal.setFromY(0.7);
		scal.setToY(1);
		scal.play();
	}

	public static void changeWidth(final TextField txtField, double width) {// its
																			// a
		// Pane
		// or
		// Regions
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(txtField.prefWidthProperty(), txtField.getWidth())),
				new KeyFrame(Duration.millis(300), new KeyValue(txtField.prefWidthProperty(), width)));

		timeline.play();
	}

	public static void show(Parent root) {
		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		scene.setRoot(root);
		primaryStage.setScene(scene);

		primaryStage.centerOnScreen();
		primaryStage.alwaysOnTopProperty();
		primaryStage.showAndWait();
	}

	public static void showMaximazed(Parent root) {
		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);
		primaryStage.alwaysOnTopProperty();
		primaryStage.showAndWait();
	}

	public static ObservableList<String> IntegerToStringObservableList(ObservableList<Integer> list) {
		ObservableList<String> list1 = FXCollections.observableArrayList();
		for (Integer n : list) {
			if (n == null) {
				list1.add("غير منتسب");

			} else {
				list1.add("" + n);

			}
		}
		return list1;
	}

	public static void setTableColumnPrefWProperty(TableView tableview, double... tableValues) {

		int i = 0;
		ObservableList<TableColumn<?, ?>> columns = tableview.getColumns();
		for (TableColumn<?, ?> tableColumn : columns) {
			tableColumn.prefWidthProperty().bind(tableview.widthProperty().multiply(tableValues[i]));

			i++;

		}
	}

	public static void rotateTransition(ImageView imageView) {
		RotateTransition rt = new RotateTransition(Duration.millis(500), imageView);
		rt.setByAngle(90);
		rt.setCycleCount(1);
		rt.setInterpolator(Interpolator.LINEAR);
		rt.play();
	}

	 

	public static Stage displayIn(Parent controller, Window owner) {

		Stage stage = new Stage();

		// populate dialog with controls.
		Scene scene = new Scene(controller);
		stage.setScene(scene);
		stage.initOwner(owner);
		stage.initModality(Modality.WINDOW_MODAL);

		return stage;

	}

	public static void setScreenSize(Stage primaryStage) {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();

		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
	}

	public static void displayPrimaryScreen(Stage stage) {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		// set Stage boundaries to visible bounds of the main screen
		stage.setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		stage.setWidth(primaryScreenBounds.getWidth());
		stage.setHeight(primaryScreenBounds.getHeight());
	}

	public static void closeStage(Control control) {
		((Stage) control.getScene().getWindow()).close();

	}

	public static Stage getStageFcontrol(Control control) {
		// TODO Auto-generated method stub
		return ((Stage) control.getScene().getWindow());
	}

	public static void requestFocusToWindow(TextField textField) {
		// TODO Auto-generated method stub
		((Stage) textField.getScene().getWindow()).setAlwaysOnTop(true);
		;

	}

}