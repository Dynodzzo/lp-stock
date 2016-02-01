package fr.acpi.stock.product.dal;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.acpi.stock.product.IProduct;
import fr.acpi.stock.product.Product;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;


public class ProduitDAO_XML {
	private String uri = "/home/moiii/public_html/lp-stock/trunk/db/xml/Catalogs.xml"; // db/xml/Catalogs.xml";
	private Document doc;

	public ProduitDAO_XML() {
		SAXBuilder sdoc = new SAXBuilder();
		try {
			doc = sdoc.build(uri);
		} catch (Exception e) {
			System.out.println("erreur construction arbre JDOM produits");
		}
	}

	public boolean creer(IProduct p) {
		try {
			Element root = doc.getRootElement();
			Element prod = new Element("product");
			prod.setAttribute("name", p.name());
			Element prix = new Element("unitPriceET");
			prod.addContent(prix.setText(String.valueOf(p.unitPriceET())));
			Element qte = new Element("amount");
			prod.addContent(qte.setText(String.valueOf(p.amount())));
			root.addContent(prod);
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			return false;
		}
	}
	
	public boolean create(IProduct product, String catalogName) {
		try {
			Element root = doc.getRootElement();
			
			List<Element> listElementCatalog = root.getChildren("catalog");
			for (Element elemCatalog : listElementCatalog) {
				if(elemCatalog.getChild("name").getText().compareToIgnoreCase(catalogName) == 0) {
					Element elementProduct = new Element("product");
					elementProduct.setAttribute("name", product.name());
					Element prix = new Element("unitPriceET");
					elementProduct.addContent(prix.setText(String.valueOf(product.unitPriceET())));
					Element qte = new Element("amount");
					elementProduct.addContent(qte.setText(String.valueOf(product.amount())));
					elemCatalog.addContent(elementProduct);
				}
			}
			return sauvegarde();
		} catch (Exception e) {
			System.out.println("erreur creer produit");
			return false;
		}
	}

	public boolean maj(IProduct p) {
		try {
			Element prod = chercheProduit(p.name());
			if (prod != null) {
				prod.getChild("amount").setText(String.valueOf(p.amount()));
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur maj produit");
			return false;
		}
	}
	

	public boolean update(IProduct product, String catalogName) {
		boolean result = false;
		try {
			Element root = doc.getRootElement();
			
			List<Element> listElementCatalog = root.getChildren("catalog");
			for (Element elemCatalog : listElementCatalog) {
				
				if(elemCatalog.getChild("name").getText().compareToIgnoreCase(catalogName) == 0) {
					List<Element> listElementProduct = elemCatalog.getChildren("product");
					
					for (Element elemProduct : listElementProduct) {
						if(elemProduct.getAttributeValue("name").compareToIgnoreCase(product.name()) == 0) {
							elemProduct.getChild("amount").setText(String.valueOf(product.amount()));
							result = sauvegarde();
						}
					}
				} else
					result = false;
			}
			return result;
		} catch (Exception e) {
			System.out.println("erreur update produit :"+product.name() +" du catalog :"+catalogName);
			return false;
		}
	}

	public boolean supprimer(IProduct p) {
		try {
			Element root = doc.getRootElement();
			Element prod = chercheProduit(p.name());
			if (prod != null) {
				root.removeContent(prod);
				return sauvegarde();
			} else
				return false;
		} catch (Exception e) {
			System.out.println("erreur supprimer produit");
			return false;
		}
	}
	

	public boolean delete(IProduct product, String catalogName) {
		boolean result = false;
		try {
			Element root = doc.getRootElement();
			
			List<Element> listElementCatalog = root.getChildren("catalog");
			for (Element elemCatalog : listElementCatalog) {
				
				if(elemCatalog.getChild("name").getText().compareToIgnoreCase(catalogName) == 0) {
					List<Element> listElementProduct = elemCatalog.getChildren("product");
					
					for (Element elemProduct : listElementProduct) {
						if(elemProduct.getAttributeValue("name").compareToIgnoreCase(product.name()) == 0) {
							elemProduct.removeContent(elemProduct);
							result = sauvegarde();
						}
					}
				} else
					result = false;
			}
			return result;
		} catch (Exception e) {
			System.out.println("erreur delete produit :"+product.name() +" du catalog :"+catalogName);
			return false;
		}
	}

	public IProduct lire(String nom) {
		Element e = chercheProduit(nom);
		if (e != null)
			return new Product(e.getAttributeValue("name"), Double.parseDouble(e.getChildText("unitPriceET")), Integer.parseInt(e.getChildText("amount")));
		else
			return null;
	}

	public List<IProduct> lireTous() {

		List<IProduct> l = new ArrayList<>();
		try {
			Element root = doc.getRootElement();
			List<Element> lProd = root.getChildren("product");

			for (Element prod : lProd) {
				String nomP = prod.getAttributeValue("name");
				Double prix = Double.parseDouble(prod.getChild("unitPriceET").getText());
				int qte = Integer.parseInt(prod.getChild("amount").getText());
				l.add(new Product(nomP, prix, qte));
			}
		} catch (Exception e) {
			System.out.println("erreur lireTous tous les produits");
		}
		return l;
	}
	
	public List<IProduct> getAll(String catalogName) {
		List<IProduct> listProducts = new ArrayList<>();
		try {
			Element root = doc.getRootElement();
			
			List<Element> listElementCatalog = root.getChildren("catalog");
			for (Element elemCatalog : listElementCatalog) {
				
				if(elemCatalog.getChild("name").getText().compareToIgnoreCase(catalogName) == 0) {
					List<Element> listElementProduct = elemCatalog.getChildren("product");
					
					for (Element elemProduct : listElementProduct) {
						String name = elemProduct.getAttributeValue("name");						
						Double unitPriceET = Double.parseDouble(elemProduct.getChild("unitPriceET").getText());
						int amount = Integer.parseInt(elemProduct.getChild("amount").getText());
						listProducts.add(new Product(name, unitPriceET, amount));					
					}
				}
			}
		} catch (Exception e) {
			System.out.println("erreur getAll tous les produits");
		}
		
		return listProducts;
	}

	private boolean sauvegarde() {
		System.out.println("Sauvegarde");
		XMLOutputter out = new XMLOutputter();
		try {
			out.output(doc, new PrintWriter(uri));
			return true;
		} catch (Exception e) {
			System.out.println("erreur sauvegarde dans fichier produits XML");
			return false;
		}
	}

	private Element chercheProduit(String nom) {
		Element root = doc.getRootElement();
		List<Element> lProd = root.getChildren("product");
		int i = 0;
		while (i < lProd.size() && !lProd.get(i).getAttributeValue("name").equals(nom))
			i++;
		if (i < lProd.size())
			return lProd.get(i);
		else
			return null;
	}
}
