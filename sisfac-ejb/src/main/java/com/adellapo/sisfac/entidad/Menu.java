package com.adellapo.sisfac.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@NamedQuery(name="Menu.findAll", query="SELECT m FROM Menu m")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="men_codigo")
	private int menCodigo;

	@Column(name="men_id_padre")
	private int menIdPadre;

	@Column(name="men_nombre")
	private String menNombre;

	@Column(name="men_url")
	private String menUrl;

	//bi-directional many-to-one association to Permiso
	@OneToMany(mappedBy="menu")
	private List<Permiso> permisos;

	public Menu() {
	}

	public int getMenCodigo() {
		return this.menCodigo;
	}

	public void setMenCodigo(int menCodigo) {
		this.menCodigo = menCodigo;
	}

	public int getMenIdPadre() {
		return this.menIdPadre;
	}

	public void setMenIdPadre(int menIdPadre) {
		this.menIdPadre = menIdPadre;
	}

	public String getMenNombre() {
		return this.menNombre;
	}

	public void setMenNombre(String menNombre) {
		this.menNombre = menNombre;
	}

	public String getMenUrl() {
		return this.menUrl;
	}

	public void setMenUrl(String menUrl) {
		this.menUrl = menUrl;
	}

	public List<Permiso> getPermisos() {
		return this.permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	public Permiso addPermiso(Permiso permiso) {
		getPermisos().add(permiso);
		permiso.setMenu(this);

		return permiso;
	}

	public Permiso removePermiso(Permiso permiso) {
		getPermisos().remove(permiso);
		permiso.setMenu(null);

		return permiso;
	}

}