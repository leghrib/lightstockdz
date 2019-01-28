//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UTILS.ExportDB;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class TestMatiere extends Application {

	public static void main(String[] args) {
		System.out.println();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");

		ExportDBPaneController root = new ExportDBPaneController() {
			
			@Override
			public Window getStage() {
				// TODO Auto-generated method stub
				return primaryStage;
			}
		};
		Scene scene = new Scene(root);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);

	}

}
