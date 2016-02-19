package com.algaworks.financeiro.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.algaworks.financeiro.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	public UsuarioController() {
		usuario = new Usuario();
		SecurityContext context = SecurityContextHolder.getContext();
		if (context instanceof SecurityContext) {
			Authentication authentication = context.getAuthentication();
			if (authentication instanceof Authentication) {
				usuario.setUsername(((User) authentication.getPrincipal()).getUsername());
				System.out.println("getPrincipal(): "
				+ ((User) authentication.getPrincipal()).getUsername() + "UsuárioID" + usuario.getId());

			}
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}