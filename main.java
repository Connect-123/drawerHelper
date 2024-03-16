import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {

	private static int parseInput(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static void main(String[] args) {
		// Create and set up the window.
		JFrame frame = new JFrame("Cash Drawer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.getContentPane().setBackground(Color.lightGray);

		// Set up layout manager
		GridBagLayout gridBag = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		frame.setLayout(gridBag);

		// Create font
		Font font = new Font("Arial", Font.PLAIN, 20);

		// Create the panel for the rolled coins.
		JPanel panelCoins = new JPanel(new GridLayout(6, 2));
		panelCoins.setBorder(BorderFactory.createTitledBorder("Rolled Coins"));
		JLabel[] coinLabels = { new JLabel("5¢"), new JLabel("10¢"), new JLabel("25¢"), new JLabel("$1"),
				new JLabel("$2") };
		JTextField[] coinFields = { new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10),
				new JTextField(10) };
		int[] coinValues = { 200, 500, 1000, 2500, 5000 }; // Updated values for rolled coins
		for (int i = 0; i < coinLabels.length; i++) {
			coinLabels[i].setFont(font);
			coinFields[i].setFont(font);
			panelCoins.add(coinLabels[i]);
			panelCoins.add(coinFields[i]);
		}

		// Create the panel for the bills and loose coins.
		JPanel panelBills = new JPanel(new GridLayout(9, 2));
		panelBills.setBorder(BorderFactory.createTitledBorder("Drawer"));
		JLabel[] billLabels = { new JLabel("$20"), new JLabel("$10"), new JLabel("$5"), new JLabel("$2"),
				new JLabel("$1"), new JLabel("25¢"), new JLabel("10¢"), new JLabel("5¢") };
		JTextField[] billFields = { new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10),
				new JTextField(10), new JTextField(10), new JTextField(10), new JTextField(10) };
		int[] billValues = { 2000, 1000, 500, 200, 100, 25, 10, 5 }; // Added values for loose coins
		for (int i = 0; i < billLabels.length; i++) {
			billLabels[i].setFont(font);
			billFields[i].setFont(font);
			panelBills.add(billLabels[i]);
			panelBills.add(billFields[i]);
		}

		// Create the target total field.
		JPanel panelTarget = new JPanel();
		JLabel targetLabel = new JLabel("Target Total (dollars):");
		targetLabel.setFont(font);
		panelTarget.add(targetLabel);
		JTextField targetField = new JTextField(10);
		targetField.setFont(font);
		panelTarget.add(targetField);

		// Create the total label.
		JLabel totalLabel = new JLabel("Total: $0.00");
		totalLabel.setFont(font);

		// Create the calculate button.
		JButton calculateButton = new JButton("Calculate");
		calculateButton.setFont(font);
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int total = 0;

				// Calculate total for coins.
				for (int i = 0; i < coinFields.length; i++) {
					total += parseInput(coinFields[i].getText()) * coinValues[i];
				}

				// Calculate total for bills.
				for (int i = 0; i < billFields.length; i++) {
					total += parseInput(billFields[i].getText()) * billValues[i];
				}

				// Compare with target total.
				int targetTotal = parseInput(targetField.getText()) * 100; // convert to cents for comparison
				if (total < targetTotal) {
					totalLabel.setText("Total: $" + total / 100.00 + ". Add $" + (targetTotal - total) / 100.00);
				} else if (total > targetTotal) {
					totalLabel.setText("Total: $" + total / 100.00 + ". Remove $" + (total - targetTotal) / 100.00);
				} else {
					totalLabel.setText("Total: $" + total / 100.00 + ". Exact amount.");
				}
			}
		});

		// Add everything to the frame.
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		frame.add(panelCoins, constraints);

		constraints.gridx = 2;
		frame.add(panelBills, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		frame.add(panelTarget, constraints);

		constraints.gridx = 1;
		frame.add(calculateButton, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		frame.add(totalLabel, constraints);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
}
