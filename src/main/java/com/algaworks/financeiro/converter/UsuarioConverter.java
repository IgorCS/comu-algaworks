package com.algaworks.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.algaworks.financeiro.model.Usuario;
import com.algaworks.financeiro.repository.Usuarios;
import com.algaworks.financeiro.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	@Inject // funciona graças ao OmniFaces
	private Usuarios usuarios;
	
	public UsuarioConverter() {
		this.usuarios = (Usuarios) CDIServiceLocator.getBean(Usuarios.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = this.usuarios.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Usuario usuario = ((Usuario) value); 
			return usuario.getId() == null ? null : usuario.getId().toString();
			//return ((Pessoa) value).getId().toString();
		}
		return null;
	}
	/*@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Lancamento lancamento = ((Lancamento) value); 
			return lancamento.getId() == null ? null : lancamento.getId().toString();
		}
		return null;
	}*/
}