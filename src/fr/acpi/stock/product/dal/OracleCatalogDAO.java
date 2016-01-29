package fr.acpi.stock.product.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.acpi.stock.DBData;
import fr.acpi.stock.catalog.Catalog;
import fr.acpi.stock.catalog.ICatalog;

public class OracleCatalogDAO implements ICatalogDAO {	
	protected Connection _connexion;
//	protected String _selectCatalogRequest = "SELECT name FROM Catalogs WHERE name = ?";
	protected String _selectCatalogsRequest = "SELECT name FROM Catalogs";
	protected String _createCatalogRequest = "{call addCatalog(?)}";
	protected String _deleteCatalogRequest = "DELETE FROM Catalogs WHERE name = ?";

//	protected PreparedStatement _selectCatalogStatement;
	protected PreparedStatement _selectCatalogsStatement;
	protected CallableStatement _createCatalogStatement;
	protected PreparedStatement _deleteCatalogStatement;

	protected ResultSet _catalogSet;

	public OracleCatalogDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			this.connect();
//			this._selectCatalogStatement = this._connexion.prepareStatement(this._selectCatalogRequest);
			this._selectCatalogsStatement = this._connexion.prepareStatement(this._selectCatalogsRequest);
			this._createCatalogStatement = this._connexion.prepareCall(this._createCatalogRequest);
			this._deleteCatalogStatement = this._connexion.prepareStatement(this._deleteCatalogRequest);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
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
	public boolean create(ICatalog catalog) {
		boolean created = false;

		try {
			this._createCatalogStatement.setString(1, catalog.name());
			created = this._createCatalogStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return created;
	}

	@Override
	public boolean delete(ICatalog catalog) {
		boolean deleted = false;

		try {
			this._deleteCatalogStatement.setString(1, catalog.name());
			int rowsUpdated = this._deleteCatalogStatement.executeUpdate();

			if (rowsUpdated == 1) {
				deleted = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return deleted;
	}
/*
	@Override
	public ICatalog get(String name) {
		ICatalog catalog = null;

		try {
			this._selectCatalogStatement.setString(1, name);
			this._catalogSet = this._selectCatalogStatement.executeQuery();

			if (this._catalogSet.next()) {
				catalog = new Catalog(this._catalogSet.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return catalog;
	}
*/
	@Override
	public List<ICatalog> getAll() {
		List<ICatalog> catalogs = new ArrayList<>();

		try {
			this._catalogSet = this._selectCatalogsStatement.executeQuery();

			while (this._catalogSet.next()) {
				catalogs.add(new Catalog(this._catalogSet.getString(1)));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return catalogs;
	}	
}
