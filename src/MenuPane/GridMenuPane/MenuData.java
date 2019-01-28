//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package MenuPane.GridMenuPane;

import EtablissementPane.InfosEtabPaneController;
import ICON.ICONE;
import Main.MainController;
import MenuPane.GridMenuPane.AbstractMenuItem.AbstractItem;
import UTILS.JfxUtils;
import UTILS.ExportDB.ExportDBPaneController;
import gererArticlePane.listePane.ListeArticlePaneController;
import gererClientPane.listePane.ListeClientPaneController;
import gererCmdFPane.listePane.ListeCmdFPaneController;
import gererCmdPane.listePane.ListeCmdPaneController;
import gererFournisseurPane.listePane.ListeFournisseurPaneController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MenuData {

	public static AbstractItem getPrincipaleMenu(MainController owner) {

		ObservableList<AbstractItem> PRINCIPALE_MENU = FXCollections.observableArrayList();

		// Menu 1
		// ====================================================================

		AbstractItem gererClientItem = new AbstractItem("الزبائن", ICONE.clients128) {
			@Override
			public void EnClick() {

				ListeClientPaneController controller = new ListeClientPaneController();
				Stage stage = JfxUtils.displayIn(controller, owner.stageMain);
				stage.setMaximized(true);
				stage.showAndWait();
			}
		};

		// ====================================================================
		// ====================================================================

		AbstractItem gererFournisseurItem = new AbstractItem("الممونون", ICONE.fournisseur128) {
			@Override
			public void EnClick() {

				ListeFournisseurPaneController controller = new ListeFournisseurPaneController();
				Stage stage = JfxUtils.displayIn(controller, owner.stageMain);
				stage.setMaximized(true);
				stage.showAndWait();
			}
		};

		// ====================================================================

		AbstractItem gererArticlesPane = new AbstractItem("السـلع", ICONE.clothes128) {
			@Override
			public void EnClick() {

				ListeArticlePaneController controller = new ListeArticlePaneController();
				Stage stage = JfxUtils.displayIn(controller, owner.stageMain);
				stage.setMaximized(true);
				stage.showAndWait();
			}
		};

		// ====================================================================

		// ====================================================================

		AbstractItem moteurRecherche = new AbstractItem("البحث", ICONE.search128) {
			@Override
			public void EnClick() {
				owner.showMoteurRecherche();

			}
		};
		// ====================================================================

				AbstractItem gererVentePane = new AbstractItem("البــيــع", ICONE.shopping128) {
					@Override
					public void EnClick() {

						ListeCmdPaneController controller = new ListeCmdPaneController();
						Stage stage = JfxUtils.displayIn(controller, owner.stageMain);
						stage.setMaximized(true);
						stage.showAndWait();
					}
				};
				// ====================================================================

				AbstractItem gererAchatPane = new AbstractItem("الشــــراء", ICONE.achat128) {
					@Override
					public void EnClick() {

						ListeCmdFPaneController controller = new ListeCmdFPaneController();
						Stage stage = JfxUtils.displayIn(controller, owner.stageMain);
						stage.setMaximized(true);
						stage.showAndWait();
					}
				};

		// Menu 1
		// ====================================================================
		AbstractItem ParametreItem = new AbstractItem("وسائط و خيارات", ICONE.setting128) {
			@Override
			public void runAnimation(ImageView imageView) {
				// TODO Auto-generated method stub
				JfxUtils.rotateTransition(imageView);
			}
		};
		AbstractItem infosEtablissemnt = new AbstractItem("معلومات المتجـــر", ICONE.store128) {

			@Override
			public void EnClick() {
				InfosEtabPaneController controller = new InfosEtabPaneController();
				Stage stage = JfxUtils.displayIn(controller, owner.stageMain);
				controller.setupSize();
				stage.showAndWait();

			}
		};
		ParametreItem.addAllSubItem(infosEtablissemnt);

		// Menu 2
		// =========================================================================
				AbstractItem gererExport = new AbstractItem("قاعدة البيانات", ICONE.bd128) {
					@Override
					public void EnClick() {

						ExportDBPaneController controller = new ExportDBPaneController() {

							@Override
							public Window getStage() {
								// TODO Auto-generated method stub
								return owner.stageMain;
							}
						};
						Stage stage = JfxUtils.displayIn(controller, owner.stageMain);
						controller.setupSize();
						stage.showAndWait();

					}

				};

				// ===================================
				//=========================================================================
				AbstractItem gererDepensePane = new AbstractItem("المصاريف", ICONE.money128) {
					@Override
					public void EnClick() {

						ExportDBPaneController controller = new ExportDBPaneController() {

							@Override
							public Window getStage() {
								// TODO Auto-generated method stub
								return owner.stageMain;
							}
						};
						Stage stage = JfxUtils.displayIn(controller, owner.stageMain);
						controller.setupSize();
						stage.showAndWait();

					}

				};

				// ===================================
		
		PRINCIPALE_MENU.add(gererClientItem);
		PRINCIPALE_MENU.add(gererFournisseurItem);
		PRINCIPALE_MENU.add(gererArticlesPane);
		PRINCIPALE_MENU.add(gererVentePane);
		PRINCIPALE_MENU.add(gererAchatPane);
		PRINCIPALE_MENU.add(ParametreItem);
		PRINCIPALE_MENU.add(moteurRecherche);
		PRINCIPALE_MENU.add(gererDepensePane);

		PRINCIPALE_MENU.add(gererExport);

		AbstractItem PrincipalMenuAbstractItem = new AbstractItem("القائمة الرئيســـــية", null);
		PrincipalMenuAbstractItem.addCollectionSubItem(PRINCIPALE_MENU);
		return PrincipalMenuAbstractItem;

	}

}
