package za.co.henrico.portfolio.routes.repository;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.henrico.portfolio.routes.model.Order;
import za.co.henrico.portfolio.routes.model.Port;
import za.co.henrico.portfolio.routes.model.Schedule;
import za.co.henrico.portfolio.routes.model.Ship;
import za.co.henrico.portfolio.routes.model.Warehouse;

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
