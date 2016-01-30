package fr.acpi.stock.view;

import fr.acpi.stock.catalog.ICatalog;
import fr.acpi.stock.product.controller.ProductController;
import fr.acpi.stock.product.controller.SalesController;
import fr.acpi.stock.product.controller.StockController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CatalogWindow extends JFrame implements ActionListener {
	private JButton btnDisplay;
	private JButton btnNew;
	private JButton btnDelete;
	private JButton btnPurchase;
	private JButton btnSale;
	private JButton btnExit;

	ProductController _productCtrl;
	SalesController _salesCtrl;
	StockController _stockCtrl;

	ICatalog _catalog;

	public CatalogWindow(ICatalog catalog, ProductController productCtrl, SalesController salesCtrl, StockController stockCtrl) {
		this._catalog = catalog;
		this._productCtrl = productCtrl;
		this._salesCtrl = salesCtrl;
		this._stockCtrl = stockCtrl;

		this.setTitle("Gestion de stock");
		this.setBounds(500, 500, 320, 250);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		JPanel pnlDisplay = new JPanel();
		JPanel pnlNewDelete = new JPanel();
		JPanel pnlPurchaseSale = new JPanel();
		JPanel pnlExit = new JPanel();

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new FlowLayout());

		this.btnDisplay = new JButton("Quantités en stock");
		this.btnNew = new JButton("Nouveau produit");
		this.btnDelete = new JButton("Supprimer produit");
		this.btnPurchase = new JButton("Acheter produit");
		this.btnSale = new JButton("Vendre produit");
		this.btnExit = new JButton("Quitter");

		this.btnDisplay.addActionListener(this);
		this.btnNew.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnPurchase.addActionListener(this);
		this.btnSale.addActionListener(this);
		this.btnExit.addActionListener(this);

		pnlDisplay.add(this.btnDisplay);
		pnlNewDelete.add(this.btnNew);
		pnlNewDelete.add(this.btnDelete);
		pnlPurchaseSale.add(this.btnPurchase);
		pnlPurchaseSale.add(this.btnSale);
		pnlExit.add(this.btnExit);

		contentPane.add(pnlDisplay);
		contentPane.add(pnlNewDelete);
		contentPane.add(pnlPurchaseSale);
		contentPane.add(pnlExit);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == this.btnDisplay) {
			new StockWindow(this._stockCtrl);
			System.out.println("Display stock");
		}
		else if (source == this.btnNew) {
			new NewWindow(this._productCtrl);
			System.out.println("New product");
		}
		else if (source == this.btnDelete) {
			new DeleteWindow(this._catalog.productsNames(), this._productCtrl);
			System.out.println("Delete product");
		}
		else if (source == this.btnPurchase) {
			new PurchaseWindow(this._catalog.productsNames(), this._salesCtrl);
			System.out.println("Purchase a product");
		}
		else if (source == this.btnSale) {
			new SaleWindow(this._catalog.productsNames(), this._salesCtrl);
			System.out.println("Sell a product");
		}
		else if (source == this.btnExit) {
			System.out.println("Exit");
			System.exit(0);
		}
	}
}
