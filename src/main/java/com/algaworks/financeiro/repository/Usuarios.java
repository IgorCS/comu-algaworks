package com.algaworks.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.algaworks.financeiro.controller.UsuarioController;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.Usuario;
import com.algaworks.financeiro.security.UserSistema;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;

	@Inject
	public Usuarios(EntityManager manager) {
		this.manager = manager;
	}
	
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}
	
	public void adicionar(Usuario usuario){
		this.manager.persist(usuario);
	}

	public Usuario guardar(Usuario usuario){
		return this.manager.merge(usuario);
	}
	
	public Usuario porUsername(String username) {
		return manager.find(Usuario.class, username);
	}
	
	    //Nesta TypedQuery faço a consulta que vai ser mapeada no metódo verificaLoginDisponibilidade.
	   //Neste caso passo um parâmetro fazendo referência à um  objeto  no meu Bean para receber _
	  //,o que o usuário enviou no form.
	 //obs: Veja que a consulta retorna apenas uma lista e no HashMap retorna uma Key para,
	//saber se o nome digitado existe ou não
		
		public List<String> usuarioExiste(String usuarioLogin) {
			TypedQuery<String> query = manager.createQuery(
					"select nome from Usuario " ,
			         String.class);
			 //Não precisa usar parâmetro pois a Key aponta pro usuário que não tem no banco de dados
			//query.setParameter("usuarioLogin", "%" + "" + "%");
			return query.getResultList();
		}

	
	
	
	public List<Usuario> todosUsuarios(String nome) {
		UsuarioController user = new UsuarioController();		
		TypedQuery<Usuario> query = manager.createQuery("from Usuario u WHERE " +
				"u.nome=:nome"
				,Usuario.class);
		query.setParameter("nome",user.getUsuario().getUsername());
		return query.getResultList();
	}

	
	
	
	public Usuario porNome(String nome) {
		Usuario usuario = null;
		
		try {
			usuario = this.manager.createQuery("from Usuario where nome = :nome", Usuario.class)
				.setParameter("nome", nome.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
		
		}
		
		return usuario;
	}
	
	public Usuario porId(Usuario usuario) {
		//Usuario usuario = null;
		TypedQuery<Usuario> query = manager.createQuery("from Usuario u where u.usuario=:usuario ",
				Usuario.class);
		query.setParameter("usuario", usuario);
		return query.getSingleResult();
		
	}
	

	
}