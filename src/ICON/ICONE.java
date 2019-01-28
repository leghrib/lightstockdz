//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package ICON;

import java.io.InputStream;

import javafx.scene.image.Image;

public class ICONE {

	public static Image ajouter32 = getImage("ajouter16.png");
	public static Image modifier32 = getImage("modifier32.png");
	public static Image modifier16 = getImage("modifier16.png");
	public static Image imprimer32 = getImage("printer16.png");
	public static Image Supprimer32 = getImage("Supprimer16.png");
	public static Image save32 = getImage("save32.png");
	public static Image save16 = getImage("save16.png");
	public static Image exit32 = getImage("exit32.png");
	public static Image portable32 = getImage("portable32.png");
	public static Image email32 = getImage("email32.png");
	public static Image telephonefixe32 = getImage("telephonefixe32.png");
	public static Image Facebook32 = getImage("Facebook32.png");
	public static Image correct16 = getImage("correct16.png");
	public static Image correct32 = getImage("correct32.png");
	public static Image false24 = getImage("false24.png");
	public static Image ajouterPersonneContact32 = getImage("ajouterPersonneContact32.png");
	public static Image personneFeminin = getImage("personneFeminin.jpg");
	public static Image personneMasculin = getImage("personneMasculin.jpg");
	public static Image camera = getImage("camera.png");
	public static Image browse = getImage("browse.png");
	public static Image capture = getImage("capture.png");
	public static Image settings_6 = getImage("settings-6.png");
	public static Image emploi = getImage("emploi.png");
	public static Image branche = getImage("branche.png");
	public static Image eleves = getImage("eleves.png");
	public static Image Enseignant = getImage("Enseignant.png");
	public static Image matieres = getImage("matieres.png");
	public static Image home = getImage("home48.png");
	public static Image back48 = getImage("back48.png");
	public static Image home24 = getImage("home24.png");
	public static Image back24 = getImage("back24.png");
	public static Image fullscreen16 = getImage("fullscreen16.png");
	public static Image recherche64 = getImage("recherche64.png");
	public static Image recherche32 = getImage("recherche32.png");
	public static Image recherche24 = getImage("recherche24.png");
	public static Image menu24 = getImage("menu24.png");
	public static Image absence64 = getImage("absence64.png");
	public static Image employe64 = getImage("employe64.png");
	public static Image exportDB64 = getImage("exportDB64.png");
	public static Image noteEleve64 = getImage("noteEleve64.png");
	public static Image etab64 = getImage("etab64.png");
	public static Image store128 = getImage("store128.png");
	public static Image shopping128 = getImage("shopping128.png");
	public static Image achat128 = getImage("achat128.png");
	public static Image clothes128 = getImage("clothes128.png");
	public static Image clients128 = getImage("clients128.png");
	public static Image bd128 = getImage("bd128.png");
	public static Image setting128 = getImage("setting128.png");
	public static Image search128 = getImage("search128.png");
	public static Image flashDisk128 = getImage("flashDisk128.png");
	public static Image LogoDefault = getImage("LogoDefault.png");
	public static Image articleDefault = getImage("articleDefault.png");
	public static Image fournisseur128 = getImage("fournisseur128.png");
	public static Image chart32 = getImage("chart32.png");
	public static Image autre32 = getImage("autre32.png");
	public static Image lightStock320 = getImage("lightStock320.png");
	public static Image money128 = getImage("money128.png");

	private static Image getImage(String url) {
		// TODO Auto-generated method stub
		String folder = "/ICON/ICONS/";
		url = folder + url;
		System.out.println(url);
		InputStream inputStream=ICONE.class.getClass().getResourceAsStream(url);
		Image image=null ;
		if(inputStream==null){
		
		}else{
			image= new Image(inputStream);

		}
		return image;

	}

}
