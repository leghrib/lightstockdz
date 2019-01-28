package UTILS.TagsPane;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import UTILS.JfxUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.util.StringConverter;

public abstract class TagsPaneController<T> extends StackPane implements Initializable {
	@FXML
	private FlowPane flowPaneItems;
	@FXML
	private ComboBox<T> comboAddItem;
	@FXML
	private Button btnAdd;
	private ObservableList<T> items;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// new AutoCompleteComboBoxListener<T>(comboAddItem);
		comboAddItem.setConverter(new StringConverter<T>() {

			@Override
			public String toString(T object) {

				try {
					return getNameItem(object);
				} catch (Exception e) {
					return "null";
				}
			}

			@Override
			public T fromString(String string) {
				return items.stream().filter(ap -> TagsPaneController.this.getNameItem(ap).equals(string)).findFirst()
						.orElse(null);
			}
		});
		remplirItems();
		btnAdd.setOnAction(event -> addTag());
		comboAddItem.setOnKeyReleased(event -> {

			if (event.getCode() == KeyCode.ENTER) {
				addTag();
			}

		});
	}

	private void remplirItems() {
		items = getItems();
		comboAddItem.setItems(items);
	}

	private void printItems() {

		ObservableList<T> list = getItems();
		for (T t : list) {

		}
	}

	public abstract ObservableList<T> getItems();

	private void addTag() {

		String text = comboAddItem.getEditor().getText().trim();
		if (text.isEmpty())
			return;
		T itemToAdd = null;
		itemToAdd = getItemByName(text);

		if (itemToAdd == null || (!getNameItem(itemToAdd).equals(text))) {
			if (!isExistItem(text)) {
				if (itemToAdd == null) {
					// **** adding new items
					// ajouter items
					// refresh
					// ajouter comme tags

					if (requestAddingNewItem(text)) {
						remplirItems();
						itemToAdd = getItemByName(text);

					} else {
						return;
					}

				}
			} else {// exist text item
				itemToAdd = getItemByName(text);

			}
		}

		if (isExistTag(itemToAdd)) {
			return;
		}

		addTagToPane(itemToAdd);

	}

	private void addTagToPane(T itemToAdd) {
		TagLabel<T> label = new TagLabel<T>(itemToAdd, getNameItem(itemToAdd)) {

			@Override
			public void removeMe(TagLabel<T> TagLabel) {
				flowPaneItems.getChildren().remove(TagLabel);

			}
		};
		flowPaneItems.getChildren().add(flowPaneItems.getChildren().size() - 2, label);

	}

	public abstract boolean requestAddingNewItem(String text);

	private T getItemByName(String text) {
		ObservableList<T> items = comboAddItem.getItems();

		for (T t : items) {
			if (text.equals(getNameItem(t))) {
				return t;
			}

		}

		return null;

	}

	public abstract String getNameItem(T t);

	private boolean isExistItem(String text) {
		for (T t : items) {
			if (text == getNameItem(t)) {
				return true;
			}

		}

		return false;

	}

	private boolean isExistTag(T itemToAdd) {
		ObservableList<Node> controls = flowPaneItems.getChildren();
		for (Node node : controls) {
			if (node instanceof TagLabel<?>) {

				// ============
				if (equals(itemToAdd, ((TagLabel<T>) node).item)) {
					return true;
				}
			}
		}

		return false;
	}

	public TagsPaneController() {
		JfxUtils.setAll("/UTILS/TagsPane/TagsPane.fxml", this);
	}

	public abstract boolean equals(T t1, T t2);

	public void chargerItems(ObservableList<T> list) {

		vider();
		for (T item : list) {
			addTagToPane(item);
		}

	}

	public ObservableList<T> gettags() {
		ObservableList<T> items = FXCollections.observableArrayList();

		ObservableList<Node> controls = flowPaneItems.getChildren();
		for (Node node : controls) {
			if (node instanceof TagLabel<?>) {

				// ============
				items.add((((TagLabel<T>) node).item));
			}
		}
		return items;
	}

	public Set<T> getSelectedTagsAsSet() {
		ObservableList<T> list = gettags();
		Set<T> list2 = new HashSet<T>();
		for (T t : list) {
			list2.add(t);
		}
		return list2;
	}

	public void vider() {
		ObservableList<Node> controls = flowPaneItems.getChildren();
		for (Node node : controls) {
			if (node instanceof TagLabel<?>) {

				flowPaneItems.getChildren().remove(node);
			}
		}
	}
}
