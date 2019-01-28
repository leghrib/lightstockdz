//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package Main;

import javafx.application.Application;
import javafx.stage.Stage;

public class loginFrameLightStock extends Application {

	public static void main(String[] args) {
		System.out.println();
		launch(args);
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");

		MainController.createWindow();
		
	}

}
