//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package MenuPane.GridMenuPane.AbstractMenuItem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AbstractItem {

	private ObservableList<AbstractItem> subItems = FXCollections.observableArrayList();
	private String title;
	private Image image;

	public final ObservableList<AbstractItem> getSubItems() {
		return subItems;
	}

	public final void setSubItems(ObservableList<AbstractItem> subItems) {
		this.subItems = subItems;
	}

	public AbstractItem(String title, Image image) {
		this.setTitle(title);
		this.setImage(image);

	}

	public void addSubItem(AbstractItem abstractItem) {
		subItems.add(abstractItem);
	}

	public void EnClick() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void runAnimation(ImageView imageView) {

	}

	public void addAllSubItem(AbstractItem... abstractItems) {

		for (AbstractItem abstractItem : abstractItems) {
			addSubItem(abstractItem);
		}
	}

	public void addCollectionSubItem(ObservableList<AbstractItem> abstractItems) {

		for (AbstractItem abstractItem : abstractItems) {
			addSubItem(abstractItem);
		}
	}

}
