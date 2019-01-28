package MenuPane.GridMenuPane.MyMenuItem;

import java.net.URL;
import java.util.ResourceBundle;

import MenuPane.GridMenuPane.AbstractMenuItem.AbstractItem;
import UTILS.JfxUtils;
import UTILS.UTILS;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class MyMenuItemController extends StackPane implements Initializable {
	@FXML
	private Label lblitem;
	@FXML
	private ImageView imageView;
	public final static String lblStyle1 = "-fx-text-fill : #000000; ";
	public final static String lblStyle2 = "-fx-text-fill : #0a0382; ";

	public final static String style1 = "-fx-border-color : #000000 ; -fx-border-width : 1 ; -fx-background-color : #ffffff ;-fx-border-radius : 6";
	public final static String style2 = "-fx-border-color : #0a0382 ; -fx-border-width : 1 ; -fx-background-color : #effdff ;-fx-border-radius : 6";

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public MyMenuItemController(AbstractItem item) {
		JfxUtils.setAll("/MenuPane/GridMenuPane/MyMenuItem/MyMenuItem.fxml", this);
		lblitem.setText(item.getTitle());

		this.setStyle(style1);
		lblitem.setStyle(lblStyle1);
		this.setOnMouseEntered(e -> {
			setStyle(style2);
			lblitem.setStyle(lblStyle2);
			item.runAnimation(imageView);
			setEffect();
		});
		this.setOnMouseExited(e -> {
			setStyle(style1);
			lblitem.setStyle(lblStyle1);
			removeEffect();
		});
		imageView.setImage(item.getImage());
		setOnMouseClicked(mc -> {
			if (item.getSubItems().size() == 0) {
				UTILS.playSound("autre/sounds/click_04.wav");

				item.EnClick();
			} else {
				showSubMenu(item);
				UTILS.playSound("autre/sounds/click_04.wav");
			}

		});
	}

	private void removeEffect() {

		setEffect(null);
	}

	private void setEffect() {
		Reflection r = new Reflection();
		r.setFraction(0.7);
		setEffect(r);
	}

	public abstract void showSubMenu(AbstractItem item);

}
