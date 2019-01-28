//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UTILS.ICONS;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ICON {

	

	private static ImageView getImage(String nomImage) {
		// TODO Auto-generated method stub
		return new ImageView(new Image("file:image/icons/"+nomImage));

	}
}
