//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UTILS;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test<T> extends Application {
	T root;

	

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");


		
		Scene scene = new Scene((Parent)root);
		scene.setRoot((Parent)root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);

	}
	public Test(String[] args, T root) {
		this.root = root;
		launch(args);

	}

}
