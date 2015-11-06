package de.alpharogroup.db.resource.bundles.db.init;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import de.alpharogroup.db.init.AbstractDatabaseInitialization;

/**
 * The class {@link DatabaseInitialization} gets the SQL script and executes them.
 */
public class DatabaseInitialization extends AbstractDatabaseInitialization {

	/**
	 * Instantiates a new {@link DatabaseInitialization}.
	 *
	 * @param databaseProperties the database properties
	 */
	public DatabaseInitialization(Properties databaseProperties) {
		super(databaseProperties);
	}

	/**
	 * {@inheritDoc}
	 */
	protected List<File> getScriptFiles() {
		return new ArrayList<>();
	}

}