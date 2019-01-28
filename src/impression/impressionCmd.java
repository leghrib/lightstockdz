//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2018  
package impression;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import CRUD.CmdCRUD;
import CRUD.NewHibernateUtil;
import Main.loginFrameLightStock;
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

public class impressionCmd {

	public static void main(String[] args) {
		try {

			imprimerCmd(227);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  static void imprimerCmd(int idCmd) throws JRException {
		Cmd cmd = new CmdCRUD(NewHibernateUtil.getSessionfactory().openSession()).getCmd(idCmd);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(cmd.getCmdarticles());
		String sourceName = "src/impression/reports/cmd.jrxml";
		JasperReport report = JasperCompileManager.compileReport(sourceName);
		
		
		
		
		//=======
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("client", cmd.getClient());
		parameters.put("cmd", cmd);
		parameters.put("codeBar", CodeBarUTILS.getCodeBarForCMD(cmd.getIdCmd()));
		parameters.put("title", "وصل الشراء");
		parameters.put("vendeur", LoginPaneController.utilisateur.getNom());
		IMPRESSION_UTILS.addOtherParameter(parameters);

		//=======
		JasperPrint filledReport = JasperFillManager.fillReport(report, parameters, jrDataSource);
		
		JFrame frame = new JFrame();
		frame.getContentPane().add(new JRViewer(filledReport));
		frame.setVisible(true);
		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null);
	}
}
