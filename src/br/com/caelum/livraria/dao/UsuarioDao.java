package br.com.caelum.livraria.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Usuario;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioDao {
	@PersistenceContext
	private EntityManager em;

	public Usuario buscaPeloLogin(String login) {
		Usuario usuario= (Usuario) this.em.createQuery("select u from Usuario u where u.login = :pLogin ")
				.setParameter("pLogin",login).getSingleResult();
		
		return usuario;
	}
	
}
