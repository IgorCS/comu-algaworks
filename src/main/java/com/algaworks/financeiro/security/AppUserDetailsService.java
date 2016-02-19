package com.algaworks.financeiro.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.algaworks.financeiro.model.Grupo;
import com.algaworks.financeiro.model.Usuario;
import com.algaworks.financeiro.repository.Usuarios;
import com.algaworks.financeiro.util.AppUser;
import com.algaworks.financeiro.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
		Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
		Usuario usuario = usuarios.porNome(nome);

		UserSistema user = null;

		if (usuario != null) {
			user = new UserSistema(usuario, getGrupos(usuario));
			System.out.printf("Buscando o usuário da Lista: " + usuarios.porNome(nome));
		}

		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		if ((usuario.getGrupos().isEmpty())) {

			AppUser anonymous = new AppUser();
			anonymous.add(anonymous.getGrupoNome());

			{
				authorities.add(new SimpleGrantedAuthority(anonymous.getGrupoNome()));
				System.out.println("GRUPOS DE USUÁRIOS ANONYMOUS: " + anonymous.getGrupoNome());

			}
		} else {

			for (Grupo grupo : usuario.getGrupos()) {
				authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
				System.out.println("GRUPOS DE USUÁRIOS: " + grupo.getNome().toUpperCase() + " GRUPOS DE USUÁRIOS ID ="
						+ grupo.getId());

			}

		}
		return authorities;
	}

}
