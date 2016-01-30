package fr.acpi.stock.catalog.dal;

import java.util.List;

import fr.acpi.stock.catalog.ICatalog;

public interface ICatalogDAO {
	boolean create(ICatalog catalog);
	boolean delete(ICatalog catalog);
	ICatalog get(String name);
	List<ICatalog> getAll();
	int products(ICatalog catalog);
}