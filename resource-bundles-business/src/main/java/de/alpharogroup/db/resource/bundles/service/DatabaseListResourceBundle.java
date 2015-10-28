package de.alpharogroup.db.resource.bundles.service;

import java.util.List;
import java.util.ListResourceBundle;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;

/**
 * The Class {@link DatabaseListResourceBundle}.
 */
public class DatabaseListResourceBundle extends ListResourceBundle { 

	public DatabaseListResourceBundle() {
	}

	/** The base name. */
	private String baseName;

    /** The locale. */
    private Locale locale;
	
	/** The resourcebundles service. */
	@Autowired
	private ResourcebundlesService resourcebundlesService; 
    
    /**
     * Instantiates a new database resources.
     *
     * @param baseName the base name
     * @param locale the locale
     */
    public DatabaseListResourceBundle(String baseName, Locale locale) {
        this.locale = locale;        
        this.baseName = baseName;
    }
    
    /**
     * Instantiates a new database resources.
     *
     * @param baseName the base name
     * @param locale the locale
     * @param resourcebundlesService the resourcebundles service
     */
    public DatabaseListResourceBundle(String baseName, Locale locale, ResourcebundlesService resourcebundlesService) {
    	setResourcebundlesService(resourcebundlesService);
        this.locale = locale;        
        this.baseName = baseName;
    }

	/**
	 * Gets the base name.
	 *
	 * @return the base name
	 */
	public String getBaseName() {
		return baseName;
	}

	/**
     * {@inheritDoc}
     */
	@Override
    protected Object[][] getContents() {
    	List<Resourcebundles> resourcebundles = resourcebundlesService.findResourceBundles(baseName, locale);
    	Object[][] all = new Object[resourcebundles.size()][2];
    	int i = 0;
    	for (Resourcebundles resourcebundle : resourcebundles) {
    		all[i] = new Object[]{resourcebundle.getKey(), resourcebundle.getValue()};
            i++;
		}
        return all;
    }

	/**
     * {@inheritDoc}
     */
	public Locale getLocale() {
		return locale;
	}

    /**
     * Gets the resourcebundles service.
     *
     * @return the resourcebundles service
     */
    public ResourcebundlesService getResourcebundlesService() {
		return resourcebundlesService;
	}

    /**
     * Sets the resourcebundles service.
     *
     * @param resourcebundlesService the new resourcebundles service
     */
    public void setResourcebundlesService(
			ResourcebundlesService resourcebundlesService) {
		this.resourcebundlesService = resourcebundlesService;
	}
}