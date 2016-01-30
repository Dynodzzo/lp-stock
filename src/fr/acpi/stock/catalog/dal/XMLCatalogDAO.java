package fr.acpi.stock.catalog.dal;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import fr.acpi.stock.catalog.dal.ICatalogDAO;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import fr.acpi.stock.catalog.Catalog;
import fr.acpi.stock.catalog.ICatalog;

public class XMLCatalogDAO implements ICatalogDAO {
	private String uri = "db/xml/Catalogs.xml";
	private Document doc;
	
	public XMLCatalogDAO() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM Catalogues");
		}
	}

	@Override
	public boolean create(ICatalog catalog) {
		try {
			Element root = doc.getRootElement();
			Element elementCatalog = new Element("catalog");
			Element name = new Element("name");
			elementCatalog.addContent(name.setText(catalog.name()));			
			root.addContent(elementCatalog);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer catalogue");
			return false;
		}
	}

	@Override
	public boolean delete(ICatalog catalog) {
		try {
			Element root = doc.getRootElement();
			Element elementCatalog = searchCatalog(catalog.name());
			if (elementCatalog != null) {
				root.removeContent(elementCatalog);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer catalogue");
			return false;
		}
	}

	@Override
	public ICatalog get(String name) {
		return null;
	}

	@Override
	public List<ICatalog> getAll() {
		List<ICatalog> listCatalog = new ArrayList<>();
		try {
			Element root = doc.getRootElement();
			List<Element> listElementCatalog = root.getChildren("catalog");

			for (Element catalog : listElementCatalog) {
				int id = Integer.parseInt(catalog.getAttributeValue("id"));
				String name = catalog.getChildText("name");
				listCatalog.add(new Catalog(name));
			}
		} catch (Exception e) {
			System.out.println("erreur getAll tous les catalogues");
		}
		return listCatalog;
	}

	@Override
	public int products(ICatalog catalog) {
		return 0;
	}

	private boolean sauvegarde() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc, new PrintWriter(uri));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier catalogs XML");
			return false;
		}
	}
	
	private Element searchCatalog(String name) {
		Element root = doc.getRootElement();
		List<Element> listElementCatalog = root.getChildren("catalog");
		int i = 0;
		while (i < listElementCatalog.size() && !listElementCatalog.get(i).getChild("name").getText().equals(name))
			i++;
		if (i < listElementCatalog.size())
			return listElementCatalog.get(i);
		else
			return null;
	}

}
