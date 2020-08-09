package com.adellapo.sisfac.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import com.adellapo.sisfac.entidad.Producto;

@Stateless
public class ProductoFacade extends AbstractFacade<Producto>{
	
	@PersistenceContext(unitName = "sisfacPU")
	private EntityManager em;
	
	public ProductoFacade() {
		super(Producto.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
