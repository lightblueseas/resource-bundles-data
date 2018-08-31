package de.alpharogroup.db.resource.bundles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.db.resource.bundles.mapper.LanguagesMapper;
import de.alpharogroup.db.resource.bundles.repositories.LanguagesRepository;
import de.alpharogroup.db.resource.bundles.service.api.LanguageService;
import de.alpharogroup.db.resource.bundles.service.api.LanguagesService;
import de.alpharogroup.service.domain.AbstractDomainService;

/**
 * The service class {@link LanguagesDomainService}
 */
@Transactional
@Service("languageDomainService")
public class LanguageDomainService
	extends
		AbstractDomainService<java.lang.Integer, Language, Languages, LanguagesRepository, LanguagesMapper>
	implements
		LanguageService
{

	/** The {@link LanguagesService} object */
	@Autowired
	private LanguagesService languagesService;

	/**
	 * Sets the specific {@link LanguagesMapper}
	 *
	 * @param mapper
	 *            the new {@link LanguagesMapper}
	 */
	@Autowired
	public void setLanguagesMapper(final LanguagesMapper mapper)
	{
		setMapper(mapper);
	}

	/**
	 * Sets the specific repository
	 *
	 * @param repository
	 *            the repository
	 */
	@Autowired
	public void setLanguagesRepository(LanguagesRepository repository)
	{
		setRepository(repository);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Language find(final String name, final String iso639Dash1)
	{
		Languages entity = languagesService.find(name, iso639Dash1);
		Language domainObject = getMapper().toDomainObject(entity);
		return domainObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Language findByName(String name)
	{
		return find(name, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Language findByIso639Dash1(String iso639Dash1)
	{
		return find(null, iso639Dash1);
	}

}