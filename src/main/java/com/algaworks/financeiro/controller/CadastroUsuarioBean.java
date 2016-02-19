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

	//private String nome;
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
		
		//HashMap para verificar se o usuário retorna uma chave e verifica se existe 
		Map<Integer, String> usuariosExistentes = new HashMap<Integer, String>();
		List<String> nomesExistentes = new ArrayList<String>();
		
		nomesExistentes.addAll(usuarios.usuarioExiste(usuarioLogin));
		//Aqui faz a chamada do Metódo e verifica se o LOGIN está disponível 
		this.usuarioExiste = this.usuarios.usuarioExiste(usuarioLogin);
		//Aqui eu passo um Objeto do Tipo interio e um do tipo String e acrescento minha coleção de String
		//à um código para melhor visualizar o que está no Array de String
		usuariosExistentes.put(new Integer(1), nomesExistentes.toString());
		nomesExistentes = new ArrayList<String>();
        //No laço é que eu verifico os usuários e na repetição ele verifica o usuário existente 
		//no banco de dados 
		for (Integer codigo : usuariosExistentes.keySet()) {
			 // for (String nome : clientes.get(codigo)) {
			for (String nomeUsuario : usuariosExistentes.values()) {				
				System.out.println("Código: "          + codigo + "KeySet()=>"+usuariosExistentes.keySet()+  
						           "KeyValues()=>"+usuariosExistentes.values()+ 
	        	                   " - Cliente: "      + usuarios.usuarioExiste(usuarioLogin)
	        	                   +"ARRAY DE NOMES DE USUARIOS:"+nomeUsuario);
				System.out.println("KEYTOSEARCH: " + usuarios.usuarioExiste(usuarioLogin) 
				        + " USUARIO DIGITADO: "
						+ this.usuario.getNome());

			}
		}
        //Aqui é a condição para que o usuário possa verificar na view se o LOGIN que foi digitado
		//por ele existe ou não.
		if (usuarioExiste.contains(this.usuario.getNome())) {
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
			context.addMessage(null, new FacesMessage("Você foi cadastrado cadastrado(a) com sucesso!"));
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

	/*public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}*/

}
