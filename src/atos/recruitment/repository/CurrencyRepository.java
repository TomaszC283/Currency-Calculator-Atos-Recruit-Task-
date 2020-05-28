/*
 * Class for Read XML File from resources
 */
package atos.recruitment.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CurrencyRepository {
	public static Map<String, Double> currencyValues = new HashMap<>();

	public static void ReadXMLFile() {

		try {
			File XMLFile = new File("src/resources/eurofxref-daily.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document currencyDoc = dBuilder.parse(XMLFile);

			currencyDoc.getDocumentElement().normalize();

			NodeList currencyList = currencyDoc.getElementsByTagName("Cube");

			for (int i = 0; i < currencyList.getLength(); i++) {
				Node currencyNode = currencyList.item(i);

				Element currencyElement = (Element) currencyNode;

				if (currencyNode.getNodeType() == Node.ELEMENT_NODE) {
					if (!currencyElement.getAttribute("rate").equals("")) {
						currencyValues.put(currencyElement.getAttribute("currency"),
								Double.parseDouble(currencyElement.getAttribute("rate")));
					}
				}
			}

		} catch (FileNotFoundException fileEx) {
			JOptionPane.showMessageDialog(null,
					"eurofxref-daily.xml not found in resources folder. Please, put the appropriate file in the folder and restart the program",
					"Error!", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
