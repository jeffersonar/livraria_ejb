package br.com.caelum.livraria.dao;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LivroDao {

	@PersistenceContext
	private EntityManager em;

	public void salva(Livro livro) {
		em.persist(livro);
	}

	public List<Livro> todosLivros() {
		return em.createQuery("select l from Livro l", Livro.class).getResultList();
	}

	public Collection<Livro> searchBookName(String nome) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Livro> query = criteriaBuilder.createQuery(Livro.class);
		Root<Livro> root = query.from(Livro.class);
		root.join("autor", JoinType.LEFT);
		Path<String> nomePath = root.<String>get("titulo");

		Predicate predicateNome = criteriaBuilder.like(nomePath, nome);

		query.where(predicateNome);

		TypedQuery<Livro> typedQuery = em.createQuery(query);
		return typedQuery.getResultList();
	}

}
