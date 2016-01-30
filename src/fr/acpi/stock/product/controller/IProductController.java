package fr.acpi.stock.product.controller;

import fr.acpi.stock.product.dal.IProductDAO;
import fr.acpi.stock.catalog.ICatalog;

public interface IProductController {
	void setCatalog(ICatalog catalog);
	void setProductDAO(IProductDAO productDAO);
}
