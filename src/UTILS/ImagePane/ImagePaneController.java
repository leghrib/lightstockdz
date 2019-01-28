package UTILS.ImagePane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import ICON.ICONE;
import UTILS.DragAndDropExample;
import UTILS.JfxUtils;
import UTILS.Test1;
import UTILS.CaptureTools.StartFrame;
import UTILS.Parametres.Parameter;
import UTILS.WebCamTool.TakePhotoFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public abstract class ImagePaneController extends StackPane implements Initializable {

	@FXML
	private Button btnCapture;

	@FXML
	private Button btnCamera;

	@FXML
	private ImageView imageView;

	@FXML
	private Button btnParcourir;

	@FXML
	private TextField txtURL;
	@FXML
	private StackPane stackPaneImage;

	@FXML
	private VBox vboxTools;

	@FXML
	void parcourir(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("اختيار صورة");
		File file = chooser.showOpenDialog(this.getScene().getWindow());
		chargerImage(file.getPath());
	}

	@FXML
	void camera(ActionEvent event) {

		new TakePhotoFrame() {

			@Override
			public void returnCapturedPhoto(String urlPhoto) {

				chargerImage(urlPhoto);
			}
		};
	}

	@FXML
	void captureEcran(ActionEvent event) {
		new StartFrame() {

			@Override
			public void chargerImage(String st) {
				ImagePaneController.this.chargerImage(st);
			}
		}.setVisible(true);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnCamera.setGraphic(new ImageView(ICONE.camera));
		btnParcourir.setGraphic(new ImageView(ICONE.browse));
		btnCapture.setGraphic(new ImageView(ICONE.capture));
		btnCamera.setStyle("-fx-background-color: transparent;");
		btnParcourir.setStyle("-fx-background-color: transparent;");
		btnCapture.setStyle("-fx-background-color: transparent;");

		imageView.fitWidthProperty().bind(stackPaneImage.widthProperty());
		imageView.fitHeightProperty().bind(stackPaneImage.heightProperty());
		
		
		
		new DragAndDropExample(stackPaneImage) {
			
			@Override
			public void chargerDroppedImage(String url) {
				ImagePaneController.this.chargerImage(url);
				
			}
		};
		
		 

		stackPaneImage.setOnMouseEntered(e -> showTools());
		stackPaneImage.setOnMouseExited(e -> hideTools());
		vboxTools.setOnMouseEntered(e -> showTools());
		vboxTools.setOnMouseExited(e -> hideTools());
		vboxTools.setVisible(false);

	}

	private void hideTools() {
		// TODO Auto-generated method stub
		vboxTools.setVisible(false);

	}

	private void showTools() {
		// TODO Auto-generated method stub
		vboxTools.setVisible(true);
	}

	public ImagePaneController(Image defaultImage) {

		JfxUtils.setAll("/UTILS/ImagePane/ImagePane.fxml", this);
		chargerImage(defaultImage, "");

	}

	public static void main(String[] args) {
		ImagePaneController controller = new ImagePaneController(ICONE.articleDefault) {
			@Override
			public void changed() {
				// TODO Auto-generated method stub

			}
		};
		Test1.runParentForTest(controller);
	}

	public void chargerImage(String url) {

		File file = createNewFileIn(Parameter.FOLDER_IMAGES, url);

		try {
			chargerImage(new Image(new File(url).toURI().toString(), true), file.getPath());
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private File createNewFileIn(String folderImages, String url) {

		File file = new File(folderImages + "/" + new Date().getTime() + "." + new File(url).getName());
		try {
			file.createNewFile();
			UTILS.FileTools.copyFile(url, file.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return file;
	}

	private void chargerImage(Image image, String url) {
		txtURL.setText(url);
		imageView.setImage(image);
		changed();
	}

	public String getImageURL() {
		// TODO Auto-generated method stub
		return txtURL.getText();
	}

	public abstract void changed();
}
