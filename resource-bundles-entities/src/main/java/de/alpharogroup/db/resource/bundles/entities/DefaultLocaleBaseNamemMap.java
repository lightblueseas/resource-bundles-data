package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.alpharogroup.db.entity.VersionableBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/** 
 * Entity class for saving in database the default {@link LanguageLocales} object for a {@link BundleNames} object.
 */
@Entity
@Table(name="default_locale_basename_map")
@Getter
@Setter
@NoArgsConstructor
public class DefaultLocaleBaseNamemMap 
extends VersionableBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;	
	/** The bundleName from this {@link BundleNames} object. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bundlename_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_DEFAULT_LOCALE_BASENAME_MAP_BUNDLENAME_ID"))	
	private BundleNames bundleName;
	/** The default locale of this bundle name. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "default_locale_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_DEFAULT_LOCALE_BASENAME_MAP_DEFAULT_LOCALE_ID"))
	private LanguageLocales defaultLocale;	
	
}