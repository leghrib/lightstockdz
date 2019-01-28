package UTILS.software;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import THIS_APPLICATION.Constantes;
import UTILS.JfxUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class MyCampanyController extends BorderPane implements Initializable {
	@FXML
	private ImageView imageViewFB;
	@FXML
	private Label lblVersion;

	@FXML
	public void visitFacebook(MouseEvent event) {
		try {
			Desktop.getDesktop().browse(new URI("www.facebook.com/lightstockdz"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MyCampanyController() {
		JfxUtils.setAll("/UTILS/software/MyCompany.fxml", this);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblVersion.setText("الاصدار ".concat("v"+Constantes.getVersion()));
	}
}
