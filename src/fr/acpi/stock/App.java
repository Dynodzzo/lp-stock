package fr.acpi.stock;

import fr.acpi.stock.product.controller.ProductController;
import fr.acpi.stock.product.controller.SalesController;
import fr.acpi.stock.product.controller.StockController;
import fr.acpi.stock.product.dal.IProductDAO;
import fr.acpi.stock.product.dal.ProductDAOFactory;
import fr.acpi.stock.product.dal.ProductDAOType;
import fr.acpi.stock.view.MainWindow;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
		IProductDAO productDAO = ProductDAOFactory.get(ProductDAOType.XML);
		AppData.Catalog.addProducts(productDAO.getAll());
		ProductController productCtrl = new ProductController(AppData.Catalog, productDAO);
		SalesController salesCtrl = new SalesController(AppData.Catalog, productDAO);
		StockController stockCtrl = new StockController(AppData.Catalog, productDAO);
		JFrame mainWindow = new MainWindow(productCtrl, salesCtrl, stockCtrl);
    }
}
