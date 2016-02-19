package com.algaworks.financeiro.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.financeiro.model.Usuario;
import com.algaworks.financeiro.repository.Usuarios;
import com.algaworks.financeiro.util.Transactional;

public class CadastroUsuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public void salvarUsuario(Usuario usuario) throws NegocioException {
		if (usuario.getNome() == null) {
			throw new NegocioException(
					"Campo Usuario nao pode ser Vazio!");
		}
		
		if (usuario.getSenha() == null) {
			throw new NegocioException(
					"Campo Senha nao pode ser Vazio!");
		}
		
		if (usuario.getEmail() == null) {
			throw new NegocioException(
					"Campo Email nao pode ser Vazio!");
		}
		
		this.usuarios.adicionar(usuario);
		
	}
	
	
	
}