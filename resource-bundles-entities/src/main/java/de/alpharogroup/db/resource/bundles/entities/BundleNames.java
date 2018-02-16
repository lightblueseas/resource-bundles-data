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
package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import de.alpharogroup.db.entity.version.VersionableBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The entity class {@link BundleNames} holds the data from the {@link BaseNames} and the
 * {@link LanguageLocales}. If you see it from the properties file view this represents an
 * properties file. So you can have the default properties file that is the properties file without
 * the locale suffix and you have for instance a French properties file with the locale suffix _fr.
 * This would be two entries from this entity class one for the default and one for the French
 * locale.
 */
@Entity
@Table(name = "bundlenames")
@NamedQueries({
		@NamedQuery(name = BundleNames.NQ_FIND_BY_OWNER, query = "select bn from BundleNames bn where bn.owner=:owner") })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BundleNames extends VersionableBaseEntity<Integer> implements Cloneable
{

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

	/** The Constant for the named query for find BundleNames by the owner. */
	public static final String NQ_FIND_BY_OWNER = "BundleNames." + "findByOwner";

	/** The base name of this bundle. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "base_name_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_BUNDLENAMES_BASE_NAME_ID"))
	private BaseNames baseName;

	/** The locale of this bundle. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locale_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_BUNDLENAMES_LOCALE_ID"))
	private LanguageLocales locale;

	/** The optional filepath from this resource bunlde. */
	@Column(name = "filepath", length = 4096)
	private String filepath;

	/** The {@link BundleApplications} that owns this {@link BundleNames} object. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_BUNDLENAMES_OWNER_ID"))
	private BundleApplications owner;

}
