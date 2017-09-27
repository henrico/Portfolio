package za.co.henrico.portfolio.service;

import java.util.Collection;

import za.co.henrico.portfolio.model.Schedule;

public interface ScheduleService {

	Collection<Schedule> getSchedules();

	Collection<Schedule> saveSchedule(Schedule schedule);

	Collection<Schedule> deleteSchedule(long id);

}
