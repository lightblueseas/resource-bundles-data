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

import de.alpharogroup.db.resource.bundles.entities.BundleApplications;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface BundleNamesRepository extends JpaRepository<BundleNames, UUID>
{

	@Transactional
	@Query("select bn from BundleNames bn where bn.baseName.name=:basename")
	List<BundleNames> findByBaseName(@Param("basename") String basename);

	@Transactional
	@Query("select bn from BundleNames bn " + "where bn.owner=:owner ")
	List<BundleNames> findByOwner(@Param("owner") final BundleApplications owner);

	@Transactional
	@Query("select bn from BundleNames bn " + "where bn.owner.name=:owner "
		+ "and bn.baseName.name=:basename")
	List<BundleNames> findByOwnerAndBaseName(@Param("owner") String owner,
		@Param("basename") String basename);

	@Transactional
	@Query("select bn from BundleNames bn " + "where bn.owner.name=:owner "
		+ "and bn.baseName.name=:basename " + "and bn.locale.locale=:locale ")
	BundleNames findDistinctByOwnerAndBaseNameAndLocale(@Param("owner") String owner,
		@Param("basename") String basename, final @Param("locale") String locale);
}
