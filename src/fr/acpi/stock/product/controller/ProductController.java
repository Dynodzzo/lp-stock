package fr.acpi.stock.product.controller;

import fr.acpi.stock.product.dal.IProductDAO;
import fr.acpi.stock.catalog.ICatalog;
import fr.acpi.stock.product.IProduct;

public class ProductController implements IProductController {
	protected ICatalog _catalog;
	protected IProductDAO _productDAO;

	public ProductController(ICatalog catalog, IProductDAO productDAO) {
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

	public boolean createProduct(IProduct product) {
		boolean created = false;

		if (this._catalog.addProduct(product) && this._productDAO.create(product, this._catalog.name())) {
			created = true;
		}

		return created;
	}

	public boolean deleteProduct(String name) {
		boolean deleted = false;

		if (this._productDAO.delete(this._catalog.product(name), this._catalog.name()) && this._catalog.deleteProduct(name)) {
			deleted = true;
		}

		return deleted;
	}
}
