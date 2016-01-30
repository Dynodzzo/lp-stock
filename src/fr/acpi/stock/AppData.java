package fr.acpi.stock;

import fr.acpi.stock.catalogs.Catalogs;
import fr.acpi.stock.catalogs.ICatalogs;

public abstract class AppData {
	public static ICatalogs Catalogs = new Catalogs();
}
