package fr.acpi.stock;

import fr.acpi.stock.product.IProductDAO;
import fr.acpi.stock.product.OracleDAO;
import fr.acpi.stock.view.MainWindow;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
		IProductDAO productDAO = new OracleDAO();
		AppData.Catalog.addProducts(productDAO.getAll());
		JFrame mainWindow = new MainWindow();
    }
}
