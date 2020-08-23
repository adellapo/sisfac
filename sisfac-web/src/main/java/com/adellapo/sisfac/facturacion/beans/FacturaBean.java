package com.adellapo.sisfac.facturacion.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.adellapo.sisfac.core.AbstractManagedBean;
import com.adellapo.sisfac.entidad.Cliente;
import com.adellapo.sisfac.entidad.DetalleFactura;
import com.adellapo.sisfac.entidad.Factura;
import com.adellapo.sisfac.entidad.Producto;
import com.adellapo.sisfac.negocio.ClienteFacade;
import com.adellapo.sisfac.negocio.FacturaFacade;
import com.adellapo.sisfac.negocio.ProductoFacade;

@ManagedBean
@ViewScoped
public class FacturaBean extends AbstractManagedBean {

	private Factura factura;
	private Factura facturaSel;
	private List<Factura> listaFacturas;
	private List<DetalleFactura> listaDetalles;
	private Cliente cliente;
	private DetalleFactura detalle;
	private DetalleFactura detalleSel;
	private Producto producto;

	private int codTmpFac;

	@EJB
	private FacturaFacade adminFactura;

	@EJB
	private ClienteFacade adminCliente;

	@EJB
	private ProductoFacade adminProducto;

	public FacturaBean() {

		this.factura = new Factura();

		this.listaFacturas = new ArrayList<Factura>();

		this.listaDetalles = new ArrayList<DetalleFactura>();

		this.cliente = new Cliente();

		this.detalle = new DetalleFactura();

		this.producto = new Producto();

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

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the detalle
	 */
	public DetalleFactura getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(DetalleFactura detalle) {
		this.detalle = detalle;
	}

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
	 * @return the detalleSel
	 */
	public DetalleFactura getDetalleSel() {
		return detalleSel;
	}

	/**
	 * @param detalleSel the detalleSel to set
	 */
	public void setDetalleSel(DetalleFactura detalleSel) {
		this.detalleSel = detalleSel;
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

//		boolean agregarDet = true;

		detalle.setFactura(factura);

		detalle.setProducto(producto);

		detalle.setDetfacCodigoTmp(++codTmpFac);

		if (listaDetalles.isEmpty() || !listaDetalles.contains(detalle)) {

			listaDetalles.add(detalle);

			anadirMensajeInformacion("Producto añadido");

		} else {

			anadirMensajeAdvertencia("El Producto ya se encuentra en la lista");

		}

//		if (listaDetalles.isEmpty()) {
//
//			listaDetalles.add(detalle);
//
//			anadirMensajeInformacion("Lista vacia. Producto añadido");
//
//		} else {
//
//			Iterator<DetalleFactura> it = listaDetalles.iterator();
//
//			while (it.hasNext()) {
//
//				Producto p = it.next().getProducto();
//
//				if (p.getProNombre().equals(producto.getProNombre())) {
//
//					agregarDet = false;
//
//					break;
//
//				}
//
//			}
//
//			if (agregarDet == true) {
//
//				listaDetalles.add(detalle);
//
//				anadirMensajeInformacion("Producto añadido");
//
//			} else {
//
//				anadirMensajeAdvertencia("El Producto ya se encuentra en la lista.");
//
//			}
//
//		}

		cancelarDetalle();

	}

	public void eliminarDetalle() {

		if (this.detalleSel != null) {

			this.listaDetalles.remove(this.detalleSel);

			anadirMensajeInformacion("Detalle eliminado");

		} else {

			anadirMensajeError("Se debe seleccionar un Detalle");

		}

	}

	public void cancelarDetalle() {

		this.detalle = null;

		this.producto = null;

		this.detalle = new DetalleFactura();

		this.detalleSel = null;
		
		this.producto = new Producto();

	}

	// inputText Nombre del Producto con autoComplete
	public List<Producto> completarProductos(String query) {

		try {

			String queryLowerCase = query.toLowerCase();

			List<Producto> proTmp = adminProducto.consultarTodos();

			return proTmp.stream().filter(t -> t.getProNombre().toLowerCase().startsWith(queryLowerCase))
					.collect(Collectors.toList());

		} catch (Exception e) {

			anadirMensajeError("No encontrado: " + e.getMessage());

			return null;

		}

	}

	// inputText Precio Producto
	public void actualizarPrecioProducto() {

		if (producto != null) {

			this.detalle.setDetfacPrecio(producto.getProPrecio());

		}

	}

	// inputText Total Detalle
	public void calcularTotalDetalle() {

		if (detalle != null) {

			BigDecimal totalDetalle = new BigDecimal(0.0);

			totalDetalle = detalle.getDetfacPrecio().multiply(new BigDecimal(detalle.getDetfacCantidad()));

			this.detalle.setDetfacTotal(totalDetalle);

		}

	}

	// otras operaciones

	public void buscarCliente() {

		try {

			cliente = adminCliente.buscarClientePorIdentificacion(cliente.getCliIdentificacion());

			if (cliente == null) {

				anadirMensajeAdvertencia("Cliente no encontrado");

				cliente = new Cliente();

			} else {

				anadirMensajeInformacion("Cliente encontrado");

			}

		} catch (Exception e) {

			anadirMensajeError("Error al buscar el cliente :" + e.getMessage());
		}

	}

	public void seleccionarFactura(SelectEvent se) {
	}

	public void seleccionarDetalleFactura(SelectEvent se) {

		this.detalleSel = (DetalleFactura) se.getObject();

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
