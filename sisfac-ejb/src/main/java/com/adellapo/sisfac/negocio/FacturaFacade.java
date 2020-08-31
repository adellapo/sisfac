package com.adellapo.sisfac.negocio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	public List<Factura> buscarFacturas(String busquedaPor, String valorBusquedaPor) {

		try {

			List<Factura> listaFacturas = new ArrayList<Factura>();

			TypedQuery<Factura> conBusFac = null;

			if (busquedaPor.equals("Numero")) {

				System.out.println("************** BUSCO POR NUMERO **************");
				conBusFac = em.createQuery("Select fac from Factura fac where fac.facNumero like :numeroFac",
						Factura.class);

				conBusFac.setParameter("numeroFac", "%" + valorBusquedaPor + "%");

			} else if (busquedaPor.equals("Cliente")) {

				System.out.println("************** BUSCO POR CLIENTE **************");
				conBusFac = em.createQuery(
						"Select fac from Factura fac where fac.cliente.cliNombres like :nombreApellidoCli or fac.cliente.cliApellidos like :nombreApellidoCli",
						Factura.class);

				conBusFac.setParameter("nombreApellidoCli", "%" + valorBusquedaPor + "%");

			} else if (busquedaPor.equals("Estado")) {

				System.out.println("************** BUSCO POR ESTADO **************");
				conBusFac = em.createQuery("Select fac from Factura fac where fac.facEstado like :estadoFac",
						Factura.class);

				conBusFac.setParameter("estadoFac", "%" + valorBusquedaPor + "%");

			} else {

				SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

				Date fechaBus = formatoFecha.parse(valorBusquedaPor);

				System.out.println("************** BUSCO POR FECHA **************");
				conBusFac = em.createQuery("Select fac from Factura fac where fac.facFecha BETWEEN :fechaFac AND :fechaFac",
						Factura.class);

				conBusFac.setParameter("fechaFac", fechaBus);

			}

			listaFacturas = conBusFac.getResultList();

			return listaFacturas;

		} catch (Exception e) {

			return null;

		}

	}

}
