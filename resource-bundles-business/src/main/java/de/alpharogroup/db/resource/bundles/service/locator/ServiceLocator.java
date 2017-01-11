package de.alpharogroup.db.resource.bundles.service.locator;

import de.alpharogroup.db.resource.bundles.service.api.BaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.BundleApplicationsService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.db.resource.bundles.service.api.DefaultLocaleBaseNamesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.api.LanguagesService;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.api.ResourcebundlesService;

/**
 * The interface {@link ServiceLocator}.
 */
public interface ServiceLocator {
	
	
	BaseNamesService getBaseNamesService();
	
	void setBaseNamesService(BaseNamesService baseNamesService);
	
	BundleApplicationsService getBundleApplicationsService();
	
	void setBundleApplicationsService(BundleApplicationsService bundleApplicationsService);
	
	BundleNamesService getBundleNamesService();
	
	void setBundleNamesService(BundleNamesService bundleNamesService);
	
	DefaultLocaleBaseNamesService getDefaultLocaleBaseNamesService();
	
	void setDefaultLocaleBaseNamesService(DefaultLocaleBaseNamesService defaultLocaleBaseNamesService);
	
	LanguageLocalesService getLanguageLocalesService();
	
	void setLanguageLocalesService(LanguageLocalesService languageLocalesService);
	
	LanguagesService getLanguagesService();
	
	void setLanguagesService(LanguagesService languagesService);
	
	PropertiesKeysService getPropertiesKeysService();
	
	void setPropertiesKeysService(PropertiesKeysService propertiesKeysService);
	
	/**
	 * Gets the resourcebundles service.
	 *
	 * @return the resourcebundles service
	 */
	ResourcebundlesService getResourcebundlesService();
	
	/**
	 * Sets the resourcebundles service.
	 *
	 * @param resourcebundlesService the resourcebundles service
	 */
	void setResourcebundlesService(ResourcebundlesService resourcebundlesService);

}
