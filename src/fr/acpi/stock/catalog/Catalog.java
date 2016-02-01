package fr.acpi.stock.catalog;

import fr.acpi.stock.product.IProduct;
import fr.acpi.stock.product.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catalog implements ICatalog {
	protected List<IProduct> _products = new ArrayList<>();
	private String _name;

	/**
	 * Creates a new catalog with the given name.
	 * @param name The name of the catalog to create.
	 */
	public Catalog(String name) {
		this._products = new ArrayList<IProduct>();
		this._name = name;
	}

	/**
	 * Returns the name of the catalog.
	 * @return The name of the catalog.
	 */
	@Override
	public String name() {
		return this._name;
	}

	/**
	 * Adds a product to the catalog.
	 * @param product The product to add to the catalog.
	 * @return True if the product has been added, false otherwise.
	 */
	@Override
	public boolean addProduct(IProduct product) {
		boolean added = false;

		if (this.valid(product) && !this.exists(product.name())) {
			this._products.add(product);
			added = true;
		}

		return added;
	}

	/**
	 * Adds a product to the catalog.
	 * @param name The name of the product to add to the catalog.
	 * @param unitPriceET The price excluding taxes of the product to add to the catalog.
	 * @param amount The amount of pieces of the product to add to the catalog.
	 * @return True if the product has ben added, false otherwise.
	 */
	@Override
	public boolean addProduct(String name, double unitPriceET, int amount) {
		boolean added = false;
		IProduct product = new Product(name, unitPriceET, amount);

		if (this.valid(product) && !this.exists(product.name())) {
			this._products.add(product);
			added = true;
		}

		return added;
	}

	/**
	 * Adds multiple products to the catalog.
	 * @param products A list of products.
	 * @return The number of products added to the catalog.
	 */
	@Override
	public int addProducts(List<IProduct> products) {
		int addedProducts = 0;

		if (products != null) {
			for (IProduct product : products) {
				if (this.addProduct(product)) {
					addedProducts++;
				}
			}
		}

		return addedProducts;
	}

	/**
	 * Deletes a product from the catalog.
	 * @param name The name of the product to delete.
	 * @return True if the product was deleted, false otherwise.
	 */
	@Override
	public boolean deleteProduct(String name) {
		boolean removed = false;

		if (this.exists(name)) {
			int productIndex = this.indexOf(name);

			if (productIndex != -1) {
				this._products.remove(productIndex);
				removed = true;
			}
		}

		return removed;
	}

	/**
	 * Buys a product.
	 * @param productName The name of the product to buy.
	 * @param amountBought The amount of pieces of the product to buy.
	 * @return True if the product was bought, false otherwise.
	 */
	@Override
	public boolean buyStock(String productName, int amountBought) {
		boolean bought = false;

		if (this.exists(productName)) {
			bought = this._products.get(this.indexOf(productName)).add(amountBought);
		}

		return bought;
	}

	/**
	 * Sells a product.
	 * @param productName The name of the product to sell.
	 * @param amountSold The amount of pieces of the product to sell.
	 * @return True if the product was sold, false otherwise.
	 */
	@Override
	public boolean sellStock(String productName, int amountSold) {
		boolean sold = false;

		if (this.exists(productName)) {
			sold = this._products.get(this.indexOf(productName)).remove(amountSold);
		}

		return sold;
	}

	/**
	 * Returns an array containing the names of the products of the catalog in lexicographic order.
	 * @return An array containing the names of the products of the catalog in lexicographic order.
	 */
	@Override
	public String[] productsNames() {
		String[] productNames = new String[this._products.size()];

		for (int i = 0; i < this._products.size(); i++) {
			productNames[i] = this._products.get(i).name();
		}

		Arrays.sort(productNames);

		return productNames;
	}

	/**
	 * Returns the value of the stock including taxes.
	 * @return The value of the stock including taxes.
	 */
	@Override
	public double totalAmountIT() {
		double totalAmount = 0;

		for (IProduct product : this._products) {
			totalAmount += product.stockPriceIT();
		}

		return (double)Math.round(totalAmount * 100) / 100;
	}

	/**
	 * Returns the product with the specified name.
	 * @param name The name of the product.
	 * @return The product with the specified name.
	 */
	@Override
	public IProduct product(String name) {
		return this._products.get(this.indexOf(name));
	}

	/**
	 * Removes all the products from the catalog.
	 */
	@Override
	public void clear() {
		this._products.clear();
	}

	/**
	 * Returns a string representation of the catalog.
	 * @return A string representation of the catalog.
	 */
	@Override
	public String toString() {
		DecimalFormat twoDigitsFormat = new DecimalFormat("0.00 €");
		StringBuilder str = new StringBuilder();

		for (IProduct product : this._products) {
			str.append(product.name());
			str.append(" - prix HT : ");
			str.append(twoDigitsFormat.format(product.unitPriceET()));
			str.append(" - prix TTC : ");
			str.append(twoDigitsFormat.format(product.unitPriceIT()));
			str.append(" - quantité en stock : ");
			str.append(product.amount());
			str.append("\n");
		}

		str.append("\n");
		str.append("Montant total TTC du stock : ");
		str.append(twoDigitsFormat.format(this.totalAmountIT()));

		return str.toString();
	}

	/**
	 * Checks whether a product with a given name exists within the catalog.
	 * @param name The name of the product to check.
	 * @return True if the product exists within the catalog, false otherwise.
	 */
	protected boolean exists(String name) {
		boolean exists = false;
		int productIndex = 0;

		while (!exists && productIndex < this._products.size()) {
			if (this._products.get(productIndex).name().equals(name)) {
				exists = true;
			}

			productIndex++;
		}

		return exists;
	}

	/**
	 * Checks whether a product is valid.
	 * @param product The product to check.
	 * @return True if the product is valid, false otherwise.
	 */
	protected boolean valid(IProduct product) {
		boolean valid = false;
		boolean notNull = product != null;

		if (notNull) {
			boolean unitPricePositive = product.unitPriceET() > 0;
			boolean amountPositive = product.amount() > 0;
			boolean nameNotEmpty = !product.name().equals("");
			valid =  unitPricePositive && amountPositive && nameNotEmpty;
		}

		return valid;
	}

	/**
	 * Returns the index of a product in the catalog's list.
	 * @param name The name of the product to find.
	 * @return The index of the product to find or -1 if the product is not found.
	 */
	protected int indexOf(String name) {
		boolean productFound = false;
		int productIndex = 0;

		while (!productFound && productIndex < this._products.size()) {
			if (this._products.get(productIndex).name().equals(name)) {
				productFound = true;
			}
			else {
				productIndex++;
			}
		}

		if (productIndex >= this._products.size()) {
			productIndex = -1;
		}

		return productIndex;
	}
}
