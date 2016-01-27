package fr.acpi.stock.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWindow extends JFrame implements ActionListener {
	private JTextField txtName;
	private JTextField txtUnitPriceET;
	private JTextField txtAmount;
	private JButton btnSubmit;

	public NewWindow() {
		this.setTitle("Créer un produit");
		this.setBounds(500, 500, 200, 250);

		JLabel lblName = new JLabel("Nom produit");
		JLabel lblUnitPriceET = new JLabel("Prix hors taxe");
		JLabel lblAmount = new JLabel("Quantité en stock");

		this.txtName = new JTextField(15);
		this.txtUnitPriceET = new JTextField(15);
		this.txtAmount = new JTextField(15);
		this.btnSubmit = new JButton("Valider");

		this.btnSubmit.addActionListener(this);

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());

		contentPane.add(lblName);
		contentPane.add(this.txtName);
		contentPane.add(lblUnitPriceET);
		contentPane.add(this.txtUnitPriceET);
		contentPane.add(lblAmount);
		contentPane.add(this.txtAmount);
		contentPane.add(this.btnSubmit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == this.btnSubmit) {
			System.out.println("Submit new product");
			this.dispose();
		}
	}
}
