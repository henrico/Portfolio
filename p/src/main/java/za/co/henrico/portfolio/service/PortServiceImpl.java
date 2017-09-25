package za.co.henrico.portfolio.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Port;
import za.co.henrico.portfolio.repository.PortRepository;

@Component
public class PortServiceImpl implements PortService {

	@Autowired
	private PortRepository portRepository;

	@Override
	public Collection<Port> getPorts() {
		return portRepository.findAll();
	}

	@Override
	public Collection<Port> deletePort(long id) {
		portRepository.delete(id);
		return getPorts();
	}

	@Override
	public Collection<Port> savePort(Port port) {
		portRepository.save(port);
		return getPorts();
	}

}
