/**
 * The MIT License
 *
 * Copyright (C) 2007 - 2015 Asterios Raptis
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
package de.alpharogroup.db.resource.bundles.repositories;

import de.alpharogroup.db.resource.bundles.entities.Resourcebundles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResourcebundlesRepository extends JpaRepository<Resourcebundles, UUID>
{
	@Transactional
	@Query("select rb from Resourcebundles rb " + "where rb.bundleName.owner.name=:owner "
		+ "and rb.bundleName.baseName.name=:basename " + "and rb.bundleName.locale.locale=:locale")
	List<Resourcebundles> findByOwnerAndBaseNameAndLocale(@Param("owner") String owner,
		@Param("basename") String baseName, @Param("locale") String locale);

	@Transactional
	@Query("select rb from Resourcebundles rb " + "where rb.bundleName.owner.name=:owner "
		+ "and rb.bundleName.baseName.name=:basename " + "and rb.bundleName.locale.locale=:locale "
		+ "and rb.key.name=:pkey ")
	List<Resourcebundles> findByOwnerAndBaseNameAndLocaleAndKeyAndValue(
		@Param("owner") String owner, @Param("basename") String baseName,
		@Param("locale") String locale, @Param("pkey") String key);

	@Transactional
	@Query("select distinct rb from Resourcebundles rb " + "where rb.bundleName.owner.name=:owner "
		+ "and rb.bundleName.baseName.name=:basename " + "and rb.bundleName.locale.locale=:locale "
		+ "and rb.key.name=:pkey ")
	Resourcebundles findDistinctByOwnerAndBaseNameAndLocaleAndKeyAndValue(
		@Param("owner") String owner, @Param("basename") String baseName,
		@Param("locale") String locale, @Param("pkey") String key);
}
