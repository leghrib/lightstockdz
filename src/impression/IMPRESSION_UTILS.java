package impression;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import THIS_APPLICATION.Loaded;
import THIS_APPLICATION.Parameter;
import UtilisateurPane.ConnectionDB;
import UtilisateurPane.LoginPane.LoginPaneController;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

public class IMPRESSION_UTILS {

	public static String MY_ROOT_DIR = "D:\\my_workspace\\OSB\\";

	public static void main(String[] args) {
	}

	public static String ArrayIntToString(int[] args) {
		return Arrays.toString(args).replace('[', '(').replace(']', ')');

	}

	public static void addOtherParameter(Map<String, Object> parameters) {
		parameters.put("idUtilisateur", LoginPaneController.utilisateur.getIdUtilisateur());
		parameters.put("FAIT_A", Parameter.FAIT_A_IMPRESSION);
		parameters.put("MY_ROOT_DIR", IMPRESSION_UTILS.MY_ROOT_DIR);
		parameters.put("entete", Loaded.getEtab().getRepEntete());
		parameters.put("logoEntete", Loaded.getEtab().getUrlLogo());
		parameters.put("nomStore", Loaded.getEtab().getNom());

	}

	public static void imprimerJARxml(String reportName) {

		try {

			// String reportName =
			// "impressionTemplate/Etablissement/Listeetablissement";
			Map<String, Object> parameters = new HashMap<String, Object>();

			IMPRESSION_UTILS.addOtherParameter(parameters);

			JasperCompileManager.compileReportToFile(reportName + ".jrxml");
			JasperPrint print = JasperFillManager.fillReport(reportName + ".jasper", parameters, ConnectionDB.getCon());
			JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			JasperViewer.viewReport(print, false, Locale.FRANCE);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

		}

	}

}
