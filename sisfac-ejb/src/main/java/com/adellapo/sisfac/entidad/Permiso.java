package com.adellapo.sisfac.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the permiso database table.
 * 
 */
@Entity
@NamedQuery(name="Permiso.findAll", query="SELECT p FROM Permiso p")
public class Permiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PermisoPK id;

	@Column(name="per_editar")
	private int perEditar;

	@Column(name="per_eliminar")
	private int perEliminar;

	@Temporal(TemporalType.DATE)
	@Column(name="per_fecha")
	private Date perFecha;

	@Column(name="per_guardar")
	private int perGuardar;

	//bi-directional many-to-one association to Menu
	@ManyToOne
	@JoinColumn(name="men_codigo")
	private Menu menu;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	@JoinColumn(name="rol_codigo")
	private Rol rol;

	public Permiso() {
	}

	public PermisoPK getId() {
		return this.id;
	}

	public void setId(PermisoPK id) {
		this.id = id;
	}

	public int getPerEditar() {
		return this.perEditar;
	}

	public void setPerEditar(int perEditar) {
		this.perEditar = perEditar;
	}

	public int getPerEliminar() {
		return this.perEliminar;
	}

	public void setPerEliminar(int perEliminar) {
		this.perEliminar = perEliminar;
	}

	public Date getPerFecha() {
		return this.perFecha;
	}

	public void setPerFecha(Date perFecha) {
		this.perFecha = perFecha;
	}

	public int getPerGuardar() {
		return this.perGuardar;
	}

	public void setPerGuardar(int perGuardar) {
		this.perGuardar = perGuardar;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}