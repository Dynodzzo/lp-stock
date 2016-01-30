package fr.acpi.stock.dal.catalog;

import java.util.List;

import fr.acpi.stock.model.catalog.ICatalog;

public interface ICatalogDAO {
	boolean create(ICatalog catalog);
	boolean delete(ICatalog catalog);
//	ICatalog get(String name);
	List<ICatalog> getAll();
}