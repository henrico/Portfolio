package za.co.henrico.portfolio.service;

import java.util.Collection;

import za.co.henrico.portfolio.model.Port;

public interface PortService {

	Collection<Port> getPorts();

	Collection<Port> deletePort(long id);

	Collection<Port> savePort(Port port);

}
