package com.luxoft.hsbc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Responsible for read the properties from the application 
 * and give access for the rest of the application.
 * 
 * @author Carlos Santos
 */
@Service
public class AccessKeysAndUri {
	
	@Value("${com.luxoft.hsbc.exchangeratesapi.API_KEY}")
    private String exchangeRatesApiKey;
	
	@Value("${com.luxoft.hsbc.exchangeratesapi.URI}")
    private String exchangeRatesUri;
	
	@Value("${com.luxoft.hsbc.LAST_MONTHS_IN_DAYS}")
    private int lastMonthsInDays;
	
	public String getExchangeRatesApiKey() {
		return exchangeRatesApiKey;
	}
	
	public String getExchangeRatesUri() {
		return exchangeRatesUri;
	}
	
	public int getLastMonthsInDays() {
		return lastMonthsInDays;
	}

}
