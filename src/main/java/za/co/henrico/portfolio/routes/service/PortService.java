package za.co.henrico.portfolio.routes.service;

import java.util.Collection;

import za.co.henrico.portfolio.routes.model.Port;

public interface PortService extends RestService<Port, Long> {

	Collection<Port> getPortsProducingProduct(long id);

}
