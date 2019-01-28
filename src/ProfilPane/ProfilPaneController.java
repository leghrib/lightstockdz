package ProfilPane;

import java.net.URL;
import java.util.ResourceBundle;

import Modele.Utilisateur;
import UTILS.JfxUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class ProfilPaneController extends StackPane implements Initializable {
	@FXML
	private ImageView imageView;
	@FXML
	private Label lblNomComplete;
	private Utilisateur UTILISATEUR;
    @FXML
    private Label lblPrivilage;

    @FXML
    private Label lblLastConnexion;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public ProfilPaneController() {

		JfxUtils.setAll("/ProfilPane/ProfilPane.fxml", this);
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.UTILISATEUR = utilisateur;
		lblNomComplete.setText(utilisateur.getNom());

	       String url =utilisateur.getImageurl();

	       Image image = new Image("file:"+url);

	       System.out.println("Is loaded: " + image.isError());
		
//		  final Circle clip = new Circle(imageView.getFitHeight()/2, imageView.getFitWidth()/2, 14);
//	        imageView.setClip(clip);
	        
			imageView.setImage(image);


	}
}
