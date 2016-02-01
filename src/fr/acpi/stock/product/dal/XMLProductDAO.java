package fr.acpi.stock.product.dal;

import fr.acpi.stock.product.IProduct;
import fr.acpi.stock.product.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

public class XMLProductDAO implements IProductDAO {
	private ProduitDAO_XML _xmlDAO;

	public XMLProductDAO() {
		this._xmlDAO = new ProduitDAO_XML();
	}

	@Override
	public IProduct get(String name, String catalogName) {
		return this._xmlDAO.lire(name);
	}

	
	public List<IProduct> getAll() {
		return this._xmlDAO.lireTous();
	}
	
	@Override
	public List<IProduct> getAll(String catalogName) {
		return this._xmlDAO.getAll(catalogName);
	}
	
	public boolean create(IProduct product) {
		return this._xmlDAO.creer(product);
	}

	@Override
	public boolean create(IProduct product, String catalogName) {
		return this._xmlDAO.create(product, catalogName);
	}

	@Override
	public boolean update(IProduct product, String catalogName) {
		return this._xmlDAO.maj(product);
	}

	@Override
	public boolean delete(IProduct product, String catalogName) {
		return this._xmlDAO.supprimer(product);
	}
}
