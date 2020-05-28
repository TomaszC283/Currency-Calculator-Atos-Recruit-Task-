package atos.recruitment.controller;

public class CurrencyConverter {

	public static double CurrencyExchange ( double euroAmount, double currencyRate) {
		
			double resultAmount = euroAmount * currencyRate;
		
		return resultAmount;
	}
}
