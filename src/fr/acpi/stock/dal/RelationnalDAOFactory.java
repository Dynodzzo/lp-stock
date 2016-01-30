package fr.acpi.stock.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.acpi.stock.DBData;
import fr.acpi.stock.dal.catalog.ICatalogDAO;
import fr.acpi.stock.dal.catalog.OracleCatalogDAO;
import fr.acpi.stock.dal.product.IProductDAO;
import fr.acpi.stock.dal.product.OracleProductDAO;

public class RelationnalDAOFactory extends DAOAbstractFactory {
	protected Connection _connexion;
	
	protected Connection connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			this._connexion = DriverManager.getConnection(DBData.url, DBData.login, DBData.password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return _connexion;	
	}
	
	@Override
	public ICatalogDAO Catalog() {
		return new OracleCatalogDAO(this.connect());
	}

	@Override
	public IProductDAO Product() {
		return new OracleProductDAO(this.connect());
	}
	
}
