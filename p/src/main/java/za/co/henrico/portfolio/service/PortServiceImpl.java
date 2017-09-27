package za.co.henrico.portfolio.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Port;
import za.co.henrico.portfolio.model.Product;
import za.co.henrico.portfolio.repository.PortRepository;
import za.co.henrico.portfolio.repository.ProductRepository;
import za.co.henrico.portfolio.repository.ScheduleRepository;

@Component
public class PortServiceImpl implements PortService {

	@Autowired
	private PortRepository portRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ScheduleRepository scheduleRepository;

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
	@Transactional
	public Collection<Port> savePort(Port port) {
		
		if (port.getId()!=null)scheduleRepository.delete(scheduleRepository.findBySource(port));
		
		portRepository.save(port);
		return getPorts();
	}
	
	@Override
	public Collection<Port> getPortsProducingProduct(long id) {
		return portRepository.findByProductId(id);
	}

}
