package com.adellapo.sisfac.facturacion.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.adellapo.sisfac.core.AbstractManagedBean;
import com.adellapo.sisfac.entidad.DetalleFactura;
import com.adellapo.sisfac.entidad.Factura;
import com.adellapo.sisfac.negocio.FacturaFacade;

@ManagedBean
@ViewScoped
public class FacturaBean extends AbstractManagedBean{

	private Factura factura;
	private Factura facturaSel;
	private List<Factura> listaFacturas;
	private List<DetalleFactura> listaDetalles;

	@EJB
	private FacturaFacade adminFactura;

	public FacturaBean() {

		this.factura = new Factura();

		this.listaFacturas = new ArrayList<Factura>();

		this.listaDetalles = new ArrayList<DetalleFactura>();

	}

	/**
	 * @return the factura
	 */
	public Factura getFactura() {
		return factura;
	}

	/**
	 * @param factura the factura to set
	 */
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	/**
	 * @return the facturaSel
	 */
	public Factura getFacturaSel() {
		return facturaSel;
	}

	/**
	 * @param facturaSel the facturaSel to set
	 */
	public void setFacturaSel(Factura facturaSel) {
		this.facturaSel = facturaSel;
	}

	/**
	 * @return the listaFacturas
	 */
	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}

	/**
	 * @param listaFacturas the listaFacturas to set
	 */
	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	/**
	 * @return the listaDetalles
	 */
	public List<DetalleFactura> getListaDetalles() {
		return listaDetalles;
	}

	/**
	 * @param listaDetalles the listaDetalles to set
	 */
	public void setListaDetalles(List<DetalleFactura> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

	// operaciones del formulario

	public void nuevo() {
	}

	public void guardar() {
	}

	public void editar() {
	}

	public void eliminar() {
	}

	public void anadirDetalle() {
	}

	public void eliminarDetalle() {
	}

	// otras operaciones

	public void seleccionarFactura(SelectEvent se) {
	}

	public void seleccionarDetalle(SelectEvent se) {
	}

	public void resetearFormulario() {
	}

	public void cargarFacturas() {

		try {

			this.listaFacturas = adminFactura.consultarTodos();

		} catch (Exception e) {
			
			anadirMensajeError("No se ha podido cargar las facturas: " + e.getMessage());

		}

	}

	@PostConstruct
	public void inicializar() {

		cargarFacturas();

	}

}
