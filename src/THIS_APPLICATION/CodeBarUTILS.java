//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2018  
package THIS_APPLICATION;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.barcode_coder.java_barcode.Barcode;
import com.barcode_coder.java_barcode.BarcodeFactory;
import com.barcode_coder.java_barcode.BarcodeType;

import UTILS.Parametres.Parameter;

public class CodeBarUTILS {
	// 11 to 99
	public static final String PERSONNE_CODE_BAR = "99";
	public static final String CMD_CODE_BAR = "98";
	public static final String ARTICLE_CODE_BAR = "97";
	public static final String CLIENT_CODE_BAR = "96";

	public static File createCodeBarImage(String IDType, String ID_Object) {

		String codeBar = IDType + "" + completeWithZero_9(ID_Object);
		System.out.println("codebar  : " + codeBar);
		Barcode b = BarcodeFactory.createBarcode(BarcodeType.Code11, codeBar);
		File file = null;

		try {
			file = File.createTempFile(new Date().getTime() + "", ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (b.export("png", 1, 20, Parameter.TEST_MODE, file.getAbsolutePath())) {
			return file;
		} else {
			return null;
		}
	}

	public static String completeWithZero_9(String s) {
		int length = s.length();
		for (int i = 0; i < 9 - length; i++)
			s = "0" + s;
		return s;
	}

	public static String getCodeBarForCMD(int id) {
		String codeBar = CMD_CODE_BAR + "" + completeWithZero_9(id + "");
		return codeBar;

	}

	public static String getCodeBarForArticle(int id) {
		String codeBar = ARTICLE_CODE_BAR + "" + completeWithZero_10(id + "");
		return codeBar;

	}

	public static String getCodeBarForClient(int id) {
		String codeBar = CLIENT_CODE_BAR + "" + completeWithZero_10(id + "");
		return codeBar;

	}

	public static String completeWithZero_10(String s) {
		int length = s.length();
		for (int i = 0; i < 10 - length; i++)
			s = "0" + s;
		return s;
	}
}
