package UTILS;
//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RunStage extends Application {

	private Stage primaryStage;

	private Parent root;

	public RunStage(String[] args) {
		super();
 		launch(args);
	}

	public Stage runStage(Parent root) {
		this.root = root;

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		primaryStage.centerOnScreen();
		primaryStage.alwaysOnTopProperty();
		primaryStage.show();
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.setPrimaryStage(primaryStage);
		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");
		System.out.println("ffff");
		

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
}
