package de.alpharogroup.db.resource.bundles.daos;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.db.resource.bundles.entities.LanguageLocales;

/**
 * The class {@link LanguageLocalesDao}.
 */
@Repository("languageLocalesDao")
public class LanguageLocalesDao extends JpaEntityManagerDao<LanguageLocales, Integer> {
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
}
