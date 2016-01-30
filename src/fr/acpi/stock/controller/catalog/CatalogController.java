package fr.acpi.stock.controller.catalog;

import java.util.ArrayList;
import java.util.List;

import fr.acpi.stock.dal.catalog.ICatalogDAO;
import fr.acpi.stock.dal.product.IProductDAO;
import fr.acpi.stock.model.ListCatalogs;
import fr.acpi.stock.model.catalog.Catalog;
import fr.acpi.stock.model.catalog.ICatalog;
import fr.acpi.stock.model.product.IProduct;

public class CatalogController {	
	private ListCatalogs _catalogs;
	private ICatalogDAO _catalogDAO;
	
	public CatalogController(ListCatalogs catalogs, ICatalogDAO catalogDAO) {
		this._catalogs = catalogs;
		this._catalogDAO  = catalogDAO;
	}
	
	public void setListCatalogs(ListCatalogs catalogs) {
		this._catalogs = catalogs;
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
	
	public String[] getNamesAllCatalogs() {
		return this._catalogs.catalogsNames();
	}
	
	public int numberCatalogs() {
		return this._catalogs.numberCatalogcatalogs();
	}
}
