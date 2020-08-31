package com.adellapo.sisfac.facturacion.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.adellapo.sisfac.util.ConstanteWeb;

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
	private String busquedaPor;
	private String valorBusquedaPor;

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
	public List<DetalleFactura> getListaDetallesFacturas() {
		return listaDetallesFacturas;
	}

	/**
	 * @param listaDetallesFacturas the listaDetallesFacturas to set
	 */
	public void setListaDetallesFacturas(List<DetalleFactura> listaDetalles) {
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
	public DetalleFactura getDetalleFactura() {
		return detalleFactura;
	}

	/**
	 * @param detalleFactura the detalleFactura to set
	 */
	public void setDetalleFactura(DetalleFactura detalle) {
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
	public DetalleFactura getDetalleFacturaSel() {
		return detalleFacturaSel;
	}

	/**
	 * @param detalleFacturaSel the detalleFacturaSel to set
	 */
	public void setDetalleFacturaSel(DetalleFactura detalleSel) {
		this.detalleFacturaSel = detalleSel;
	}

	/**
	 * @return the codTmpFac
	 */
	public int getCodTmpFac() {
		return codTmpFac;
	}

	/**
	 * @param codTmpFac the codTmpFac to set
	 */
	public void setCodTmpFac(int codTmpFac) {
		this.codTmpFac = codTmpFac;
	}

	/**
	 * @return the busquedaPor
	 */
	public String getBusquedaPor() {
		return busquedaPor;
	}

	/**
	 * @param busquedaPor the busquedaPor to set
	 */
	public void setBusquedaPor(String busquedaPor) {
		this.busquedaPor = busquedaPor;
	}

	/**
	 * @return the valorBusquedaPor
	 */
	public String getValorBusquedaPor() {
		return valorBusquedaPor;
	}

	/**
	 * @param valorBusquedaPor the valorBusquedaPor to set
	 */
	public void setValorBusquedaPor(String valorBusquedaPor) {
		this.valorBusquedaPor = valorBusquedaPor;
	}

	// operaciones del formulario

	public void nuevo() {
		cancelarDetalle();
		resetearFormulario();
		anadirMensajeInformacion("Nueva Factura");
	}

	public void guardar() {

		try {

			if (cliente != null) {

				if (!listaDetallesFacturas.isEmpty()) {

					// 3 - CASCADE

					factura.setCliente(cliente);

					factura.setFacEstado(ConstanteWeb.EMITIDO.getValorNumerico());

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

	public void anular() {

		try {

			if (facturaSel != null) {

				facturaSel.setFacEstado(ConstanteWeb.ANULADO.getValorNumerico());

				adminFactura.actualizar(facturaSel);

				anadirMensajeInformacion("Factura anulada (código: " + facturaSel.getFacCodigo() + ")");

				cargarFacturas();

				resetearFormulario();

			} else {

				anadirMensajeAdvertencia("Seleccione una Factura");

			}

		} catch (Exception e) {

			anadirMensajeError("Error al anular Factura");

		}

	}

	public void eliminar() {

		try {

			if (facturaSel != null) {

				adminFactura.eliminar(facturaSel);

				anadirMensajeInformacion("Factura eliminada");

				cargarFacturas();

				resetearFormulario();

			} else {

				anadirMensajeAdvertencia("Seleccione una Factura");

			}

		} catch (Exception e) {

			anadirMensajeError("No se pudo eliminar Factura: " + e.getMessage());

		}

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

			this.detalleFactura.setDetfacCantidad(1);
			
			calcularTotalDetalle();

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

	public void buscarFacturas() {

		int cantidadEncontrada = 0;

		try {

			// cambio valor del nombre del estado al valor numerico del estado

			if (busquedaPor.equals("Estado")
					&& valorBusquedaPor.toUpperCase().matches("(.*)" + ConstanteWeb.ANULADO + "(.*)")) {

				this.valorBusquedaPor = String.valueOf(ConstanteWeb.ANULADO.getValorNumerico());

			} else if (busquedaPor.equals("Estado")
					&& valorBusquedaPor.toUpperCase().matches("(.*)" + ConstanteWeb.EMITIDO + "(.*)")) {

				this.valorBusquedaPor = String.valueOf(ConstanteWeb.EMITIDO.getValorNumerico());

			} else if (busquedaPor.equals("Estado")
					&& valorBusquedaPor.toUpperCase().matches("(.*)" + ConstanteWeb.ELIMINADO + "(.*)")) {

				this.valorBusquedaPor = String.valueOf(ConstanteWeb.ELIMINADO.getValorNumerico());

			}

			this.listaFacturas = this.adminFactura.buscarFacturas(this.busquedaPor, this.valorBusquedaPor);

			if (!this.listaFacturas.isEmpty() || this.listaFacturas != null) {

				cantidadEncontrada = this.listaFacturas.size();

				anadirMensajeInformacion(cantidadEncontrada + " Factura" + (cantidadEncontrada > 1 ? "s " : " ")
						+ "encontrada" + (cantidadEncontrada > 1 ? "s " : " "));

			} else {

				anadirMensajeAdvertencia("Facturas no encontradas");

			}

		} catch (Exception e) {

			anadirMensajeError("Error al realizar la busqueda: " + e.getMessage());

		}

	}

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

		this.facturaSel = (Factura) se.getObject();

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

	public void resetearTablaFacturas() {

		this.listaFacturas.clear();

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

	}

}
