//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package gererCmdFPane;

import gererCmdFPane.listePane.ListeCmdFPaneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestListeCmdF extends Application {

	public static void main(String[] args) {
		System.out.println();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");

		ListeCmdFPaneController root = new ListeCmdFPaneController();
		Scene scene = new Scene(root);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);

	}

}
