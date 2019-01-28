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

import Modele.Cmd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CmdCRUD {
	private Session session;

	public CmdCRUD(Session session) {

		if (session == null) {
			session = NewHibernateUtil.getSessionfactory().openSession();
		} else {
			this.session = session;
		}
	}

	public ObservableList<Cmd> getCollection(String recherche, int LD) {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Cmd> criteriaQuery = builder.createQuery(Cmd.class);
		Root<Cmd> root = criteriaQuery.from(Cmd.class);
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

		List<Cmd> persons = session.createQuery(criteriaQuery).getResultList();
		return FXCollections.observableArrayList(persons);
	}

	public Cmd ajouter(Cmd cmd) {

		try {

			session.beginTransaction();
			session.save("Cmd", cmd);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {
		}
		return cmd;
	}

	public boolean modifier(Cmd o) {

		try {

			session.beginTransaction();
			session.save("Cmd", o);
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
		CriteriaUpdate<Cmd> criteria = builder.createCriteriaUpdate(Cmd.class);
		Root<Cmd> root = criteria.from(Cmd.class);
		criteria.set(root.get("ld"), true);
		criteria.where(builder.equal(root.get("idCmd"), id));
		int updated = session.createQuery(criteria).executeUpdate();
		session.getTransaction().commit();
		return updated == 1;

	}

	public Cmd getCmd(int i) {
		Cmd cmd = null;

		try {

			session.beginTransaction();

			cmd = (Cmd) session.get(Cmd.class, i);
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
