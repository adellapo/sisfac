package com.adellapo.sisfac.admin.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.adellapo.sisfac.entidad.TipoProducto;
import com.adellapo.sisfac.negocio.TipoProductoFacade;

/**
 * Clase para administrar el formulario de tipo de producto
 * 
 * @author Andres Della Porta
 *
 */
@ManagedBean
@ViewScoped
public class TipoProductoBean {

	private TipoProducto tipoProducto;
	private TipoProducto tipoProductoSel;
	private List<TipoProducto> listaTipoProducto;

	@EJB
	private TipoProductoFacade adminTipoProducto;

	public TipoProductoBean() {
		this.tipoProducto = new TipoProducto();
		this.listaTipoProducto = new ArrayList<TipoProducto>();
	}

	/**
	 * @return the tipoProducto
	 */
	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * @param tipoProducto the tipoProducto to set
	 */
	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/**
	 * @return the tipoProductoSel
	 */
	public TipoProducto getTipoProductoSel() {
		return tipoProductoSel;
	}

	/**
	 * @param tipoProductoSel the tipoProductoSel to set
	 */
	public void setTipoProductoSel(TipoProducto tipoProductoSel) {
		this.tipoProductoSel = tipoProductoSel;
	}

	/**
	 * @return the listaTipoProducto
	 */
	public List<TipoProducto> getListaTipoProducto() {
		return listaTipoProducto;
	}

	/**
	 * @param listaTipoProducto the listaTipoProducto to set
	 */
	public void setListaTipoProducto(List<TipoProducto> listaTipoProducto) {
		this.listaTipoProducto = listaTipoProducto;
	}

	public void seleccionarRegistro(SelectEvent e) {
		this.tipoProductoSel = (TipoProducto) e.getObject();
	}

	// oparaciones principales
	public void guardar() {

		FacesMessage mensaje = new FacesMessage();

		try {

			if (tipoProducto.getTipproCodigo() != 0) {

				adminTipoProducto.actualizar(tipoProducto);
				mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
				mensaje.setSummary("Registro actualizado exitosamente.");
				FacesContext.getCurrentInstance().addMessage(null, mensaje);

			} else {

				adminTipoProducto.guardar(tipoProducto);
				mensaje.setSeverity(FacesMessage.SEVERITY_INFO);
				mensaje.setSummary("Registro guardado exitosamente.");
				FacesContext.getCurrentInstance().addMessage(null, mensaje);

			}

			cargarTipoProductos();
			resetearFormulario();

		} catch (Exception e) {

			mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensaje.setSummary("No se ha podido guardar: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, mensaje);

		}

	}

	public void editar() {

		if (this.tipoProductoSel != null) {

			this.tipoProducto = this.tipoProductoSel;

		} else {

			FacesMessage mensaje = new FacesMessage();
			mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensaje.setSummary("Se debe seleccionar un tipo de producto");
			FacesContext.getCurrentInstance().addMessage(null, mensaje);

		}

	}

	public void eliminar() {

	}

	public void resetearFormulario() {

		this.tipoProducto = new TipoProducto();
		this.tipoProductoSel = null;

	}

	private void cargarTipoProductos() {

		try {

			this.listaTipoProducto = adminTipoProducto.consultarTodos();

		} catch (Exception e) {

			FacesMessage mensaje = new FacesMessage();
			mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
			mensaje.setSummary("No se ha podido cargar los tipos de productos: " + e.getMessage());

		}

	}

	@PostConstruct
	public void inicializar() {

		cargarTipoProductos();

	}

}
