package fr.acpi.stock.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaleWindow extends JFrame implements ActionListener {
	private JButton btnSubmit;
	private JTextField txtAmount;
	private JComboBox<String> cbxProducts;

	public SaleWindow(String[] productsNames) {
		this.setTitle("Vendre un produit");
		this.setBounds(500, 500, 200, 125);

		this.btnSubmit = new JButton("Vendre");
		this.txtAmount = new JTextField(5);
		this.txtAmount.setText("0");
		this.cbxProducts = new JComboBox<>(productsNames);
		this.cbxProducts.setPreferredSize(new Dimension(100, 20));
		JLabel lblName = new JLabel("Produit");
		JLabel lblAmount = new JLabel("Quantité vendue");

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());

		contentPane.add(lblName);
		contentPane.add(this.cbxProducts);
		contentPane.add(lblAmount);
		contentPane.add(this.txtAmount);
		contentPane.add(this.btnSubmit);

		this.btnSubmit.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == this.btnSubmit) {
			System.out.println("Submit sold product");
			this.dispose();
		}
	}
}
