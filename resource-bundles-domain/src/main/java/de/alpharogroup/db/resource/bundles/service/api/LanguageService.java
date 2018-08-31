package de.alpharogroup.db.resource.bundles.service.api;

import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.service.domain.DomainService;

/**
 * The domain service interface {@link LanguageService}
 */
public interface LanguageService extends DomainService<Integer, Language>
{

	/**
	 * Find the {@link Language} object from the name baseName and iso639Dash1.
	 *
	 * @param name
	 *            the name
	 * @param iso639Dash1
	 *           the specific code for the representation for the name of language
	 * 
	 * @return the found {@link Language} object or null if not.
	 */
	Language find(final String name, final String iso639Dash1);

	/**
	 * Find the {@link Language} object from the name of the language
	 *
	 * @param name
	 *            the name of the language
	 * @return the found {@link Language} object or null if not.
	 */
	Language findByName(final String name);

	/**
	 * Find the {@link Language} object from the specific code of the language
	 *
	 * @param name
	 * @param iso639Dash1
	 *            the specific code for the representation for the name of language
	 * @return the found {@link Language} object or null if not.
	 */
	Language findByIso639Dash1(final String iso639Dash1);
	
}