package de.alpharogroup.db.resource.bundles.application;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import de.alpharogroup.db.resource.bundles.service.DatabaseListResourceBundle;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;

/**
 * The Class DatabaseResourceBundle.
 */
public class DatabaseResourceBundle extends ResourceBundle {
    
    /**
     * Instantiates a new database resource bundle.
     *
     * @param baseName the base name
     * @param locale the locale
     */
    public DatabaseResourceBundle(String baseName, Locale locale) {
        setParent(new DatabaseListResourceBundle(baseName, locale));
    }
    
    /**
     * Instantiates a new database resource bundle.
     *
     * @param baseName the base name
     * @param locale the locale
     * @param resourcebundlesService the resourcebundles service
     */
    public DatabaseResourceBundle(String baseName, Locale locale, ResourcebundlesService resourcebundlesService) {    	
        setParent(new DatabaseListResourceBundle(baseName, locale, resourcebundlesService));
    }

	/**
     * {@inheritDoc}
     */
	@Override
	protected Object handleGetObject(String key) {
        return parent.getObject(key);
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public Enumeration<String> getKeys() {
        return parent.getKeys();
    }

}