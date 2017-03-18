package de.alpharogroup.db.resource.bundles.daos;

import org.springframework.stereotype.Repository;

import de.alpharogroup.db.dao.jpa.JpaEntityManagerDao;
import de.alpharogroup.db.resource.bundles.entities.Languages;

@Repository("languagesDao")
public class LanguagesDao extends JpaEntityManagerDao<Languages, Integer>{

	private static final long serialVersionUID = 1L;

}
