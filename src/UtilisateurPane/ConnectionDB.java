package UtilisateurPane;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectionDB {
	public static Connection con;// =connectNetwork();
	public static Connection conZaki;// = connectZaki();
	// http://46.101.87.92:5000/

	private static Connection connect() {
		try {
			String url = "jdbc:mysql://localhost:3306/sgs";
			System.out.println(url);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"root", "");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Probleme Connexion au serveur , ressayer", "Erreur",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			return null;

		}

	}

	public static Connection connectNetwork() {

		try {
			String url = "jdbc:mysql://MySql.Kacimi-dz.com:3306/scsem479_kacimi-1";
			String user = "scsem479_mlk";
			String pass = "Dsys2016";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Probleme de connexion au serveur");
		}
		return null;

	}

	public static Connection connectZaki() {
		Connection connn = null;

		try {
			String url = "jdbc:mysql://localhost:3306/oeuvresbijaya";
			String user = "root";
			String pass = "";
			Class.forName("com.mysql.jdbc.Driver");

			connn = DriverManager.getConnection(url, user, pass);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Probleme de connexion au serveur");
		}

		return connn;

	}

	public static Connection getCon() {
		if (con == null) {
			return con = connect();
		}
		return con;
	}

	public static void refreshCon() {
		con = connect();
	}

	public void repare() {
		String requete = "" + " alter table client   auto_increment=1;" + "alter table personne  auto_increment=1;"
				+ "alter table carte  auto_increment=1;" + "alter table fournisseur  auto_increment=1;"
				+ "alter table compteapplication  auto_increment=1;" + "alter table compteccp  auto_increment=1;"
				+ "alter table clientcmd  auto_increment=1;" + "alter table clientcmdarticle  auto_increment=1;"
				+ "alter table clientlivraison  auto_increment=1;" + "alter table photo  auto_increment=1"
				+ "alter table prelevement  auto_increment=1;" + "alter table versement  auto_increment=1;";
		System.out.println(requete);

		/*
		 * 
		 * 
		 * 
		 * delete from personne;
		 * 
		 * alter table client auto_increment=1; alter table utilisateur
		 * auto_increment=1; alter table personne auto_increment=1; alter table
		 * carte auto_increment=1; alter table fournisseur auto_increment=1;
		 * alter table compteapplication auto_increment=1; alter table compteccp
		 * auto_increment=1; alter table clientcmd auto_increment=1; alter table
		 * clientcmdarticle auto_increment=1; alter table clientlivraison
		 * auto_increment=1; alter table photo auto_increment=1; alter table
		 * prelevement auto_increment=1; alter table versement auto_increment=1;
		 */
		// GRANT ALL ON *.* to root@'%' IDENTIFIED BY '';

	}
}
