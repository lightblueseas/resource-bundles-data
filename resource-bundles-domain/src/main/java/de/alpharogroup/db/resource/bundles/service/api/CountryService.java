package de.alpharogroup.db.resource.bundles.service.api;

import de.alpharogroup.db.resource.bundles.domain.Country;
import de.alpharogroup.db.resource.bundles.entities.Countries;
import de.alpharogroup.service.domain.DomainService;

/**
 * The interface {@link CountryService}
 */
public interface CountryService extends DomainService<Integer, Country>
{

	/**
	 * Find the {@link Countries} object from the given ISO 3166-1 alpha-2 name.
	 *
	 * @param iso3166A2name
	 *            the iso 3166 A 2 name
	 * @return the found {@link Countries} object or null if not.
	 */
	Country find(final String iso3166A2name);

	/**
	 * Find the {@link Countries} object from the given ISO 3166-1 alpha-2 name.
	 *
	 * @param name
	 *            the name
	 * @param iso3166A2name
	 *            the iso 3166 A 2 name
	 * @return the found {@link Countries} object or null if not.
	 */
	Country find(final String name, final String iso3166A2name);
}
