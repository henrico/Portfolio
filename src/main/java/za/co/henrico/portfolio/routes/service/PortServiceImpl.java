package za.co.henrico.portfolio.routes.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.repository.PortRepository;
import za.co.henrico.portfolio.routes.repository.ProductRepository;
import za.co.henrico.portfolio.routes.repository.ScheduleRepository;

@Component
public class PortServiceImpl extends AbstractRestServiceImpl<Port> implements PortService {

	@Autowired
	private PortRepository portRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	@Transactional
	public Collection<Port> save(Port port) {

		if (port.getId() != null)
			scheduleRepository.deleteAll(scheduleRepository.findBySource(port));

		return super.save(port);
	}

	protected JpaRepository<Port, Long> getRepository() {
		return portRepository;
	}

	@Override
	public Collection<Port> getPortsProducingProduct(long id) {
		return portRepository.findByProductId(id);
	}

}
