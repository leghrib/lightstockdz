package UTILS.CaptureTools;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import UTILS.Parametres.*;
public abstract class SelectionZoneFrame extends JFrame {

	/**
	 * 
	 */
	public abstract void chargerImage(String url);

	private static final long serialVersionUID = 6381666900295353036L;
	private JPanel contentPane;
	Point pointStart = new Point(0, 0);
	Point pointEnd = new Point(0, 0);
	boolean isResizing = false;
	private JLabel lblzone;
	JPopupMenu jPopupMenu;

	public static void main(String[] args) {
		new SelectionZoneFrame("") {

			@Override
			public void chargerImage(String url) {
				// TODO Auto-generated method stub

			}
		}.setVisible(true);
	}

	public SelectionZoneFrame(String url) {
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 829);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("pressed");
				isResizing = true;
				pointStart = e.getPoint();
				pointEnd = e.getPoint();

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				draw();
				System.out.println("clicked");
			}

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				System.out.println("wheel moved");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				pointEnd = e.getPoint();
				isResizing = false;

				System.out.println("released");
				draw();

				jPopupMenu.show(SelectionZoneFrame.this, e.getX(), e.getY());

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("dragged");

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println("moved");

			}

		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblzone = new JLabel("اختر المساحة التي تريد تصويرها");
		lblzone.setForeground(Color.RED);
		lblzone.setHorizontalTextPosition(SwingConstants.CENTER);
		lblzone.setHorizontalAlignment(SwingConstants.CENTER);
		lblzone.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblzone.setOpaque(true);
		lblzone.setBackground(Color.CYAN);
		lblzone.setBorder(new LineBorder(Color.red, 2));
		lblzone.setBounds(400, 200, 430, 148);
		contentPane.add(lblzone);
		jPopupMenu = new JPopupMenu();
		jPopupMenu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		Font MENU_FONT = new Font("Times New Roman", Font.BOLD, 14);
		// ========================================================
		JMenu menuSaveAs = new JMenu("حفظ");
		menuSaveAs.setFont(MENU_FONT);
		menuSaveAs.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		jPopupMenu.add(menuSaveAs);

		// ==========================================//===================
		JMenuItem menuSaveJPG = new JMenuItem("JPG");
		menuSaveJPG.setFont(MENU_FONT);
		menuSaveJPG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				preparerEcran();
				String url = Capture.capture(lblzone.getBounds(), "jpg",Parameter.FOLDER_TEMP);
				chargerImage(url);
				SelectionZoneFrame.this.dispose();
				;
			}
		});
		menuSaveAs.add(menuSaveJPG);

		// ==========================================//===================
		JMenuItem menuSaveBMP = new JMenuItem("BMP");
		menuSaveBMP.setFont(MENU_FONT);
		menuSaveBMP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				preparerEcran();
				String url = Capture.capture(lblzone.getBounds(), "bmp",Parameter.FOLDER_TEMP);
				chargerImage(url);
				SelectionZoneFrame.this.dispose();
				;
			}
		});
		menuSaveAs.add(menuSaveBMP);

		// ==========================================
		// ==========================================//===================
		JMenuItem menuSavePNG = new JMenuItem("PNG");
		menuSavePNG.setFont(MENU_FONT);
		menuSavePNG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				preparerEcran();
				String url = Capture.capture(lblzone.getBounds(), "png",Parameter.FOLDER_TEMP);
				chargerImage(url);
				SelectionZoneFrame.this.dispose();
				;
			}
		});
		// menuSaveAs.add(menuSavePNG);
		Drawing();
		setOpacity(0.65f);
	}

	void Drawing() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(10);
						while (isResizing) {
							Thread.sleep(10);

							draw();
						}
					}
				} catch (Exception e) {
				}
			}
		});
		t.start();
	}

	void draw() {
		pointEnd = MouseInfo.getPointerInfo().getLocation();
		Point startDrawingPoint = getStartDrawingPoint();
		lblzone.setBounds(startDrawingPoint.x, startDrawingPoint.y,
				Math.abs(pointEnd.x - pointStart.x),
				Math.abs(pointEnd.y - pointStart.y));
		lblzone.setText(lblzone.getHeight() + " X " + lblzone.getWidth());

	}

	void preparerEcran() {
		setVisible(false);
		jPopupMenu.setVisible(false);
	}

	Point getStartDrawingPoint() {
		int x = (pointStart.x > pointEnd.x) ? pointEnd.x : pointStart.x;
		int y = (pointStart.y > pointEnd.y) ? pointEnd.y : pointStart.y;
		return new Point(x, y);
	}

	Rectangle getScreenRec() {
		return new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	}

	// public String cutImage(String url, java.awt.Rectangle rectangle) {
	//
	// try {
	// Image image=ImageIO.read(new File(url));
	// Image image1=image.getScaledInstance(rectangle.width, rectangle.height,
	// 1);
	// ImageIO.write(image1., "png", new File(""));
	//
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return "";
	// }
}
