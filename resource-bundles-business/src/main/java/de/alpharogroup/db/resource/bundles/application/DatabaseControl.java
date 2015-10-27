package de.alpharogroup.db.resource.bundles.application;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;

/**
 * The class DatabaseControl can be used to load ResourceBundle from the database.
 */
public class DatabaseControl extends Control {
	public DatabaseControl() {
		super();
	}
	public DatabaseControl(ResourcebundlesService resourcebundlesService) {
		super();
		this.resourcebundlesService = resourcebundlesService;
	}

	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger(DatabaseControl.class
			.getName());
	private ResourcebundlesService resourcebundlesService;

	/**
     * {@inheritDoc}
     */
	@Override
	public ResourceBundle newBundle(String baseName, Locale locale,
			String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {
		LOGGER.log(Level.INFO, "reload {0} ", reload);
		DatabaseResourceBundle databaseResourceBundle;
		if(resourcebundlesService!=null) {
			databaseResourceBundle = new DatabaseResourceBundle(baseName, locale, resourcebundlesService);
		} else {
			databaseResourceBundle = new DatabaseResourceBundle(baseName, locale);
		}
		return databaseResourceBundle;
	}

}