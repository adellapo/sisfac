package com.adellapo.sisfac.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.adellapo.sisfac.entidad.Factura;

@Stateless
public class FacturaFacade extends AbstractFacade<Factura> {

	@PersistenceContext(unitName = "sisfacPU")
	private EntityManager em;

	// da error ==> public FacturaFacade(Class<Factura> classEntity) {...}
	public FacturaFacade() {
		super(Factura.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
