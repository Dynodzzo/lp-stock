package fr.acpi.stock.product;

import fr.acpi.stock.catalog.ICatalog;

public interface IProductController {
	void setCatalog(ICatalog catalog);
	void setProductDAO(IProductDAO productDAO);
}
