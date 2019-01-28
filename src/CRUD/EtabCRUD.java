//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package CRUD;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import Modele.Cmd;
import Modele.Etab;

public class EtabCRUD {
	private Session session;

	public EtabCRUD() {

		session = NewHibernateUtil.getSessionfactory().openSession();
	}

	public EtabCRUD(Session session) {

		this.session = session;
	}

	public Etab getEtab(int i) {
		Etab etab = null;

		try {

			session.beginTransaction();

			etab = (Etab) session.get(Etab.class, i);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
		return etab;
	}

	public boolean ajouter(Etab a) {

		try {

			session.beginTransaction();
			session.save("Etab", a);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;

		} finally {
		}
		return true;
	}

	public boolean modifier(Etab o) {

		try {

			session.beginTransaction();
			session.update("Etab", o);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;

		} finally {
		}
		return true;
	}

	public boolean supprimer(Integer id) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaUpdate<Etab> criteria = builder.createCriteriaUpdate(Etab.class);
		Root<Etab> root = criteria.from(Etab.class);
		criteria.set(root.get("ld"), true);
		criteria.where(builder.equal(root.get("idEtab"), id));
		int updated = session.createQuery(criteria).executeUpdate();
		session.getTransaction().commit();
		return updated == 1;

	}

}
