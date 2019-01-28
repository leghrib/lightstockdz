//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2018  
package UTILS.MyCompenents;

import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;

public class MyToolTip extends Tooltip {

	public MyToolTip(String text) {
		super();
		this.setFont(new Font("times new roman", 16));
		setText(text);
	}

}
