package gererCmdFPane.InfosGeneralePane;

import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.hibernate.Session;

import CRUD.ArticleCRUD;
import CRUD.CmdfCRUD;
import CRUD.FournisseurCRUD;
import Modele.Article;
import Modele.Cmdarticlef;
import Modele.Cmdf;
import Modele.Fournisseur;
import THIS_APPLICATION.Loaded;
import UTILS.JfxUtils;
import UTILS.UTILS;
import UTILS.rechercheMiniFrame.RechercheMiniFrameController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.BorderPane;

public abstract class formulaireCmdFController extends BorderPane implements Initializable {

	@FXML
	private TextField txtNumeroInscription;

	@FXML
	private TextField txtClient;

	@FXML
	private DatePicker chooserDate;

	@FXML
	private TableView<Cmdarticlef> table;

	@FXML
	private TableColumn<Cmdarticlef, Integer> column1;

	@FXML
	private TableColumn<Cmdarticlef, String> column2;

	@FXML
	private TableColumn<Cmdarticlef, Double> column3;

	@FXML
	private TableColumn<Cmdarticlef, Double> column4;

	@FXML
	private TableColumn<Cmdarticlef, Double> column5;

	@FXML
	private TableColumn<Cmdarticlef, Double> column6;

	@FXML
	private TextField txtArticle;

	@FXML
	private TextField txtQuantite;

	@FXML
	private TextField txtPrixUnitaireVente;

	@FXML
	private TextField txtPrixUnitaireAchat;

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

	private Cmdf commande = new Cmdf();

	@FXML
	void AnnulerArticleCMD(ActionEvent event) {

	}

	@FXML
	void ajouterArticleCMD(ActionEvent event) {

		double quantite = UTILS.getDoubleTXTmoney(txtQuantite);
		double prixVente = UTILS.getDoubleTXTmoney(txtPrixUnitaireVente);
		double prixUnitaireAchat = UTILS.getDoubleTXTmoney(txtPrixUnitaireAchat);
		boolean exist = false;

		// ----duplicate article , somme de la quantite
		for (Iterator iterator = commande.getCmdarticlefs().iterator(); iterator.hasNext();) {
			Cmdarticlef articlecmd = (Cmdarticlef) iterator.next();
			if (article.getIdArticle() == articlecmd.getArticle().getIdArticle()) {
				exist = true;
				articlecmd.setQte(articlecmd.getQte() + quantite);
			}

		}
		// ---nouveau article
		if (!exist) {
			Cmdarticlef cmdArticle;
			cmdArticle = new Cmdarticlef(article, commande, quantite, prixUnitaireAchat, prixVente, 0);
			commande.getCmdarticlefs().add(cmdArticle);

		}

		calculateTotaux();
		updateCommamnde(); //
		chargerCmd();
		new ArticleCRUD(getHibernateSession()).refreshArticleQte(article);

	}

	private void calculateTotaux() {

		double somme = 0;
		for (Cmdarticlef cmdarticle : commande.getCmdarticlefs()) {
			somme += cmdarticle.getPrixAchatTotal();
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

		commande.getCmdarticlefs().removeIf(
				ca -> ca.getIdCmdarticleF() == table.getSelectionModel().getSelectedItem().getIdCmdarticleF());

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

	private RechercheMiniFrameController<Fournisseur> miniFrameFournisseur;

	private RechercheMiniFrameController<Article> miniFrameArticle;

	protected Article article;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		column1.setCellValueFactory(new PropertyValueFactory<Cmdarticlef, Integer>("idArticle"));
		column2.setCellValueFactory(new PropertyValueFactory<Cmdarticlef, String>("ref"));
		column3.setCellValueFactory(new PropertyValueFactory<Cmdarticlef, Double>("prixAchat"));
		column4.setCellValueFactory(new PropertyValueFactory<Cmdarticlef, Double>("prixVente"));
		column5.setCellValueFactory(new PropertyValueFactory<Cmdarticlef, Double>("qte"));
		column6.setCellValueFactory(new PropertyValueFactory<Cmdarticlef, Double>("prixAchatTotal"));

		column1.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
		column2.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
		column3.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
		column4.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
		column5.prefWidthProperty().bind(table.widthProperty().multiply(0.15));
		column6.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
		
		
		UTILS.setColumnTextFieldConverter(UTILS.getConverterMoney(), column3,column4,column6);
		UTILS.setColumnTextFieldConverter(UTILS.getConverterQuantite(), column5);

		
		
		showRechercheArticle();
		showRechercheFournisseur();

		Label label = new Label("الطلب فارغ");
		table.setPlaceholder(label);

		txtQuantite.setOnKeyReleased(e -> calculeTotalprixAchat());
		txtPrixUnitaireAchat.setOnKeyReleased(e -> calculeTotalprixAchat());
		txtQuantite.setOnKeyReleased(e -> {
			calculeTotalprixAchat();

			if (e.getCode() == KeyCode.ENTER) {
				txtPrixUnitaireAchat.requestFocus();
				txtPrixUnitaireAchat.selectAll();

			}
		});
		txtPrixUnitaireAchat.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				btnOkArticle.requestFocus();
			}
			calculeTotalprixAchat();

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

		txtRemise.setOnKeyReleased(e -> calculateTotaux());
		txtVersement.setOnKeyReleased(e -> calculateTotaux());
	}

	protected void showRechercheFournisseur() {
		miniFrameFournisseur = new RechercheMiniFrameController<Fournisseur>() {
			@Override
			public ObservableList<Fournisseur> getListItems() {
				return new FournisseurCRUD(getHibernateSession()).getCollection(txtClient.getText(),
						UTILS.NON_DELETED_ONLY);
			}

			@Override
			public String getTextFromItem(Fournisseur item) {
				return item.getRaisonSociale();
			}

			@Override
			public void returnResult(Fournisseur f) {

				chooserDate.requestFocus();
				commande.setFournisseur(f);
				miniFrameFournisseur.closeStage();
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
		new CmdfCRUD(getHibernateSession()).modifier(commande);
	}

	protected void showRechercheArticle() {

		miniFrameArticle = new RechercheMiniFrameController<Article>() {
			@Override
			public ObservableList<Article> getListItems() {
				return new ArticleCRUD(getHibernateSession()).getCollection(txtArticle.getText(), UTILS.NON_DELETED_ONLY);
			}

			@Override
			public String getTextFromItem(Article item) {
				return item.getRef();
			}

			@Override
			public void returnResult(Article selectedArticle) {
				article = selectedArticle;

				txtArticle.setText(selectedArticle.getRef());
				formulaireCmdFController.this.article = selectedArticle;
				miniFrameArticle.closeStage();
				txtQuantite.setText("1");
				txtPrixUnitaireVente.setText(UTILS.FMoney(article.getPrixVente()));
				txtPrixUnitaireAchat.setText(UTILS.FMoney(article.getPrixAchat()));
				txtQuantite.requestFocus();
				txtQuantite.selectAll();
				calculeTotalprixAchat();

			}

			@Override
			public TextField getTextRecherche() {
				return txtArticle;
			}
		};

	}

	protected void calculeTotalprixAchat() {

		txtSommePrixVente
				.setText(UTILS.getDoubleTXTmoney(txtQuantite) * UTILS.getDoubleTXTmoney(txtPrixUnitaireAchat) + "");

	}

	public abstract void refresh();

	public formulaireCmdFController() {

		JfxUtils.setAll("/gererCmdFPane/InfosGeneralePane/InfosGeneralePane.fxml", this);

	}

	public abstract Session getHibernateSession();

	private void chargerCmd() {

		txtNumeroInscription.setText(commande.getIdCmdF() + "");

		table.getItems().setAll(commande.getCmdarticlefs());

		try {
			txtClient.setText(commande.getFournisseur().getRaisonSociale());
		} catch (Exception e) {
			txtClient.setText("");
		}
		try {
			UTILS.setDateToChooser(commande.getDate(), chooserDate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		table.refresh();
		txtVersement.setText(UTILS.FMoney(commande.getVersement()));
		txtRemise.setText(UTILS.FMoney(commande.getTotalRemise()));

		calculateTotaux();

	}

	public void chargerElement(Cmdf a) {

		this.commande = a;
		chargerCmd();

	}

	public void pourAjouter() {
		POUR = UTILS.POUR_AJOUTER;
		UTILS.setDateToChooser(new Date(), chooserDate);
		commande = new CmdfCRUD(getHibernateSession()).ajouter(commande);
		chargerCmd();
	}

	public Cmdf getCmd() {

		return commande;
	}

}
