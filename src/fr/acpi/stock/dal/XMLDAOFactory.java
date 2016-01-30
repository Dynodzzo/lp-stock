package fr.acpi.stock.dal;

import fr.acpi.stock.dal.catalog.ICatalogDAO;
import fr.acpi.stock.dal.catalog.XMLCatalogDAO;
import fr.acpi.stock.dal.product.IProductDAO;
import fr.acpi.stock.dal.product.XMLProductDAO;

public class XMLDAOFactory extends DAOAbstractFactory {

	@Override
	public ICatalogDAO Catalog() {
		return new XMLCatalogDAO();
	}

	@Override
	public IProductDAO Product() {
		return new XMLProductDAO();
	}
}
