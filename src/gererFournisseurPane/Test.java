//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package gererFournisseurPane;

import CRUD.ClientCRUD;
import gererClientPane.gererElementPane.GererClientPaneController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

	public static void main(String[] args) {
		System.out.println();
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		System.setProperty("prism.text", "t2k");
		System.setProperty("prism.lcdtext", "false");

		GererClientPaneController root = new GererClientPaneController(){
			 @Override
			public void refreshListe() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void refresh(Modele.Client insc2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public ClientCRUD getInscCrud() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		//root.chargerInsc(new ClientCRUD().getInsc(12));
		Scene scene = new Scene(root);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);

	}

}
