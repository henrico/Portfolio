package za.co.henrico.portfolio.service;

import java.util.Collection;

import org.springframework.data.jpa.domain.AbstractPersistable;

public interface RestService<E extends AbstractPersistable> {

	Collection<E> getList();

	Collection<E> delete(long id);

	Collection<E> save(E object);

}
