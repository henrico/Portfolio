package za.co.henrico.portfolio.routes.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

public abstract class AbstractRestServiceImpl<O extends AbstractPersistable<T>, T extends Serializable>
		implements RestService<O, T> {

	public Optional<O> getById(T id) {
		return getRepository().findById(id);
	}

	@Override
	public Collection<O> getList() {
		return getRepository().findAll();
	}

	@Override
	public Collection<O> delete(T id) {
		getRepository().deleteById(id);
		return getList();
	}

	@Override
	@Transactional
	public Collection<O> save(O object) {

		getRepository().save(object);
		return getList();
	}

	protected abstract JpaRepository<O, T> getRepository();

}
