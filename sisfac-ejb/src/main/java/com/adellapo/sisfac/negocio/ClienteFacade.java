package com.adellapo.sisfac.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.adellapo.sisfac.entidad.Cliente;

@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

	@PersistenceContext(unitName = "sisfacPU")
	private EntityManager em;

	public ClienteFacade() {
		super(Cliente.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public Cliente buscarClientePorIdentificacion(String identificacion) throws Exception {

		Cliente clienteIde = null;

		try {

			TypedQuery<Cliente> conCliIde = em.createQuery(
					"Select cli from Cliente cli where cli.cliIdentificacion = :identificacion", Cliente.class);

			conCliIde.setParameter("identificacion", identificacion);

			clienteIde = conCliIde.getSingleResult();

		} catch (Exception e) {

			clienteIde = null;

		}

		return clienteIde;
	}
}
