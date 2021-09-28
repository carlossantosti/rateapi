package com.luxoft.hsbc.ratemanagement;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.hsbc.common.HsbcException;

/**
 * Service layer responsible to business logic and to communicate between repository and controller.
 * 
 * @author Carlos Santos
 *
 */
@Service
public class RateService {

	@Autowired
	private RateRepository rateRepository;
	
	public RateService(RateRepository rateRepository) {
		super();
		this.rateRepository = rateRepository;
	}
	
	/**
	 * Responsible to add all rates at once into the database using the repository.
	 * 
	 * @param rates - the list of rates to be stored.
	 * @throws HsbcException - in case of any troubles in order to save the data.
	 */
	public void addNewRates(List<Rate> rates) throws HsbcException {
		try {
			rateRepository.saveAll(rates);
		} catch (Exception e) {
			throw new HsbcException("Ooops! I couldn't save the rate into the database. Details: "+e.getMessage());
		}
	}
	
	/**
	 * Find a rate by date.
	 * 
	 * @param date - the date to be searched.
	 * @return the rate found.
	 */
	public Rate getRate(LocalDate date) {
		return rateRepository.findByDate(date);
	}
	
	/**
	 * Get a list of rate between the dates informed.
	 * 
	 * @param startDate - the date to start to search.
	 * @param endDate - the final date to retrieve data from the storage.
	 * @return the list of data found.
	 */
	public List<Rate> getRateByRange(LocalDate startDate, LocalDate endDate) {
		return rateRepository.findByDateBetween(startDate, endDate);
	}

}
