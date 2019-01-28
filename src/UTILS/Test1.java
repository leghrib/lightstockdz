//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UTILS;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test1 extends Application {

	public static Parent parent;

	public static void runParentForTest(Parent parent) {
	Test1.	parent=parent;
		launch(null);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");

		
		Scene scene = new Scene(parent);
		scene.setRoot(parent);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);

	}

}
