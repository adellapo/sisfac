package com.adellapo.sisfac.negocio;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class AbstractFacade<T> {

	private Class<T> classEntity;

	public AbstractFacade(Class<T> classEntity) {
		this.classEntity = classEntity;
	}

	protected abstract EntityManager getEntityManager();

	public void guardar(T entidad) throws Exception {
		getEntityManager().persist(entidad);
	}

	public void actualizar(T entidad) throws Exception {
		getEntityManager().merge(entidad);
	}

	public void eliminar(T entidad) throws Exception {
		getEntityManager().remove(getEntityManager().merge(entidad));
	}

	public T consultarPorId(Object id) throws Exception {
		return getEntityManager().find(classEntity, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> consultarTodos() throws Exception {
		return getEntityManager().createQuery("SELECT alias FROM " + classEntity.getSimpleName() + " alias")
				.getResultList();
	}

}
