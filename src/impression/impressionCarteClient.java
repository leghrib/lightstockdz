//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2018  
package impression;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import CRUD.ClientCRUD;
import CRUD.CmdCRUD;
import CRUD.NewHibernateUtil;
import Main.loginFrameLightStock;
import Modele.Client;
import Modele.Cmd;
import THIS_APPLICATION.CodeBarUTILS;
import UtilisateurPane.LoginPane.LoginPaneController;
import application.Main;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

public class impressionCarteClient {

	public static void main(String[] args) {
		 
	}

	public  static void imprimer(Client client) throws JRException {

		String sourceName = "src/impression/reports/carteClient.jrxml";
		JasperReport report = JasperCompileManager.compileReport(sourceName);
		
		
		
		
		//=======
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		 
		parameters.put("codeBar", CodeBarUTILS.getCodeBarForArticle(client.getIdClient()));
		IMPRESSION_UTILS.addOtherParameter(parameters);

		//=======
		JasperPrint filledReport = JasperFillManager.fillReport(report, parameters);
		
		JFrame frame = new JFrame();
		frame.getContentPane().add(new JRViewer(filledReport));
		frame.setVisible(true);
		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null);
	}
}
