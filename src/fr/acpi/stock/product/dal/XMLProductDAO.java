package fr.acpi.stock.product.dal;

import fr.acpi.stock.product.IProduct;
import fr.acpi.stock.product.dal.IProductDAO;
import fr.acpi.stock.product.dal.ProduitDAO_XML;

import java.util.List;

public class XMLProductDAO implements IProductDAO {
	private ProduitDAO_XML _xmlDAO;

	public XMLProductDAO() {
		this._xmlDAO = new ProduitDAO_XML();
	}

	@Override
	public IProduct get(String name) {
		return this._xmlDAO.lire(name);
	}

	@Override
	public List<IProduct> getAll() {
		return this._xmlDAO.lireTous();
	}

	@Override
	public boolean create(IProduct product) {
		return this._xmlDAO.creer(product);
	}

	@Override
	public boolean update(IProduct product) {
		return this._xmlDAO.maj(product);
	}

	@Override
	public boolean delete(IProduct product) {
		return this._xmlDAO.supprimer(product);
	}
}
