//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UTILS.TagsPane;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public abstract class TagLabel<T> extends Label {

	public T item;

	public TagLabel(T item,String name) {
		super();
		this.item = item;
		Button closeButton = new Button("X");


		closeButton.setStyle("-fx-font-size: 8pt; -fx-text-fill:black;-fx-background-color: transparent;");
		closeButton.setOnMouseEntered(e -> closeButton.setStyle("-fx-font-size: 8pt; -fx-text-fill:red;-fx-background-color: transparent;"));
		closeButton.setOnMouseExited(e -> closeButton.setStyle("-fx-font-size: 8pt; -fx-text-fill:black;-fx-background-color: transparent;"));

		setGraphic(closeButton);
		setContentDisplay(ContentDisplay.RIGHT);
		setStyle("-fx-border-color: #cccccc; -fx-border-width: 3;-fx-border-radius:3;");

		closeButton.setOnAction(event -> removeMe(TagLabel.this));
		setText(name);
	}

	public abstract void removeMe(TagLabel<T> TagLabel);
}
