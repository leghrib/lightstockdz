package MenuPane.GridMenuPane;

import java.net.URL;
import java.util.ResourceBundle;

import MenuPane.GridMenuPane.AbstractMenuItem.AbstractItem;
import MenuPane.GridMenuPane.MyMenuItem.MyMenuItemController;
import UTILS.JfxUtils;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

public abstract class GridSubMenuPaneController extends FlowPane implements Initializable {

	private AbstractItem menu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public GridSubMenuPaneController(AbstractItem item) {
		this.menu = item;
		JfxUtils.setAll("/MenuPane/GridMenuPane/GridSubMenuPane.fxml", this);
		chargerMenu();

	}

	private void chargerMenu() {

		for (AbstractItem abstractItem : menu.getSubItems()) {

			MyMenuItemController itemController = new MyMenuItemController(abstractItem) {
				@Override
				public void showSubMenu(AbstractItem item) {
					GridSubMenuPaneController.this.showSubMenu(item,GridSubMenuPaneController.this);
					
				}
			};

			this.getChildren().add(itemController);
			JfxUtils.animateEnter(itemController, 1000);

		}

	}

	public abstract void showSubMenu(AbstractItem item, GridSubMenuPaneController gridSubMenuPaneController);

}
