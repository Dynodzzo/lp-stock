package fr.acpi.stock.view;

import fr.acpi.stock.catalog.Catalog;
import fr.acpi.stock.catalog.ICatalog;
import fr.acpi.stock.catalog.controller.CatalogController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnSelect;
	private JTextField txtAdd;
	private JLabel lblCatalogsNumber;
	private JComboBox cbxDelete;
	private JComboBox cbxSelect;
	private TextArea txaCatalogsDetails;

	private CatalogController _catalogCtrl;

	public MainWindow(CatalogController catalogCtrl) {
		this._catalogCtrl = catalogCtrl;

		this.setTitle("Choisissez un catalogue");
		this.setBounds(500, 500, 200, 125);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		Container contentPane = this.getContentPane();

		JPanel pnlInfosCatalogs = new JPanel();
		JPanel pnlCatalogsNumber = new JPanel();
		JPanel pnlCatalogsDetails = new JPanel();
		JPanel pnlCatalogsManagement = new JPanel();
		JPanel pnlAdd = new JPanel();
		JPanel pnlDelete = new JPanel();
		JPanel pnlSelect = new JPanel();

		pnlCatalogsNumber.setBackground(Color.white);
		pnlCatalogsDetails.setBackground(Color.white);
		pnlAdd.setBackground(Color.gray);
		pnlDelete.setBackground(Color.lightGray);
		pnlSelect.setBackground(Color.gray);

		pnlCatalogsNumber.add(new JLabel("Nous avons actuellement : "));
		this.lblCatalogsNumber = new JLabel();
		pnlCatalogsNumber.add(this.lblCatalogsNumber);

		this.txaCatalogsDetails = new TextArea();
		this.txaCatalogsDetails.setEditable(false);
		new JScrollPane(this.txaCatalogsDetails);
		this.txaCatalogsDetails.setPreferredSize(new Dimension(300, 100));
		pnlCatalogsDetails.add(this.txaCatalogsDetails);

		pnlAdd.add(new JLabel("Ajouter un catalogue : "));
		this.txtAdd = new JTextField(10);
		pnlAdd.add(this.txtAdd);
		this.btnAdd = new JButton("Ajouter");
		pnlAdd.add(this.btnAdd);

		pnlDelete.add(new JLabel("Supprimer un catalogue : "));
		this.cbxDelete = new JComboBox();
		this.cbxDelete.setPreferredSize(new Dimension(100, 20));
		pnlDelete.add(this.cbxDelete);
		this.btnDelete = new JButton("Supprimer");
		pnlDelete.add(this.btnDelete);

		pnlSelect.add(new JLabel("Sélectionner un catalogue : "));
		this.cbxSelect = new JComboBox();
		this.cbxSelect.setPreferredSize(new Dimension(100, 20));
		pnlSelect.add(this.cbxSelect);
		this.btnSelect = new JButton("Sélectionner");
		pnlSelect.add(this.btnSelect);

		pnlCatalogsManagement.setLayout (new BorderLayout());
		pnlCatalogsManagement.add(pnlAdd, "North");
		pnlCatalogsManagement.add(pnlDelete);
		pnlCatalogsManagement.add(pnlSelect, "South");

		pnlInfosCatalogs.setLayout(new BorderLayout());
		pnlInfosCatalogs.add(pnlCatalogsNumber, "North");
		pnlInfosCatalogs.add(pnlCatalogsDetails, "South");

		contentPane.add(pnlInfosCatalogs, "North");
		contentPane.add(pnlCatalogsManagement, "South");
		pack();

		this.btnAdd.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnSelect.addActionListener(this);

		this.update();

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == this.btnAdd) {
			String name = this.txtAdd.getText();
			this.txtAdd.setText("");

			ICatalog catalog = new Catalog(name);
			this._catalogCtrl.createCatalog(catalog);
			this.update();
		}
		else if (source == this.btnDelete) {
			String name = this.cbxDelete.getSelectedItem().toString();
			this._catalogCtrl.deleteCatalog(name);
			this.update();
		}
		else if (source == this.btnSelect) {
			System.out.println("Catalog selected");
			this._catalogCtrl.selectCatalog(this.cbxSelect.getSelectedItem().toString());
			this.dispose();
		}
	}

	private void updateLists(String[] catalogNames) {
		this.cbxDelete.removeAllItems();
		this.cbxSelect.removeAllItems();

		if (catalogNames != null) {
			for (int i = 0; i < catalogNames.length; i++) {
				this.cbxDelete.addItem(catalogNames[i]);
				this.cbxSelect.addItem(catalogNames[i]);
			}
		}
	}

	private void updateCatalogsNumber(int catalogsNumber) {
		this.lblCatalogsNumber.setText(catalogsNumber + " catalogues");
	}

	private void updateCatalogsDetails(String[] catalogsDetails) {
		if (catalogsDetails != null) {
			StringBuilder details = new StringBuilder();

			for (int i = 0; i < catalogsDetails.length; i++) {
				details.append(catalogsDetails[i]);
			}

			this.txaCatalogsDetails.setText(details.toString());
		}

	}

	private void update() {
		this.updateLists(this._catalogCtrl.catalogsNames());
		this.updateCatalogsNumber(this._catalogCtrl.catalogs());
		this.updateCatalogsDetails(this._catalogCtrl.catalogsDetails());
	}
}
