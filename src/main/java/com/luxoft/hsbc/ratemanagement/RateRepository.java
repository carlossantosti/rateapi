package com.luxoft.hsbc.ratemanagement;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface responsible to manage the interaction to the database.
 * 
 * @author Carlos Santos
 *
 */
@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
	
	/**
	 * Find rate by date.
	 * 
	 * @param date - the date to be searched.
	 * @return the rate if it was found, otherwise null.
	 */
	Rate findByDate(LocalDate date);
	
	/**
	 * Find rates between the dates informed.
	 * 
	 * @param startDate - the initial date to search.
	 * @param endDate - the final date to search.
	 * @return the list of rate found, otherwise empty.
	 */
	List<Rate> findByDateBetween(LocalDate startDate, LocalDate endDate);

}