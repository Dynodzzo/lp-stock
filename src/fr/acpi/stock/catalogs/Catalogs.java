package fr.acpi.stock.catalogs;

import fr.acpi.stock.catalog.Catalog;
import fr.acpi.stock.catalog.ICatalog;

import java.util.ArrayList;
import java.util.List;

public class Catalogs implements ICatalogs {
	protected List<ICatalog> _catalogs;

	public Catalogs() {
		this._catalogs = new ArrayList<>();
	}

	@Override
	public boolean addCatalog(ICatalog catalog) {
		boolean added = false;

		if (this.valid(catalog) && !this.exists(catalog.name())) {
			this._catalogs.add(catalog);
			added = true;
		}

		return added;
	}

	@Override
	public boolean addCatalog(String name) {
		boolean added = false;
		ICatalog catalog = new Catalog(name);

		if (this.valid(catalog) && !this.exists(catalog.name())) {
			this._catalogs.add(catalog);
			added = true;
		}

		return added;
	}

	@Override
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

	@Override
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

	@Override
	public String[] catalogsNames() {
		String names[] = new String[this._catalogs.size()];

		for (int i = 0; i < this._catalogs.size(); i++) {
			names[i] = this._catalogs.get(i).name();
		}

		return names;
	}

	@Override
	public ICatalog catalog(String name) {
		return this._catalogs.get(this.indexOf(name));
	}

	@Override
	public void clear() {
		this._catalogs.clear();
	}

	@Override
	public int size() {
		return this._catalogs.size();
	}

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
			valid = nameNotEmpty;
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
}
