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

import Modele.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientCRUD {
	private Session session;

	public ClientCRUD(Session session) {
		if (session == null) {
			session = NewHibernateUtil.getSessionfactory().openSession();
		} else {
			this.session=session;
		}
	}

	public ObservableList<Client> getCollection(String recherche, int LD) {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
		Root<Client> root = criteriaQuery.from(Client.class);
		criteriaQuery.select(root);
		// criteria.w

		ArrayList<Predicate> arrayList = new ArrayList<Predicate>();
		if (LD != UTILS.UTILS.DELETED_AND_NON_DELETED) {
			arrayList.add(builder.equal(root.get("ld"), LD));
		}
		if (recherche != null && recherche != "") {
			arrayList.add(builder.like(root.get("raisonSociale"), "%" + recherche + "%"));
		}
		Predicate[] predicates = arrayList.toArray(new Predicate[arrayList.size()]);
		criteriaQuery.where(predicates);

		List<Client> persons = session.createQuery(criteriaQuery).getResultList();
		return FXCollections.observableArrayList(persons);
	}

	public boolean ajouter(Client insc) {

		try {

			session.beginTransaction();
			session.save("Client", insc);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;

		} finally {
		}
		return true;
	}

	public boolean modifier(Client o) {

		try {

			session.beginTransaction();
			session.update("Client", o);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;

		} finally {
		}
		return true;
	}

	public boolean supprimer(Integer idClient) {
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaUpdate<Client> criteria = builder.createCriteriaUpdate(Client.class);
		Root<Client> root = criteria.from(Client.class);
		criteria.set(root.get("ld"), true);
		criteria.where(builder.equal(root.get("idClient"), idClient));
		int updated = session.createQuery(criteria).executeUpdate();
		session.getTransaction().commit();
		return updated == 1;

	}

}
