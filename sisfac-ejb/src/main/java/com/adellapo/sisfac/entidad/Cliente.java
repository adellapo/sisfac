package com.adellapo.sisfac.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cli_codigo")
	private int cliCodigo;

	@Column(name="cli_apellidos")
	private String cliApellidos;

	@Column(name="cli_direccion")
	private String cliDireccion;

	@Column(name="cli_identificacion")
	private String cliIdentificacion;

	@Column(name="cli_nombres")
	private String cliNombres;

	@Column(name="cli_telefono")
	private String cliTelefono;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="cliente")
	private List<Factura> facturas;

	public Cliente() {
	}

	public int getCliCodigo() {
		return this.cliCodigo;
	}

	public void setCliCodigo(int cliCodigo) {
		this.cliCodigo = cliCodigo;
	}

	public String getCliApellidos() {
		return this.cliApellidos;
	}

	public void setCliApellidos(String cliApellidos) {
		this.cliApellidos = cliApellidos;
	}

	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	public String getCliIdentificacion() {
		return this.cliIdentificacion;
	}

	public void setCliIdentificacion(String cliIdentificacion) {
		this.cliIdentificacion = cliIdentificacion;
	}

	public String getCliNombres() {
		return this.cliNombres;
	}

	public void setCliNombres(String cliNombres) {
		this.cliNombres = cliNombres;
	}

	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setCliente(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setCliente(null);

		return factura;
	}

}