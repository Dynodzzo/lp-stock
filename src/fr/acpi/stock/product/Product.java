package fr.acpi.stock.product;

public class Product implements IProduct {
	protected String _name;
	protected double _unitPriceEC;
	protected int _amount;
	public static double VATRate = .2;

	public Product(String name, double unitPriceEC, int amount) {
		this._name = name;
		this._unitPriceEC = unitPriceEC;
		this._amount = amount;
	}

	@Override
	public boolean add(int amount) {
		boolean added = false;

		if (amount > 0) {
			this._amount += amount;
			added = true;
		}

		return added;
	}

	@Override
	public boolean remove(int amount) {
		boolean removed = false;

		if (amount > 0 && amount <= this._amount) {
			this._amount -= amount;
			removed = true;
		}

		return removed;
	}

	@Override
	public String name() {
		return this._name;
	}

	@Override
	public int amount() {
		return this._amount;
	}

	@Override
	public double unitPriceET() {
		return this._unitPriceEC;
	}

	@Override
	public double unitPriceIT() {
		return this._unitPriceEC * (1 + Product.VATRate);
	}

	@Override
	public double stockPriceIT() {
		return this._amount * this.unitPriceIT();
	}

	@Override
	public String toString() {
		StringBuilder productString = new StringBuilder();

		productString.append(this._name);
		productString.append(" | prix HT : ");
		productString.append(this._unitPriceEC);
		productString.append("€ | prix TTC : ");
		productString.append(this.unitPriceIT());
		productString.append("€ | quantité en stock : ");
		productString.append(this._amount);

		return productString.toString();
	}
}
