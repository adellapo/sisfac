package com.adellapo.sisfac.negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.adellapo.sisfac.entidad.Usuario;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

	@PersistenceContext(unitName = "sisfacPU")
	private EntityManager em;

	public UsuarioFacade() {
		super(Usuario.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	/**
	 * Metodo para validar el usuario
	 * @param usuario
	 * @param clave
	 * @return
	 */
	public Usuario validarUsuario(String usuario, String clave)throws Exception {
	
		Usuario u = null;
		
		try {

			TypedQuery<Usuario> consultaUsuario = em.createQuery("Select u from Usuario u where u.usuUsuario = :parametroUsuario and u.usuClave = :parametroClave", Usuario.class);
			
			consultaUsuario.setParameter("parametroUsuario", usuario);
			
			consultaUsuario.setParameter("parametroClave", clave);
			
			u = consultaUsuario.getSingleResult();
			
		} catch (Exception e) {
			
			throw new Exception("Usuario no encontrado");
			
		}
		
		return u;
	
	}
	
}
