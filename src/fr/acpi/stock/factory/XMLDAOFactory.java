package fr.acpi.stock.factory;

import fr.acpi.stock.catalog.dal.ICatalogDAO;
import fr.acpi.stock.catalog.dal.XMLCatalogDAO;
import fr.acpi.stock.product.dal.IProductDAO;
import fr.acpi.stock.product.dal.XMLProductDAO;

public class XMLDAOFactory extends DAOFactory {
	@Override
	public IProductDAO productDAO() {
		return new XMLProductDAO();
	}

	@Override
	public ICatalogDAO catalogDAO() {
		return new XMLCatalogDAO();
	}
}
