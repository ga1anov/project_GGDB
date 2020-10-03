import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class Results {
	private int[] num;
	private double[] sum;
	private String[] pro;
	private String[] ost;
	private String[] platezh;
	private boolean annu;
private boolean diff;
	private double result;
	private double osnDolg;
	private int srok;

	public Results(int num[], String[] ost, double result, int srok, String[] pro, double[] sum, boolean diff,
			boolean annu, String[] platezh) {
		this.sum = sum;
		this.pro = pro;
		this.annu = annu;
		this.platezh = platezh;
		this.diff = diff;
		this.srok = srok;
		this.result = result;
		this.osnDolg = osnDolg;
		if (diff == true) {
			this.num = num;
		this.ost = ost;
			JFrame frame = new JFrame("График платежей");
			frame.setBounds(100, 100, 700, 300);
			frame.setLayout(new BorderLayout());

			JPanel panel = new JPanel();

			JPanel form = new JPanel();
			form.setLayout(new GridBagLayout());
			JPanel resultPanel = new JPanel();
			// Размещение компонентов в удобной форме
			GridBagConstraints c = new GridBagConstraints();

			JPanel top = new JPanel();
			JLabel labelTitle = new JLabel("");
			labelTitle.setFont(new Font("Arial", Font.ITALIC, 22));
			String[] columnNames = { "№", "Сумма платежа", "Основной долг", "Начисленные %", "Остаток долга" };
			String[][] data = new String[100][100];
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
			for (int i = 0; i < num.length; i++) {
				if (num[i] != 0) {
					data[i][0] = "" + num[i];
					data[i][1] = "" + String.format("%.2f", sum[i]);
					data[i][2] = platezh[i];
					data[i][3] = pro[i];
					data[i][4] = ost[i];

				} else {
					break;
				}

			}

			JTable table = new JTable(data, columnNames);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setSize(700, 300);
			table.setMinimumSize(new Dimension(700, 300));
			TableColumn column = table.getColumnModel().getColumn(0);
			column.setPreferredWidth(7);
			JScrollPane scroll = new JScrollPane(table);

			panel.add(scroll);

			top.add(labelTitle);
			frame.add(panel, BorderLayout.NORTH);
			frame.setSize(500, 400);
			frame.setVisible(true);

		} else {
			JFrame frame = new JFrame("График платежей");
			frame.setBounds(100, 100, 700, 300);
			JPanel panel = new JPanel(null);
			NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
			JLabel label = new JLabel("Кредит на " + srok + "месяцев(а).");
			JLabel label1 = new JLabel("Ежемесячный платеж " + currencyFormat.format(result) + ".");
			JButton ok = new JButton("Принять");
			ok.setBounds(145, 100, 100, 40);
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					frame.dispose();
				}
			});
			label.setBounds(10, 5, 200, 40);
			label1.setBounds(10, 45, 300, 40);
			panel.add(ok);
			panel.add(label1);
			panel.add(label);
			frame.setContentPane(panel);
			frame.setSize(300, 200);
			frame.setVisible(true);
		}

	}
}