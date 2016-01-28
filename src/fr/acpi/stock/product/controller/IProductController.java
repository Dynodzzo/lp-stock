package fr.acpi.stock.product.controller;

import fr.acpi.stock.catalog.ICatalog;
import fr.acpi.stock.product.dal.IProductDAO;

public interface IProductController {
	void setCatalog(ICatalog catalog);
	void setProductDAO(IProductDAO productDAO);
}
