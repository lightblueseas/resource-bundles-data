package de.alpharogroup.db.resource.bundles.service.api;

import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.db.service.api.BusinessService;

/**
 * The interface {@link LanguagesService}.
 */
public interface LanguagesService  extends BusinessService<Languages, Integer> {

	/**
	 * Find the {@link Languages} object from the name baseName and iso639Dash1.
	 *
	 * @param name
	 *            the name
	 * @param iso639Dash1
	 *            the iso639Dash1
	 *            
	 * @return the found {@link Languages} object or null if not.
	 */
	Languages find(final String name, final String iso639Dash1);
	
}
