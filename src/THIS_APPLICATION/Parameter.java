package THIS_APPLICATION;

import java.util.Date;

import UTILS.UTILS;
import UTILS.ConfigTools;
import UTILS.Traduction.G;

public class Parameter {
	public static int LANGUE = G.FRANCAIS;

	// database
	public static String DB_NAME = "gestion_o_s";
	public static String HOST = "localhost";
	public static String USERNAME = "root";
	public static String PASSWORD = "";
	public static String IP = "localhost";// 192.168.1.4
	public static int PORT = 3306;

	// autre
	public static String NAME_APPLICATION = "OSB3.jar";
	public static boolean ASK_EXIT_ON_CLOSE = false;
	public static int GENERATED_PASSWORD_LENGHT = 10;
	public static boolean isVisible_CodeBar = false;
	public static boolean isAutoMajusculeInTextFieldBarCode = true;
	// ccp
	public static String ccpCooperativeString = "0007444022";
	// Mailer
	public static String MAIL = "ostenbejaia@gmail.com";
	public static String PASSWORD_EMAIL_SERVICE = "cXAPGo8OVUU";
	// ===================Impression====
	public static final String FAIT_A_IMPRESSION = "Ain M'lila";

	// Point
	public static java.awt.Point LOCATION_PROFIL_PANEL = new java.awt.Point();
	public static java.awt.Point LOCATION_LBL_DATE = new java.awt.Point();
	public static java.awt.Point LOCATION_LBL_TIME = new java.awt.Point();
	public static java.awt.Point LOCATION_MOTOR_RECHERCHE_PANE = new java.awt.Point();
	public static java.awt.Point LOCATION_RACCOURCI_PANE = new java.awt.Point();
	public static java.awt.Point LOCATION_LOGO_PANE = new java.awt.Point();
	public static java.awt.Point LOCATION_LBL_LINE = new java.awt.Point();
	public static java.awt.Point LOCATION_LBL_STAT = new java.awt.Point();
	public static java.awt.Point LOCATION_VOLUME_PANE = new java.awt.Point();

	// environnement

	public static ConfigTools configTools = new ConfigTools("other/config/config.ini");

	public static boolean upload_Client_Photo_to_DB = false;
	public static boolean upload_fournisseur_photo_to_db = true;
	public static boolean upload_Utilisateur_Photo_to_DB = true;
	public static boolean upload_article_photo_to_db = true;

	public static boolean isStockage_photo_fournisseur_in_folder = false;
	public static boolean isStockage_photo_client_in_folder = true;
	public static boolean isStockage_photo_utilisateur_in_folder = false;
	public static boolean isStockage_photo_article_in_folder = false;

	public static String FOLDER_STOCKAGE_PHOTO_FOURNISSEUR = "d:/workspace/photo/";
	public static String FOLDER_STOCKAGE_PHOTO_UTILISATEUR = "d:/workspace/photo/";
	public static String FOLDER_STOCKAGE_PHOTO_CLIENT = "d:/workspace/photo/";
	public static String FOLDER_STOCKAGE_PHOTO_ARTICLE = "D:/";

	public static String TEMP_FOLDER_STOCKAGE_PHOTO_ARTICLE = "E:/";
	public static String TEMP_FOLDER_STOCKAGE_PHOTO_FOURNISSEUR = "image/personne/";
	public static String TEMP_FOLDER_STOCKAGE_PHOTO_CLIENT = "image/personne/";
	public static String TEMP_FOLDER_STOCKAGE_PHOTO_UTILISATEUR = "image/personne/";

	// design
	public static String DEFAULT_BACKGROUND_IMAGE = "image/icon/BG/yahoo.jpg";

	//
	public static String FOLDER_TEMP = "image/temp/";
	public static int VOLUME = 6;
	public static String maniereInscriptionClient = "dsys_leghr";
	public static boolean is_checked_jtable_rows = false;
	public static int current_year = new Date().getYear() + 1900;
	public static boolean ACTIVE_MOTOR_SEARCH_GADGET = true;
	public static String logoEntete;

	public static void loadConfiguration() throws Exception {

		NAME_APPLICATION = configTools.getStringValue("NAME_APPLICATION");

		// database
		HOST = configTools.getStringValue("HOST");
		USERNAME = configTools.getStringValue("USERNAME");
		PASSWORD = configTools.getStringValue("PASSWORD");
		IP = configTools.getStringValue("IP");
		PORT = configTools.getIntValue("PORT");
		// autre
		ASK_EXIT_ON_CLOSE = configTools.getBooleanValue("ASK_EXIT_ON_CLOSE");
		GENERATED_PASSWORD_LENGHT = configTools.getIntValue("GENERATED_PASSWORD_LENGHT");
		isVisible_CodeBar = configTools.getBooleanValue("isVisible_CodeBar");
		upload_article_photo_to_db = configTools.getBooleanValue("upload_article_photo_to_db");
		isAutoMajusculeInTextFieldBarCode = configTools.getBooleanValue("isAutoMajusculeInTextFieldBarCode");
		// ccp
		ccpCooperativeString = configTools.getStringValue("ccpCooperativeString");
		// mailer
		MAIL = configTools.getStringValue("MAIL");
		PASSWORD_EMAIL_SERVICE = configTools.getStringValue("PASSWORD_EMAIL_SERVICE");
		// point
		LOCATION_PROFIL_PANEL = configTools.getPointValue("LOCATION_PROFIL_PANEL");
		LOCATION_LBL_DATE = configTools.getPointValue("LOCATION_LBL_DATE");
		LOCATION_LBL_TIME = configTools.getPointValue("LOCATION_LBL_TIME");
		LOCATION_MOTOR_RECHERCHE_PANE = configTools.getPointValue("LOCATION_MOTOR_RECHERCHE_PANE");
		LOCATION_RACCOURCI_PANE = configTools.getPointValue("LOCATION_RACCOURCI_PANE");
		LOCATION_LOGO_PANE = configTools.getPointValue("LOCATION_LOGO_PANE");
		LOCATION_LBL_LINE = configTools.getPointValue("LOCATION_LBL_LINE");
		// environnement
		FOLDER_STOCKAGE_PHOTO_CLIENT = configTools.getStringValue("FOLDER_STOCKAGE_PHOTO_CLIENT");
		FOLDER_STOCKAGE_PHOTO_FOURNISSEUR = configTools.getStringValue("FOLDER_STOCKAGE_PHOTO_FOURNISSEUR");
		FOLDER_STOCKAGE_PHOTO_UTILISATEUR = configTools.getStringValue("FOLDER_STOCKAGE_PHOTO_UTILISATEUR");
		FOLDER_STOCKAGE_PHOTO_ARTICLE = configTools.getStringValue("FOLDER_STOCKAGE_PHOTO_ARTICLE");

		// temp
		TEMP_FOLDER_STOCKAGE_PHOTO_CLIENT = configTools.getStringValue("TEMP_FOLDER_STOCKAGE_PHOTO_CLIENT");
		TEMP_FOLDER_STOCKAGE_PHOTO_FOURNISSEUR = configTools.getStringValue("TEMP_FOLDER_STOCKAGE_PHOTO_FOURNISSEUR");
		TEMP_FOLDER_STOCKAGE_PHOTO_UTILISATEUR = configTools.getStringValue("TEMP_FOLDER_STOCKAGE_PHOTO_UTILISATEUR");
		TEMP_FOLDER_STOCKAGE_PHOTO_ARTICLE = configTools.getStringValue("TEMP_FOLDER_STOCKAGE_PHOTO_ARTICLE");

		// stockage is in folder ?
		isStockage_photo_client_in_folder = configTools.getBooleanValue("isStockage_photo_client_in_folder");
		isStockage_photo_fournisseur_in_folder = configTools.getBooleanValue("isStockage_photo_fournisseur_in_folder");
		isStockage_photo_utilisateur_in_folder = configTools.getBooleanValue("isStockage_photo_utilisateur_in_folder");
		isStockage_photo_article_in_folder = configTools.getBooleanValue("isStockage_photo_article_in_folder");
		// upload to photo db
		upload_Client_Photo_to_DB = configTools.getBooleanValue("upload_Client_Photo_to_DB");
		upload_fournisseur_photo_to_db = configTools.getBooleanValue("upload_fournisseur_photo_to_db");
		upload_Utilisateur_Photo_to_DB = configTools.getBooleanValue("upload_Utilisateur_Photo_to_DB");
		upload_article_photo_to_db = configTools.getBooleanValue("upload_article_photo_to_db");
		// ======================================
		DEFAULT_BACKGROUND_IMAGE = configTools.getStringValue("DEFAULT_BACKGROUND_IMAGE");
		LANGUE = configTools.getIntValue("LANGUE");
		LOCATION_LBL_STAT = configTools.getPointValue("LOCATION_LBL_STAT");
		LOCATION_VOLUME_PANE = configTools.getPointValue("LOCATION_VOLUME_PANE");
		VOLUME = configTools.getIntValue("VOLUME");
		ACTIVE_MOTOR_SEARCH_GADGET = configTools.getBooleanValue("ACTIVE_MOTOR_SEARCH_GADGET");
		logoEntete = configTools.getStringValue("logoEntete");

	}

	public static void saveConfiguration() {

		// database
		configTools.setStringAttribute("HOST", HOST);
		configTools.setStringAttribute("USERNAME", USERNAME);
		configTools.setStringAttribute("PASSWORD", PASSWORD);
		configTools.setStringAttribute("IP", IP);
		configTools.setIntAttribute("PORT", PORT);

		// autre
		configTools.setStringAttribute("NAME_APPLICATION", NAME_APPLICATION);
		configTools.setBooleanAttribute("ASK_EXIT_ON_CLOSE", ASK_EXIT_ON_CLOSE);
		configTools.setIntAttribute("GENERATED_PASSWORD_LENGHT", GENERATED_PASSWORD_LENGHT);
		configTools.setBooleanAttribute("isVisible_CodeBar", isVisible_CodeBar);
		configTools.setBooleanAttribute("upload_article_photo_to_db", upload_article_photo_to_db);
		configTools.setBooleanAttribute("isAutoMajusculeInTextFieldBarCode", isAutoMajusculeInTextFieldBarCode);
		configTools.setStringAttribute("logoEntete", logoEntete);

		// ccp
		configTools.setStringAttribute("ccpCooperativeString", ccpCooperativeString);
		// mailer
		configTools.setStringAttribute("MAIL", MAIL);
		configTools.setStringAttribute("PASSWORD_EMAIL_SERVICE", PASSWORD_EMAIL_SERVICE);
		// point
		configTools.setPointAttribute("LOCATION_PROFIL_PANEL", LOCATION_PROFIL_PANEL);
		configTools.setPointAttribute("LOCATION_LBL_DATE", LOCATION_LBL_DATE);
		configTools.setPointAttribute("LOCATION_LBL_TIME", LOCATION_LBL_TIME);
		configTools.setPointAttribute("LOCATION_MOTOR_RECHERCHE_PANE", LOCATION_MOTOR_RECHERCHE_PANE);
		configTools.setPointAttribute("LOCATION_RACCOURCI_PANE", LOCATION_RACCOURCI_PANE);
		configTools.setPointAttribute("LOCATION_LOGO_PANE", LOCATION_LOGO_PANE);
		configTools.setPointAttribute("LOCATION_LBL_LINE", LOCATION_LBL_LINE);
		configTools.setStringAttribute("DEFAULT_BACKGROUND_IMAGE", DEFAULT_BACKGROUND_IMAGE);
		configTools.setIntAttribute("LANGUE", LANGUE);
		configTools.setPointAttribute("LOCATION_LBL_STAT", LOCATION_LBL_STAT);
		configTools.setPointAttribute("LOCATION_VOLUME_PANE", LOCATION_VOLUME_PANE);
		configTools.setIntAttribute("VOLUME", VOLUME);
		configTools.setBooleanAttribute("ACTIVE_MOTOR_SEARCH_GADGET", ACTIVE_MOTOR_SEARCH_GADGET);

		// stockage photo
		configTools.setStringAttribute("FOLDER_STOCKAGE_PHOTO_CLIENT", FOLDER_STOCKAGE_PHOTO_CLIENT);
		configTools.setStringAttribute("FOLDER_STOCKAGE_PHOTO_FOURNISSEUR", FOLDER_STOCKAGE_PHOTO_FOURNISSEUR);
		configTools.setStringAttribute("FOLDER_STOCKAGE_PHOTO_UTILISATEUR", FOLDER_STOCKAGE_PHOTO_UTILISATEUR);
		configTools.setStringAttribute("FOLDER_STOCKAGE_PHOTO_ARTICLE", FOLDER_STOCKAGE_PHOTO_ARTICLE);

		// temp stockage photo
		configTools.setStringAttribute("TEMP_FOLDER_STOCKAGE_PHOTO_CLIENT", TEMP_FOLDER_STOCKAGE_PHOTO_CLIENT);
		configTools.setStringAttribute("TEMP_FOLDER_STOCKAGE_PHOTO_FOURNISSEUR",
				TEMP_FOLDER_STOCKAGE_PHOTO_FOURNISSEUR);
		configTools.setStringAttribute("TEMP_FOLDER_STOCKAGE_PHOTO_UTILISATEUR",
				TEMP_FOLDER_STOCKAGE_PHOTO_UTILISATEUR);
		configTools.setStringAttribute("TEMP_FOLDER_STOCKAGE_PHOTO_ARTICLE", TEMP_FOLDER_STOCKAGE_PHOTO_ARTICLE);
		// stockage is in folder ? =================================
		configTools.setBooleanAttribute("isStockage_photo_client_in_folder", isStockage_photo_client_in_folder);
		configTools.setBooleanAttribute("isStockage_photo_fournisseur_in_folder",
				isStockage_photo_fournisseur_in_folder);
		configTools.setBooleanAttribute("isStockage_photo_utilisateur_in_folder",
				isStockage_photo_utilisateur_in_folder);
		configTools.setBooleanAttribute("isStockage_photo_article_in_folder", isStockage_photo_article_in_folder);
		// upload to photo db
		configTools.setBooleanAttribute("upload_Client_Photo_to_DB", upload_Client_Photo_to_DB);
		configTools.setBooleanAttribute("upload_fournisseur_photo_to_db", upload_fournisseur_photo_to_db);
		configTools.setBooleanAttribute("upload_Utilisateur_Photo_to_DB", upload_Utilisateur_Photo_to_DB);
		configTools.setBooleanAttribute("upload_article_photo_to_db", upload_article_photo_to_db);

	}

	public static void loadDefaultConfiguration() {
		DB_NAME = "gestion_o_s";
		NAME_APPLICATION = "OSB3.jar";
		HOST = "";
		USERNAME = "root";
		PASSWORD = "";
		IP = "localhost";// 192.168.1.4
		PORT = 3306;
		ASK_EXIT_ON_CLOSE = false;
		GENERATED_PASSWORD_LENGHT = 10;
		isVisible_CodeBar = false;
		isAutoMajusculeInTextFieldBarCode = true;
		ccpCooperativeString = "0007444022";
		MAIL = "ostenbejaia@gmail.com";
		PASSWORD_EMAIL_SERVICE = "cXAPGo8OVUU";
		logoEntete = "";
		// Points
		LOCATION_PROFIL_PANEL = new java.awt.Point();
		LOCATION_LBL_DATE = new java.awt.Point();
		LOCATION_LBL_TIME = new java.awt.Point();
		LOCATION_MOTOR_RECHERCHE_PANE = new java.awt.Point();
		LOCATION_RACCOURCI_PANE = new java.awt.Point();
		LOCATION_LBL_LINE = new java.awt.Point();
		LOCATION_LOGO_PANE = new java.awt.Point();
		LOCATION_LBL_STAT = new java.awt.Point();
		LOCATION_VOLUME_PANE = new java.awt.Point();

		// environnement
		FOLDER_STOCKAGE_PHOTO_CLIENT = "d:/workspace/photo/";
		FOLDER_STOCKAGE_PHOTO_FOURNISSEUR = "image/fournisseur";
		FOLDER_STOCKAGE_PHOTO_UTILISATEUR = "image/utilisateur";
		FOLDER_STOCKAGE_PHOTO_ARTICLE = "image/article";

		// temp
		TEMP_FOLDER_STOCKAGE_PHOTO_CLIENT = "image/temp/personne/";
		TEMP_FOLDER_STOCKAGE_PHOTO_FOURNISSEUR = "image/temp/fournisseur/";
		TEMP_FOLDER_STOCKAGE_PHOTO_UTILISATEUR = "image/temp/utilisateur/";
		TEMP_FOLDER_STOCKAGE_PHOTO_ARTICLE = "image/temp/article/";
		// stockage is in folder ?
		isStockage_photo_client_in_folder = true;
		isStockage_photo_fournisseur_in_folder = false;
		isStockage_photo_utilisateur_in_folder = false;
		isStockage_photo_article_in_folder = false;
		//
		upload_Client_Photo_to_DB = false;
		upload_fournisseur_photo_to_db = true;
		upload_Utilisateur_Photo_to_DB = true;
		upload_article_photo_to_db = true;

		// design
		DEFAULT_BACKGROUND_IMAGE = "image/icon/yahoo.jpg";
		LANGUE = G.FRANCAIS;
		VOLUME = 100;
		ACTIVE_MOTOR_SEARCH_GADGET = true;
	}

	 
}
