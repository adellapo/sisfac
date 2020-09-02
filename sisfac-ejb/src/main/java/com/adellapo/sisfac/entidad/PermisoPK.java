package com.adellapo.sisfac.entidad;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the permiso database table.
 * 
 */
@Embeddable
public class PermisoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="rol_codigo", insertable=false, updatable=false)
	private int rolCodigo;

	@Column(name="men_codigo", insertable=false, updatable=false)
	private int menCodigo;

	public PermisoPK() {
	}
	public int getRolCodigo() {
		return this.rolCodigo;
	}
	public void setRolCodigo(int rolCodigo) {
		this.rolCodigo = rolCodigo;
	}
	public int getMenCodigo() {
		return this.menCodigo;
	}
	public void setMenCodigo(int menCodigo) {
		this.menCodigo = menCodigo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PermisoPK)) {
			return false;
		}
		PermisoPK castOther = (PermisoPK)other;
		return 
			(this.rolCodigo == castOther.rolCodigo)
			&& (this.menCodigo == castOther.menCodigo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.rolCodigo;
		hash = hash * prime + this.menCodigo;
		
		return hash;
	}
}