package fr.acpi.stock.catalog;

import fr.acpi.stock.product.IProduct;

import java.util.List;

public interface ICatalog {
	String name();
	boolean addProduct(IProduct product);
	boolean addProduct(String name, double unitPriceET, int amount);
	int addProducts(List<IProduct> products);
	boolean deleteProduct(String name);
	boolean buyStock(String productName, int amountBought);
	boolean sellStock(String productName, int amountSold);
	String[] productsNames();
	double totalAmountIT();
	IProduct product(String name);
	void clear();
	int getIndex();	
}
