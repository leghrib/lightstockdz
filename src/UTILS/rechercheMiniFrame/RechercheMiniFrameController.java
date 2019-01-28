package UTILS.rechercheMiniFrame;

import java.net.URL;
import java.util.ResourceBundle;

import UTILS.JfxUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class RechercheMiniFrameController<E> extends BorderPane implements Initializable {
	@FXML
	private ListView<E> listView;
	private Stage primaryStage;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listView.setCellFactory(lv -> new ListCell<E>() {
			@Override
			public void updateItem(E item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setText(null);
				} else {
					setText(getTextFromItem(item));
				}
			}
		});
		listView.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {

				System.out.println("selected index : "+listView.getSelectionModel().getSelectedIndex());
				if (arg0.getCode() == KeyCode.ENTER) {
					returnResult(listView.getSelectionModel().getSelectedItem());

				} else if (arg0.getCode() == KeyCode.UP && listView.getSelectionModel().getSelectedIndex() <0) {
					closeStage();

				}
			}
		});

		getTextRecherche().setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				
				setStageLocalisation();
				
				if (arg0.getCode() == KeyCode.DOWN) {

					listView.requestFocus();
				}else{
					remplirList();
				}
			}
		});

		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()==2){
					returnResult(listView.getSelectionModel().getSelectedItem());

				}

			}
		});
		
		focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				if (!newValue) {
					closeStage();
				}
			}

		});
		
		

	}

	public abstract TextField getTextRecherche();

	public void closeStage() {
		primaryStage.hide();
		//(RechercheMiniFrameController.this).getScene().getWindow().hide();


	}

	public abstract void returnResult(E selectedItem);

	public abstract String getTextFromItem(E item);

	public RechercheMiniFrameController() {
 		JfxUtils.setAll("/UTILS/rechercheMiniFrame/rechercheMiniFrame.fxml", this);
		 
		locateMe(getTextRecherche());
		remplirList();
		
		primaryStage.alwaysOnTopProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				if(!newValue){
					System.out.println("cloosed 4245");
					closeStage();
				}
			}
		});;


	}

	private void locateMe(TextField textField) {
		primaryStage = new Stage();
		Scene scene = new Scene(this);
		scene.setRoot(this);
		primaryStage.setScene(scene);

		primaryStage.initStyle(StageStyle.UNDECORATED);

	}

	protected void remplirList() {

		listView.setItems(getListItems());
	}

	public abstract ObservableList<E> getListItems();

	public void setStageLocalisation() {
		Bounds boundsInScreen = getTextRecherche().localToScreen(getTextRecherche().getBoundsInLocal());

		double x = boundsInScreen.getMinX();
		double y = boundsInScreen.getMinY();
		System.out.println("x= " + x + ", y= " + y);
		primaryStage.setX(x);
		primaryStage.setY(y+getTextRecherche().getHeight());
		primaryStage.setAlwaysOnTop(true);
		primaryStage.show();
		getTextRecherche().requestFocus();

		}

}
