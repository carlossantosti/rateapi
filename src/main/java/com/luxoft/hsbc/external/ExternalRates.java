package com.luxoft.hsbc.external;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.luxoft.hsbc.common.Utils;
import com.luxoft.hsbc.config.AccessKeysAndUri;
import com.luxoft.hsbc.ratemanagement.Rate;

/**
 * Service responsible to manage the external API.
 * 
 * @author Carlos Santos
 *
 */
@Service
public class ExternalRates {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AccessKeysAndUri accessKeysAndUri;
	
	private static final String P_ACCESS_KEY = "?access_key=";
	private static final String P_SYMBOLS = "&symbols=";
	
	/**
	 * This method will bring the historical rates for the date specified as a parameter.
	 * 
	 * @param date - the date to be searched.
	 * @return the object containing the searched date, otherwise null.
	 */
	public Rate getHistorical(Date date) {
		Objects.requireNonNull(date, "Error trying to get the historical rates (date cannot be null)");
		
		StringBuilder uri = new StringBuilder();
		
		uri.append(accessKeysAndUri.getExchangeRatesUri());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Utils.DATE_FORMAT_US);
		uri.append(simpleDateFormat.format(date));
		
		uri.append(P_ACCESS_KEY);
		uri.append(accessKeysAndUri.getExchangeRatesApiKey());
		uri.append(P_SYMBOLS);
		
		uri.append("GBP,USD,HKD");

		return restTemplate.getForObject(uri.toString(), Rate.class);
	}
}
