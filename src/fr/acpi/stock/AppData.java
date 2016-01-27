package fr.acpi.stock;

import fr.acpi.stock.catalog.Catalog;
import fr.acpi.stock.catalog.ICatalog;

public abstract class AppData {
	public static ICatalog Catalog = new Catalog();
}
