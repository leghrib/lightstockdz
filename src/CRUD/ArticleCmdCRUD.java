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

import Modele.Cmdarticle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArticleCmdCRUD {
	private Session session;

	public ArticleCmdCRUD() {

		session = NewHibernateUtil.getSessionfactory().openSession();
	}

	public ObservableList<Cmdarticle> getCollection(String recherche ) {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Cmdarticle> criteriaQuery = builder.createQuery(Cmdarticle.class);
		Root<Cmdarticle> root = criteriaQuery.from(Cmdarticle.class);
		criteriaQuery.select(root);
		// criteria.w

		ArrayList<Predicate> arrayList = new ArrayList<Predicate>();
		 
		if (recherche != null && recherche != "") {
			arrayList.add(builder.equal(root.get("idCmdArticle"), UTILS.UTILS.getIntFromString(recherche)));
		}
		Predicate[] predicates = arrayList.toArray(new Predicate[arrayList.size()]);
		criteriaQuery.where(predicates);

		List<Cmdarticle> persons = session.createQuery(criteriaQuery).getResultList();
		return FXCollections.observableArrayList(persons);
	}

	

	public Cmdarticle  ajouter(Cmdarticle cmd) {

		try {

			session.beginTransaction();
			 session.save("Cmdarticle", cmd);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {
		}
		return cmd;
	}

	public boolean modifier(Cmdarticle o) {

		try {

			session.beginTransaction();
			session.update("Cmdarticle", o);
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
		CriteriaUpdate<Cmdarticle> criteria = builder.createCriteriaUpdate(Cmdarticle.class);
		Root<Cmdarticle> root = criteria.from(Cmdarticle.class);
		criteria.set(root.get("ld"), true);
		criteria.where(builder.equal(root.get("idCmdarticle"), id));
		int updated = session.createQuery(criteria).executeUpdate() ;
		session.getTransaction().commit();
		return updated==1;
		

	}
	
	public  Cmdarticle getCmd(int i) {
		Cmdarticle cmd = null;


		try {

			session.beginTransaction();

			cmd = (Cmdarticle) session.get(Cmdarticle.class, i);
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
