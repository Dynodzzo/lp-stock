package fr.acpi.stock.controller.product;

import fr.acpi.stock.dal.product.IProductDAO;
import fr.acpi.stock.model.catalog.ICatalog;

public interface IProductController {
	void setCatalog(ICatalog catalog);
	void setProductDAO(IProductDAO productDAO);
}
