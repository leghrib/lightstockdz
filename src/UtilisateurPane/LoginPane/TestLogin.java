//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UtilisateurPane.LoginPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestLogin extends Application {

	public static void main(String[] args) {
		System.out.println();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");

		LoginPaneController root = new LoginPaneController(){
			@Override
			public void logged() {
				// TODO Auto-generated method stub
				
			}
		};
		Scene scene = new Scene(root);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
//		primaryStage.setMaximized(true);

	}

}
