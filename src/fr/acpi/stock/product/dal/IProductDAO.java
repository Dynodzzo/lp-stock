package fr.acpi.stock.product.dal;

import fr.acpi.stock.product.IProduct;

import java.util.List;

public interface IProductDAO {
	IProduct get(String name, String catalogName);
	List<IProduct> getAll(String catalogName);
	boolean create(IProduct product, String catalogName);
	boolean update(IProduct product, String catalogName);
	boolean delete(IProduct product, String catalogName);
}
