package com.adellapo.sisfac.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fac_codigo")
	private int facCodigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "fac_fecha")
	private Date facFecha;

	@Column(name = "fac_impuesto")
	private BigDecimal facImpuesto;

	@Column(name = "fac_numero")
	private String facNumero;

	@Column(name = "fac_subtotal")
	private BigDecimal facSubtotal;

	@Column(name = "fac_total")
	private BigDecimal facTotal;

	// bi-directional many-to-one association to DetalleFactura
	// 1 - CASCADE
	@OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetalleFactura> detalleFacturas;

	// bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name = "cli_codigo")
	private Cliente cliente;

	public Factura() {
	}

	public int getFacCodigo() {
		return this.facCodigo;
	}

	public void setFacCodigo(int facCodigo) {
		this.facCodigo = facCodigo;
	}

	public Date getFacFecha() {
		return this.facFecha;
	}

	public void setFacFecha(Date facFecha) {
		this.facFecha = facFecha;
	}

	public BigDecimal getFacImpuesto() {
		return this.facImpuesto;
	}

	public void setFacImpuesto(BigDecimal facImpuesto) {
		this.facImpuesto = facImpuesto;
	}

	public String getFacNumero() {
		return this.facNumero;
	}

	public void setFacNumero(String facNumero) {
		this.facNumero = facNumero;
	}

	public BigDecimal getFacSubtotal() {
		return this.facSubtotal;
	}

	public void setFacSubtotal(BigDecimal facSubtotal) {
		this.facSubtotal = facSubtotal;
	}

	public BigDecimal getFacTotal() {
		return this.facTotal;
	}

	public void setFacTotal(BigDecimal facTotal) {
		this.facTotal = facTotal;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().add(detalleFactura);
		detalleFactura.setFactura(this);

		return detalleFactura;
	}

	public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().remove(detalleFactura);
		detalleFactura.setFactura(null);

		return detalleFactura;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
