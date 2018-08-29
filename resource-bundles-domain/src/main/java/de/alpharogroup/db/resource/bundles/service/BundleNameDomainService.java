package de.alpharogroup.db.resource.bundles.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.db.resource.bundles.domain.BaseName;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.domain.LanguageLocale;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import de.alpharogroup.db.resource.bundles.mapper.BundleNamesMapper;
import de.alpharogroup.db.resource.bundles.mapper.LanguageLocalesMapper;
import de.alpharogroup.db.resource.bundles.repositories.BundleNamesRepository;
import de.alpharogroup.db.resource.bundles.service.api.BundleNameService;
import de.alpharogroup.db.resource.bundles.service.api.BundleNamesService;
import de.alpharogroup.service.domain.AbstractDomainService;

/**
 * The service class {@link BundleNamesDomainService}
 */
@Transactional
@Service("bundleNameService")
public class BundleNameDomainService 
	extends 
		AbstractDomainService<java.lang.Integer, BundleName, BundleNames, BundleNamesRepository, BundleNamesMapper>	
	implements 
		BundleNameService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The {@link BundleNamesService} object */
	@Autowired
	private BundleNamesService bundleNamesService;
	
	/**
	 * Sets the specific {@link LanguageLocalesMapper}.
	 *
	 * @param mapper
	 *            the new {@link LanguageLocalesMapper}.
	 */
	@Autowired
	public void setBundleNamesMapper(final BundleNamesMapper mapper)
	{
		setMapper(mapper);
	}

	/**
	 * Sets the specific repository.
	 *
	 * @param repository the repository
	 */
	@Autowired
	public void setBundleNamesRepository(BundleNamesRepository repository) {
		setRepository(repository);
	}

	@Override
	public List<BundleName> find(BundleApplication owner)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BundleName> find(BundleApplication owner, BaseName baseName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BundleName find(BundleApplication owner, BaseName baseName,
		LanguageLocale languageLocales)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BundleName> find(BundleApplication owner, String baseName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BundleName find(BundleApplication owner, String baseName, Locale locale)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BundleName> find(BundleApplication owner, String baseName, String locale)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LanguageLocale getDefaultLocale(BundleApplication owner, String baseName)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LanguageLocale getDefaultLocale(BundleName bundleNames)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BundleName getOrCreateNewBundleName(BundleApplication owner, String baseName,
		Locale locale)
	{
		// TODO Auto-generated method stub
		return null;
	}

}