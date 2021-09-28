package com.luxoft.hsbc.ratemanagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.hsbc.common.Utils;
import com.luxoft.hsbc.config.AccessKeysAndUri;
import com.luxoft.hsbc.external.ExternalRates;

/**
 * Responsible to the manage the interaction in our end-points.
 * Mapping request data to the defined handler method and returning the info or just processing.
 * 
 * @author Carlos Santos
 *
 */
@RestController
@RequestMapping(path = "api/v1/")
public class RateController {
	
	@Autowired
	private RateService rateService;
	
	@Autowired
	private ExternalRates externalRates;
	
	@Autowired
	private AccessKeysAndUri accessKeysAndUri;
	
	
	/**
	 * Responsible to retrieve all the info between today and the past 12 months (365 days).
	 * 
	 * @return the list of rate values found and inserted to the database.
	 */
	@GetMapping(path = "retrieve")
	public List<Rate> retrieve() {
		List<Rate> rates = new ArrayList<>();
		
		for (int i = accessKeysAndUri.getLastMonthsInDays(); i >= 0; --i) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -i);
			Rate rate = externalRates.getHistorical(calendar.getTime());
			if (rate != null) {
				rates.add(rate);
			}
		}
		
		rateService.addNewRates(rates);
		
		return rates;
	}
	
	/**
	 * Responsible to find the rate info for the date informed and then create the message to the consumer.
	 * 
	 * @param date - the date to be searched.
	 * @return the text containing the rate, in case the info was not found, return null.
	 */
	@GetMapping(path = "rate/{date}")
	public String getRate(@PathVariable String date) {
		Rate rate = rateService.getRate(Utils.convertToLocalDate(date, Utils.DATE_FORMAT_US));
		if (rate != null)
			return "GBP rate for ".concat(date).concat(" equals ").concat(String.valueOf(rate.getRates().get("GBP")));
		return null;
	}
	
	/**
	 * Responsible to find the rate info for the range date informed.
	 *  
	 * @param startDate - the start date to be searched.
	 * @param endDate - the final date (the limit) to be searched.
	 * @return the list of data found between the dates.
	 */
	@GetMapping(path = "rate")
	public List<Rate> getRate(@RequestParam(name = "start_date") String startDate, @RequestParam(name = "end_date") String endDate) {
		return rateService.getRateByRange(Utils.convertToLocalDate(startDate, Utils.DATE_FORMAT_US), Utils.convertToLocalDate(endDate, Utils.DATE_FORMAT_US));
	}

}
