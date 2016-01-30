package fr.acpi.stock.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.acpi.stock.model.catalog.Catalog;
import fr.acpi.stock.model.catalog.ICatalog;

public class ListCatalogs {
	protected List<ICatalog> _catalogs; // = new ArrayList<>();
	
	public ListCatalogs() {
		this._catalogs = new ArrayList<ICatalog>();
	}

	public boolean addCatalog(ICatalog catalog) {
		boolean added = false;

		if (this.valid(catalog) && !this.exists(catalog.name())) {
			this._catalogs.add(catalog);
			added = true;
		}

		return added;
	}

	public boolean addCatalog(String name) {
		boolean added = false;
		ICatalog product = new Catalog(name);

		if (this.valid(product) && !this.exists(product.name())) {
			this._catalogs.add(product);
			added = true;
		}

		return added;
	}

	public int addCatalogs(List<ICatalog> catalogs) {
		int addedCatalogs = 0;

		if (catalogs != null) {
			for (ICatalog catalog : catalogs) {
				if (this.addCatalog(catalog)) {
					addedCatalogs++;
				}
			}
		}

		return addedCatalogs;
	}

	public boolean deleteCatalog(String name) {
		boolean removed = false;

		if (this.exists(name)) {
			int catalogIndex = this.indexOf(name);

			if (catalogIndex != -1) {
				this._catalogs.remove(catalogIndex);
				removed = true;
			}
		}

		return removed;
	}

	public String[] catalogsNames() {
		String[] catalogNames = new String[this._catalogs.size()];

		for (int i = 0; i < this._catalogs.size(); i++) {
			catalogNames[i] = this._catalogs.get(i).name();
		}

		Arrays.sort(catalogNames);

		return catalogNames;
	}

	public ICatalog catalog(String name) {
		return this._catalogs.get(this.indexOf(name));
	}

	public void clear() {
		this._catalogs.clear();
	}
/*
	public String toString() {
		StringBuilder str = new StringBuilder();

		for (ICatalog catalog : this._catalogs) {
			str.append(catalog.name());
			str.append("\n");
		}
		str.append("\n");
		return str.toString();
	}
*/
	protected boolean exists(String name) {
		boolean exists = false;
		int catalogIndex = 0;

		while (!exists && catalogIndex < this._catalogs.size()) {
			if (this._catalogs.get(catalogIndex).name().equals(name)) {
				exists = true;
			}

			catalogIndex++;
		}

		return exists;
	}

	protected boolean valid(ICatalog catalog) {
		boolean valid = false;
		boolean notNull = catalog != null;

		if (notNull) {
			boolean nameNotEmpty = !catalog.name().equals("");
			valid =  nameNotEmpty;
		}

		return valid;
	}

	protected int indexOf(String name) {
		boolean catalogFound = false;
		int catalogIndex = 0;

		while (!catalogFound && catalogIndex < this._catalogs.size()) {
			if (this._catalogs.get(catalogIndex).name().equals(name)) {
				catalogFound = true;
			}
			else {
				catalogIndex++;
			}
		}

		if (catalogIndex >= this._catalogs.size()) {
			catalogIndex = -1;
		}

		return catalogIndex;
	}
	
	public int numberCatalogcatalogs() {
		return this._catalogs.size();
	}
}
