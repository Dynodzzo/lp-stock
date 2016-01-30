package fr.acpi.stock.model.product;

public class Product implements IProduct {
	protected String _name;
	protected double _unitPriceET;
	protected int _amount;
	public static double VATRate = .2;

	public Product(String name, double unitPriceET, int amount) {
		this._name = name;
		this._unitPriceET = unitPriceET;
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
		return this._unitPriceET;
	}

	@Override
	public double unitPriceIT() {
		return this._unitPriceET * (1 + Product.VATRate);
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
		productString.append(this._unitPriceET);
		productString.append("€ | prix TTC : ");
		productString.append(this.unitPriceIT());
		productString.append("€ | quantité en stock : ");
		productString.append(this._amount);

		return productString.toString();
	}
}
