package fr.acpi.stock.product;

import fr.acpi.stock.DBData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleProductDAO implements IProductDAO {
	protected Connection _connexion;
	protected String _selectProductRequest = "SELECT name, unitPriceHT, stockAmount FROM Products WHERE name = ?";
	protected String _selectProductsRequest = "SELECT name, unitPriceHT, stockAmount FROM Products";
	protected String _createProductRequest = "{call addProduct(?, ?, ?)}";
	protected String _deleteProductRequest = "DELETE FROM Products WHERE name = ?";

	protected PreparedStatement _selectProductStatement;
	protected PreparedStatement _selectProductsStatement;
	protected CallableStatement _createProductStatement;
	protected PreparedStatement _deleteProductStatement;

	protected ResultSet _productSet;

	public OracleProductDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try {
			this.connect();
			this._selectProductStatement = this._connexion.prepareStatement(this._selectProductRequest);
			this._selectProductsStatement = this._connexion.prepareStatement(this._selectProductsRequest);
			this._createProductStatement = this._connexion.prepareCall(this._createProductRequest);
			this._deleteProductStatement = this._connexion.prepareStatement(this._deleteProductRequest);
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
	public IProduct get(String name) {
		IProduct product = null;

		try {
			this._selectProductStatement.setString(1, name);
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
	public List<IProduct> getAll() {
		List<IProduct> products = new ArrayList<>();

		try {
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
	public boolean create(IProduct product) {
		boolean created = false;

		try {
			this._createProductStatement.setString(1, product.name());
			this._createProductStatement.setDouble(2, product.unitPriceET());
			this._createProductStatement.setInt(3, product.amount());
			created = this._createProductStatement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return created;
	}

	@Override
	public boolean update(IProduct product) {
		return false;
	}

	@Override
	public boolean delete(String name) {
		boolean deleted = false;

		try {
			this._deleteProductStatement.setString(1, name);
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
