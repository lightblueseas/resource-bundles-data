package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
 * Entity class for saving in database bundle names with the current locale and what locale default for this bundle name.
 */
@Entity
@Table(name="bundlenames")
@Getter
@Setter
@NoArgsConstructor
public class BundleNames 
extends VersionableBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;	
	/** The bundle name. */
	@Column( name = "base_name", length = 1024  )
	private String baseName;
	/** The default locale of this bundle name. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "locale_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_BUNDLENAMES_LOCALE_ID"))	
	private LanguageLocales locale;
	
}