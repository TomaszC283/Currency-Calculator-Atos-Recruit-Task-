package test;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import atos.recruitment.repository.CurrencyRepository;

class CurrencyRepositoryTest {

	
	@Test
	void currencyMapShouldntBeEmpty() {
		
		CurrencyRepository.ReadXMLFile();
		assertNotNull(CurrencyRepository.currencyValues);
	}
}
