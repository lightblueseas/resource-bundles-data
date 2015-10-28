package de.alpharogroup.db.resource.bundles.application;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import lombok.Getter;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;

/**
 * The class {@link DatabaseControl} can be used to load ResourceBundle from the database.
 */
public class DatabaseControl extends Control
{

	/** the singleton instance of DatabaseControl. */
	private final static DatabaseControl INSTANCE = new DatabaseControl();

	/**
	 * Gets the single instance of DatabaseControl.
	 *
	 * @return single instance of DatabaseControl
	 */
	public static DatabaseControl getInstance()
	{
		return INSTANCE;
	}

	/** The resourcebundles service. */
	@Getter
	private ResourcebundlesService resourcebundlesService;

	/**
	 * Instantiates a new {@link DatabaseControl}.
	 */
	private DatabaseControl()
	{
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format,
		ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException,
		IOException
	{
		DatabaseResourceBundle databaseResourceBundle;
		if (resourcebundlesService != null)
		{
			databaseResourceBundle = new DatabaseResourceBundle(baseName, locale,
				resourcebundlesService);
		}
		else
		{
			databaseResourceBundle = new DatabaseResourceBundle(baseName, locale);
		}
		return databaseResourceBundle;
	}

	/**
	 * Sets the resourcebundles service.
	 *
	 * @param resourcebundlesService the resourcebundles service
	 * @return this {@link DatabaseControl} object.
	 */
	public synchronized DatabaseControl setResourcebundlesService(
		ResourcebundlesService resourcebundlesService)
	{
		this.resourcebundlesService = resourcebundlesService;
		return this;
	}

}