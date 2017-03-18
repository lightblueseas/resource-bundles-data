package de.alpharogroup.db.resource.bundles.daos;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.db.resource.bundles.entities.DefaultLocaleBaseNames;

/**
 * The class {@link DefaultLocaleBaseNamesDao}.
 */
@Repository("defaultLocaleBaseNamesDao")
public class DefaultLocaleBaseNamesDao extends JpaEntityManagerDao<DefaultLocaleBaseNames, Integer> {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
}
