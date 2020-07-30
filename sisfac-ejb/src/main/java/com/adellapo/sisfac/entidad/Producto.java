package com.adellapo.sisfac.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pro_codigo")
	private int proCodigo;

	@Column(name="pro_descripcion")
	private String proDescripcion;

	@Column(name="pro_nombre")
	private String proNombre;

	@Column(name="pro_precio")
	private BigDecimal proPrecio;

	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="producto")
	private List<DetalleFactura> detalleFacturas;

	//bi-directional many-to-one association to TipoProducto
	@ManyToOne
	@JoinColumn(name="tippro_codigo")
	private TipoProducto tipoProducto;

	public Producto() {
	}

	public int getProCodigo() {
		return this.proCodigo;
	}

	public void setProCodigo(int proCodigo) {
		this.proCodigo = proCodigo;
	}

	public String getProDescripcion() {
		return this.proDescripcion;
	}

	public void setProDescripcion(String proDescripcion) {
		this.proDescripcion = proDescripcion;
	}

	public String getProNombre() {
		return this.proNombre;
	}

	public void setProNombre(String proNombre) {
		this.proNombre = proNombre;
	}

	public BigDecimal getProPrecio() {
		return this.proPrecio;
	}

	public void setProPrecio(BigDecimal proPrecio) {
		this.proPrecio = proPrecio;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().add(detalleFactura);
		detalleFactura.setProducto(this);

		return detalleFactura;
	}

	public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().remove(detalleFactura);
		detalleFactura.setProducto(null);

		return detalleFactura;
	}

	public TipoProducto getTipoProducto() {
		return this.tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

}