package de.alpharogroup.db.resource.bundles.service;

import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.LanguageLocalesDao;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;
import de.alpharogroup.db.resource.bundles.service.api.LanguageLocalesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;
import de.alpharogroup.resourcebundle.locale.LocaleExtensions;

/**
 * The class {@link LanguageLocalesBusinessService}.
 */
@Transactional
@Service("languageLocalesService")
public class LanguageLocalesBusinessService extends AbstractBusinessService<LanguageLocales, Integer, LanguageLocalesDao> implements LanguageLocalesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
     * {@inheritDoc}
     */
	@Autowired
	public void setLanguageLocalesDao(final LanguageLocalesDao dao) {
		setDao(dao);
	}
	
	public LanguageLocales find(Locale locale) {
		return find(LocaleExtensions.getLocaleFilenameSuffix(locale));
	}
	/**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	@Override
	public LanguageLocales find(String locale) {
		final String hqlString = HqlStringCreator.forLanguageLocales(locale);
		final Query query = getQuery(hqlString);
		if(locale != null && !locale.isEmpty()){
			query.setParameter("locale", locale);
		}		
		final List<LanguageLocales> languageLocales = query.getResultList();
		return ListExtensions.getFirst(languageLocales);
	}

}
