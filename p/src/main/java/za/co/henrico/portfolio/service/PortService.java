package za.co.henrico.portfolio.service;

import java.util.Collection;

import za.co.henrico.portfolio.model.Port;

public interface PortService extends RestService<Port> {

	Collection<Port> getPortsProducingProduct(long id);

}
