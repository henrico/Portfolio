package za.co.henrico.portfolio.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.model.Order;
import za.co.henrico.portfolio.model.Port;
import za.co.henrico.portfolio.model.Schedule;
import za.co.henrico.portfolio.model.Ship;
import za.co.henrico.portfolio.model.Warehouse;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	Collection<Schedule> findDateReleventSchedule(Date deliverDate, Date collectionDate, Ship current);

	Schedule findLastScheduleRelatedToShip(Ship ship);

	Collection<Schedule> findBySource(Port source);

	Collection<Schedule> findDateReleventScheduleByWarehouse(Date endDate, Date collectionDate, Warehouse current);

	Collection<Schedule> findByOrder(Order order);

	Collection<Schedule> findByShip(Ship ship);

	Collection<Schedule> findByProduct();

	Schedule findFirstScheduleRelatedToShipFromDate(Ship current, Date endDate);

}
