package fr.acpi.stock.factory;

import fr.acpi.stock.DBData;
import fr.acpi.stock.catalog.dal.ICatalogDAO;
import fr.acpi.stock.catalog.dal.OracleCatalogDAO;
import fr.acpi.stock.product.dal.IProductDAO;
import fr.acpi.stock.product.dal.OracleProductDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDAOFactory extends DAOFactory {
	protected Connection _connexion;

	public OracleDAOFactory() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		this.connect();
	}

	protected void connect() {
		try {
			this._connexion = DriverManager.getConnection(DBData.url, DBData.login, DBData.password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void disconnect() {
		try {
			this._connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IProductDAO productDAO() {
		return new OracleProductDAO(this._connexion);
	}

	@Override
	public ICatalogDAO catalogDAO() {
		return new OracleCatalogDAO(this._connexion);
	}
}
