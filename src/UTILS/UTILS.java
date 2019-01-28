//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UTILS;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.Expression;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.text.JTextComponent;

import UTILS.Parametres.Parameter;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class UTILS {

	public static final int POUR_AJOUTER = 1;
	public static final int POUR_MODIFIER = 2;
	public static final int POUR_AFFICHER = 3;
	public static final int POUR_DEFAULT = POUR_AFFICHER;
	public static final boolean MASCULIN = true;
	public static final boolean FEMININ = false;

	//
	public static final int DELETED_AND_NON_DELETED = 2;
	public static final int DELETED_ONLY = 1;
	public static final int NON_DELETED_ONLY = 0;
	//
	public static int PORTABLE = 1;
	public static int EMAIL = 2;
	public static int FIXE = 3;
	public static int FACEBOOK = 4;

	public static FloatControl gainControl;

	public static void imprimerList(List<?> persons) {
		System.out.println("size : " + persons.size());
		for (Object object : persons) {
			System.out.println(object.toString());

		}
	}

	public static boolean isPhoneNumberCorrect(String pPhoneNumber) {

		Pattern pattern = Pattern.compile("((\\+[1-9]{3,4}|0[1-9]{4}|00[1-9]{3})\\-?)?\\d{8,20}");
		Matcher matcher = pattern.matcher(pPhoneNumber);

		if (matcher.matches())
			return true;

		return false;
	}

	public static int getEntier(TextField textField) {
		// TODO Auto-generated method stub
		try {
			return Integer.valueOf(textField.getText()).intValue();
		} catch (Exception e) {

			return 0;
		}
	}

	public static boolean looksLikeMail(String str) {
		int lastAtPos = str.lastIndexOf('@');
		int lastDotPos = str.lastIndexOf('.');
		return (lastAtPos < lastDotPos && lastAtPos > 0 && str.indexOf("@@") == -1 && lastDotPos > 2
				&& (str.length() - lastDotPos) > 2);
	}

	public static Alert runAlert(AlertType alertType, String title, String header, String content) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
		return alert;
	}

	public static void setDateToChooser(Date dateNaiss, DatePicker chooser) {
		try {
			chooser.setValue(DateUtils.asLocalDate(dateNaiss));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static Date getDateFromchooser(DatePicker chooser) {
		// TODO Auto-generated method stub
		try {
			return DateUtils.asDate(chooser.getValue());
		} catch (Exception e) {
			return null;
		}
	}

	public static Integer getIntFromTextField(TextField txtClasse) {
		try {
			return Integer.valueOf(txtClasse.getText()).intValue();
		} catch (Exception e) {
			return null;
		}
	}

	public static void setIntegerInField(TextField txtClasse, Integer classe) {

		if (classe == null) {

		} else {
			txtClasse.setText(classe.intValue() + "");

		}
	}

	public static void setColumnEditable(boolean b, TableColumn<?, ?>... tableColumns) {

		for (TableColumn<?, ?> tableColumn : tableColumns) {

			tableColumn.setEditable(true);

		}
	}

	public static void setColumnTextFieldConverter(StringConverter converter, TableColumn... columns) {

		for (TableColumn tableColumn : columns) {

			tableColumn.setCellFactory(TextFieldTableCell.forTableColumn(converter));

		}

	}

	public static String getRoundedFloat(Float object) {
		// TODO Auto-generated method stub
		if (object == null)
			return null;
		return new DecimalFormat("0.##").format(object) + "";
	}

	public static String getRoundedDouble(Double object) {
		// TODO Auto-generated method stub
		if (object == null)
			return null;
		return new DecimalFormat("###,###,###.##").format(object) + "";
	}

	public static String getRoundedDoubleQuantite(Double object) {
		// TODO Auto-generated method stub
		if (object == null)
			return null;
		return new DecimalFormat("###.##").format(object) + "";
	}

	public static String colorToHex(Color color) {
		return colorChanelToHex(color.getRed()) + colorChanelToHex(color.getGreen()) + colorChanelToHex(color.getBlue())
				+ colorChanelToHex(color.getOpacity());
	}

	private static String colorChanelToHex(double chanelValue) {
		String rtn = Integer.toHexString((int) Math.min(Math.round(chanelValue * 255), 255));
		if (rtn.length() == 1) {
			rtn = "0" + rtn;
		}
		return rtn;
	}

	public static ButtonType runAlertSaveChanges() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("هل تريد فعلا حفظ تغييرات ؟");
		alert.setHeaderText("هل تريد فعلا حفظ تغييرات ؟");
		alert.setContentText("هل تريد فعلا حفظ تغييرات ؟");

		alert.showAndWait();

		return alert.getResult();
	}

	public static ButtonType runAlertAskForExit(Stage stageMain) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("هل تريد فعلا الخروج من البرمجي ؟");
		alert.setHeaderText("هل تريد فعلا الخروج من البرمجي ؟");
		alert.setContentText("هل تريد فعلا الخروج من البرمجي ؟");

		alert.initOwner(stageMain);
		alert.showAndWait();

		return alert.getResult();
	}

	public static int calculeNbrOccurence(Integer groupe, ObservableList<Integer> affectedGroupe) {
		int nbr = 0;
		for (Integer integer : affectedGroupe) {
			if (integer != null && integer.intValue() == groupe.intValue()) {
				nbr++;
			}
		}
		return nbr;
	}

	public static void runAlertSaveDone() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("تم الحفظ بنجاح.");
		alert.setHeaderText("تم الحفظ بنجاح.");
		alert.setContentText("تم الحفظ بنجاح.");

		alert.showAndWait();

	}

	public static void runAlertSaveNotDone() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("لم يتم الحفظ");
		alert.setHeaderText("لم يتم الحفظ");
		alert.setContentText("لم يتم الحفظ");

		alert.showAndWait();
	}

	public static synchronized void playSound(final String url) {
		try {
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url));
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		new Thread(new Runnable() {
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(url));
					clip.open(inputStream);
					gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(Parameter.VOLUME); // Reduce volume

					clip.start();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void openfile(String path) {
		openfile(new File(path));
	}

	public static void openfile(File file) {
		Desktop dt = Desktop.getDesktop();
		try {
			dt.open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getDesktopFolder() {
		return (System.getProperty("user.home") + "/Desktop").replace("\\", "/");
	}

	public static File getDesktopFileFolder() {
		return new File((System.getProperty("user.home") + "/Desktop").replace("\\", "/"));
	}

	public static void openFileInFolderExplorer(File file) {
		try {
			Runtime.getRuntime().exec("explorer.exe /select," + file.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sleep(int i) {

		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static double getDoubleTXTmoney(TextField textComponent) {

		String st = textComponent.getText().trim();
		return getDoubleFromStringMoney(st);

	}

	public static double getDoubleTXTmoney(Labeled textComponent) {

		String st = textComponent.getText().trim();
		return getDoubleFromStringMoney(st);

	}

	public static double getDoubleFromStringMoney(String st) {
		try {

			st = st.replaceAll("//s+", "").replaceAll("DZD", "").replaceAll(",", "").replaceFirst(" ", "");
			System.out.println(st);
			double d = Double.parseDouble(st);
			return d;
		} catch (Exception e) {
			System.out.println(st);
			e.printStackTrace();
		}
		return 0;
	}

	public static NumberFormat formatter = NumberFormat.getCurrencyInstance();

	public static String FMoney(double money) {
		try {
			formatter.setCurrency(Currency.getInstance("DZD"));

			return formatter.format(money).replaceAll("DZD", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static int getIntFromString(String recherche) {
		// TODO Auto-generated method stub
		try {
			return Integer.valueOf(recherche).intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public static String readFile(String pathname) {

		File file = new File(pathname);
		StringBuilder fileContents = new StringBuilder((int) file.length());
		Scanner scanner;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FileNotFoundException";
		}
		String lineSeparator = System.getProperty("line.separator");

		try {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			return fileContents.toString();
		} finally {
			scanner.close();
		}
	}

	public static StringConverter getConverterMoney() {

		StringConverter<Double> converter = new StringConverter<Double>() {
			@Override
			public Double fromString(String string) {

				try {
					return Double.valueOf(string);
				} catch (Exception e) {
					return null;
				}
			}

			@Override
			public String toString(Double object) {

				if (object == null)
					return null;
				return UTILS.getRoundedDouble(object);
			}
		};
		return converter;

	}

	public static StringConverter getConverterQuantite() {

		StringConverter<Double> converter = new StringConverter<Double>() {
			@Override
			public Double fromString(String string) {

				try {
					return Double.valueOf(string);
				} catch (Exception e) {
					return null;
				}
			}

			@Override
			public String toString(Double object) {

				if (object == null)
					return null;
				return UTILS.getRoundedDoubleQuantite(object);
			}
		};
		return converter;

	}
}
