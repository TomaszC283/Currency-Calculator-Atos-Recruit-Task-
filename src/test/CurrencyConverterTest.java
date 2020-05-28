package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import atos.recruitment.controller.CurrencyConverter;
import atos.recruitment.repository.CurrencyRepository;

import org.junit.jupiter.api.Test;

class CurrencyConverterTest {

	
	@Test
	void multiplicationOfCurrencyShouldReturnCorrectValue() {
		
		CurrencyRepository.ReadXMLFile();
		
		/* For PLN, Euro rate is 1 : 4.4372 */
		assertEquals(4.4372, CurrencyConverter.CurrencyExchange(1, CurrencyRepository.currencyValues.get("PLN")));
	}


}
