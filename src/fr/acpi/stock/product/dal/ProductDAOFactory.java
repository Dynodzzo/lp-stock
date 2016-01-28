package fr.acpi.stock.product.dal;

public abstract class ProductDAOFactory {
	public static IProductDAO get(ProductDAOType productDAOType) {
		IProductDAO productDAO = null;

		switch (productDAOType) {
			case Oracle:
				productDAO = new OracleProductDAO();
				break;
			case XML:
				productDAO = new XMLProductDAO();
				break;
		}

		return productDAO;
	}
}
