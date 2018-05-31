package br.com.caelum.livraria.webService;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.interceptador.LogInterceptador;
import br.com.caelum.livraria.modelo.Livro;

@WebService
@Stateless
@Interceptors({ LogInterceptador.class })
public class LivrariaWS {

	@Inject
	LivroDao livroDao;

	@WebResult(name = "autores")
	public List<Livro> getLivrosPeloNome(@WebParam(name="titulo") String nome) {
		List<Livro> retorno = (List<Livro>) livroDao.searchBookName(nome);

		System.out.println("LivrariaWS: teste");
		return retorno;
	}
}
