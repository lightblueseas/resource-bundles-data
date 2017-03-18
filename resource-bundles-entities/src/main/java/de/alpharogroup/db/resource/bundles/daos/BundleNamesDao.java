package de.alpharogroup.db.resource.bundles.daos;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;

/**
 * The class {@link BundleNamesDao}.
 */
@Repository("bundleNamesDao")
public class BundleNamesDao extends JpaEntityManagerDao<BundleNames, Integer> {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
}
