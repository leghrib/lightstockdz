package UTILS.WebCamTool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;



public abstract class TakePhotoFrame extends JFrame {
	BufferedImage buff;
	private static final long serialVersionUID = 1L;
	private WebCamPlayer myThread = null;
	int count = 0;
	VideoCapture webSource = null;
	Mat frame;
	MatOfByte mob;

	public static void main(String[] args) {
		new TakePhotoFrame() {
			private static final long serialVersionUID = 1L;

			@Override
			public void returnCapturedPhoto(String urlPhoto) {
				System.out.println(urlPhoto);
			}
		}.setVisible(true);

	}

	public abstract void returnCapturedPhoto(String urlPhoto);

	public TakePhotoFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				putCameraOff();

			}
		});
		setTitle("D_sys - Prendre une photo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"image/icon/camera32.png"));
		setSize(512, 512);
		setLocationRelativeTo(null);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		frame = new Mat();
		mob = new MatOfByte();
		screen = new javax.swing.JPanel();
		screen.setBackground(Color.WHITE);
		startBtn = new javax.swing.JButton();
		stopBtn = new javax.swing.JButton();
		stopBtn.setIcon(new ImageIcon("image/icon/camera32.png"));

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		startBtn.setText("اعادة المحاولة");
		startBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		stopBtn.setText("التقاط صورة");
		stopBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});

		getContentPane().add(screen, java.awt.BorderLayout.CENTER);

		getContentPane().add(startBtn, java.awt.BorderLayout.PAGE_START);

		getContentPane().add(stopBtn, java.awt.BorderLayout.PAGE_END);

		setVisible(true);
		myThread = new WebCamPlayer();
		start();

	}

	protected void putCameraOff() {
		myThread.runnable = false;
		webSource.release();
	}

	public void start() {

		webSource = new VideoCapture(0);
		myThread.runnable = true;
		Thread player = new Thread(myThread);
		player.setDaemon(true);
		player.start();
		startBtn.setEnabled(false);
		stopBtn.setEnabled(true);

	}

	public void stop() {

		putCameraOff();

		startBtn.setEnabled(true);
		stopBtn.setEnabled(false);

		try {
			File outputfile = new File("image/temp/" + new Date().getTime()
					+ ".jpg");
			outputfile.createNewFile();
			ImageIO.write(buff, "jpg", outputfile);
			returnCapturedPhoto(outputfile.getPath());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class WebCamPlayer implements Runnable {
		protected volatile boolean runnable = false;

		@Override
		public void run() {

			synchronized (this) {
				while (runnable) {
					if (webSource.grab()) {
						try {
							webSource.retrieve(frame);
							Highgui.imencode(".bmp", frame, mob);

							Image im = ImageIO.read(new ByteArrayInputStream(
									mob.toArray()));

							buff = (BufferedImage) im;
							Graphics g = screen.getGraphics();

							if (g.drawImage(buff, 0, 0, getWidth(),
									getHeight() - 150, 0, 0, buff.getWidth(),
									buff.getHeight(), null))

								if (runnable == false) {

									this.wait();
								}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	private javax.swing.JPanel screen;
	private javax.swing.JButton startBtn;
	private javax.swing.JButton stopBtn;
}
