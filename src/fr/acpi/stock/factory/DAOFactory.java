package fr.acpi.stock.factory;

import fr.acpi.stock.catalog.dal.ICatalogDAO;
import fr.acpi.stock.product.dal.IProductDAO;

public abstract class DAOFactory {
	public static DAOFactory get(DAOFactoryType type) {
		DAOFactory factory = null;

		switch (type) {
			case Oracle:
				factory = new OracleDAOFactory();
				break;
			case XML:
				factory = new XMLDAOFactory();
				break;
		}

		return factory;
	}

	public abstract IProductDAO productDAO();
	public abstract ICatalogDAO catalogDAO();
}
