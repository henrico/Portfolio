package za.co.henrico.portfolio.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Port;
import za.co.henrico.portfolio.model.Route;
import za.co.henrico.portfolio.model.Schedule;
import za.co.henrico.portfolio.repository.RouteRepository;
import za.co.henrico.portfolio.repository.ScheduleRepository;
import za.co.henrico.portfolio.utils.Utils;

@Component
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private Utils utils;

	@Override
	public Collection<Schedule> getSchedules() {
		return scheduleRepository.findAll();
	}

	@Override
	public Collection<Schedule> deleteSchedule(long id) {
		scheduleRepository.delete(id);
		return getSchedules();
	}

	@Override
	public Collection<Schedule> saveSchedule(Schedule schedule) {
		
		Port source = schedule.getSource();
		Port destination = schedule.getOrder().getDestination();
		
		Route route = routeRepository.findRouteFromPorts(source, destination);
		
		schedule.setDeliveryDate(utils.getEndDateFromDays(schedule.getCollectionDate(), schedule.getShip(), route));

		Collection<Schedule> existing = scheduleRepository.findBySource(schedule.getSource());
		
		int total = 0;
		for (Schedule cur : existing) {
			total+=cur.getStoredCrates();
		}
		
		int remaining = schedule.getOrder().getQuantity()-total;
		
		schedule.setStoredCrates(remaining<schedule.getShip().getCapacity()?remaining:schedule.getShip().getCapacity());
		
		scheduleRepository.save(schedule);
		return getSchedules();
	}

}
