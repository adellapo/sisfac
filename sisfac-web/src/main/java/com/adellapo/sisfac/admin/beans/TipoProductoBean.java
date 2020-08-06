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

import com.adellapo.sisfac.core.AbstractManagedBean;
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
public class TipoProductoBean extends AbstractManagedBean {

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

	// selector de registro
	public void seleccionarRegistro(SelectEvent e) {
		this.tipoProductoSel = (TipoProducto) e.getObject();
	}

	// operaciones principales
	public void guardar() {

		try {

			if (tipoProducto.getTipproCodigo() != 0) {

				adminTipoProducto.actualizar(tipoProducto);
				anadirMensajeInformacion("Registro actualizado exitosamente.");

			} else {

				adminTipoProducto.guardar(tipoProducto);
				anadirMensajeInformacion("Registro guardado exitosamente.");

			}

			cargarTipoProductos();
			resetearFormulario();

		} catch (Exception e) {

			anadirMensajeError("No se ha podido guardar: " + e.getMessage());

		}

	}

	public void editar() {

		if (this.tipoProductoSel != null) {

			this.tipoProducto = this.tipoProductoSel;

		} else {

			anadirMensajeAdvertencia("Se debe seleccionar un tipo de producto");

		}

	}

	public void eliminar() {

		if (this.tipoProductoSel != null) {

			this.tipoProducto = this.tipoProductoSel;

			try {

				adminTipoProducto.eliminar(tipoProductoSel);
				anadirMensajeInformacion("Tipo de producto eliminado");

			} catch (Exception e) {

				anadirMensajeError("Error al eliminar tipo de producto");

			}

		} else {

			anadirMensajeAdvertencia("Se debe seleccionar un tipo de producto");

		}

		cargarTipoProductos();
		resetearFormulario();

	}

	public void nuevo() {

		resetearFormulario();
		anadirMensajeInformacion("Nuevo registro de tipo de producto");

	}

	public void resetearFormulario() {

		this.tipoProducto = new TipoProducto();
		this.tipoProductoSel = null;

	}

	private void cargarTipoProductos() {

		try {

			this.listaTipoProducto = adminTipoProducto.consultarTodos();

		} catch (Exception e) {

			anadirMensajeError("No se ha podido cargar los tipos de productos: " + e.getMessage());

		}

	}

	@PostConstruct
	public void inicializar() {

		cargarTipoProductos();

	}

}
