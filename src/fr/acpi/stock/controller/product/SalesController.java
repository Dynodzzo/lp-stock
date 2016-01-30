package fr.acpi.stock.controller.product;

import fr.acpi.stock.controller.product.IProductController;
import fr.acpi.stock.dal.product.IProductDAO;
import fr.acpi.stock.model.catalog.ICatalog;

public class SalesController implements IProductController {
	protected ICatalog _catalog;
	protected IProductDAO _productDAO;

	public SalesController(ICatalog catalog, IProductDAO productDAO) {
		this._catalog = catalog;
		this._productDAO = productDAO;
	}

	@Override
	public void setCatalog(ICatalog catalog) {
		this._catalog = catalog;
	}

	@Override
	public void setProductDAO(IProductDAO productDAO) {
		this._productDAO = productDAO;
	}

	public boolean buyProduct(String name, int amount) {
		boolean bought = false;

		if (this._catalog.buyStock(name, amount) && this._productDAO.update(this._catalog.product(name))) {
			bought = true;
		}

		return bought;
	}

	public boolean sellProduct(String name, int amount) {
		boolean sold = false;

		if (this._catalog.sellStock(name, amount) && this._productDAO.update(this._catalog.product(name))) {
			sold = true;
		}

		return sold;
	}
}
