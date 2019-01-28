//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2018  
package THIS_APPLICATION;

import org.hibernate.Session;

import CRUD.ArticleCRUD;
import CRUD.EtabCRUD;
import CRUD.NewHibernateUtil;
import Modele.Etab;

public class Loaded {
	public static Session session;
	private static Etab etablissement;

	public static Etab getEtab() {
		if (etablissement == null) {
			etablissement = new EtabCRUD(Loaded.getSessionP()).getEtab(1);

		}
		return etablissement;
	}

	private static ArticleCRUD articleCRUD;

	public static ArticleCRUD getArticleCRUD() {
		if (articleCRUD == null) {
			articleCRUD = new ArticleCRUD();
		}
		return articleCRUD;
	}

	public static Session getSessionP() {
		if (session == null) {
			session = NewHibernateUtil.getSessionfactory().openSession();
		} else {
			return session;
		}
		return session;
	}

}
