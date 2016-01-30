package fr.acpi.stock.product.dal;

import fr.acpi.stock.DBData;
import fr.acpi.stock.product.IProduct;
import fr.acpi.stock.product.Product;
import fr.acpi.stock.product.dal.IProductDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleProductDAO implements IProductDAO {
	protected Connection _connexion;
	protected String _selectProductRequest = "SELECT P.name, unitPriceET, amount FROM Products P, Catalogs C WHERE P.catalogId = C.id AND name = ? and C.name = ?";
	protected String _selectProductsRequest = "SELECT P.name, unitPriceET, amount FROM Products P, Catalogs C WHERE P.catalogId = C.id AND C.name = ?";
	protected String _createProductRequest = "{CALL addProduct(?, ?, ?, ?)}";
	protected String _updateProductRequest = "UPDATE Products SET name = ?, unitPriceET = ?, amount = ? WHERE name = ? and catalogId = (SELECT id FROM Catalogs WHERE name = ?)";
	protected String _deleteProductRequest = "DELETE FROM Products WHERE name = ? and catalogId = (SELECT id FROM Catalogs WHERE name = ?)";

	protected PreparedStatement _selectProductStatement;
	protected PreparedStatement _selectProductsStatement;
	protected CallableStatement _createProductStatement;
	protected PreparedStatement _updateProductStatement;
	protected PreparedStatement _deleteProductStatement;

	protected ResultSet _productSet;

	public OracleProductDAO(Connection connexion) {
		this._connexion = connexion;

		try {
			this._selectProductStatement = this._connexion.prepareStatement(this._selectProductRequest);
			this._selectProductsStatement = this._connexion.prepareStatement(this._selectProductsRequest);
			this._createProductStatement = this._connexion.prepareCall(this._createProductRequest);
			this._updateProductStatement = this._connexion.prepareStatement(this._updateProductRequest);
			this._deleteProductStatement = this._connexion.prepareStatement(this._deleteProductRequest);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IProduct get(String name, String catalogName) {
		IProduct product = null;

		try {
			this._selectProductStatement.setString(1, name);
			this._selectProductStatement.setString(2, catalogName);
			this._productSet = this._selectProductStatement.executeQuery();

			if (this._productSet.next()) {
				product = new Product(this._productSet.getString(1), this._productSet.getDouble(2), this._productSet.getInt(3));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}

	@Override
	public List<IProduct> getAll(String catalogName) {
		List<IProduct> products = new ArrayList<>();

		try {
			this._selectProductsStatement.setString(1, catalogName);
			this._productSet = this._selectProductsStatement.executeQuery();

			while (this._productSet.next()) {
				products.add(new Product(this._productSet.getString(1), this._productSet.getDouble(2), this._productSet.getInt(3)));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public boolean create(IProduct product, String catalogName) {
		boolean created = false;

		try {
			this._createProductStatement.setString(1, product.name());
			this._createProductStatement.setDouble(2, product.unitPriceET());
			this._createProductStatement.setInt(3, product.amount());
			this._createProductStatement.setString(4, catalogName);
			created = this._createProductStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return created;
	}

	@Override
	public boolean update(IProduct product, String catalogName) {
		boolean updated = false;

		try {
			this._updateProductStatement.setString(1, product.name());
			this._updateProductStatement.setDouble(2, product.unitPriceET());
			this._updateProductStatement.setInt(3, product.amount());
			this._updateProductStatement.setString(4, product.name());
			this._updateProductStatement.setString(5, catalogName);
			int rowsUpdated = this._updateProductStatement.executeUpdate();

			if (rowsUpdated == 1) {
				updated = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return updated;
	}

	@Override
	public boolean delete(IProduct product, String catalogName) {
		boolean deleted = false;

		try {
			this._deleteProductStatement.setString(1, product.name());
			this._deleteProductStatement.setString(2, catalogName);
			int rowsUpdated = this._deleteProductStatement.executeUpdate();

			if (rowsUpdated == 1) {
				deleted = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return deleted;
	}
}
