package com.adellapo.sisfac.admin.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.adellapo.sisfac.core.AbstractManagedBean;
import com.adellapo.sisfac.entidad.Producto;
import com.adellapo.sisfac.entidad.TipoProducto;
import com.adellapo.sisfac.negocio.ProductoFacade;
import com.adellapo.sisfac.negocio.TipoProductoFacade;

@ManagedBean
@ViewScoped
public class ProductoBean extends AbstractManagedBean {

	private Producto producto; // permite crear un registro, va a bindear cada elemento a la pantalla
	private Producto productoSel; // permite seleccionar un producto de la tabla
	private List<Producto> listaProducto;
	private List<TipoProducto> listaTipoProducto;

	@EJB
	private ProductoFacade adminProducto;

	@EJB
	private TipoProductoFacade adminTipoProducto;

	public ProductoBean() {
		this.producto = new Producto();
		this.listaProducto = new ArrayList<Producto>();
		this.listaTipoProducto = new ArrayList<TipoProducto>();
	}

	// getters y setters
	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the productoSel
	 */
	public Producto getProductoSel() {
		return productoSel;
	}

	/**
	 * @param productoSel the productoSel to set
	 */
	public void setProductoSel(Producto productoSel) {
		this.productoSel = productoSel;
	}

	/**
	 * @return the listaProducto
	 */
	public List<Producto> getListaProducto() {
		return listaProducto;
	}

	/**
	 * @param listaProducto the listaProducto to set
	 */
	public void setListaProducto(List<Producto> listaProducto) {
		this.listaProducto = listaProducto;
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

	// operaciones de los botones

	public void nuevo() {

		if (this.productoSel != null) {

			this.productoSel = null;

		}

	}

	public void guardar() {

		try {

			if (this.producto.getProCodigo() == 0) {

				adminProducto.guardar(producto);

				anadirMensajeInformacion("Producto registrado exitosamente");

			} else {

				adminProducto.actualizar(producto);

				anadirMensajeInformacion("Producto actualizado exitosamente");

			}

		} catch (Exception e) {

			anadirMensajeError("No se pudo registrar el Producto: " + e.getMessage());

		}

		cargarProductos();

		resetearFormulario();

	}

	public void editar() {

		if (this.productoSel != null) {

			this.producto = this.productoSel;

		} else {

		}

	}

	public void eliminar() {

		if (this.productoSel != null) {

			try {

				adminProducto.eliminar(productoSel);

				anadirMensajeInformacion("Producto eliminado correctamente");

			} catch (Exception e) {

				anadirMensajeError("No se ha podido eliminar el Producto: " + e.getMessage());

			}

		} else {

			anadirMensajeAdvertencia("Seleccione un Producto");

		}

		cargarProductos();

		resetearFormulario();

	}

	// otras operaciones

	public void seleccionarRegistro(SelectEvent e) {

		this.productoSel = (Producto) e.getObject();

	}

	public void cargarProductos() {

		try {

			listaProducto = adminProducto.consultarTodos();

		} catch (Exception e) {

			anadirMensajeError("No se pudo cargar los Productos: " + e.getMessage());

		}

	}

	private void cargarTipoProductos() {

		try {

			listaTipoProducto = adminTipoProducto.consultarTodos();

		} catch (Exception e) {

			anadirMensajeError("No se pudo cargar los Tipos de Productos: " + e.getMessage());
		}

	}

	public void resetearFormulario() {

		this.producto = new Producto();

		this.productoSel = null;

	}

	@PostConstruct
	public void inicializar() {

		cargarProductos();

		cargarTipoProductos();

	}

}
