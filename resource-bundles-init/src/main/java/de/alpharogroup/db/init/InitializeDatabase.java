package de.alpharogroup.db.init;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import de.alpharogroup.db.resource.bundles.db.init.DatabaseInitialization;
import de.alpharogroup.lang.PropertiesUtils;

/**
 * The Class {@link InitializeDatabase} initialize the specific database.
 */
public class InitializeDatabase {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(final String[] args) throws ClassNotFoundException,
			SQLException, IOException {
		Properties dbProperties = PropertiesUtils.loadProperties("jdbc.properties");
		new DatabaseInitialization(dbProperties).initializeDatabase();
	}

}