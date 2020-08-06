package com.adellapo.sisfac.core;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Esta clase tendra los metodos genericos que permitan optimizar el controlador
 * de vista
 * 
 * @author Andres
 *
 */
public class AbstractManagedBean {

	protected FacesContext obtenerContexto() {
		return FacesContext.getCurrentInstance();
	}

	protected void anadirMensajeInformacion(String mensaje) {
		anadirMensaje(mensaje, FacesMessage.SEVERITY_INFO);
	}

	protected void anadirMensajeError(String mensaje) {
		anadirMensaje(mensaje, FacesMessage.SEVERITY_ERROR);
	}

	protected void anadirMensajeAdvertencia(String mensaje) {
		anadirMensaje(mensaje, FacesMessage.SEVERITY_WARN);
	}

	private void anadirMensaje(String mensaje, Severity severityInfo) {

		FacesMessage menJSF = new FacesMessage();
		menJSF.setSeverity(severityInfo);
		menJSF.setSummary(mensaje);
		obtenerContexto().addMessage(null, menJSF);

	}
}
