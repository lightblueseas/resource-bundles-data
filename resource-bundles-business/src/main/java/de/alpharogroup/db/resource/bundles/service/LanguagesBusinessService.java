package de.alpharogroup.db.resource.bundles.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.LanguagesDao;
import de.alpharogroup.db.resource.bundles.entities.Languages;
import de.alpharogroup.db.resource.bundles.service.api.LanguagesService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;

/**
 * The class {@link LanguagesBusinessService}.
 */
@Transactional
@Service("languagesService")
public class LanguagesBusinessService extends AbstractBusinessService<Languages, Integer, LanguagesDao> implements LanguagesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
     * {@inheritDoc}
     */
	@Autowired
	public void setLanguagesDao(final LanguagesDao dao) {
		setDao(dao);
	}
	
	
	@SuppressWarnings("unchecked")
	public Languages find(final String name, final String iso639Dash1) {
		final String hqlString = HqlStringCreator.forLanguages(name, iso639Dash1);
		final Query query = getQuery(hqlString);
		if(name != null && !name.isEmpty()){
			query.setParameter("name", name);
		}
		if(iso639Dash1 != null && !iso639Dash1.isEmpty()){
			query.setParameter("iso639Dash1", iso639Dash1);
		}
		
		final List<Languages> languages = query.getResultList();
		return ListExtensions.getFirst(languages);		
	}	

}
