package UTILS.CaptureTools;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Capture {

	public static String capture(Rectangle rectangle, String extension,String outPut) {
		BufferedImage image;
		String url = null;
		try {
			image = new Robot().createScreenCapture(rectangle);
			long name = System.currentTimeMillis();
			url = outPut + "/" + name + "." + extension;
			File file = new File(url);
			file.createNewFile();
			System.out.println(url);
			ImageIO.write(image, extension, new File(url));
			int x = image.getWidth() - 165;
			int y = image.getHeight() - 65;
			addWaterMark(url, "Badreddine", extension, x, y);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;

	}

	public static void addWaterMark(String path, String mark, String extension,
			int x, int y) throws MalformedURLException, IOException {

		final BufferedImage image = ImageIO.read(new File(path));

		Graphics g = image.getGraphics();
		Font font = new Font("Times New Roman", Font.BOLD, 15);
		g.setFont(font);

		URL url = ClassLoader.getSystemClassLoader().getResource("k" + ".png");
		Image image1 = Toolkit.getDefaultToolkit().getImage(url);

		g.drawImage(image1, x, y, null);
		g.setColor(Color.BLUE);

		// g.drawString(mark, x, y);
		g.dispose();

		ImageIO.write(image, extension, new File(path));
	}

	public static String chooseFile(Component c) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (fileChooser.showOpenDialog(c) == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile().getAbsolutePath();

		}
		return "C:/Users/Public/Pictures";
	}
}
