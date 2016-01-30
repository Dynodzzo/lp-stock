package fr.acpi.stock.catalog.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.acpi.stock.DBData;
import fr.acpi.stock.catalog.Catalog;
import fr.acpi.stock.catalog.ICatalog;
import fr.acpi.stock.catalog.dal.ICatalogDAO;

public class OracleCatalogDAO implements ICatalogDAO {
	protected Connection _connexion;
	protected String _selectCatalogRequest = "SELECT name FROM Catalogs WHERE name = ?";
	protected String _selectCatalogsRequest = "SELECT name FROM Catalogs";
	protected String _createCatalogRequest = "{CALL addCatalog(?)}";
	protected String _deleteCatalogRequest = "DELETE FROM Catalogs WHERE name = ?";
	protected String _countProductsRequest = "SELECT COUNT(P.id) FROM Products P, Catalogs C WHERE P.catalogId = C.id AND C.name = ?";

	protected PreparedStatement _selectCatalogStatement;
	protected PreparedStatement _selectCatalogsStatement;
	protected CallableStatement _createCatalogStatement;
	protected PreparedStatement _deleteCatalogStatement;
	protected PreparedStatement _countProductsStatement;

	protected ResultSet _catalogSet;

	public OracleCatalogDAO(Connection connexion) {
		this._connexion = connexion;

		try {
			this._selectCatalogStatement = this._connexion.prepareStatement(this._selectCatalogRequest);
			this._selectCatalogsStatement = this._connexion.prepareStatement(this._selectCatalogsRequest);
			this._createCatalogStatement = this._connexion.prepareCall(this._createCatalogRequest);
			this._deleteCatalogStatement = this._connexion.prepareStatement(this._deleteCatalogRequest);
			this._countProductsStatement = this._connexion.prepareStatement(this._countProductsRequest);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

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

	@Override
	public int products(ICatalog catalog) {
		int productsNb = 0;

		try {
			this._countProductsStatement.setString(1, catalog.name());
			this._catalogSet = this._countProductsStatement.executeQuery();

			if (this._catalogSet.next()) {
				productsNb = this._catalogSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productsNb;
	}
}
