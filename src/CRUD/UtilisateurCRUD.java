//DEVELOPPEUR LEGHRIB BADREDDINE D-SYS 1997-2017  
package CRUD;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import Modele.Utilisateur;

public class UtilisateurCRUD {

	public static void main(String[] args) {

		// ajouterUtilisateur(Utilisateur.EXAMPLE);
		List<Integer> integers = new ArrayList<Integer>();
		integers.add(1);
		getUtilisateurCollection(integers);
	}

	public static boolean ajouterUtilisateur(Utilisateur Utilisateur) {

		Session session = NewHibernateUtil.getSessionfactory().openSession();

		try {

			session.beginTransaction();
			session.save(Utilisateur);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();

			e.printStackTrace();
			return false;
		} finally {
		}
		return true;
	}

	public static java.util.List<Utilisateur> getUtilisateurCollection() {

		Session session = NewHibernateUtil.getSessionfactory().openSession();

		try {
			Criteria q = session.createCriteria(Utilisateur.class);
			return q.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static boolean modifierUtilisateur(Utilisateur Utilisateur) {
		Session session = NewHibernateUtil.getSessionfactory().openSession();

		try {

			session.beginTransaction();
			session.update(Utilisateur);
			session.getTransaction().commit();

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
 		}
		return true;
	}

	public static boolean supprimerUtilisateur(Utilisateur Utilisateur) {
		Session session = NewHibernateUtil.getSessionfactory().openSession();

		try {

			session.beginTransaction();
			session.delete(session.get(Utilisateur.class, Utilisateur.getIdUtilisateur()));
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;

		} finally {
			session.close();
		}
		return true;
	}

	public static Utilisateur getUtilisateur(int i) {
		Utilisateur Utilisateur = null;
		Session session = NewHibernateUtil.getSessionfactory().openSession();

		try {

			session.beginTransaction();

			Utilisateur = (Utilisateur) session.get(Utilisateur.class, i);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();

		} finally {
			session.close();
		}
		return Utilisateur;
	}

	public static List<Utilisateur> getUtilisateurCollection(List<Integer> list) {

		Session session = NewHibernateUtil.getSessionfactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Utilisateur> criteriaQuery = builder.createQuery(Utilisateur.class);
		Root<Utilisateur> root = criteriaQuery.from(Utilisateur.class);
		criteriaQuery.select(root).where(root.get("type").in(list));
		// criteria.w

		List<Utilisateur> persons = session.createQuery(criteriaQuery).getResultList();
		return persons;

	}

	public static Utilisateur getUtilisateur(String pass, String username) {

		Session session = NewHibernateUtil.getSessionfactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Utilisateur> criteriaQuery = builder.createQuery(Utilisateur.class);
		Root<Utilisateur> root = criteriaQuery.from(Utilisateur.class);
		// criteria.w
		Predicate[] predicates = new Predicate[] { builder.equal(root.get("password"), pass),
				builder.equal(root.get("username"), username)

		};

		criteriaQuery.select(root).where(predicates);
		Utilisateur utilisateur;
		try {
			utilisateur = session.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			return null;
		}
		return utilisateur;
	}

}
