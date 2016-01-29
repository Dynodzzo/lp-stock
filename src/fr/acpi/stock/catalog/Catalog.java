package fr.acpi.stock.catalog;

import fr.acpi.stock.product.IProduct;
import fr.acpi.stock.product.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catalog implements ICatalog {
	protected List<IProduct> _products; // = new ArrayList<>();
	private String _name;
	
	public Catalog() {
		this._products = new ArrayList<IProduct>();
	}
	
	public Catalog(String name) {
		this._products = new ArrayList<IProduct>();
		this._name = name;
	}
	
	@Override
	public String name() {
		return this._name;
	}

	@Override
	public boolean addProduct(IProduct product) {
		boolean added = false;

		if (this.valid(product) && !this.exists(product.name())) {
			this._products.add(product);
			added = true;
		}

		return added;
	}

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

	@Override
	public boolean buyStock(String productName, int amountBought) {
		boolean bought = false;

		if (this.exists(productName)) {
			bought = this._products.get(this.indexOf(productName)).add(amountBought);
		}

		return bought;
	}

	@Override
	public boolean sellStock(String productName, int amountSold) {
		boolean sold = false;

		if (this.exists(productName)) {
			sold = this._products.get(this.indexOf(productName)).remove(amountSold);
		}

		return sold;
	}

	@Override
	public String[] productsNames() {
		String[] productNames = new String[this._products.size()];

		for (int i = 0; i < this._products.size(); i++) {
			productNames[i] = this._products.get(i).name();
		}

		Arrays.sort(productNames);

		return productNames;
	}

	@Override
	public double totalAmountIT() {
		double totalAmount = 0;

		for (IProduct product : this._products) {
			totalAmount += product.stockPriceIT();
		}

		return (double)Math.round(totalAmount * 100) / 100;
	}

	@Override
	public IProduct product(String name) {
		return this._products.get(this.indexOf(name));
	}

	@Override
	public void clear() {
		this._products.clear();
	}

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
