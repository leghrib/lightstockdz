//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UTILS.software;

import UTILS.signalerProblemPane.SignalerProbPaneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestCompany extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

	 
		
		
		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");

		MyCampanyController root = new MyCampanyController();
		Scene scene = new Scene(root);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);

	}

}
