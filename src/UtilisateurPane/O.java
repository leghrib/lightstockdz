//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package UtilisateurPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class O {
	public static int AJOUTER_LES_EMPLOYEES = 1;
	public static int SUPPRIMER_LES_EMPLOYEES = 2;
	public static int MODIFIER_LES_EMPLOYEES = 3;
	public static int SUPPRIMER_LES_ELEVES = 4;
	public static int AJOUTER_LES_ELEVES = 5;
	public static int MODIFIER_LES_ELEVES = 6;
	// = Taches ===============================================
	public static int GERER_LES_EMPLOYEES = 1;
	public static int GERER_LES_ELEVES = 2;
	public static int GERER_LES_MATIERES = 3;
	public static int GERER_LES_FILIERES = 4;

	public static void main(String[] args) {
		printOperation();
		System.out.println("//= Taches ===============================================");
		printTaches();
	}

	private static void printTaches() {
		try {
			ResultSet rs = ConnectionDB.getCon().createStatement().executeQuery("select * from tache order by idtache");
			while (rs.next()) {
				String ligne = "public static int " + rs.getNString("nomTache") + " = " + rs.getInt("idtache") + ";";
				System.out.println(ligne);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void printOperation() {
		try {
			ResultSet rs = ConnectionDB.getCon().createStatement()
					.executeQuery("select * from operation order by idtache,idoperation");
			while (rs.next()) {
				String ligne = "public static int " + rs.getNString("nomOperation") + " = " + rs.getInt("idOperation")
						+ ";";
				System.out.println(ligne);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean isActive(int idOperation) {
		return true;
//		Set<Operationutilisateur> operations = LoginPaneController.UTILISATEUR.getPersonne().getOperationutilisateurs();
//
//		for (Operationutilisateur operationutilisateur : operations) {
//			if (operationutilisateur.getOperation().getIdOperation() == idOperation) {
//				return true;
//			}
//		}
//
//		return false;
	}

}
