package fr.acpi.stock.dal.product;

import fr.acpi.stock.model.product.IProduct;

import java.util.List;

public interface IProductDAO {
	IProduct get(String name);
	List<IProduct> getAll();
	boolean create(IProduct product);
	boolean update(IProduct product);
	boolean delete(IProduct product);
}
