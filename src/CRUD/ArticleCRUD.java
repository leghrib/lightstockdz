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

import Modele.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArticleCRUD {
	private Session session;

	public ArticleCRUD(Session session) {

		this.session = session;
	}

	public ArticleCRUD() {

		session = NewHibernateUtil.getSessionfactory().openSession();
	}

	public ObservableList<Article> getCollection(String recherche, int LD) {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Article> criteriaQuery = builder.createQuery(Article.class);
		Root<Article> root = criteriaQuery.from(Article.class);
		criteriaQuery.select(root);
		// criteria.w

		ArrayList<Predicate> arrayList = new ArrayList<Predicate>();
		if (LD != UTILS.UTILS.DELETED_AND_NON_DELETED) {
			arrayList.add(builder.equal(root.get("ld"), LD));
		}
		if (recherche != null && recherche != "") {
			arrayList.add(builder.like(root.get("ref"), "%" + recherche + "%"));
		}
		Predicate[] predicates = arrayList.toArray(new Predicate[arrayList.size()]);
		criteriaQuery.where(predicates);

		List<Article> persons = session.createQuery(criteriaQuery).getResultList();
		return FXCollections.observableArrayList(persons);
	}

	public boolean ajouter(Article a) {

		try {

			session.beginTransaction();
			session.save("Article", a);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;

		} finally {
		}
		return true;
	}

	public boolean modifier(Article o) {

		try {

			session.beginTransaction();
			session.update("Article", o);
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
		CriteriaUpdate<Article> criteria = builder.createCriteriaUpdate(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.set(root.get("ld"), true);
		criteria.where(builder.equal(root.get("idArticle"), id));
		int updated = session.createQuery(criteria).executeUpdate();
		session.getTransaction().commit();
		return updated == 1;

	}

	public double getResteQte(int idArticle) {
		double qte = (double) session.createSQLQuery("select getResteQte(" + idArticle + ")").uniqueResult();

		return qte;
	}

	public void refreshArticleQte(Article article) {

		article.setQte(getResteQte(article.getIdArticle()));
		modifier(article);

	}

}
