package fr.acpi.stock.controller.product;

import fr.acpi.stock.controller.product.IProductController;
import fr.acpi.stock.dal.product.IProductDAO;
import fr.acpi.stock.model.catalog.ICatalog;
import fr.acpi.stock.model.product.IProduct;

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

		if (this._catalog.addProduct(product) && this._productDAO.create(product)) {
			created = true;
		}

		return created;
	}

	public boolean deleteProduct(String name) {
		boolean deleted = false;

		if (this._productDAO.delete(this._catalog.product(name)) && this._catalog.deleteProduct(name)) {
			deleted = true;
		}

		return deleted;
	}
}
