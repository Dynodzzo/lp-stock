package com.acpi.stock.product;

public interface IProduct {
	boolean add(int amount);
	boolean remove(int amount);

	String name();
	int amount();

	double unitPriceET();
	double unitPriceIT();
	double stockPriceIT();
}
