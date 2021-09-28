package com.luxoft.hsbc.ratemanagement;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.luxoft.hsbc.common.Utils;

@ExtendWith(MockitoExtension.class)
class RateServiceTest {
	
	@Mock
	private RateRepository rateRepository;
	private RateService rateService;
	
	@BeforeEach
	void setUp() {
		rateService = new RateService(rateRepository);
	}

	@Test
	void shouldReturnNullWhenDataIsNotFoundNullParameter() {
		Assertions.assertNull(rateService.getRate(null));
	}
	
	@Test
	void shouldReturnNullWhenDataIsNotFound() {
		Assertions.assertNull(rateService.getRate(Utils.convertToLocalDate("2021-09-15", Utils.DATE_FORMAT_US)));
	}
	
	@Test
	void shouldReturnEmptyWhenDataIsNotFoundNullParameter() {
		Assertions.assertTrue(rateService.getRateByRange(null, null).isEmpty());
	}
	
	@Test
	void shouldReturnEmptyWhenDataIsNotFound() {
		Assertions.assertTrue(rateService.getRateByRange(Utils.convertToLocalDate("2021-09-15", Utils.DATE_FORMAT_US), Utils.convertToLocalDate("2021-09-25", Utils.DATE_FORMAT_US)).isEmpty());
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@Test
	void addNewRates() {
		// given
		Map<String, BigDecimal> ratesMap = new HashMap<>();
		ratesMap.put("GBP", new BigDecimal(0.98989));
		ratesMap.put("USD", new BigDecimal(1.98989));
		List<Rate> rates = List.of(new Rate(1L, true, 123123123L, true, null, LocalDate.now(), ratesMap),
				new Rate(2L, true, 123145646L, false, null, LocalDate.now(), ratesMap));
		
		// when
		rateService.addNewRates(rates);
		
		// then		
		ArgumentCaptor<List<Rate>> argumentCaptor = ArgumentCaptor.forClass((Class) List.class);
		verify(rateRepository).saveAll(argumentCaptor.capture());
		List<Rate> capturedRates = argumentCaptor.getValue();
		
		Assertions.assertEquals(capturedRates, rates);
	}

}
