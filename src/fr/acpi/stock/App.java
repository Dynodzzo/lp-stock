package fr.acpi.stock;

import fr.acpi.stock.product.IProductController;
import fr.acpi.stock.product.IProductDAO;
import fr.acpi.stock.product.OracleProductDAO;
import fr.acpi.stock.product.ProductController;
import fr.acpi.stock.view.MainWindow;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
		IProductDAO productDAO = new OracleProductDAO();
		AppData.Catalog.addProducts(productDAO.getAll());
		ProductController productCtrl = new ProductController(AppData.Catalog, productDAO);
		JFrame mainWindow = new MainWindow(productCtrl);
    }
}
