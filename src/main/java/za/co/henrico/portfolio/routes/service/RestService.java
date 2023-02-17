package za.co.henrico.portfolio.routes.service;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.jpa.domain.AbstractPersistable;

public interface RestService<E extends AbstractPersistable<T>, T extends Serializable> {

	Collection<E> getList();

	Collection<E> delete(long id);

	Collection<E> save(E object);

}
