package UTILS.signalerProblemPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

import THIS_APPLICATION.Loaded;
import UTILS.JfxUtils;
import UTILS.Mailer.ServiceMailer;
import eu.hansolo.enzo.notification.Notification.Notifier;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;

public class SignalerProbPaneController extends BorderPane implements Initializable {
	@FXML
	private TextArea txtMessage;
	@FXML
	private Label lblRemarque;
	@FXML
	private HBox hboxNomDirecteur1;
	@FXML
	private Button btnExit;
	@FXML
	private Button btnEnvoyer;

	// Event Listener on Button[#btnExit].onAction
	@FXML
	public void exit(ActionEvent event) {
		JfxUtils.closeStage(btnExit);

	}

	// Event Listener on Button[#btnEnvoyer].onAction
	@FXML
	public void envoyer(ActionEvent event) {

		String from = Loaded.getEtab().getEmail();

		String to = "lightstockdz@gmail.com";
		String msg = txtMessage.getText();
		String subject = "Client|LightStock";

		JfxUtils.getStageFcontrol(btnEnvoyer).hide();
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				boolean sent = new ServiceMailer(Loaded.getEtab().getEmail(), Loaded.getEtab().getPassword()).send(from,
						to, msg, subject, null, null);

				if (sent) {
					Notifier.INSTANCE.notifySuccess("تم ارســال", "تمّ ارسال ملاحظاتك ، شكرا لك");
				} else {
					Notifier.INSTANCE.notifyError("فشل ارسال ملاحظاتك", "ربما أنت غير متصل بالانترنت");

				}
			}
		});
		//

	}

	public SignalerProbPaneController() {

		JfxUtils.setAll("/UTILS/signalerProblemPane/signalerProblemPane.fxml", this);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}
