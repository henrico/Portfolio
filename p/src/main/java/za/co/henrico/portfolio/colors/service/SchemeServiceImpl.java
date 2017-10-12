package za.co.henrico.portfolio.colors.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import za.co.henrico.portfolio.colors.model.Scheme;
import za.co.henrico.portfolio.colors.repository.SchemeRepository;

@Component
public class SchemeServiceImpl implements SchemeService {

	@Autowired
	private SchemeRepository scheduleRepository;

	public Collection<Scheme> getList(){
		return scheduleRepository.findAll();
	}

	public void save(Scheme object){
		scheduleRepository.save(object);
	}

	public void deleteAll(){
		scheduleRepository.deleteAllInBatch();
	}

}
