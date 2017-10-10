package za.co.henrico.portfolio.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractRestServiceImpl<O extends AbstractPersistable> implements RestService<O> {

	@Override
	public Collection<O> getList() {
		return getRepository().findAll();
	}

	@Override
	public Collection<O> delete(long id) {
		getRepository().delete(id);
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
