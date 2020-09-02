package com.adellapo.sisfac.seguridad.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.adellapo.sisfac.core.AbstractManagedBean;
import com.adellapo.sisfac.entidad.Usuario;
import com.adellapo.sisfac.negocio.UsuarioFacade;

@ManagedBean(name = "myLoginBean")
@ViewScoped
public class LoginBean extends AbstractManagedBean {

	private String usuario;
	private String clave;
	private Usuario usuarioSession;

	@EJB
	private UsuarioFacade adminUsuario;

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * @return the usuarioSession
	 */
	public Usuario getUsuarioSession() {
		return usuarioSession;
	}

	/**
	 * @param usuarioSession the usuarioSession to set
	 */
	public void setUsuarioSession(Usuario usuarioSession) {
		this.usuarioSession = usuarioSession;
	}

	/**
	 * Metodo para validar el usuario
	 */
	public void validarUsuario() {

		try {

			usuarioSession = adminUsuario.validarUsuario(this.usuario, this.clave);

			anadirMensajeInformacion("Usuario encontrado");

			FacesContext.getCurrentInstance().getExternalContext().redirect("./principal.adp");

		} catch (Exception e) {

			anadirMensajeAdvertencia(e.getMessage());

		}

	}

}
