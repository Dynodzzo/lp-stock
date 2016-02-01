package fr.acpi.stock;

import fr.acpi.stock.catalog.controller.CatalogController;
import fr.acpi.stock.catalog.dal.ICatalogDAO;
import fr.acpi.stock.factory.DAOFactory;
import fr.acpi.stock.factory.DAOFactoryType;
import fr.acpi.stock.product.dal.IProductDAO;
import fr.acpi.stock.view.MainWindow;

import javax.swing.*;

public class App {

    public static void main(String[] args) {
		DAOFactory daoFactory = DAOFactory.get(DAOFactoryType.XML);
		ICatalogDAO catalogDAO = daoFactory.catalogDAO();
		IProductDAO productDAO = daoFactory.productDAO();
		CatalogController catalogCtrl = new CatalogController(AppData.Catalogs, catalogDAO, productDAO);
		JFrame mainWindow = new MainWindow(catalogCtrl);
    }
}
