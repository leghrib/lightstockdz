//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package gererCmdPane;

import gererCmdPane.listePane.ListeCmdPaneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestListeCmd extends Application {

	public static void main(String[] args) {
		System.out.println();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");

		ListeCmdPaneController root = new ListeCmdPaneController();
		Scene scene = new Scene(root);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);

	}

}
