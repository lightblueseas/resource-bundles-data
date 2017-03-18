package de.alpharogroup.db.resource.bundles.daos;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.db.resource.bundles.entities.BaseNames;

/**
 * The class {@link BaseNamesDao}.
 */
@Repository("baseNamesDao")
public class BaseNamesDao extends JpaEntityManagerDao<BaseNames, Integer> {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
}
