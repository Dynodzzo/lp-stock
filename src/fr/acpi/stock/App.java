package fr.acpi.stock;

import fr.acpi.stock.product.*;
import fr.acpi.stock.view.MainWindow;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
		IProductDAO productDAO = new OracleProductDAO();
		AppData.Catalog.addProducts(productDAO.getAll());
		ProductController productCtrl = new ProductController(AppData.Catalog, productDAO);
		SalesController salesCtrl = new SalesController(AppData.Catalog, productDAO);
		JFrame mainWindow = new MainWindow(productCtrl, salesCtrl);
    }
}
