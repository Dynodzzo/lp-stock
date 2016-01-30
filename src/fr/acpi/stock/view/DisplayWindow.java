package fr.acpi.stock.view;

import fr.acpi.stock.controller.product.StockController;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayWindow extends JFrame implements ActionListener {
	private JButton btnLeave;

	private StockController _stockCtrl;

	public DisplayWindow(StockController stockCtrl) {
		this._stockCtrl = stockCtrl;

		this.setTitle("Etat du stock");
		this.setBounds(500, 500, 450, 250);

		JPanel pnlTop = new JPanel();
		JPanel pnlBottom = new JPanel();
		JTextArea txaStock = new JTextArea(this._stockCtrl.getStock(), 10, 5);

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		this.btnLeave = new JButton("Quitter");
		this.btnLeave.addActionListener(this);

		pnlTop.setLayout(new BorderLayout());
		pnlBottom.setLayout(new BorderLayout());

		pnlTop.add(txaStock);
		pnlBottom.add(this.btnLeave);

		contentPane.add(pnlTop, "North");
		contentPane.add(pnlBottom, "South");

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == this.btnLeave) {
			System.out.println("Exit from display");
			this.dispose();
		}
	}
}
