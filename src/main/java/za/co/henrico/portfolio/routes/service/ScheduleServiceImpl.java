package za.co.henrico.portfolio.routes.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.model.Route;
import za.co.henrico.portfolio.routes.model.Schedule;
import za.co.henrico.portfolio.routes.repository.RouteRepository;
import za.co.henrico.portfolio.routes.repository.ScheduleRepository;
import za.co.henrico.portfolio.routes.utils.Utils;

@Component
public class ScheduleServiceImpl extends AbstractRestServiceImpl<Schedule, Long> implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private Utils utils;

	@Override
	@Transactional
	public Collection<Schedule> save(Schedule schedule) {

	    Port source = schedule.getSource();
	    Port destination = schedule.getOrder().getDestination();

	    Route route = routeRepository.findRouteFromPorts(source, destination).orElseThrow();

	    schedule.setDeliveryDate(utils.getEndDateFromDays(schedule.getCollectionDate(), schedule.getShip(), route));

	    Collection<Schedule> existing = scheduleRepository.findBySource(schedule.getSource());

	    int total = existing.stream()
	            .mapToInt(Schedule::getStoredCrates)
	            .sum();

	    int remaining = schedule.getOrder().getQuantity() - total;

	    schedule.setStoredCrates(
	            Math.min(remaining, schedule.getShip().getCapacity()));

	    return super.save(schedule);
	}


	protected JpaRepository<Schedule, Long> getRepository() {
		return scheduleRepository;
	}

}
