package fr.acpi.stock;


import fr.acpi.stock.controller.catalog.CatalogController;
import fr.acpi.stock.controller.product.ProductController;
import fr.acpi.stock.controller.product.SalesController;
import fr.acpi.stock.controller.product.StockController;
import fr.acpi.stock.dal.DAOAbstractFactory;
import fr.acpi.stock.dal.DAOType;
import fr.acpi.stock.dal.catalog.ICatalogDAO;
import fr.acpi.stock.dal.product.IProductDAO;
import fr.acpi.stock.view.HomeWindow;
import fr.acpi.stock.view.MainWindow;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
		IProductDAO productDAO = DAOAbstractFactory.getInstance(DAOType.XML).Product();
		ICatalogDAO catalogDAO = DAOAbstractFactory.getInstance(DAOType.XML).Catalog();
		
		AppData.catalog.addProducts(productDAO.getAll());
		AppData.catalogs.addCatalogs(catalogDAO.getAll());
		
		ProductController productCtrl = new ProductController(AppData.catalog, productDAO);
		SalesController salesCtrl = new SalesController(AppData.catalog, productDAO);
		StockController stockCtrl = new StockController(AppData.catalog, productDAO);
		CatalogController catalogCtrl = new CatalogController(AppData.catalogs, catalogDAO);
		
		JFrame homeWindow = new HomeWindow(catalogCtrl);
		JFrame mainWindow = new MainWindow(productCtrl, salesCtrl, stockCtrl);
    }
}
