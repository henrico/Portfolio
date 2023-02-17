package za.co.henrico.portfolio.routes.service;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

public abstract class AbstractRestServiceImpl<O extends AbstractPersistable<T>, T extends Serializable> implements RestService<O, T> {

	@Override
	public Collection<O> getList() {
		return getRepository().findAll();
	}

	@Override
	public Collection<O> delete(long id) {
		getRepository().deleteById(id);
		return getList();
	}

	@Override
	@Transactional
	public Collection<O> save(O object) {

		getRepository().save(object);
		return getList();
	}

	protected abstract JpaRepository<O, Long> getRepository();

}
