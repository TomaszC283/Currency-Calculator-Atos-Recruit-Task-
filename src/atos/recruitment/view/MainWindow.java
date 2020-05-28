package atos.recruitment.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import atos.recruitment.controller.CurrencyConverter;
import atos.recruitment.repository.CurrencyRepository;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	/* GUI */
	private JButton closeAppButton = new JButton("Close application");
	private JComboBox<String> currencyList = new JComboBox<String>();
	private JTextField anotherCurrencyTF = new JTextField();
	private JLabel header = new JLabel("Currency calculator");
	private JLabel currencyRateLabel = new JLabel();
	private JLabel euroLabel = new JLabel("Amount of Euros :");
	private JLabel chooseCurrencyLabel = new JLabel("Choose currency :");
	private JLabel anotherCurrencyLabel = new JLabel("Result amount :");
	private JLabel languageLabel = new JLabel("Language :");
	private JPanel currencyPanel = new JPanel();

	private ImageIcon imageIcon = new ImageIcon("src/resources/background.jpg");
	private ImageIcon flagPLIcon = new ImageIcon("src/resources/flag-pl.png");
	private ImageIcon flagENIcon = new ImageIcon("src/resources/flag-en.png");
	private JLabel background;
	private JLabel flagPL;
	private JLabel flagEN;

	public JTextField euroCurrencyTF = new JTextField(); // Public for set Focus

	/* VARIABLES */
	private static double euroAmount;
	private static double currencyRate;
	private static boolean isPolishLanguage = false;

	public MainWindow() {

		/* Initialize window */
		super("Atos - Currency Calculator - Tomasz Czopur");
		setSize(600, 350);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		refreshComboBox();
		refreshCurrencyRateLabel(currencyList.getSelectedItem().toString());

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - 600) / 2);
		int y = (int) ((dimension.getHeight() - 400) / 2);
		setLocation(x, y);

		/* Side image */
		background = new JLabel("", imageIcon, JLabel.CENTER);
		background.setBounds(0, 0, 194, 322);
		background.setBorder(new LineBorder(Color.BLACK, 2));
		add(background);

		/* Components */
		currencyPanel.setLayout(null);
		currencyPanel.setBackground(new Color(222, 252, 255));
		currencyPanel.setBorder(new LineBorder(Color.BLACK, 2));
		currencyPanel.setBounds(192, 0, 402, 322);
		add(currencyPanel);

		header.setFont(new Font("Dialog", Font.BOLD, 16));
		header.setBounds(15, 20, 200, 20);
		currencyPanel.add(header);

		languageLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		languageLabel.setBounds(250, 10, 100, 20);
		currencyPanel.add(languageLabel);

		flagPL = new JLabel("", flagPLIcon, JLabel.CENTER);
		flagPL.setBounds(325, 13, 25, 15);
		currencyPanel.add(flagPL);

		flagEN = new JLabel("", flagENIcon, JLabel.CENTER);
		flagEN.setBounds(355, 13, 25, 15);
		currencyPanel.add(flagEN);

		currencyRateLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		currencyRateLabel.setBounds(0, 60, 402, 20);
		currencyRateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currencyPanel.add(currencyRateLabel);

		chooseCurrencyLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		chooseCurrencyLabel.setBounds(30, 100, 150, 20);
		currencyPanel.add(chooseCurrencyLabel);

		currencyList.setBounds(200, 100, 150, 20);
		currencyList.setBackground(Color.WHITE);
		currencyList.setBorder(new LineBorder(Color.BLACK, 1));
		((JLabel) currencyList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		currencyPanel.add(currencyList);

		euroLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		euroLabel.setBounds(30, 150, 150, 20);
		currencyPanel.add(euroLabel);

		euroCurrencyTF.setBounds(200, 150, 150, 20);
		euroCurrencyTF.setFont(new Font("Dialog", Font.BOLD, 15));
		euroCurrencyTF.setBorder(new LineBorder(Color.BLACK, 1));
		euroCurrencyTF.setHorizontalAlignment(JTextField.CENTER);
		currencyPanel.add(euroCurrencyTF);

		anotherCurrencyLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		anotherCurrencyLabel.setBounds(30, 200, 150, 20);
		currencyPanel.add(anotherCurrencyLabel);

		anotherCurrencyTF.setBounds(200, 200, 150, 20);
		anotherCurrencyTF.setFont(new Font("Dialog", Font.BOLD, 15));
		anotherCurrencyTF.setBorder(new LineBorder(Color.BLACK, 1));
		anotherCurrencyTF.setEditable(false);
		anotherCurrencyTF.setHorizontalAlignment(JTextField.CENTER);
		currencyPanel.add(anotherCurrencyTF);

		closeAppButton.setBackground(Color.GRAY);
		closeAppButton.setBorder(new LineBorder(Color.BLACK, 1));
		closeAppButton.setForeground(Color.WHITE);
		closeAppButton.setBounds(232, 282, 150, 25);
		currencyPanel.add(closeAppButton);

		/* Focus on Euro Text Field */
		euroCurrencyTF.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				((JTextField) e.getSource()).selectAll();
			}

			public void focusLost(FocusEvent e) {
			}
		});

		/* Listeners */
		flagPL.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				header.setText("Kalkulator walutowy");
				languageLabel.setText("       Język :");
				chooseCurrencyLabel.setText("Wybierz walutę :");
				euroLabel.setText("Kwota w Euro :");
				anotherCurrencyLabel.setText("Kwota po przeliczeniu :");
				closeAppButton.setText("Zamknij aplikację");
				setTitle("Atos - Kalkulator walutowy - Tomasz Czopur");
				
				isPolishLanguage = true;
				refreshCurrencyRateLabel(currencyList.getSelectedItem().toString());
			}
		});

		flagEN.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				header.setText("Currency calculator");
				languageLabel.setText("Language :");
				chooseCurrencyLabel.setText("Choose currency :");
				euroLabel.setText("Amount of Euros :");
				anotherCurrencyLabel.setText("Result amount :");
				closeAppButton.setText("Close application");
				setTitle("Atos - Currency calculator - Tomasz Czopur");
				
				isPolishLanguage = false;
				refreshCurrencyRateLabel(currencyList.getSelectedItem().toString());
			}
		});

		closeAppButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		/* Detecting ComboBox Item selection changed */
		currencyList.addItemListener(new ItemListener() {
			// Listening if a new items of the combo box has been selected.
			public void itemStateChanged(ItemEvent event) {

				if (event.getStateChange() == ItemEvent.SELECTED) {
					refreshResult();
					refreshCurrencyRateLabel(currencyList.getSelectedItem().toString());
				}
			}
		});

		/*
		 * Detecting entered value and converting this to Correct for double format,
		 * avoid letters
		 */
		euroCurrencyTF.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent evt) {

				char c = evt.getKeyChar();

				String weightString = euroCurrencyTF.getText();

				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_COMMA))) {
					evt.consume();
				}

				if (c == KeyEvent.VK_COMMA) {
					evt.setKeyChar((char) KeyEvent.VK_PERIOD);
				}

				if (weightString.equals("00")) {
					euroCurrencyTF.setText("");
				}

				if (weightString.equals(".")) {
					euroCurrencyTF.setText("0.");
				}

				if (weightString.length() > 1 && weightString.charAt(0) == KeyEvent.VK_0
						&& weightString.charAt(1) != KeyEvent.VK_PERIOD) {
					euroCurrencyTF.setText("0." + weightString.substring(1));
				}

				if (weightString.equals("")) {
					anotherCurrencyTF.setText("");
				}

				if (!weightString.equals("")) {
					if (currencyList.getSelectedItem() != null) {
						refreshResult();
					}
				}

			}

			@Override
			public void keyTyped(KeyEvent evt) {

				char c = evt.getKeyChar();

				String weightString = euroCurrencyTF.getText();

				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == KeyEvent.VK_PERIOD) || (c == KeyEvent.VK_COMMA))) {
					evt.consume();
				}

				if (c == KeyEvent.VK_COMMA) {
					evt.setKeyChar((char) KeyEvent.VK_PERIOD);
				}

				if (weightString.contains(".") && c == KeyEvent.VK_PERIOD) {
					evt.consume();
				}

				if (weightString.contains(".") && c == KeyEvent.VK_COMMA) {
					evt.consume();
				}
			}
		});
	}

	/* Methods */

	private void refreshComboBox() {

		List<String> sortedList = new LinkedList<>();

		for (Map.Entry<String, Double> entry : CurrencyRepository.currencyValues.entrySet()) {
			sortedList.add(entry.getKey());
		}

		Collections.sort(sortedList);

		for (String sortedStr : sortedList) {
			currencyList.addItem(sortedStr);
		}
	}

	private void refreshCurrencyRateLabel(String item) {

		if (item.equals(null)) {
			item = currencyList.getItemAt(1).toString();
		}

		if (isPolishLanguage) {
			currencyRateLabel.setText("Kurs wymiany      1 EUR : "
					+ CurrencyRepository.currencyValues.get(currencyList.getSelectedItem().toString()) + " " + item);
		} else {
			currencyRateLabel.setText("Exchange rate      1 EUR : "
					+ CurrencyRepository.currencyValues.get(currencyList.getSelectedItem().toString()) + " " + item);
		}
	}

	private void refreshResult() {

		if (!euroCurrencyTF.getText().equals("")) {

			euroAmount = Double.parseDouble(euroCurrencyTF.getText());

			currencyRate = CurrencyRepository.currencyValues.get(currencyList.getSelectedItem());

			anotherCurrencyTF
					.setText(String.format("%.2f", CurrencyConverter.CurrencyExchange(euroAmount, currencyRate)));
		}
	}
}
