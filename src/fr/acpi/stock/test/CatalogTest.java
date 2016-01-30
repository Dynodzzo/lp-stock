package fr.acpi.stock.test;

import fr.acpi.stock.catalog.Catalog;
import fr.acpi.stock.catalog.ICatalog;
import fr.acpi.stock.product.IProduct;
import fr.acpi.stock.product.Product;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CatalogTest {
	protected ICatalog catalog;

	@Before
	public void before() {
		this.catalog = new Catalog("Test");
	}

	@Test
	public void testCatalogCreation() {
		Assert.assertNotNull("Création d'un catalogue", this.catalog);
	}

	@Test
	public void testAddNullProduct_IProduct() {
		IProduct p = null;
		Assert.assertFalse("Ajout d'un produit null", this.catalog.addProduct(p));
	}

	@Test
	public void testAddProduct_IProduct_One() {
		IProduct p = new Product("Mars", 10, 4);
		Assert.assertTrue("Ajout d'un produit", this.catalog.addProduct(p));
	}

	@Test
	public void testAddProduct_IProduct_Two() {
		IProduct p1 = new Product("Mars", 10, 4);
		IProduct p2 = new Product("Treets", 10, 2);
		this.catalog.addProduct(p1);
		Assert.assertTrue("Ajout de deux produits", this.catalog.addProduct(p2));
	}

	@Test
	public void testAddSameProductAsLast() {
		IProduct p1 = new Product("Mars", 10, 4);
		IProduct p2 = new Product("Treets", 10, 2);
		this.catalog.addProduct(p1);
		this.catalog.addProduct(p2);
		Assert.assertFalse("Ajout d'un produit identique au dernier", this.catalog.addProduct(p2));
	}

	@Test
	public void testAddSameProductAsFirst() {
		IProduct p1 = new Product("Mars", 10, 4);
		IProduct p2 = new Product("Treets", 10, 2);
		this.catalog.addProduct(p1);
		this.catalog.addProduct(p2);
		Assert.assertFalse("Ajout d'un produit identique au premier", this.catalog.addProduct(p1));
	}

	@Test
	public void testAddProductSameNameAsLast() {
		IProduct p1 = new Product("Mars", 10, 4);
		IProduct p2 = new Product("Treets", 10, 2);
		IProduct p3 = new Product("Treets", 10, 4);
		this.catalog.addProduct(p1);
		this.catalog.addProduct(p2);
		Assert.assertFalse("Ajout d'un produit identique au premier", this.catalog.addProduct(p3));
	}

	@Test
	public void testAddProductSameNameAsFirst() {
		IProduct p1 = new Product("Mars", 10, 4);
		IProduct p2 = new Product("Treets", 10, 2);
		IProduct p3 = new Product("Mars", 10, 4);
		this.catalog.addProduct(p1);
		this.catalog.addProduct(p2);
		Assert.assertFalse("Ajout d'un produit identique au premier", this.catalog.addProduct(p3));
	}

	@Test
	public void testAddProductWithNegativeAmount() {
		IProduct p = new Product("Mars", 10, -1);
		Assert.assertFalse("Ajout d'un produit avec quantité négative", this.catalog.addProduct(p));
	}

	@Test
	public void testAddProductWithNullAmount() {
		IProduct p = new Product("Mars", 10, 0);
		Assert.assertFalse("Ajout d'un produit avec quantité null", this.catalog.addProduct(p));
	}

	@Test
	public void testAddProductWithNegativePrice() {
		IProduct p = new Product("Mars", -10, 4);
		Assert.assertFalse("Ajout d'un produit avec prix négatif", this.catalog.addProduct(p));
	}

	@Test
	public void testAddProductWithNullPrice() {
		IProduct p = new Product("Mars", 0, 1);
		Assert.assertFalse("Ajout d'un produit avec prix null", this.catalog.addProduct(p));
	}

	@Test
	public void testAddProduct_StringDoubleInt_One() {
		Assert.assertTrue("Ajout d'un produit", this.catalog.addProduct("Mars", 10, 4));
	}

	@Test
	public void testAddProduct_StringDoubleInt_Two() {
		IProduct p = new Product("Mars", 10, 4);
		this.catalog.addProduct(p);
		Assert.assertTrue("Ajout de deux produits", this.catalog.addProduct("Treets", 10, 2));
	}

	@Test
	public void testAddProductStringDoubleIntSameNameAsLast() {
		IProduct p1 = new Product("Mars", 10, 4);
		IProduct p2 = new Product("Treets", 10, 2);
		this.catalog.addProduct(p1);
		this.catalog.addProduct(p2);
		Assert.assertFalse("Ajout d'un produit identique au dernier", this.catalog.addProduct("Treets", 10, 2));
	}

	@Test
	public void testAddProductStringDoubleIntSameNameAsFirst() {
		IProduct p1 = new Product("Mars", 10, 4);
		IProduct p2 = new Product("Treets", 10, 2);
		this.catalog.addProduct(p1);
		this.catalog.addProduct(p2);
		Assert.assertFalse("Ajout d'un produit identique au premier", this.catalog.addProduct("Mars", 10, 4));
	}

	@Test
	public void testAddProductStringDoubleIntWithNegativeAmount() {
		Assert.assertFalse("Ajout d'un produit avec quantité négative", this.catalog.addProduct("Mars", 10, -1));
	}

	@Test
	public void testAddProductStringDoubleIntWithNullAmount() {
		Assert.assertFalse("Ajout d'un produit avec quantité null", this.catalog.addProduct("Mars", 10, 0));
	}

	@Test
	public void testAddProductStringDoubleIntWithNullPrice() {
		Assert.assertFalse("Ajout d'un produit avec prix nul", this.catalog.addProduct("Mars", 0, 1));
	}

	@Test
	public void testAddProductStringDoubleIntWithNegativePrice() {
		Assert.assertFalse("Ajout d'un produit avec prix négatif", this.catalog.addProduct("Mars", -10, 1));
	}

	@Test
	public void testAddProductsNull() {
		List<IProduct> l = null;
		Assert.assertEquals("Ajout d'une liste de produits nulle", 0, this.catalog.addProducts(l));
	}

	@Test
	public void testAddProductsEmpty() {
		List<IProduct> l = new ArrayList<>();
		Assert.assertEquals("Ajout d'une liste de produits vide", 0, this.catalog.addProducts(l));
	}

	@Test
	public void testAddProductsNoDoublesWithEmptyCatalog() {
		List<IProduct> l = new ArrayList<>();
		IProduct p1 = new Product("Mars", 10, 4);
		IProduct p2 = new Product("Treets", 10, 2);
		l.add(p1);
		l.add(p2);
		Assert.assertEquals("Ajout d'une liste de produits pleine", 2, this.catalog.addProducts(l));
	}

	@Test
	public void testAddProductsNoDoublesWithFilledCatalog() {
		IProduct p0 = new Product("Twix", 10, 6);
		this.catalog.addProduct(p0);
		List<IProduct> l = new ArrayList<>();
		IProduct p1 = new Product("Mars", 10, 4);
		IProduct p2 = new Product("Treets", 10, 2);
		l.add(p1);
		l.add(p2);
		Assert.assertEquals("Ajout d'une liste de produits pleine", 2, this.catalog.addProducts(l));
	}

	@Test
	public void testAddProductsWithDoubles() {
		IProduct p0 = new Product("Mars", 10, 4);
		this.catalog.addProduct(p0);
		List<IProduct> l = new ArrayList<>();
		IProduct p1 = new Product("Mars", 10, 4);
		IProduct p2 = new Product("Treets", 10, 2);
		IProduct p3 = new Product("M&M's", 8, 1);
		l.add(p1);
		l.add(p2);
		l.add(p3);
		Assert.assertEquals("Ajout d'une liste de produits pleine avec doublons", 2, this.catalog.addProducts(l));
	}

	@Test
	public void testAddProductsWithNegativeAmount() {
		IProduct p0 = new Product("Mars", 10, 4);
		IProduct p1 = new Product("Treets", 10, -3);
		IProduct p2 = new Product("Twix", 10, 2);
		List<IProduct> l = new ArrayList<>();
		l.add(p0);
		l.add(p1);
		l.add(p2);
		Assert.assertEquals("Ajout d'une liste avec stock négatif", 2, this.catalog.addProducts(l));
	}

	@Test
	public void testAddProductsWithNullAmount() {
		IProduct p0 = new Product("Mars", 10, 4);
		IProduct p1 = new Product("Treets", 10, 0);
		IProduct p2 = new Product("Twix", 10, 2);
		List<IProduct> l = new ArrayList<>();
		l.add(p0);
		l.add(p1);
		l.add(p2);
		Assert.assertEquals("Ajout d'une liste avec stock nul", 2, this.catalog.addProducts(l));
	}

	@Test
	public void testAddProductsWithNullPrice() {
		IProduct p0 = new Product("Mars", 10, 4);
		IProduct p1 = new Product("Treets", 0, 6);
		IProduct p2 = new Product("Twix", 10, 2);
		List<IProduct> l = new ArrayList<>();
		l.add(p0);
		l.add(p1);
		l.add(p2);
		Assert.assertEquals("Ajout d'une liste avec stock nul", 2, this.catalog.addProducts(l));
	}

	@Test
	public void testAddProductsWithNegativePrice() {
		IProduct p0 = new Product("Mars", 10, 4);
		IProduct p1 = new Product("Treets", -1, 6);
		IProduct p2 = new Product("Twix", 10, 2);
		List<IProduct> l = new ArrayList<>();
		l.add(p0);
		l.add(p1);
		l.add(p2);
		Assert.assertEquals("Ajout d'une liste avec stock nul", 2, this.catalog.addProducts(l));
	}

	@Test
	public void testRemoveExistingProduct() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertTrue("Suppression d'un produit existant", this.catalog.deleteProduct("Mars"));
	}

	@Test
	public void testRemoveNonExistingProduct() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertFalse("Suppression d'un produit existant", this.catalog.deleteProduct("Twix"));
	}

	@Test
	public void testRemoveNullName() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertFalse("Suppression d'un produit existant", this.catalog.deleteProduct(null));
	}

	@Test
	public void testBuyProductThatDoesntExists() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertFalse("Achat d'un produit qui n'existe pas", this.catalog.buyStock("Twix", 2));
	}

	@Test
	public void testBuyProductThatExists() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertTrue("Achat d'un produit qui existe", this.catalog.buyStock("Mars", 2));
	}

	@Test
	public void testBuyProductWithNegativeAmount() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertFalse("Achat d'un produit avec quantité négative", this.catalog.buyStock("Mars", -1));
	}

	@Test
	public void testBuyProductWithNullAmount() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertFalse("Achat d'un produit avec quantité nulle", this.catalog.buyStock("Mars", 0));
	}

	@Test
	public void testSellProductThatDoesntExists() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertFalse("Vente d'un produit qui n'existe pas", this.catalog.sellStock("Twix", 2));
	}

	@Test
	public void testSellProductThatExists() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertTrue("Vente d'un produit qui existe", this.catalog.sellStock("Mars", 2));
	}

	@Test
	public void testSellProductWithNegativeAmount() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertFalse("Vente d'un produit avec quantité négative", this.catalog.sellStock("Mars", -1));
	}

	@Test
	public void testSellProductWithNullAmount() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertFalse("Vente d'un produit avec quantité nulle", this.catalog.sellStock("Mars", 0));
	}

	@Test
	public void testSellProductWithNotEnoughStock() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.addProduct("Treets", 10, 6);
		Assert.assertFalse("Vente d'un produit avec quantitéinsuffisante", this.catalog.sellStock("Mars", 3));
	}

	@Test
	public void testProductNamesEmpty() {
		String[] n = new String[0];
		Assert.assertArrayEquals("Récupération des noms des produits avec liste vide", n, this.catalog.productsNames());
	}

	@Test
	public void testProductNamesOneProduct() {
		String[] n = {"Mars"};
		this.catalog.addProduct("Mars", 10, 1);
		Assert.assertArrayEquals("Récupération des noms des produits avec un produit", n, this.catalog.productsNames());
	}

	@Test
	public void testProductNamesTwoProducts() {
		String[] n = {"Mars", "Treets"};
		this.catalog.addProduct("Mars", 10, 1);
		this.catalog.addProduct("Treets", 10, 2);
		Assert.assertArrayEquals("Récupération des noms des produits avec deux produits", n, this.catalog.productsNames());
	}

	@Test
	public void testProductNamesAlphabeticOrder() {
		String[] n = {"Mars", "Raider", "Treets"};
		this.catalog.addProduct("Mars", 10, 1);
		this.catalog.addProduct("Raider", 10, 6);
		this.catalog.addProduct("Treets", 10, 2);
		Assert.assertArrayEquals("Récupération des noms des produits ordre alphabétique", n, this.catalog.productsNames());
	}

	@Test
	public void testProductNamesArbitraryOrder() {
		String[] n = {"Mars", "Raider", "Treets"};
		this.catalog.addProduct("Treets", 10, 2);
		this.catalog.addProduct("Mars", 10, 1);
		this.catalog.addProduct("Raider", 10, 6);
		Assert.assertArrayEquals("Récupération des noms des produits ordre arbitraire", n, this.catalog.productsNames());
	}

	@Test
	public void testTotalAmountICWithEmptyCatalog() {
		Assert.assertEquals("Récupération montant total TTC avec catalogue vide", 0, this.catalog.totalAmountIT(), 0);
	}

	@Test
	public void testTotalAmountICWithNullAmount() {
		this.catalog.addProduct("Mars", 10, 0);
		Assert.assertEquals("Récupération montant total TTC avec stock nul", 0, this.catalog.totalAmountIT(), 0);
	}

	@Test
	public void testTotalAmountICWithoutComa() {
		this.catalog.addProduct("Mars", 100, 4);
		this.catalog.addProduct("Treets", 20, 5);
		Assert.assertEquals("Récupération montant total TTC sans virgule", 600, this.catalog.totalAmountIT(), 0);
	}

	@Test
	public void testTotalAmountICWithtComa_NoRound_OneDigit() {
		this.catalog.addProduct("Mars", 10, 6);
		this.catalog.addProduct("Treets", 10, 4);
		this.catalog.addProduct("Raider", 1, 12);
		Assert.assertEquals("Récupération montant total TTC avec un chiffre après la virgule", 134.4, this.catalog.totalAmountIT(), 0);
	}

	@Test
	public void testTotalAmountICWithtComa_NoRound_TwoDigits() {
		this.catalog.addProduct("Mars", 10, 5);
		this.catalog.addProduct("Treets", 10, 4);
		this.catalog.addProduct("Raider", 1, 10);
		this.catalog.addProduct("Twix", 12.6, 1);
		Assert.assertEquals("Récupération montant total TTC avec deux chiffres après la virgule", 135.12, this.catalog.totalAmountIT(), 0);
	}

	@Test
	public void testTotalAmountICWithtComa_RoundFloor_ThreeDigits() {
		this.catalog.addProduct("Mars", 10, 5);
		this.catalog.addProduct("Treets", 10, 4);
		this.catalog.addProduct("Raider", 1, 10);
		this.catalog.addProduct("Twix", 12.66, 1);
		Assert.assertEquals("Récupération montant total TTC arrondi inférieur avec deux chiffres après la virgule", 135.19, this.catalog.totalAmountIT(), 0);
	}

	@Test
	public void testTotalAmountICWithtComa_RoundCeil_ThreeDigits() {
		this.catalog.addProduct("Mars", 10, 5);
		this.catalog.addProduct("Treets", 10, 4);
		this.catalog.addProduct("Raider", 1, 10);
		this.catalog.addProduct("Twix", 12.69, 1);
		Assert.assertEquals("Récupération montant total TTC arrondi supérieur (arrondir stock, pas les produits)", 135.23, this.catalog.totalAmountIT(), 0);
	}

	@Test
	public void testTotalAmountICWithtComa_RoundCeil_ThreeDigits_RoundStockNotUnit() {
		this.catalog.addProduct("Mars", 10, 5);
		this.catalog.addProduct("Treets", 10, 4);
		this.catalog.addProduct("Raider", 1, 10);
		this.catalog.addProduct("Twix", 12.67, 1);
		this.catalog.addProduct("Nuts", 12.67, 1);
		Assert.assertEquals("Récupération montant total TTC arrondi supérieur (arrondir stock, pas les produits)", 150.41, this.catalog.totalAmountIT(), 0);
	}

	@Test
	public void testToStringEmptyCatalog() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("Montant total TTC du stock : 0,00 €");
		Assert.assertEquals("Montant total avec catalogue vide", sb.toString(), this.catalog.toString());
	}

	@Test
	public void testToStringFilledCatalog_NoDigits() {
		StringBuilder sb = new StringBuilder();
		sb.append("Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5");
		sb.append("\n");
		sb.append("Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4");
		sb.append("\n");
		sb.append("Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10");
		sb.append("\n");
		sb.append("\n");
		sb.append("Montant total TTC du stock : 120,00 €");

		this.catalog.addProduct("Mars", 10, 5);
		this.catalog.addProduct("Treets", 10, 4);
		this.catalog.addProduct("Raider", 1, 10);
		Assert.assertEquals("Montant total avec catalogue sans virgule", sb.toString(), this.catalog.toString());
	}

	@Test
	public void testToStringFilledCatalog_OneDigit() {
		StringBuilder sb = new StringBuilder();
		sb.append("Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5");
		sb.append("\n");
		sb.append("Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4");
		sb.append("\n");
		sb.append("Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10");
		sb.append("\n");
		sb.append("Twix - prix HT : 10,45 € - prix TTC : 12,54 € - quantité en stock : 5");
		sb.append("\n");
		sb.append("\n");
		sb.append("Montant total TTC du stock : 182,70 €");

		this.catalog.addProduct("Mars", 10, 5);
		this.catalog.addProduct("Treets", 10, 4);
		this.catalog.addProduct("Raider", 1, 10);
		this.catalog.addProduct("Twix", 10.45, 5);
		Assert.assertEquals("Montant total avec catalogue un chiffre après la virgule", sb.toString(), this.catalog.toString());
	}

	@Test
	public void testToStringFilledCatalog_TwoDigits() {
		StringBuilder sb = new StringBuilder();
		sb.append("Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5");
		sb.append("\n");
		sb.append("Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4");
		sb.append("\n");
		sb.append("Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10");
		sb.append("\n");
		sb.append("Twix - prix HT : 10,40 € - prix TTC : 12,48 € - quantité en stock : 1");
		sb.append("\n");
		sb.append("\n");
		sb.append("Montant total TTC du stock : 132,48 €");

		this.catalog.addProduct("Mars", 10, 5);
		this.catalog.addProduct("Treets", 10, 4);
		this.catalog.addProduct("Raider", 1, 10);
		this.catalog.addProduct("Twix", 10.4, 1);
		Assert.assertEquals("Montant total avec catalogue deux chiffres après la virgule", sb.toString(), this.catalog.toString());
	}

	@Test
	public void testToStringFilledCatalog_ThreeDigits_RoundFloor() {
		StringBuilder sb = new StringBuilder();
		sb.append("Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5");
		sb.append("\n");
		sb.append("Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4");
		sb.append("\n");
		sb.append("Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10");
		sb.append("\n");
		sb.append("Twix - prix HT : 10,47 € - prix TTC : 12,56 € - quantité en stock : 1");
		sb.append("\n");
		sb.append("\n");
		sb.append("Montant total TTC du stock : 132,56 €");

		this.catalog.addProduct("Mars", 10, 5);
		this.catalog.addProduct("Treets", 10, 4);
		this.catalog.addProduct("Raider", 1, 10);
		this.catalog.addProduct("Twix", 10.47, 1);
		Assert.assertEquals("Montant total avec catalogue deux chiffres après la virgule, montant total TTC calculé avec prix unitaires non arrondis", sb.toString(), this.catalog.toString());
	}

	@Test
	public void testToStringFilledCatalog_ThreeDigits_RoundCeil() {
		StringBuilder sb = new StringBuilder();
		sb.append("Mars - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 5");
		sb.append("\n");
		sb.append("Treets - prix HT : 10,00 € - prix TTC : 12,00 € - quantité en stock : 4");
		sb.append("\n");
		sb.append("Raider - prix HT : 1,00 € - prix TTC : 1,20 € - quantité en stock : 10");
		sb.append("\n");
		sb.append("Twix - prix HT : 10,47 € - prix TTC : 12,56 € - quantité en stock : 2");
		sb.append("\n");
		sb.append("\n");
		sb.append("Montant total TTC du stock : 145,13 €");

		this.catalog.addProduct("Mars", 10, 5);
		this.catalog.addProduct("Treets", 10, 4);
		this.catalog.addProduct("Raider", 1, 10);
		this.catalog.addProduct("Twix", 10.47, 2);
		Assert.assertEquals("Montant total avec catalogue deux chiffres après la virgule, montant total TTC calculé avec prix unitaires non arrondis", sb.toString(), this.catalog.toString());
	}

	@Test
	public void testClear() {
		this.catalog.addProduct("Mars", 10, 2);
		this.catalog.clear();
		Assert.assertEquals("La liste doit être vide", 0, this.catalog.productsNames().length);
	}
}