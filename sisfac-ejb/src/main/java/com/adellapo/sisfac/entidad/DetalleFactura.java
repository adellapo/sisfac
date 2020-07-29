package com.adellapo.sisfac.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the detalle_factura database table.
 * 
 */
@Entity
@Table(name="detalle_factura")
@NamedQuery(name="DetalleFactura.findAll", query="SELECT d FROM DetalleFactura d")
public class DetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="detfac_codigo")
	private int detfacCodigo;

	@Column(name="detfac_cantidad")
	private int detfacCantidad;

	@Column(name="detfac_observacion")
	private String detfacObservacion;

	@Column(name="detfac_precio")
	private BigDecimal detfacPrecio;

	@Column(name="detfac_total")
	private BigDecimal detfacTotal;

	//bi-directional many-to-one association to Factura
	@ManyToOne
	@JoinColumn(name="fac_codigo")
	private Factura factura;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="pro_codigo")
	private Producto producto;

	public DetalleFactura() {
	}

	public int getDetfacCodigo() {
		return this.detfacCodigo;
	}

	public void setDetfacCodigo(int detfacCodigo) {
		this.detfacCodigo = detfacCodigo;
	}

	public int getDetfacCantidad() {
		return this.detfacCantidad;
	}

	public void setDetfacCantidad(int detfacCantidad) {
		this.detfacCantidad = detfacCantidad;
	}

	public String getDetfacObservacion() {
		return this.detfacObservacion;
	}

	public void setDetfacObservacion(String detfacObservacion) {
		this.detfacObservacion = detfacObservacion;
	}

	public BigDecimal getDetfacPrecio() {
		return this.detfacPrecio;
	}

	public void setDetfacPrecio(BigDecimal detfacPrecio) {
		this.detfacPrecio = detfacPrecio;
	}

	public BigDecimal getDetfacTotal() {
		return this.detfacTotal;
	}

	public void setDetfacTotal(BigDecimal detfacTotal) {
		this.detfacTotal = detfacTotal;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}