package fr.acpi.stock.dal;

import fr.acpi.stock.dal.catalog.ICatalogDAO;
import fr.acpi.stock.dal.product.IProductDAO;
import fr.acpi.stock.dal.DAOType;

public abstract class DAOAbstractFactory {
	public static DAOAbstractFactory instance;
	
	protected DAOAbstractFactory() {}
	
	public static synchronized DAOAbstractFactory getInstance(DAOType DAOType) {
		if (instance == null) {
			switch (DAOType) {
			case Oracle:
				instance = new RelationnalDAOFactory();
				break;
			case XML:
				instance = new XMLDAOFactory();
				break;
			}
		}
		
		return instance;
	}
	
	public abstract ICatalogDAO Catalog();
	public abstract IProductDAO Product();
}
