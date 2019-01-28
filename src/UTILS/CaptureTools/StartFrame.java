package UTILS.CaptureTools;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public abstract class StartFrame extends JDialog {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame() {
						@Override
						public void chargerImage(String st) {
							// TODO Auto-generated method stub

						}
					};
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public abstract void chargerImage(String st);

	public StartFrame() {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(295, 167);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("تصوير الشاشة");
		JButton btnDemmarer = new JButton("تصوير الشاشة");
		Font font = new Font("Times New Roman", Font.BOLD, 22);
		btnDemmarer.setFont(font);	
		btnDemmarer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new SelectionZoneFrame("") {

					private static final long serialVersionUID = 1L;

					@Override
					public void chargerImage(String url) {
						StartFrame.this.chargerImage(url);
						StartFrame.this.dispose();
					}
				}.setVisible(true);
				StartFrame.this.dispose();
			}
		});
		contentPane.add(btnDemmarer, BorderLayout.CENTER);
	}

}
