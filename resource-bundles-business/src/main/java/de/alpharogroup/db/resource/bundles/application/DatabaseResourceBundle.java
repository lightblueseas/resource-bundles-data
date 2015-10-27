package de.alpharogroup.db.resource.bundles.application;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;

/**
 * The Class DatabaseResourceBundle.
 */
public class DatabaseResourceBundle extends ResourceBundle {

    /** The Constant LOGGER. */
    private final static Logger LOGGER = Logger.getLogger(DatabaseResourceBundle.class.getName());
    
    /**
     * Instantiates a new database resource bundle.
     *
     * @param baseName the base name
     * @param locale the locale
     */
    public DatabaseResourceBundle(String baseName, Locale locale) {
        LOGGER.log(Level.FINE, "DatabaseResourceBundle(String baseName, Locale locale)");
        setParent(ResourceBundle.getBundle(baseName, locale, new DatabaseControl()));
    }
    
    /**
     * Instantiates a new database resource bundle.
     *
     * @param baseName the base name
     * @param locale the locale
     * @param resourcebundlesService the resourcebundles service
     */
    public DatabaseResourceBundle(String baseName, Locale locale, ResourcebundlesService resourcebundlesService) {
        LOGGER.log(Level.FINE, "DatabaseResourceBundle(String baseName, Locale locale)");        
        setParent(ResourceBundle.getBundle(baseName, locale, new DatabaseControl(resourcebundlesService)));
    }

	/**
     * {@inheritDoc}
     */
	@Override
	protected Object handleGetObject(String key) {
        LOGGER.log(Level.FINE, "handleGetObject() Locale {0} Key: {1} ", new Object[]{parent.getLocale().toString(), key});
        return parent.getObject(key);
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public Enumeration<String> getKeys() {
        LOGGER.log(Level.FINE, "getKeys() Parent Locale {0} ", parent.getLocale());
        return parent.getKeys();
    }

}