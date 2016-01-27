package fr.acpi.stock.view;

import fr.acpi.stock.product.IProduct;
import fr.acpi.stock.product.Product;
import fr.acpi.stock.product.ProductController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWindow extends JFrame implements ActionListener {
	private JTextField txtName;
	private JTextField txtUnitPriceET;
	private JTextField txtAmount;
	private JButton btnSubmit;

	private ProductController _productCtrl;

	public NewWindow(ProductController productCtrl) {
		this._productCtrl = productCtrl;

		this.setTitle("Créer un produit");
		this.setBounds(500, 500, 200, 250);

		JLabel lblName = new JLabel("Nom du produit");
		JLabel lblUnitPriceET = new JLabel("Prix hors taxe");
		JLabel lblAmount = new JLabel("Quantité en stock");

		this.txtName = new JTextField(15);
		this.txtUnitPriceET = new JTextField(15);
		this.txtAmount = new JTextField(15);
		this.btnSubmit = new JButton("Valider");

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());

		contentPane.add(lblName);
		contentPane.add(this.txtName);
		contentPane.add(lblUnitPriceET);
		contentPane.add(this.txtUnitPriceET);
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
			System.out.println("Submit new product1");
			String name = this.txtName.getText();
			double unitPriceET = Double.parseDouble(this.txtUnitPriceET.getText());
			int amount = Integer.parseInt(this.txtAmount.getText());
			IProduct product = new Product(name, unitPriceET, amount);
			this._productCtrl.createProduct(product);
			this.dispose();
		}
	}
}
