package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.financeiro.model.Usuario;
import com.algaworks.financeiro.repository.Usuarios;
import com.algaworks.financeiro.service.CadastroUsuarios;
import com.algaworks.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// private String nome;
	private String usuarioLogin;

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	@Inject
	private CadastroUsuarios cadastroUsuario;

	@Inject
	private Usuario usuario;

	private List<Usuario> listaUsuario;

	@Inject
	private Usuarios usuarios;

	private List<String> usuarioExiste;

	public List<String> getUsuarioExiste() {
		return this.usuarioExiste;
	}

	public void prepararCadastroUsuario() {

		if (this.usuario == null) {
			this.usuario = new Usuario();
		}
	}

	public List<String> usuariosExistentes(String nome) {
		return this.usuarios.usuarioExiste(nome);
	}

	public void verificarDisponibilidadeLogin() {
		FacesMessage msg = null;
		// simula demora no processamento
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		// HashMap para verificar se o usuário retorna uma chave e verifica se
		// existe
		Map<Integer, String> usuariosExistentes = new HashMap<Integer, String>();
		List<String> nomesExistentes = new ArrayList<String>();

		nomesExistentes.addAll(usuarios.usuarioExiste(usuarioLogin));
		// Aqui faz a chamada do Metódo e verifica se o LOGIN está disponível
		this.usuarioExiste = this.usuarios.usuarioExiste(usuarioLogin);
		// Aqui eu passo um Objeto do Tipo inteiro e um do tipo String e
		// acrescento minha coleção de String
		// à uma referência chamada (código=1) para melhor visualizar o que está
		// no Array de String e nome ser
		// apontado.
		usuariosExistentes.put(new Integer(1), nomesExistentes.toString());
		nomesExistentes = new ArrayList<String>();
		// No laço é que eu verifico os usuários e na repetição ele verifica o
		// usuário existente
		// no banco de dados
		int aux = 0;
		boolean find = false;
		for (int i = 0; i < this.usuarios.usuarioExiste(usuarioLogin).size(); i++) {
			for (Integer codigo : usuariosExistentes.keySet()) {
				for (String nomeUsuario : usuariosExistentes.values()) {
					if (this.usuarios.usuarioExiste(usuarioLogin).get(i).contains(this.usuario.getNome())) {
						System.out.println(" A POSIÇÃO DO ARRAY DO NOME DIGITADO É: " + i
								+ " E O USUARIO DIGITADO FOI: " + this.usuario.getNome()
						// " ARRAY DE NOMES DE
						// USUARIOS:"+this.usuarios.usuarioExiste(usuarioLogin)
						);

					}
					aux = i;
					find = true;
				}

			}

		}
		// Aqui é a condição para que o usuário possa verificar na view se o
		// LOGIN que foi digitado
		// por ele existe ou não.
		if (usuarioExiste.contains(this.usuario.getNome()) && find == true) {
			msg = new FacesMessage("Este Login já está em uso!");
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
		} else {
			msg = new FacesMessage("Login disponível.");
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void salvarUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			this.cadastroUsuario.salvarUsuario(this.usuario);

			this.usuario = new Usuario();

			int Z = (this.usuarios.usuarioExiste(usuarioLogin).size() - 1);

			System.out.println("polyPosition: " + Z);

			context.addMessage(null, new FacesMessage("Olá " + (this.usuarios.usuarioExiste(usuarioLogin).get(Z))
					+ " você foi cadastrado(a) com sucesso!"));

		} catch (NegocioException e) {

			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public List<Usuario> getTodosUsuarios() {
		return this.listaUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/*
	 * public String getNome() { return nome; }
	 * 
	 * public void setNome(String nome) { this.nome = nome; }
	 */

}
