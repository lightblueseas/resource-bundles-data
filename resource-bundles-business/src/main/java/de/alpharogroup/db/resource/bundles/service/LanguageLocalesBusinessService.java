/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
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
public class LanguageLocalesBusinessService extends
		AbstractBusinessService<LanguageLocales, Integer, LanguageLocalesDao> implements LanguageLocalesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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
		if (locale != null && !locale.isEmpty()) {
			query.setParameter("locale", locale);
		}
		final List<LanguageLocales> languageLocales = query.getResultList();
		return ListExtensions.getFirst(languageLocales);
	}

	/**
	 * {@inheritDoc}
	 */
	@Autowired
	public void setLanguageLocalesDao(final LanguageLocalesDao dao) {
		setDao(dao);
	}

}
