package za.co.henrico.portfolio.colors.service;

import java.util.Collection;

import za.co.henrico.portfolio.colors.model.Scheme;

public interface SchemeService {

	Collection<Scheme> getList();

	void save(Scheme object);

	void deleteAll();

}
