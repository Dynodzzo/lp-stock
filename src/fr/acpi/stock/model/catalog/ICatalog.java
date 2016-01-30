package fr.acpi.stock.model.catalog;

import fr.acpi.stock.model.product.IProduct;

import java.util.List;

public interface ICatalog {
	String name();
	int getIndex();
	
	int numberCatalogProducts();
	
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
		
}
