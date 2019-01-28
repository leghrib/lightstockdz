//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UTILS.ImagePane;

import ICON.ICONE;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestImagePane extends Application {

	public static void main(String[] args) {
launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");

		ImagePaneController root= new ImagePaneController(ICONE.personneMasculin){
			@Override
			public void changed() {
				// TODO Auto-generated method stub
				
			}
		};
		root.chargerImage("a.jpg");
		Scene scene = new Scene(root);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);

		
		
		
	}

}
