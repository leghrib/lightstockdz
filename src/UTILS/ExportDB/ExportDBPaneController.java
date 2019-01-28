package UTILS.ExportDB;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import ICON.ICONE;
import UTILS.FileTools;
import UTILS.JfxUtils;
import UTILS.UTILS;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public abstract class ExportDBPaneController extends BorderPane implements Initializable {
	@FXML
	private Label lblTitle;
	@FXML
	private Label lblMessage;
	@FXML
	private ImageView imageView;
	@FXML
	private Button btnExit;
	@FXML
	private Button btnSave;
	@FXML
	private Text txtObservation;
	@FXML
	private Hyperlink hyperLinkOpenFolder;

	private File generatedFile;

	@FXML
	void OpenFolder(ActionEvent event) {

		UTILS.openFileInFolderExplorer(generatedFile);
	}

	// Event Listener on Button[#btnExit].onAction
	@FXML
	public void exit(ActionEvent event) {

		JfxUtils.closeStage(btnExit);
	}

	// Event Listener on Button[#btnSave].onAction
	@FXML
	public void save(ActionEvent event) {
		DirectoryChooser chooser = new DirectoryChooser();

		chooser.setInitialDirectory(UTILS.getDesktopFileFolder());
		File file = chooser.showDialog(JfxUtils.getStageFcontrol(btnExit));

		generatedFile = new File(file, "DB_LightStock" + new Date().getTime() + ".sql");
		File fileCMD = null;
		try {
			fileCMD = File.createTempFile("CMD_DB_LightStock_" + new Date().getTime(), ".bat");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			generatedFile.createNewFile();
			boolean b = generatedFile.canWrite();
			System.out.println("can writeee ? " + b);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(generatedFile.getAbsolutePath());
		FileTools fileTools = new FileTools(fileCMD.getAbsolutePath());

		String cmd0 = "@echo off";
		String cmd1 = "C:/xampp/mysql/bin/mysqldump.exe  -u root lightStock >" + generatedFile.getAbsolutePath();
		System.out.println(cmd1);
		fileTools.addLineAtFirst(cmd0);
		fileTools.addLineAtLast(cmd1);
		UTILS.openfile(fileCMD);

		fileCMD.deleteOnExit();

		setupAfterSave();

	}

	private void setupAfterSave() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				hyperLinkOpenFolder.setVisible(true);

				for (int i = 0; i < 10; i++) {
					UTILS.sleep(500);
					hyperLinkOpenFolder.setText("عرض النسخة المحفوظة ("+generatedFile.length()/2024+" Ko(");
					System.out.println("ffff");
				}
				lblMessage.setText("تم حفظ نسخة للبيانات");
			}
		});

	}

	public static boolean backupDB(String dbName, String dbUserName, String dbPassword, String path) {

		String executeCmd = "mysqldump -u " + dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName
				+ " -r " + path;
		Process runtimeProcess;
		try {

			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();

			if (processComplete == 0) {
				System.out.println("Backup created successfully");
				return true;
			} else {
				System.out.println("Could not create the backup");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

	public abstract Window getStage();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imageView.setImage(ICONE.flashDisk128);
		hyperLinkOpenFolder.setVisible(false);
	}

	public ExportDBPaneController() {
		JfxUtils.setAll("/UTILS/ExportDB/ExportDBPane.fxml", this);
	}

	public void setupSize() {
		btnExit.getScene().getWindow().setHeight(500);
		btnExit.getScene().getWindow().setWidth(700);
		btnExit.getScene().getWindow().centerOnScreen();
	}
}
