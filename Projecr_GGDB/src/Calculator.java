import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.NumberFormat;

import javax.swing.*;

//Создание оконной формы
public class Calculator {
	private JTextField CreditField;
	private JLabel CreditLabel;
	private JLabel rubLabel;
	private JLabel email1;
	
	private JLabel email3;
	private JLabel email2;
	private JLabel email4;
	private JTextField srokField;
	private JLabel srokLabel;
	private JLabel yearsLabel;
	private JTextField stavkaField;
	private JLabel stavkaLabel;
	private JLabel procentLabel;
	private JButton calcButton;
	private JButton inf;
	private JLabel resultLabel;
	private JFrame mainFrame;
	private JRadioButton annuitCheck;
	private JRadioButton diffCheck;
	boolean annu = false;
	boolean diff = false;

	public Calculator() {
		CreditLabel = new JLabel("Сумма кредита");
		annuitCheck = new JRadioButton("Аннуитетный", true);
		diffCheck = new JRadioButton("Дифференциальный", false);
		CreditField = new JTextField("0");
		CreditField.setHorizontalAlignment(JTextField.RIGHT);
		rubLabel = new JLabel("руб");
		srokLabel = new JLabel("Срок кредита");
		srokField = new JTextField("0");
		srokField.setHorizontalAlignment(JTextField.RIGHT);
		yearsLabel = new JLabel("год");
		stavkaLabel = new JLabel("Кредитная ставка");
		stavkaField = new JTextField("0");
		stavkaField.setHorizontalAlignment(JTextField.RIGHT);
		procentLabel = new JLabel("%");
		calcButton = new JButton("Расчитать");
		inf = new JButton("Информация");
		email1 = new JLabel("Галанов 1_galanov@bk.ru");
		email2 = new JLabel("Галиуллин gal9.tima@bk.ru");

		email3 = new JLabel("Байбурина oli6574@mail.ru");
		email4 = new JLabel("Дьяконов diakonov54@gmail.com");

		calcButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				double ostatok = 0.0;
				double plat = 0.0;
				double result = 0.0;
				int[] num = new int[100];
				String[] ost = new String[100];
				String[] pro = new String[100];
				String[] platezh = new String[100];
				double[] sum = new double[100];
				double[] cred = new double[100];
				double osnDolg = 0.0;
				try {
					double Credit = Double.parseDouble(CreditField.getText());
					int srok = Integer.parseInt(srokField.getText());
					double stavka = Double.parseDouble(stavkaField.getText());
					if (srok <= 0 || Credit < 1000 || stavka < 0.01) {
						resultLabel.setText("Введены некорректные данные");

					} else {
						srok = srok * 12;
						stavka /= 100.0;
						stavka /= 12.0;
						result = (Credit * stavka) / (1 - Math.pow(1 + stavka, -srok));
						if (annuitCheck.isSelected()) {
							annu = true;
							diff = false;

						} else {
							diff = true;
							annu = false;
							double mesPlatezh = 0.0;
							double osnPlatezh = 0.0;
							double proc = 0.0;
							num[0] = 1;
							ostatok = Credit;
							for (int i = 0; i <= srok; i++) {
								if (ostatok >= 10) {
									num[i] = i + 1;
									osnPlatezh = Credit / srok;
									platezh[i] = "" + String.format("%.2f", osnPlatezh);
									ostatok = ostatok - osnPlatezh;
									ost[i] = "" + String.format("%.2f", ostatok);
									proc = ostatok * stavka;
									pro[i] = "" + String.format("%.2f", proc);
									sum[i] = proc + osnPlatezh;

								}
							}

						}
						System.out.println("ost = " + "" + " num = " + "" + "pro = " + "");

						new Results(num, ost, result, srok, pro, sum, diff, annu, platezh);
					}
				} catch (NumberFormatException nfe) {
					resultLabel.setText("Введены некорректные данные");
				}
			}
			
		});
		
		ButtonGroup group = new ButtonGroup();

		group.add(diffCheck);
		group.add(annuitCheck);

		JButton site = new JButton("Сайт ЦБ");
		site.setBounds(200, 310, 120, 50);
		site.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		launchBrowser("cbr.ru");
		}
		});
		
		inf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,"https://github.com/ga1anov/project_GGDB","Info", JOptionPane.INFORMATION_MESSAGE);
			}
			});
		
		
		
		JButton convert = new JButton("Конвертер");
		convert.setBounds(200, 310, 120, 50);
		convert.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		launchBrowser("cash.rbc.ru/cash/converter.html");
		}
		});

		JButton calc = new JButton("Онлайн-калькулятор");
		calc.setBounds(200, 310, 120, 50);
		calc.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		launchBrowser("web2.0calc.ru");
		}
		});
		
		resultLabel = new JLabel("");

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.insets = new Insets(10, 40, 10, 10);
		
		//Кнопка на сайт ЦБ
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(site, constraints);
		
		//Кнопка на сайт конвертера
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(convert, constraints);
		
		//Кнопка для онлайн-калькулятора
		constraints.gridx = 1;
		constraints.gridy = 6;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(calc, constraints);

		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.WEST;// west
		constraints.ipadx = 90;
		constraints.gridy = 0;
		layout.setConstraints(CreditLabel, constraints);
		constraints.gridy = 1;
		layout.setConstraints(srokLabel, constraints);
		constraints.gridy = 2;
		layout.setConstraints(stavkaLabel, constraints);

		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.ipadx = 50;
		constraints.gridy = 0;
		layout.setConstraints(CreditField, constraints);
		constraints.gridy = 1;
		layout.setConstraints(srokField, constraints);
		constraints.gridy = 2;
		layout.setConstraints(stavkaField, constraints);

		constraints.gridx = 2;
		constraints.ipadx = 0;
		constraints.gridy = 0;
		layout.setConstraints(rubLabel, constraints);
		constraints.gridy = 1;
		layout.setConstraints(yearsLabel, constraints);
		constraints.gridy = 2;
		layout.setConstraints(procentLabel, constraints);
		constraints.gridx = 1;
		constraints.gridy = 7;
		layout.setConstraints(email1, constraints);
		constraints.gridx = 1;
		constraints.gridy = 8;
		layout.setConstraints(email2, constraints);

		
		constraints.gridx = 1;
		constraints.gridy = 10;
		layout.setConstraints(email4, constraints);

		
		
		constraints.gridx = 1;
		constraints.gridy = 9;
		layout.setConstraints(email3, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(calcButton, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 11;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.anchor = GridBagConstraints.EAST;
		layout.setConstraints(inf, constraints);

		constraints.gridy = 5;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(resultLabel, constraints);

		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(annuitCheck, constraints);

		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(diffCheck, constraints);

		mainFrame = new JFrame("Кредитный калькулятор");
		mainFrame.setSize(700, 700);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(layout);

		
		
		mainFrame.add(email3);
		mainFrame.add(email4);
		mainFrame.add(email1);
		mainFrame.add(email2);
		mainFrame.add(CreditLabel);
		mainFrame.add(srokLabel);
		mainFrame.add(stavkaLabel);
		mainFrame.add(CreditField);
		mainFrame.add(srokField);
		mainFrame.add(stavkaField);
		mainFrame.add(rubLabel);
		mainFrame.add(yearsLabel);
		mainFrame.add(procentLabel);
		mainFrame.add(calcButton);
		mainFrame.add(inf);
		mainFrame.add(resultLabel);
		mainFrame.add(diffCheck);
		mainFrame.add(annuitCheck);
		mainFrame.add(site);
		mainFrame.add(convert);
		mainFrame.add(calc);
		

		mainFrame.pack();
		mainFrame.setVisible(true);
		
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Calculator();
			}
		});
	}
	private void launchBrowser(String uriStr) {
		Desktop desktop;
		if (Desktop.isDesktopSupported()) {
		desktop = Desktop.getDesktop();
		if (desktop.isSupported(Desktop.Action.BROWSE)) {
		// launch browser
		URI uri;
		try {
		uri = new URI("http://" + uriStr);
		desktop.browse(uri);
		} catch (IOException ioe) {
		ioe.printStackTrace();
		} catch (URISyntaxException use) {
		use.printStackTrace();
		}
		}
		
		}
	}
}


