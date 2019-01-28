//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package CRUD;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import Modele.Cmdf;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CmdfCRUD {
	private Session session;

	public CmdfCRUD(Session session) {

		if (session == null) {
			session = NewHibernateUtil.getSessionfactory().openSession();
		} else {
			this.session = session;
		}
	}

	public ObservableList<Cmdf> getCollection(String recherche, int LD) {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Cmdf> criteriaQuery = builder.createQuery(Cmdf.class);
		Root<Cmdf> root = criteriaQuery.from(Cmdf.class);
		criteriaQuery.select(root);
		// criteria.w

		ArrayList<Predicate> arrayList = new ArrayList<Predicate>();
		if (LD != UTILS.UTILS.DELETED_AND_NON_DELETED) {
			arrayList.add(builder.equal(root.get("ld"), LD));
		}
		// if (recherche != null && recherche != "") {
		// arrayList.add(builder.equal(root.get("idCmd"),
		// UTILS.UTILS.getIntFromString(recherche)));
		// }
		Predicate[] predicates = arrayList.toArray(new Predicate[arrayList.size()]);
		criteriaQuery.where(predicates);

		List<Cmdf> persons = session.createQuery(criteriaQuery).getResultList();
		return FXCollections.observableArrayList(persons);
	}

	public Cmdf ajouter(Cmdf cmd) {

		try {

			session.beginTransaction();
			session.save("Cmdf", cmd);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {
		}
		return cmd;
	}

	public boolean modifier(Cmdf o) {

		try {

			session.beginTransaction();
			session.save("Cmdf", o);
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
		CriteriaUpdate<Cmdf> criteria = builder.createCriteriaUpdate(Cmdf.class);
		Root<Cmdf> root = criteria.from(Cmdf.class);
		criteria.set(root.get("ld"), true);
		criteria.where(builder.equal(root.get("idCmdF"), id));
		int updated = session.createQuery(criteria).executeUpdate();
		session.getTransaction().commit();
		return updated == 1;

	}

	public Cmdf getCmd(int i) {
		Cmdf cmd = null;

		try {

			session.beginTransaction();

			cmd = (Cmdf) session.get(Cmdf.class, i);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
		return cmd;
	}

}
