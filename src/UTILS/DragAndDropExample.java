package UTILS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class DragAndDropExample  {

	ImageView imageView;
	StackPane contentPane;


	public DragAndDropExample(StackPane pane) {
		this.contentPane = pane;
		start();
		// TODO Auto-generated constructor stub
	}

	public void start() {

		contentPane.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				// TODO Auto-generated method stub

				mouseDragOver(event);

			}

		});

		contentPane.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				// TODO Auto-generated method stub
				mouseDragDropped(event);

			}

		});

		contentPane.setOnDragExited(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				// TODO Auto-generated method stub

				contentPane.setStyle("-fx-border-color: #C6C6C6;");

			}

		});

	}

	public abstract void chargerDroppedImage(String url);

	private void mouseDragDropped(final DragEvent e) {
		final Dragboard db = e.getDragboard();
		boolean success = false;
		if (db.hasFiles()) {
			success = true;
			// Only get the first file from the list
			final File file = db.getFiles().get(0);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					 

					chargerDroppedImage(file.getAbsolutePath());
				}
			});
		}
		e.setDropCompleted(success);
		e.consume();
	}

	private void mouseDragOver(final DragEvent e) {
		final Dragboard db = e.getDragboard();

		final boolean isAccepted = db.getFiles().get(0).getName().toLowerCase().endsWith(".png")
				|| db.getFiles().get(0).getName().toLowerCase().endsWith(".jpeg")
				|| db.getFiles().get(0).getName().toLowerCase().endsWith(".jpg");

		if (db.hasFiles()) {
			if (isAccepted) {
				contentPane.setStyle("-fx-border-color: red;" + "-fx-border-width: 5;"
						+ "-fx-background-color: #C6C6C6;" + "-fx-border-style: solid;");
				e.acceptTransferModes(TransferMode.COPY);
			}
		} else {
			e.consume();
		}
	}

}