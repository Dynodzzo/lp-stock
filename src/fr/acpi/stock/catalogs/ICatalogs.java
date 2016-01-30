package fr.acpi.stock.catalogs;

import fr.acpi.stock.catalog.ICatalog;

import java.util.List;

public interface ICatalogs {
	boolean addCatalog(ICatalog catalog);
	boolean addCatalog(String name);
	int addCatalogs(List<ICatalog> catalogs);
	boolean deleteCatalog(String name);
	String[] catalogsNames();
	ICatalog catalog(String name);
	void clear();
	int size();
}
