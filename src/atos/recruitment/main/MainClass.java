/*
 * Atos - Recruitment task 
 * @Autor - Tomasz Czopur
 * EUR Currency calculator
 * version 1.2 ( 28.05.2020 )
 */

package atos.recruitment.main;

import atos.recruitment.repository.CurrencyRepository;
import atos.recruitment.view.MainWindow;

public class MainClass {
	public static void main(String[] args) {
		
		CurrencyRepository.ReadXMLFile();
		
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
		mw.euroCurrencyTF.requestFocus();
	}
}
