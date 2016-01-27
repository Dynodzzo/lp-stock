package fr.acpi.stock.view;

import fr.acpi.stock.product.ProductController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteWindow extends JFrame implements ActionListener {
	private JButton btnSubmit;
	private JComboBox<String> cbxProducts;

	private ProductController _productCtrl;

	public DeleteWindow(String[] productsNames, ProductController productCtrl) {
		this._productCtrl = productCtrl;

		this.setTitle("Supprimer un produit");
		this.setBounds(500, 500, 200, 105);

		this.btnSubmit = new JButton("Supprimer");
		this.cbxProducts = new JComboBox<>(productsNames);
		this.cbxProducts.setPreferredSize(new Dimension(100, 20));
		JLabel lblName = new JLabel("Produit");

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());

		contentPane.add(lblName);
		contentPane.add(this.cbxProducts);
		contentPane.add(this.btnSubmit);

		this.btnSubmit.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == this.btnSubmit) {
			System.out.println("Submit delete product");
			this._productCtrl.deleteProduct(this.cbxProducts.getSelectedItem().toString());
			this.dispose();
		}
	}
}
