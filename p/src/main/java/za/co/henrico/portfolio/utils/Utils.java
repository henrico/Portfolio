package za.co.henrico.portfolio.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.model.Route;
import za.co.henrico.portfolio.model.Ship;

@Component
@Scope("singleton")
public class Utils {

	public Date getEndDateFromDays(Date from,Ship ship,Route route) {
		int days = new BigDecimal(route.getDistance()).divide(new BigDecimal(ship.getSpeed()),RoundingMode.CEILING).intValue();
		
		Calendar c = Calendar.getInstance();
		Date deliverDate = new Date(from.getTime());
		c.setTime(deliverDate);
		c.add(Calendar.DATE, days);
		
		return c.getTime();
	}
	
}
