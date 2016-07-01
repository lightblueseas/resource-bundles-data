package de.alpharogroup.db.resource.bundles.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.resource.bundles.daos.PropertiesKeysDao;
import de.alpharogroup.db.resource.bundles.entities.PropertiesKeys;
import de.alpharogroup.db.resource.bundles.service.api.PropertiesKeysService;
import de.alpharogroup.db.resource.bundles.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;

/**
 * The class {@link PropertiesKeysBusinessService}.
 */
@Transactional
@Service("propertiesKeysService")
public class PropertiesKeysBusinessService extends AbstractBusinessService<PropertiesKeys, Integer, PropertiesKeysDao> implements PropertiesKeysService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	@Override
	public PropertiesKeys find(String propertiesKey) {
		final String hqlString = HqlStringCreator.forPropertiesKeys(propertiesKey);
		final Query query = getQuery(hqlString);
		if(propertiesKey != null && !propertiesKey.isEmpty()){
			query.setParameter("propertiesKey", propertiesKey);
		}		
		final List<PropertiesKeys> propertiesKeys = query.getResultList();
		return ListExtensions.getFirst(propertiesKeys);
	}

}
