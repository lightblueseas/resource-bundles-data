package de.alpharogroup.db.resource.bundles.factories;

import java.io.Serializable;
import java.util.Locale;

import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.locale.LocaleUtils;

/**
 * A factory for creating Domain objects for the resource bundles.
 */
public class ResourceBundlesDomainObjectFactory implements Serializable {

	/** The Constant instance. */
	private static final ResourceBundlesDomainObjectFactory instance = new ResourceBundlesDomainObjectFactory();

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets the single instance of MessageSystemFactory.
	 * 
	 * @return single instance of MessageSystemFactory
	 */
	public static ResourceBundlesDomainObjectFactory getInstance() {
		return instance;
	}

	/**
	 * Instantiates a new MessageSystemFactory object.
	 */
	private ResourceBundlesDomainObjectFactory() {
		super();
	}

	/**
	 * Gets the resourcebundles.
	 *
	 * @param baseName the bundlename
	 * @param locale the locale
	 * @param propertieskey the propertieskey
	 * @param value the value
	 * @return the resourcebundles
	 */
	public Resourcebundles newResourcebundles(String baseName, String locale, String propertieskey,
			String value) {
		Resourcebundles resourcebundles = new Resourcebundles();
		resourcebundles.setBaseName(baseName);
		resourcebundles.setLocale(locale);
		resourcebundles.setKey(propertieskey);
		resourcebundles.setValue(value);
		return resourcebundles;
	}

	/**
	 * Gets the resourcebundles.
	 *
	 * @param baseName the bundlename
	 * @param locale the locale
	 * @param propertieskey the propertieskey
	 * @param value the value
	 * @return the resourcebundles
	 */
	public Resourcebundles newResourcebundles(String baseName, Locale locale, String propertieskey,
			String value) {
		Resourcebundles resourcebundles = new Resourcebundles();
		resourcebundles.setBaseName(baseName);
		resourcebundles.setLocale(LocaleUtils.getLocaleFilenameSuffix(locale));
		resourcebundles.setKey(propertieskey);
		resourcebundles.setValue(value);
		return resourcebundles;
	}

}
