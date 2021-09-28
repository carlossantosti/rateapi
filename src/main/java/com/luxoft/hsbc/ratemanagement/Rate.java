package com.luxoft.hsbc.ratemanagement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The entity of the Rate data.
 * The model that is going to be saved on the database and be manipulated on the application.
 * 
 * @author Carlos Santos
 *
 */
@Entity
public class Rate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Boolean success;
	
    private Long timestamp;
    
    private Boolean historical;
    
    private String base;
    
    private LocalDate date;
    
    @ElementCollection
    Map<String, BigDecimal> rates;
    
	public Rate() {
	}

	public Rate(Long id, Boolean success, Long timestamp, Boolean historical, String base, LocalDate date,
			Map<String, BigDecimal> rates) {
		super();
		this.id = id;
		this.success = success;
		this.timestamp = timestamp;
		this.historical = historical;
		this.base = base;
		this.date = date;
		this.rates = rates;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Boolean getHistorical() {
		return historical;
	}

	public void setHistorical(Boolean historical) {
		this.historical = historical;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Map<String, BigDecimal> getRates() {
		return rates;
	}

	public void setRates(Map<String, BigDecimal> rates) {
		this.rates = rates;
	}
}
