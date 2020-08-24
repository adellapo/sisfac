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
	private DetalleFactura detalleFactura;
	private DetalleFactura detalleFacturaSel;
	private List<DetalleFactura> listaDetallesFacturas;
	private Cliente cliente;
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

		this.listaDetallesFacturas = new ArrayList<DetalleFactura>();

		this.cliente = new Cliente();

		this.detalleFactura = new DetalleFactura();

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
	 * @return the listaDetallesFacturas
	 */
	public List<DetalleFactura> getListaDetalles() {
		return listaDetallesFacturas;
	}

	/**
	 * @param listaDetallesFacturas the listaDetallesFacturas to set
	 */
	public void setListaDetalles(List<DetalleFactura> listaDetalles) {
		this.listaDetallesFacturas = listaDetalles;
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
	 * @return the detalleFactura
	 */
	public DetalleFactura getDetalle() {
		return detalleFactura;
	}

	/**
	 * @param detalleFactura the detalleFactura to set
	 */
	public void setDetalle(DetalleFactura detalle) {
		this.detalleFactura = detalle;
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
	 * @return the detalleFacturaSel
	 */
	public DetalleFactura getDetalleSel() {
		return detalleFacturaSel;
	}

	/**
	 * @param detalleFacturaSel the detalleFacturaSel to set
	 */
	public void setDetalleSel(DetalleFactura detalleSel) {
		this.detalleFacturaSel = detalleSel;
	}

	// operaciones del formulario

	public void nuevo() {
	}

	public void guardar() {

		try {

			if (cliente != null) {

				if (!listaDetallesFacturas.isEmpty()) {

					// 3 - CASCADE

					factura.setCliente(cliente);

					factura.setDetalleFacturas(listaDetallesFacturas);

					adminFactura.guardar(factura);

					anadirMensajeInformacion("Factura añadida");

					cargarFacturas();

					resetearFormulario();

				}

			} else {

				anadirMensajeAdvertencia("Se debe ingresar un Cliente");

			}

		} catch (Exception e) {

			anadirMensajeError("Error al guardar la Factura: " + e.getMessage());

		}

	}

	public void editar() {
	}

	public void eliminar() {
	}

	public void anadirDetalle() {

		// 2 - CASCADE

		detalleFactura.setFactura(factura);

		detalleFactura.setProducto(producto);

		detalleFactura.setDetfacCodigoTmp(++codTmpFac);

		if (listaDetallesFacturas.isEmpty() || !listaDetallesFacturas.contains(detalleFactura)) {

			listaDetallesFacturas.add(detalleFactura);

			actualizarValoresFactura();

			anadirMensajeInformacion("Producto añadido");

		} else {

			anadirMensajeAdvertencia("El Producto ya se encuentra en la lista");

		}

		cancelarDetalle();

	}

	public void eliminarDetalle() {

		if (this.detalleFacturaSel != null) {

			this.listaDetallesFacturas.remove(this.detalleFacturaSel);

			actualizarValoresFactura();

			anadirMensajeInformacion("Detalle eliminado");

		} else {

			anadirMensajeError("Se debe seleccionar un Detalle");

		}

	}

	public void cancelarDetalle() {

		this.detalleFactura = null;

		this.detalleFactura = new DetalleFactura();

		this.detalleFacturaSel = null;

		this.producto = null;

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

			this.detalleFactura.setDetfacPrecio(producto.getProPrecio());

		}

	}

	// inputText Total Detalle
	public void calcularTotalDetalle() {

		if (detalleFactura != null) {

			BigDecimal totalDetalle = new BigDecimal(0.0);

			totalDetalle = detalleFactura.getDetfacPrecio()
					.multiply(new BigDecimal(detalleFactura.getDetfacCantidad()));

			this.detalleFactura.setDetfacTotal(totalDetalle);

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

		this.detalleFacturaSel = (DetalleFactura) se.getObject();

	}

	public void resetearFormulario() {

		this.factura = new Factura();

		this.facturaSel = null;

		this.listaDetallesFacturas.clear();
		
		this.cliente = new Cliente();
		
	}

	public void cargarFacturas() {

		try {

			this.listaFacturas = adminFactura.consultarTodos();

		} catch (Exception e) {

			anadirMensajeError("No se ha podido cargar las facturas: " + e.getMessage());

		}

	}

	// agregada para calcular los valores subtotal, impuesto y total
	private void actualizarValoresFactura() {

		BigDecimal subtotal = new BigDecimal(0.0);

		for (DetalleFactura df : listaDetallesFacturas) {

			subtotal = subtotal.add(df.getDetfacTotal());

		}

		factura.setFacSubtotal(subtotal);

		factura.setFacImpuesto(factura.getFacSubtotal().multiply(new BigDecimal(0.21)));

		factura.setFacTotal(factura.getFacSubtotal().add(factura.getFacImpuesto()));

	}

	@PostConstruct
	public void inicializar() {

		cargarFacturas();

	}

}
