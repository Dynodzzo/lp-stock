package fr.acpi.stock.product;

import fr.acpi.stock.AppData;
import fr.acpi.stock.catalog.ICatalog;

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
		return AppData.Catalog.toString();
	}
}
