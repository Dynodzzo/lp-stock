package fr.acpi.stock;

import fr.acpi.stock.model.ListCatalogs;
import fr.acpi.stock.model.catalog.Catalog;
import fr.acpi.stock.model.catalog.ICatalog;

public abstract class AppData {
	public static ICatalog catalog = new Catalog();
	public static ListCatalogs catalogs = new ListCatalogs();
}
