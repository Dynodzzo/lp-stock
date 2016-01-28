package fr.acpi.stock;

public abstract class DBData {
	public static String localNetworkAddress = "";
	public static String globalNetworkAddress = "";
	public static String port = "";
	public static String dbName = "";
	public static String url = "jdbc:oracle:thin:@" + globalNetworkAddress + ":" + port + ":" + dbName;
	public static String login = "";
	public static String password = "";
}