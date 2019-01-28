package gererCmdPane.InfosGeneralePane;

import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.hibernate.Session;

import CRUD.ArticleCRUD;
import CRUD.ClientCRUD;
import CRUD.CmdCRUD;
import Modele.Article;
import Modele.Client;
import Modele.Cmd;
import Modele.Cmdarticle;
import THIS_APPLICATION.Loaded;
import UTILS.ConverterHelper;
import UTILS.JfxUtils;
import UTILS.UTILS;
import UTILS.rechercheMiniFrame.RechercheMiniFrameController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public abstract class formulaireCmdController extends BorderPane implements Initializable {

	@FXML
	private TextField txtNumeroInscription;

	@FXML
	private TextField txtClient;

	@FXML
	private DatePicker chooserDate;

	@FXML
	private TableView<Cmdarticle> table;

	@FXML
	private TableColumn<Cmdarticle, Integer> column1;

	@FXML
	private TableColumn<Cmdarticle, String> column2;

	@FXML
	private TableColumn<Cmdarticle, Double> column3;

	@FXML
	private TableColumn<Cmdarticle, Double> column4;

	@FXML
	private TableColumn<Cmdarticle, Double> column5;

	@FXML
	private TextField txtArticle;

	@FXML
	private TextField txtQuantite;

	@FXML
	private TextField txtPrixUnitaireVente;

	@FXML
	private TextField txtSommePrixVente;

	@FXML
	private Button btnOkArticle;

	@FXML
	private Button btnAnnulerArticle;

	@FXML
	private Label lblMontant;

	@FXML
	private Button btnUp;

	@FXML
	private Button btnDown;

	@FXML
	private Button btnDeleteArticle;

	@FXML
	private Button btnEdit;

	@FXML
	private TextField txtVersement;

	@FXML
	private TextField txtReste;

	@FXML
	private Label lblRemise;

	@FXML
	private TextField txtRemise;

	private Cmd commande = new Cmd();

	@FXML
	void AnnulerArticleCMD(ActionEvent event) {

	}

	@FXML
	void ajouterArticleCMD(ActionEvent event) {

		double quantite = UTILS.getDoubleTXTmoney(txtQuantite);
		double prixVenteFinal = UTILS.getDoubleTXTmoney(txtPrixUnitaireVente);
		boolean exist = false;

		// ----duplicate article , somme de la quantite
		for (Iterator iterator = commande.getCmdarticles().iterator(); iterator.hasNext();) {
			Cmdarticle articlecmd = (Cmdarticle) iterator.next();
			if (article.getIdArticle() == articlecmd.getIdArticle()) {
				exist = true;
				articlecmd.setQte(articlecmd.getQte() + quantite);
			}

		}
		// ---nouveau article
		if (!exist) {
			Cmdarticle cmdArticle;
			cmdArticle = new Cmdarticle(article, commande, quantite, article.getPrixVente(), prixVenteFinal, 0);
			commande.getCmdarticles().add(cmdArticle);

		}

		calculateTotaux();
		updateCommamnde(); //
		chargerCmd();
		new ArticleCRUD(getHibernateSession()).refreshArticleQte(article);
	}

	private void calculateTotaux() {

		double somme = 0;
		for (Cmdarticle cmdarticle : commande.getCmdarticles()) {
			somme += cmdarticle.getPrixVenteTotal();
		}
		lblMontant.setText(UTILS.FMoney(somme));
		commande.setTotalCmd(somme);

		double versement = UTILS.getDoubleTXTmoney(txtVersement);
		double remise = UTILS.getDoubleTXTmoney(txtRemise);
		double reste = somme - remise - versement;

		txtReste.setText(UTILS.FMoney(reste));

	}

	@FXML
	void deleteArticle(ActionEvent event) {

		commande.getCmdarticles()
				.removeIf(ca -> ca.getIdCmdArticle() == table.getSelectionModel().getSelectedItem().getIdCmdArticle());

		calculateTotaux();
		updateCommamnde(); //
		chargerCmd();

	}

	@FXML
	void down(ActionEvent event) {

	}

	@FXML
	void modififer(ActionEvent event) {

	}

	@FXML
	void up(ActionEvent event) {

	}

	private int POUR = UTILS.POUR_AJOUTER;

	private RechercheMiniFrameController<Client> miniFrameClient;

	private RechercheMiniFrameController<Article> miniFrameArticle;

	protected Article article;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		column1.setCellValueFactory(new PropertyValueFactory<Cmdarticle, Integer>("idArticle"));
		column2.setCellValueFactory(new PropertyValueFactory<Cmdarticle, String>("ref"));
		column3.setCellValueFactory(new PropertyValueFactory<Cmdarticle, Double>("prixVente"));
		column4.setCellValueFactory(new PropertyValueFactory<Cmdarticle, Double>("qte"));
		column5.setCellValueFactory(new PropertyValueFactory<Cmdarticle, Double>("prixVenteTotal"));

		column1.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
		column2.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
		column3.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
		column4.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
		column5.prefWidthProperty().bind(table.widthProperty().multiply(0.25));

		UTILS.setColumnTextFieldConverter(UTILS.getConverterMoney(), column3, column5);
		UTILS.setColumnTextFieldConverter(UTILS.getConverterQuantite(), column4);

		showRechercheArticle();
		showRechercheClient();

		Label label = new Label("الطلب فارغ");
		table.setPlaceholder(label);

		txtQuantite.setOnKeyReleased(e -> calculeTotalprixVente());
		txtPrixUnitaireVente.setOnKeyReleased(e -> calculeTotalprixVente());
		txtQuantite.setOnKeyReleased(e -> {
			calculeTotalprixVente();

			if (e.getCode() == KeyCode.ENTER) {
				txtPrixUnitaireVente.requestFocus();
				txtPrixUnitaireVente.selectAll();

			}
		});
		txtPrixUnitaireVente.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				btnOkArticle.requestFocus();
			}
			calculeTotalprixVente();

		});
		btnOkArticle.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER)
				btnOkArticle.fire();
			txtArticle.requestFocus();
			txtArticle.selectAll();

		});

		// ======
		chooserDate.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER)
				txtArticle.requestFocus();

		});

		lblMontant.textProperty().addListener(e -> lblMontant.setTooltip(new Tooltip(lblMontant.getText())));
		lblMontant.setOnMouseClicked(e -> {
			if (e.getClickCount() != 2)
				return;
			txtVersement.setText(
					UTILS.FMoney(UTILS.getDoubleTXTmoney(lblMontant) - UTILS.getDoubleTXTmoney(txtRemise)) + "");
			calculateTotaux();
			updateCommamnde();
		});

		chooserDate.valueProperty().addListener((ov, oldValue, newValue) -> {

			javafx.application.Platform.runLater(new Runnable() {

				@Override
				public void run() {
					updateCommamnde();
				}
			});
		});

		txtRemise.setOnKeyReleased(e -> {
			calculateTotaux();
			updateCommamnde();
		});
		txtVersement.setOnKeyReleased(e -> {
			calculateTotaux();
			updateCommamnde();
		});
	}

	protected void showRechercheClient() {
		miniFrameClient = new RechercheMiniFrameController<Client>() {
			@Override
			public ObservableList<Client> getListItems() {
				return new ClientCRUD(getHibernateSession()).getCollection(txtClient.getText(), UTILS.NON_DELETED_ONLY);
			}

			@Override
			public String getTextFromItem(Client item) {
				return item.getRaisonSociale();
			}

			@Override
			public void returnResult(Client selectedClient) {

				chooserDate.requestFocus();
				commande.setClient(selectedClient);
				miniFrameClient.closeStage();
				updateCommamnde();
				chargerCmd();

			}

			@Override
			public TextField getTextRecherche() {
				return txtClient;
			}
		};

	}

	protected void updateCommamnde() {
		commande.setVersement(UTILS.getDoubleTXTmoney(txtVersement));
		commande.setTotalRemise(UTILS.getDoubleTXTmoney(txtRemise));
		commande.setDate(UTILS.getDateFromchooser(chooserDate));
		new CmdCRUD(getHibernateSession()).modifier(commande);
	}

	protected void showRechercheArticle() {

		miniFrameArticle = new RechercheMiniFrameController<Article>() {
			@Override
			public ObservableList<Article> getListItems() {
				return new ArticleCRUD(getHibernateSession()).getCollection(txtArticle.getText(),
						UTILS.NON_DELETED_ONLY);
			}

			@Override
			public String getTextFromItem(Article item) {
				return item.getRef();
			}

			@Override
			public void returnResult(Article selectedArticle) {
				article = selectedArticle;

				txtArticle.setText(selectedArticle.getRef());
				formulaireCmdController.this.article = selectedArticle;
				miniFrameArticle.closeStage();
				txtQuantite.setText("1");
				txtPrixUnitaireVente.setText(article.getPrixVente() + "");
				txtQuantite.requestFocus();
				txtQuantite.selectAll();
				calculeTotalprixVente();

			}

			@Override
			public TextField getTextRecherche() {
				return txtArticle;
			}
		};

	}

	protected void calculeTotalprixVente() {

		txtSommePrixVente
				.setText(UTILS.getDoubleTXTmoney(txtQuantite) * UTILS.getDoubleTXTmoney(txtPrixUnitaireVente) + "");

	}

	public abstract void refresh();

	public formulaireCmdController() {

		JfxUtils.setAll("/gererCmdPane/InfosGeneralePane/InfosGeneralePane.fxml", this);

	}

	public abstract Session getHibernateSession();

	private void chargerCmd() {

		txtNumeroInscription.setText(commande.getIdCmd() + "");

		table.getItems().setAll(commande.getCmdarticles());

		try {
			txtClient.setText(commande.getClient().getRaisonSociale());
		} catch (Exception e) {
			txtClient.setText("");
		}
		try {
			UTILS.setDateToChooser(commande.getDate(), chooserDate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		table. refresh();
		txtVersement.setText(UTILS.FMoney(commande.getVersement()));
		txtRemise.setText(UTILS.FMoney(commande.getTotalRemise()));

		calculateTotaux();

	}

	public void chargerElement(Cmd a) {

		this.commande = a;
		chargerCmd();

	}

	public void pourAjouter() {
		POUR = UTILS.POUR_AJOUTER;
		UTILS.setDateToChooser(new Date(), chooserDate);
		commande = new CmdCRUD(getHibernateSession()).ajouter(commande);
		chargerCmd();
	}

	public Cmd getCmd() {

		return commande;
	}

}
