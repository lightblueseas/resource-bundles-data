package de.alpharogroup.db.init;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import de.alpharogroup.db.resource.bundles.db.init.DatabaseInitialization;
import de.alpharogroup.lang.PropertiesExtensions;

/**
 * The Class {@link InitializeDatabase} initialize the specific database.
 */
public class InitializeDatabase {

	/**
	 * The main method to initialize the specific database.
	 *
	 * @param args the arguments
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(final String[] args) throws ClassNotFoundException,
			SQLException, IOException {
		Properties dbProperties = PropertiesExtensions.loadProperties("jdbc.properties");
		new DatabaseInitialization(dbProperties).initializeDatabase();
	}

}