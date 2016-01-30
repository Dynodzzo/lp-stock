package fr.acpi.stock.catalog.controller;

import fr.acpi.stock.catalog.ICatalog;
import fr.acpi.stock.catalog.dal.ICatalogDAO;
import fr.acpi.stock.catalogs.ICatalogs;
import fr.acpi.stock.product.controller.ProductController;
import fr.acpi.stock.product.controller.SalesController;
import fr.acpi.stock.product.controller.StockController;
import fr.acpi.stock.product.dal.IProductDAO;
import fr.acpi.stock.view.CatalogWindow;

public class CatalogController {
	ICatalogs _catalogs;
	ICatalogDAO _catalogDAO;
	IProductDAO _productDAO;
	ProductController _productCtrl;
	SalesController _salesCtrl;
	StockController _stockCtrl;

	public CatalogController(ICatalogs catalogs, ICatalogDAO catalogDAO, IProductDAO productDAO) {
		this._catalogs = catalogs;
		this._catalogDAO = catalogDAO;
		this._productDAO = productDAO;
		this._productCtrl = new ProductController(null, this._productDAO);
		this._salesCtrl = new SalesController(null, this._productDAO);
		this._stockCtrl = new StockController(null, this._productDAO);
		this._catalogs.addCatalogs(this._catalogDAO.getAll());
	}

	public void setCatalogDAO(ICatalogDAO catalogDAO) {
		this._catalogDAO = catalogDAO;
	}

	public boolean createCatalog(ICatalog catalog) {
		boolean created = false;

		if (this._catalogs.addCatalog(catalog) && this._catalogDAO.create(catalog)) {
			created = true;
		}

		return created;
	}

	public boolean deleteCatalog(String name) {
		boolean deleted = false;

		if (this._catalogDAO.delete(this._catalogs.catalog(name)) && this._catalogs.deleteCatalog(name)) {
			deleted = true;
		}

		return deleted;
	}

	public String[] catalogsNames() {
		return this._catalogs.catalogsNames();
	}

	public int catalogs() {
		return this._catalogs.size();
	}

	public String[] catalogsDetails() {
		String[] catalogsNames = this._catalogs.catalogsNames();
		String[] details = new String[this._catalogs.size()];

		for (int i = 0; i < this._catalogs.size(); i++) {
			int products = this._catalogDAO.products(this._catalogs.catalog(catalogsNames[i]));
			details[i] = catalogsNames[i] + " : " + products + " produits\n";
		}

		return details;
	}

	public void selectCatalog(String catalogName) {
		ICatalog selectedCatalog = this._catalogs.catalog(catalogName);
		selectedCatalog.addProducts(this._productDAO.getAll(catalogName));

		this._productCtrl.setCatalog(selectedCatalog);
		this._salesCtrl.setCatalog(selectedCatalog);
		this._stockCtrl.setCatalog(selectedCatalog);

		new CatalogWindow(selectedCatalog, this._productCtrl, this._salesCtrl, this._stockCtrl);
	}
}
