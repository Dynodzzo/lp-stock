package fr.acpi.stock.controller.product;

import fr.acpi.stock.AppData;
import fr.acpi.stock.controller.product.IProductController;
import fr.acpi.stock.dal.product.IProductDAO;
import fr.acpi.stock.model.catalog.ICatalog;

public class StockController implements IProductController {
	protected ICatalog _catalog;
	protected IProductDAO _productDAO;

	public StockController(ICatalog catalog, IProductDAO productDAO) {
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

	public String getStock() {
		return AppData.catalog.toString();
	}
}
