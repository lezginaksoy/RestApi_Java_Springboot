package com.ConsumeMeter.restful.service;

import java.io.Serializable;
import java.util.List;


public interface CRUDService<E> {

	E save(E entity);

	E getById(Serializable id);

	List<E> getAll();

	void delete(Serializable id);
}
