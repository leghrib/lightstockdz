package UTILS.Traduction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class OperationTraduction {
	private static Connection connection = connect();

	public static Connection connect() {
		try {
			String url = "jdbc:mysql://localhost:3306/traduction";
			String user = "root";
			String pass = "";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);

			return con;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					M.Probleme_de_connexion_au_serveur);
		}

		return null;

	}

	public static Connection getConnection() {
		return connection;
	}

	public static String translate(String word, int idLangue0, int idLangue1) {
		try {
			String requete0 = "select * from traduction where idLangue="
					+ idLangue0 + " and word = '" + word + "'";
			int idGlossaire = -1;
			ResultSet rs = getConnection().createStatement().executeQuery(
					requete0);
			if (rs.next()) {
				idGlossaire = rs.getInt("idGlossaire");
			} else {
				return "INTROUVABLE";
			}

			String requete = "select * from traduction where idGlossaire="
					+ idGlossaire + " and idLangue=" + idLangue1;
			rs = getConnection().createStatement().executeQuery(requete);
			if (rs.next()) {
				return rs.getString("word");
			} else {
				return "INTROUVALE DANS CETTE LANGUE";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "introuvable";
		}

	}

}
